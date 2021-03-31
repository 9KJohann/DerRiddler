package de.der_riddler.game;

import de.der_riddler.game.command.Command;
import de.der_riddler.game.riddle.*;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import static de.der_riddler.game.InOut.*;

public class DerRiddler implements EscapeRoom {
    public static final String RIDDLER = "RiDD13r";

    @Override
    public List<Riddle> makeRiddles(Game game) {
        return List.of(
                new Riddle1(game),
                new Riddle2(game),
                new Riddle3(game),
                new Riddle4(game),
                new Riddle5(game)
        );
    }

    @Override
    public List<File> makeFiles(Game game) {
        final InOut inOut = game.getInOut();
        return List.of(
                new File("AsciiTable.class", Util.getRandomBytes(210), game.getDate(), args -> {
                    if (args.length == 2) {
                        if (args[0].equals("char")) {
                            inOut.println(String.valueOf((char) Byte.valueOf(args[1]).byteValue()));
                            return;
                        }
                        if (args[0].equals("dec")) {
                            inOut.println(String.valueOf((byte) args[1].charAt(0)));
                            return;
                        }
                    }
                    inOut.printError("Usage: java AsciiTable char <0-255>\treturns char for ascii code");
                    inOut.printError("Usage: java AsciiTable dec <char>\treturns ascii code for char");
                })
        );
    }

    @Override
    public List<Command> makeCommands(Game game) {
        final InOut inOut = game.getInOut();
        return List.of(
                new Command("zoom", "zoom", "start zoom with root access to win the game") {
                    @Override
                    public void execute(String[] args) {
                        if (game.isRoot()) {
                            game.onGameWon();
                            return;
                        }
                        inOut.printError("Permission denied");
                    }
                },
                new Command("su", "su", "become superuser") {
                    private final Iterator<String> failMessages = List.of(
                            "ja ne, so einfach ist das passwort nicht",
                            "tik tak, die zeit läuft, aber lass dich nicht hetzen",
                            "denk mal scharf nach, das passwort ist doch so offensichtlich"
                            ).iterator();
                    @Override
                    public void execute(String[] args) {
                        inOut.print("Password: ");
                        final var password = inOut.nextLine();
                        if (Util.getMD5Hash(password).equals("bd03a7dafd87ced350a7a6cafd6068d1")) {
                            game.setRoot(true);
                            return;
                        }
                        inOut.printError("su: Authentication failure");
                        if (failMessages.hasNext()) game.printChatMessage(RIDDLER, failMessages.next(), ANSI_PURPLE);
                        else game.onGameLost();
                    }
                }
        );
    }

    @Override
    public long getTimeOffset() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() - System.currentTimeMillis();
    }

    @Override
    public long getTimeLimit() {
        return 15 * 1 * 1000;
    }

    @Override
    public Supplier<String>[] makeIntro(Game game) {
        final String username = game.getUsername();
        final String hostname = game.getHostname();
        // Da die Nachrichten verzögert geprinted werden, ist es notwendig die Nachricht in einen Supplier zu wrappen
        // damit der Zeitstempel von den Chat Messages richtig ist
        Supplier<String>[] suppliers = new Supplier[]{
                () -> ANSI_BOLD + ANSI_CYAN + "*BEEP* *BEEP* *BEEP*\n" + ANSI_RESET,
                () -> ANSI_CYAN + "Hach. Schonwieder der Wecker.",
                () -> "Es beginnt ein neuer Tag im Corona-Studentenleben.",
                () -> "Wir sind Informatik-Student an der Hochschule Coburg,",
                () -> "und Präsenzunterricht ist momentan leider nicht möglich...",
                () -> "Die Kaffeemaschine ist das erste Gerät was Frühs angeschmissen wird",
                () -> "und der Schreibtischstuhl weiß schon was ihn wieder erwartet:",
                () -> "viele Stunden und lange Nächte.",
                () -> "Nach dem ersten Kaffee kommt man zum Glück doch langsam ins Rollen und",
                () -> "man ist mental bereit für seine erste Vorlesung heute Morgen: Programmieren." + ANSI_RESET,
                () -> "",
                () -> Game.makePrompt(username, hostname) + "zoom",
                () -> "Permission denied",
                () -> "",
                () -> ANSI_CYAN + "Doch was ist das? Zoom lässt sich nicht öffnen!" + ANSI_RESET,
                () -> "",
                () -> Game.makePrompt(username, hostname) + "su",
                () -> "Password: ****",
                () -> "su: Authentication failure",
                () -> Game.makePrompt(username, hostname) + "su",
                () -> "Password: *********",
                () -> "su: Authentication failure",
                () -> "",
                () -> ANSI_CYAN + "Unser Root Passwort funktioniert auch nicht mehr!",
                () -> "Da tritt eine neue Person unserem IRC Chat bei" + ANSI_RESET,
                () -> "",
                () -> String.format("[%tR] %s has joined", game.getDate(), RIDDLER),
                () -> ANSI_PURPLE + Game.makeChatMessage(game.getDate(), RIDDLER, "hallo " + username),
                () -> ANSI_YELLOW + Game.makeChatMessage(game.getDate(), username, "wer bist du?"),
                () -> ANSI_PURPLE + Game.makeChatMessage(game.getDate(), RIDDLER, "mein name ist riddler"),
                () -> ANSI_PURPLE + Game.makeChatMessage(game.getDate(), RIDDLER, "sorry, aber dein PC steht jetzt unter meiner kontrolle"),
                () -> ANSI_YELLOW + Game.makeChatMessage(game.getDate(), username, "was soll der mist? verschwinde aus meinem system!"),
                () -> ANSI_YELLOW + Game.makeChatMessage(game.getDate(), username, "in 15 minuten beginnt meine vorlesung beim pfeiffer"),
                () -> ANSI_PURPLE + Game.makeChatMessage(game.getDate(), RIDDLER, "so so, vorlesung beim pfeiffer also"),
                () -> ANSI_PURPLE + Game.makeChatMessage(game.getDate(), RIDDLER, "ein dufter kerl, hatte ihn zu meiner zeit auch"),
                () -> ANSI_PURPLE + Game.makeChatMessage(game.getDate(), RIDDLER, "nun gut, zeig mal was du bis jetzt gelernt hast"),
                () -> ANSI_YELLOW + Game.makeChatMessage(game.getDate(), username, "wie meinst du das?"),
                () -> ANSI_PURPLE + Game.makeChatMessage(game.getDate(), RIDDLER, "ich hinterleg dir paar java datein in deinem verzeichnis"),
                () -> ANSI_PURPLE + Game.makeChatMessage(game.getDate(), RIDDLER, "jedes rätsel ist ein teil des passworts"),
                () -> ANSI_YELLOW + Game.makeChatMessage(game.getDate(), username, "du gehst mir auf die nerven, dir werde ich es zeigen!"),
                () -> ANSI_RESET,
        };
        return suppliers;
    }

    @Override
    public void onGameWon(Game game) {
        game.printChatMessage(RIDDLER, "Respekt! Du hast rechtzeitig die Kontrolle zurück gewonnen. Viel Spaß bei der Vorlesung :3", ANSI_PURPLE);
    }

    @Override
    public void onGameLost(Game game) {
        game.printChatMessage(RIDDLER, "XD -> Das muss besser werden", ANSI_PURPLE);
        if (game.getTimeLeft() < 0) game.printChatMessage(RIDDLER, "Die Vorlesung muss wohl ohne dich stattfinden, kein großer Verlust!", ANSI_PURPLE);
        game.printChatMessage(RIDDLER, "Schau dir am besten nochmal die Unterlagen aus dem ersten Semester an", ANSI_PURPLE);
    }
}
