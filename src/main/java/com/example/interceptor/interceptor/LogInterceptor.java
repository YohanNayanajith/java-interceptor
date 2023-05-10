package com.example.interceptor.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

@Component
public class LogInterceptor implements HandlerInterceptor {
    public static final String CORRELATION_ID_HEADER_KEY = "X-Correlation-Id";
    private Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String correlationId = StringUtils.isNotEmpty(httpServletRequest.getHeader(CORRELATION_ID_HEADER_KEY)) ?
                httpServletRequest.getHeader(CORRELATION_ID_HEADER_KEY) : UUID.randomUUID().toString();
        LOG.info("Correlation ID {}",correlationId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOG.info("Post Handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        if(ex != null){
            LOG.error("Exception throw "+ex.getMessage());
        }
    }
}
