#!/usr/bin/env kotlin

fun run() {
    ProcessBuilder("java", "-jar", "build/libs/tic-tac-toe.jar").start();
}

fun compile() {
    ProcessBuilder(if(System.getProperty("os.name").lowercase() in "windows") "gradlew.bat" else  "./gradlew", "build").start().waitFor();
}

if (args.isEmpty() || args[0] == "target" || args[0] == "fast") {
    compile();
}
if (args[0] == "test" || args[0] == "fast") {
    run();
    if (args[0] == "test" || args[0] == "fast") {
        run();
    }
}