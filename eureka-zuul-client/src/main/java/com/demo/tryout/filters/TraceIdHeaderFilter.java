package com.demo.tryout.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.demo.tryout.util.DemoTracer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class TraceIdHeaderFilter extends ZuulFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(TraceIdHeaderFilter.class);
	
    @Value("${app.trace.header.name:X-TRACE-ID}")
    private String TRACE_ID_HEADER;

    @Value("${app.trace.header.enabled:true}")
    private Boolean shouldFilter;

    @Autowired
    private DemoTracer tracer;

    @Override
    public boolean shouldFilter() {
        return shouldFilter;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        LOGGER.info("Zuul Trace ID {}", tracer.getTraceId());
        ctx.addZuulResponseHeader(TRACE_ID_HEADER, tracer.getTraceId());
        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 999;
    }

}
