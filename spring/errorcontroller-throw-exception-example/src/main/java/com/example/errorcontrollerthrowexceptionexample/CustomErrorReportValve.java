package com.example.errorcontrollerthrowexceptionexample;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ErrorReportValve;

public class CustomErrorReportValve extends ErrorReportValve {

    // Create a simple logger
    Logger log = Logger.getLogger(CustomErrorReportValve.class.getName());

    @Override
    protected void report(final Request request, final Response response, final Throwable t) {
        try {
            // Write a more friendly, less technical message to the user
            final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            out.write("<html><head><title>Oops</title><body>");
            out.write("<h1>Oops</h1>");
            out.write("<p>Well, that didn't go as we had expected.</p>");
            out.write("<p>Don't worry though, we're working on it.</p>");
            out.write("</body></html>");
            out.close();

            // Log the error with your favorite logging framework...
            log.severe("Uncaught throwable was thrown: " + t.getMessage());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
