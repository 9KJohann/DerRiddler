package de.der_riddler.game.command;

import de.der_riddler.game.Game;
import de.der_riddler.game.InOut;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HelpCommand extends Command {
    private final InOut inOut;
    private final Collection<Command> commands;

    public HelpCommand(Game game) {
        super("help", "help", "display information about builtin commands");
        inOut = game.getInOut();
        commands = game.getCommandMap().values();
    }

    private Function<Command, String> makeFormatOutput(int length) {
        return command -> String.format("%-" + length + "s\t%s", command.getUsage(), command.getDescription());
    }

    @Override
    public void execute(String[] args) {
        final int length = commands
                .stream()
                .map(c -> c.getUsage().length())
                .max(Integer::compare)
                .orElse(10);
        final String output = commands
                .stream()
                .sorted(Comparator.comparing(Command::getUsage))
                .map(makeFormatOutput(length))
                .collect(Collectors.joining("\n"));
        inOut.println(output);
    }
}
