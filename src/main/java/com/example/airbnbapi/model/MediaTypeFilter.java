package com.example.airbnbapi.model;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONObject;

import org.springframework.stereotype.Component;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class MediaTypeFilter implements Filter {

    private static final Pattern MEDIA_TYPE_REGEX = Pattern.compile("^/api/v1/media/([^/]+)");

    private static final List<String> SUBMISSION_METHODS = Arrays.asList("POST", "PUT");

    private static final String TYPE = "type";



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Default impl
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // Add request wrapper so the body can be read multiple times
        MediaTypeRequestWrapper multiReadRequest = new MediaTypeRequestWrapper((HttpServletRequest) request);

        chain.doFilter(multiReadRequest, response);
    }

    @Override
    public void destroy() {
        // Default impl
    }

    private class MediaTypeRequestWrapper extends HttpServletRequestWrapper {

        private String body;

        private MediaTypeRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);

            // If submission request
            if (SUBMISSION_METHODS.contains(request.getMethod())) {

                // Read request body to json object
                JSONObject j = new JSONObject(IOUtils.toString(request.getReader()));

                // For requests of a given format, append an accounts resource package to the body
                Matcher matcher = MEDIA_TYPE_REGEX.matcher(request.getRequestURI());
                if (matcher.find()) {
                    j.put(TYPE, "." + WordUtils.capitalizeFully(matcher.group(1)));
                }

                // Cache the body for later reading
                body = j.toString();
            }
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {

            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());

            return new ServletInputStream() {

                @Override
                public boolean isFinished() {
                    return byteArrayInputStream.available() == 0;
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setReadListener(ReadListener readListener) {
                    throw new UnsupportedOperationException();
                }

                public int read() throws IOException {
                    return byteArrayInputStream.read();
                }
            };
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(this.getInputStream()));
        }
    }
}
