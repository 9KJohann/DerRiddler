package de.der_riddler.game.riddle;

import de.der_riddler.game.Game;
import de.der_riddler.game.InOut;

import java.util.Iterator;
import java.util.List;

import static de.der_riddler.game.DerRiddler.RIDDLER;
import static de.der_riddler.game.InOut.ANSI_PURPLE;

public class Riddle3 extends Riddle {
    private final InOut inOut;
    private final Game game;
    private Iterator<String> failMessages;

    public Riddle3(Game game) {
        super(
                "Riddle3",
                "public class Riddle3 {\n" +
                        "\tpublic static void main(String[] args) {\n" +
                        "\t\tint a[][] = new int [11][11];\n" +
                        "\t\tfor (int i = 0; i<=10; i++) {\n" +
                        "\t\t\tfor (int j = 0; j<=10; j++) {\n" +
                        "\t\t\t\ta[i][j] = i*j;\n" +
                        "\t\t\t}\n" +
                        "\t\t}\t\n" +
                        "\t\tint result = a[5][6] + a[9][8] -1;\n" +
                        "\t\tSystem.out.println(\n" +
                        "\t\t\t(args.length > 0 && args[0].equals(Integer.valueOf(result).toString())) \n" +
                        "\t\t\t\t? \"Richtig\" \n" +
                        "\t\t\t\t: \"Falsch\"\n" +
                        "\t\t);\n" +
                        "\t}\n" +
                        "\n" +
                        "}"
        );
        this.inOut = game.getInOut();
        this.game = game;
        failMessages = List.of(
                "Verdammt Junge/Mädel (Genderwahnsinn), was hast du eigentlich gelernt? Ich dachte du studierst Informatik? Glück für dich, dass ich heute einen guten Tag habe..2 Versuche gewähre ich dir noch!",
                "l2p noob!"
        ).iterator();
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            inOut.println("Falsch");
            return;
        }
        int a[][] = new int[11][11];
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                a[i][j] = i * j;
            }
        }
        int result = a[5][6] + a[9][8] - 1;
        if (args[0].equals(Integer.valueOf(result).toString())) {
            inOut.println("Richtig");
            return;
        }
        inOut.println("Falsch");
        if (failMessages.hasNext()) game.printChatMessage(RIDDLER, failMessages.next(), ANSI_PURPLE);
        else game.onGameLost();
    }
}
