package de.der_riddler.game.command;

import de.der_riddler.game.File;
import de.der_riddler.game.Game;
import de.der_riddler.game.InOut;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LsCommand extends Command {
    private final DateFormat dateFormat;
    private final Collection<File> files;
    private final Supplier<String> username;
    private final InOut inOut;

    public LsCommand(Game game) {
        super("ls", "ls", "list directory contents");
        this.dateFormat = new SimpleDateFormat("MMM dd HH:mm", Locale.ENGLISH);
        files = game.getFileMap().values();
        inOut = game.getInOut();
        username = game::getUsername;
    }

    private Function<File, String> makeFormatOutput(int length) {
        return file -> String.format(
                "-r--r--r-- 1 %s %s %" + length + "d %s %s",
                username.get(),
                username.get(),
                file.getBytes(),
                dateFormat.format(file.getCreatedAt()),
                file.getName()
        );
    }

    @Override
    public void execute(String[] args) {
        final Optional<Integer> maxBytes = files.stream().map(File::getBytes).max(Integer::compare);
        final int length = String.valueOf(maxBytes.orElse(0)).length();
        final String output = files
                .stream()
                .sorted(Comparator.comparing(File::getName))
                .map(makeFormatOutput(length))
                .collect(Collectors.joining("\n"));
        inOut.println(output);
    }
}
