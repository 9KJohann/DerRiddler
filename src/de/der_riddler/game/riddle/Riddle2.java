package de.der_riddler.game.riddle;

import de.der_riddler.game.Game;
import de.der_riddler.game.InOut;

import java.util.Iterator;
import java.util.List;

import static de.der_riddler.game.DerRiddler.RIDDLER;
import static de.der_riddler.game.InOut.ANSI_PURPLE;

public class Riddle2 extends Riddle {
    public final InOut inOut;
    private final Game game;
    private Iterator<String> failMessages;

    public Riddle2(Game game) {
        super(
                "Riddle2",
                "public class Riddle2 {\n" +
                        "\tpublic static void main(String[] args) {\n" +
                        "\t\tint result = 0;\n" +
                        "\t\tint[] arr2 = new int[8];\n" +
                        "\t\tfor (int i = 0; i < 6; i++) {\n" +
                        "\t\t\tarr2[i] = i + 3;\n" +
                        "\t\t\tfor (int a : arr2) {\n" +
                        "\t\t\t\tresult += a;\n" +
                        "\t\t\t}\n" +
                        "\t\t}\n" +
                        "\t\tSystem.out.println(\n" +
                        "\t\t\t(args.length > 0 && args[0].equals(Integer.valueOf(result + arr2[1]).toString())) \n" +
                        "\t\t\t\t? \"Richtig\" \n" +
                        "\t\t\t\t: \"Falsch\"\n" +
                        "\t\t);\n" +
                        "\t}\n" +
                        "}"
        );
        this.inOut = game.getInOut();
        this.game = game;
        failMessages = List.of(
                "Hahaha, wieso besuchst du überhaupt noch die Vorlesung, wenn du nicht mal die einfachsten Grundlagen beherrscht?",
                "Jetzt verstehe ich langsam, warum dir deine Informatik-Vorlesung so wichtig ist. Nun gut, ich will dir noch eine Chance geben"
        ).iterator();
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            inOut.println("Falsch");
            return;
        }
        int result = 0;
        int[] arr2 = new int[8];
        for (int i = 0; i < 6; i++) {
            arr2[i] = i + 3;
            for (int a : arr2) {
                result += a;
            }
        }
        if (args[0].equals(Integer.valueOf(result + arr2[1]).toString())) {
            inOut.println("Richtig");
            return;
        }
        inOut.println("Falsch");
        if (failMessages.hasNext()) game.printChatMessage(RIDDLER, failMessages.next(), ANSI_PURPLE);
        else game.onGameLost();
    }
}
