package com.demo.tryout.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import brave.Span;
import brave.Tracer;

@Component
public class DemoTracer {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoTracer.class);
	
	@Autowired
	Tracer tracer;
	
	public String getTraceId() {
		Span span = tracer.currentSpan();
		if (span == null) {
			span = tracer.newTrace().start();
		}
		LOGGER.info("Trace ID {}", span.context().traceId());
		LOGGER.info("Span ID {}", span.context().spanId());
		return span.context().traceIdString();
	}
}
