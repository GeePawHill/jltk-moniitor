package org.geepawhill.jltk;


import org.geepawhill.jltk.flow.*;

public class Main {
    public static void main(String... args) {
        MonitorOptions options = new MonitorOptions(args);
        if (options.isPostCommit) {
            new Recorder().logPostCommit();
        }
    }
}