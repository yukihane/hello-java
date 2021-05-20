package com.example.errorcontrollerthrowexceptionexample;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ErrorReportValve;
import org.apache.coyote.ActionCode;
import org.apache.tomcat.util.ExceptionUtils;

public class CustomErrorReportValve extends ErrorReportValve {

    // Create a simple logger
    Logger log = Logger.getLogger(CustomErrorReportValve.class.getName());

    @Override
    protected void report(final Request request, final Response response, final Throwable throwable) {
        // ErrorReportValve を参考に実装

        final int statusCode = response.getStatus();

        // Do nothing on a 1xx, 2xx and 3xx status
        // Do nothing if anything has been written already
        // Do nothing if the response hasn't been explicitly marked as in error
        //    and that error has not been reported.
        if (statusCode < 400 || response.getContentWritten() > 0 || !response.setErrorReported()) {
            return;
        }

        // If an error has occurred that prevents further I/O, don't waste time
        // producing an error report that will never be read
        final AtomicBoolean result = new AtomicBoolean(false);
        response.getCoyoteResponse().action(ActionCode.IS_IO_ALLOWED, result);
        if (!result.get()) {
            return;
        }

        try {
            try {
                response.setContentType("text/html");
                response.setCharacterEncoding("utf-8");
            } catch (final Throwable t) {
                ExceptionUtils.handleThrowable(t);
                if (container.getLogger().isDebugEnabled()) {
                    container.getLogger().debug("status.setContentType", t);
                }
            }
            final Writer writer = response.getReporter();
            if (writer != null) {
                // If writer is null, it's an indication that the response has
                // been hard committed already, which should never happen
                writer.write("<!doctype html><html lang=\"en\"><title>error</title><body>Error occured.</body></html>");
                response.finishResponse();
            }
        } catch (IOException | IllegalStateException e) {
            // Ignore
        }
    }
}
