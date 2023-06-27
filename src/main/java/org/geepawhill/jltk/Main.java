package org.geepawhill.jltk;


public class Main {
    public static void main(String... args) {
        MonitorOptions options = new MonitorOptions(args);
        if (options.isPostCommit) {
            System.out.println("jltk-monitor: Post-commit.");
        }
    }
}