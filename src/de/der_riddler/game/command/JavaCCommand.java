package de.der_riddler.game.command;

import de.der_riddler.game.File;
import de.der_riddler.game.Game;
import de.der_riddler.game.InOut;
import de.der_riddler.game.riddle.Riddle;

import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaCCommand extends Command {
    private final String regex;
    private final Pattern pattern;
    private final InOut inOut;
    private final Map<String, File> fileMap;
    private final Map<String, Riddle> riddleMap;
    private final Supplier<Date> dateSupplier;

    public JavaCCommand(Game game) {
        super("javac", "javac <source file>", "compiles a java file");
        this.inOut = game.getInOut();
        this.fileMap = game.getFileMap();
        this.riddleMap = game.getRiddleMap();
        this.dateSupplier = game::getDate;
        regex = "(.*)(\\.java)";
        pattern = Pattern.compile(regex, Pattern.MULTILINE);
    }

    private Riddle getRiddle(String name) {
        final Matcher matcher = pattern.matcher(name);
        if (!matcher.find()) return null;
        return riddleMap.get(matcher.group(1));
    }

    private File compileRiddle(Riddle riddle) {
        final StringBuilder builder = new StringBuilder();
        for (char c : riddle.getSourceCode().toCharArray())
            builder.append(Integer.toHexString(c));
        return new File(riddle.getName() + ".class", builder.toString(), dateSupplier.get(), riddle);
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            inOut.printError("Usage: " + getUsage());
            return;
        }
        final Riddle riddle = getRiddle(args[0]);
        if (riddle == null) {
            inOut.printError(String.format("error: Class names, '%s', are only accepted if annotation processing is explicitly requested\n1 error", args[0]));
            return;
        }
        final File file = compileRiddle(riddle);
        fileMap.put(file.getName(), file);
    }
}
