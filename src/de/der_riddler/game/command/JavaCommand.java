package de.der_riddler.game.command;

import de.der_riddler.game.File;
import de.der_riddler.game.Game;
import de.der_riddler.game.InOut;

import java.util.Arrays;
import java.util.Map;

public class JavaCommand extends Command {
    private final InOut inOut;
    private final Map<String, File> fileMap;

    public JavaCommand(Game game) {
        super("java", "java <mainclass> [args...]", "executes a java file");
        this.inOut = game.getInOut();
        this.fileMap = game.getFileMap();
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            inOut.printError("Usage: " + getUsage());
            return;
        }
        final String className = args[0];
        final File file = fileMap.get(className + ".class");
        if (file == null || file.getExecutable() == null) {
            inOut.printError(String.format(
                    "Error: Could not find or load main class %s\nCaused by: java.lang.ClassNotFoundException: %s",
                    className, className
            ));
            return;
        }
        file.getExecutable().execute(Arrays.copyOfRange(args, 1, args.length));
    }
}
