package com.ptumulty.ceramic_api.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class JavaUtils
{
    /**
     * Java doesn't have an officially supported way of determining if the debugger is present.
     * Got this hack from this stack overflow article.
     * <p>
     * <a href="https://stackoverflow.com/questions/5393403/can-a-java-application-detect-that-a-debugger-is-attached">StackOverflow</a>
     *
     * @return true if debugger is present
     */
    public static boolean isDebuggerPresent()
    {
        // Get a hold of the Java Runtime Environment (JRE) management interface
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();

        // Get the command line arguments that we were originally passed in
        List<String> args = runtime.getInputArguments();

        // Check if the Java Debug Wire Protocol (JDWP) agent is used.
        // One of the items might contain something like "-agentlib:jdwp=transport=dt_socket,address=9009,server=y,suspend=n"
        // We're looking for the string "jdwp".
        return args.toString().contains("jdwp");
    }
}
