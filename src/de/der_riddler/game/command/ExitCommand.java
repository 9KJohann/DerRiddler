package de.der_riddler.game.command;

import de.der_riddler.game.Game;

public class ExitCommand extends Command {
    private final Game game;

    public ExitCommand(Game game) {
        super("exit", "exit", "leave the game");
        this.game = game;
    }

    @Override
    public void execute(String[] args) {
        game.setRunning(false);
    }
}
