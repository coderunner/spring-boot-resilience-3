
package com.inf5190.resilience;

import java.io.IOException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class HttpFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(HttpFilter.class);

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;

        String correlationId = httpRequest.getHeader(CorrelationId.CORRELATION_ID);
        if (correlationId == null || correlationId.length() == 0) {
            correlationId = UUID.randomUUID().toString();
        }

        httpRequest.setAttribute(CorrelationId.CORRELATION_ID, correlationId);
        MDC.put(CorrelationId.CORRELATION_ID, correlationId);

        try {
            logger.info("Request {} for URL {}", httpRequest.getMethod(),
                    httpRequest.getRequestURI());
            chain.doFilter(request, response);
        } finally {
            MDC.remove(CorrelationId.CORRELATION_ID);
        }
    }
}
