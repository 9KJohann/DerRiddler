package de.der_riddler.game.riddle;

import de.der_riddler.game.Game;
import de.der_riddler.game.InOut;

import java.util.Iterator;
import java.util.List;

import static de.der_riddler.game.DerRiddler.RIDDLER;
import static de.der_riddler.game.InOut.ANSI_PURPLE;

public class Riddle5 extends Riddle {
    private final InOut inOut;
    private final Game game;
    private Iterator<String> failMessages;

    public Riddle5(Game game) {
        super(
            "Riddle5",
            "public class Riddle5 {\n" +
                    "\tpublic static void main(String[] args) {\n" +
                    "\t\tString a = \"KGyPFwb\";\n" +
                    "\t\tint b = 0;\n" +
                    "\t\tfor(int i = 0; i < a.length(); i++)\n" +
                    "\t\t\tb += (a.charAt(i) % 2 ) << (a.length() - i - 1) % 8;\n" +
                    "\t\tSystem.out.println((args.length > 0 && args[0].charAt(0) == (char)b) ? \"Richtig\" : \"Falsch\");\n" +
                    "\t}\n" +
                    "}"
        );
        this.inOut = game.getInOut();
        this.game = game;
        failMessages = List.of(
                "die ascii tabelle hast du schon gesehen, oder?",
                "Was für eine Enttäuschung",
                "come on, last try"
        ).iterator();
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            inOut.println("Falsch");
            return;
        }
        String a = "KGyPFwb";
        int b = 0;
        for(int i = 0; i < a.length(); i++)
            b += (a.charAt(i) % 2 ) << (a.length() - i - 1) % 8;
        if (args[0].charAt(0) == (char)b) {
            inOut.println("Richtig");
            return;
        }
        inOut.println("Falsch");
        if (failMessages.hasNext()) game.printChatMessage(RIDDLER, failMessages.next(), ANSI_PURPLE);
        else game.onGameLost();
    }
}
