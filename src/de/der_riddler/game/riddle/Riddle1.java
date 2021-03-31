package de.der_riddler.game.riddle;

import de.der_riddler.game.DerRiddler;
import de.der_riddler.game.Game;
import de.der_riddler.game.InOut;

import java.util.Iterator;
import java.util.List;

import static de.der_riddler.game.DerRiddler.RIDDLER;
import static de.der_riddler.game.InOut.ANSI_PURPLE;

public class Riddle1 extends Riddle {
    private final InOut inOut;
    private final Game game;
    private Iterator<String> failMessages;

    public Riddle1(Game game) {
        super(
                "Riddle1",
                "public class Riddle1 {\n" +
                        "\tpublic static void main(String[] args){\n" +
                        "\t\tSystem.out.println(\n" +
                        "\t\t\t(args.length > 0 && args[0].equals(Integer.valueOf(a(8)).toString())) \n" +
                        "\t\t\t\t? \"Richtig\" \n" +
                        "\t\t\t\t: \"Falsch\"\n" +
                        "\t\t);\n" +
                        "\t}\n" +
                        "\tpublic static int a(int b) {\n" +
                        "\t\tif (b % 40 == 0) return b;\n" +
                        "\t\treturn a(b + b % 13);\n" +
                        "\t}\n" +
                        "}"
        );
        this.inOut = game.getInOut();
        this.game = game;
        failMessages = List.of(
                "Fail, hab ich erwähnt das du noch 3 Versuche hast? >:)",
                "noob, da waren es nur noch 2!",
                "streng dich doch mal an"
        ).iterator();
    }

    private int a(int b) {
        if (b % 40 == 0) return b;
        return a(b + b % 13);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            inOut.println("Falsch");
            return;
        }
        if (args[0].equals(Integer.valueOf(a(8)).toString())) {
            inOut.println("Richtig");
            return;
        }
        inOut.println("Falsch");
        if (failMessages.hasNext()) game.printChatMessage(RIDDLER, failMessages.next(), ANSI_PURPLE);
        else game.onGameLost();
    }
}
