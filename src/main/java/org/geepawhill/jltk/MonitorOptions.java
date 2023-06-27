package org.geepawhill.jltk;

import org.apache.commons.cli.*;

public class MonitorOptions {

    boolean isPostCommit = false;

    public MonitorOptions(String... args) {
        var options = new Options()
                .addOption("p", "post-commit", false, "Add a commit to the log.");
        DefaultParser parser = new DefaultParser();
        try {
            var cmdLine = parser.parse(options, args);
            if (cmdLine.hasOption("p")) {
                isPostCommit = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            new HelpFormatter().printHelp("jltk-monitor args...", options);
        }
    }
}
