package de.der_riddler.game.riddle;

import de.der_riddler.game.Game;
import de.der_riddler.game.InOut;

import java.util.Iterator;
import java.util.List;

import static de.der_riddler.game.DerRiddler.RIDDLER;
import static de.der_riddler.game.InOut.ANSI_PURPLE;

public class Riddle4 extends Riddle {
    private final InOut inOut;
    private final Game game;
    private Iterator<String> failMessages;

    public Riddle4(Game game) {
        super(
                "Riddle4",
                "public class Riddle4 {\n" +
                        "\tpublic static void main(String[] args) {\n" +
                        "\t\tString s = \"P-r-o-g-r-a-m-i-e-r-e-n\";\n" +
                        "\t\tString [] letters = s.split(\"-\", -1);\n" +
                        "\t\tSystem.out.println((args.length > 0 && args[0].equals(letters[7])) ? \"Richtig\" : \"Falsch\");\n" +
                        "\t}\n" +
                        "}"
        );
        this.inOut = game.getInOut();
        this.game = game;
        failMessages = List.of(
               "also bitte, das ist doch easy, für dieses rätsel hast du nur noch einen versuch"
        ).iterator();
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            inOut.println("Falsch");
            return;
        }
        String s = "P-r-o-g-r-a-m-i-e-r-e-n";
        String[] letters = s.split("-", -1);
        if (args[0].equals(letters[7])) {
            inOut.println("Richtig");
            return;
        }
        inOut.println("Falsch");
        if (failMessages.hasNext()) game.printChatMessage(RIDDLER, failMessages.next(), ANSI_PURPLE);
        else game.onGameLost();
    }
}
