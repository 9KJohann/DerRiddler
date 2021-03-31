package de.der_riddler.game;

import de.der_riddler.game.command.Command;
import de.der_riddler.game.riddle.Riddle;

import java.util.List;
import java.util.function.Supplier;

public interface EscapeRoom {
    List<Riddle> makeRiddles(Game game);

    List<File> makeFiles(Game game);

    List<Command> makeCommands(Game game);

    long getTimeOffset();

    long getTimeLimit();

    Supplier<String>[] makeIntro(Game game);

    void onGameLost(Game game);

    void onGameWon(Game game);
}
