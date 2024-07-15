package cj.cloudwave.member.service;

import datadog.trace.api.CorrelationIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Service {
    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    public void processRequest() {
        // There must be spans started and active before this block.
        try {
            MDC.put("dd.trace_id", CorrelationIdentifier.getTraceId());
            MDC.put("dd.span_id", CorrelationIdentifier.getSpanId());

            // Log something
            logger.info("Processing request");

        } finally {
            MDC.remove("dd.trace_id");
            MDC.remove("dd.span_id");
        }
    }
}
