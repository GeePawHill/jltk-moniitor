package org.geepawhill.jltk;


import org.apache.commons.cli.*;

public class Main {
    public static void main(String... args) {
        var options = new Options()
                .addOption("c", "commit", false, "Add a commit to the log.");
        DefaultParser parser = new DefaultParser();
        try {
            var cmdLine = parser.parse(options, args);
            if (cmdLine.hasOption('c')) {
                System.out.println("jltk-monitor --commit");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            new HelpFormatter().printHelp("jltk-monitor args...", options);
        }
    }
}