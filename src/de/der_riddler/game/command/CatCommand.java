package de.der_riddler.game.command;

import de.der_riddler.game.File;
import de.der_riddler.game.Game;
import de.der_riddler.game.InOut;

import java.util.Map;

public class CatCommand extends Command {
    private final InOut inOut;
    private final Map<String, File> fileMap;

    public CatCommand(Game game) {
        super("cat", "cat <file>", "prints file content");
        this.inOut = game.getInOut();
        this.fileMap = game.getFileMap();
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            inOut.printError("Usage: " + getUsage());
            return;
        }
        final String name = args[0];
        final File file = fileMap.get(name);
        if (file == null) {
            inOut.printError(String.format("cat: %s: No such file", name));
            return;
        }
        inOut.println(file.getContent());
    }
}
