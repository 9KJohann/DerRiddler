package de.der_riddler.game;

import de.der_riddler.game.command.*;
import de.der_riddler.game.riddle.Riddle;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static de.der_riddler.game.InOut.*;

public class Game implements Runnable {
    private final Map<String, Command> commandMap = new HashMap<>();
    private final Map<String, File> fileMap = new HashMap<>();
    private final Map<String, Riddle> riddleMap = new HashMap<>();
    private final Supplier<String>[] intro;
    private final InOut inOut;
    private final String username;
    private final String hostname;
    private final long timeOffset;
    private final long timeUntilLoose;
    private final EscapeRoom escapeRoom;
    private final Timer timer;
    private boolean root = false;
    private boolean running = false;
    private boolean helpRequired = true;


    public Game(InOut inOut, String username, String hostname, EscapeRoom escapeRoom) {
        this.inOut = inOut;
        this.username = username;
        this.hostname = hostname;
        this.escapeRoom = escapeRoom;
        timeOffset = escapeRoom.getTimeOffset();
        intro = escapeRoom.makeIntro(this);
        Util.putAll(commandMap, makeDefaultCommands());
        Util.putAll(commandMap, escapeRoom.makeCommands(this));
        Util.putAll(fileMap, escapeRoom.makeFiles(this));
        Util.putAll(riddleMap, escapeRoom.makeRiddles(this));
        Util.putAll(fileMap, riddleMap.values().stream().map(this::mapRiddleToJavaFile).collect(Collectors.toList()));
        timer = new Timer(true);
        timer.schedule(Util.makeTimerTask(this::onTimeout), escapeRoom.getTimeLimit());
        timeUntilLoose = System.currentTimeMillis() + escapeRoom.getTimeLimit();
    }

    public static void main(String[] args) {
        final Game game = new Game(
                new InOut(),
                Util.getUsername(),
                Util.getHostname(),
                new DerRiddler()
        );
        game.run();
    }

    public static String makePrompt(String username, String hostname) {
        return String.format("%s@%s:~$ ", username, hostname);
    }

    public static String makeChatMessage(Date date, String username, String message) {
        return String.format("[%tR] <@%s> %s", date, username, message);
    }

    private void onTimeout() {
        inOut.println("");
        escapeRoom.onGameLost(this);
        running = false;
    }

    private File mapRiddleToJavaFile(Riddle riddle) {
        return new File(riddle.getName() + ".java", riddle.getSourceCode(), getDate(), null);
    }

    private List<Command> makeDefaultCommands() {
        return List.of(
                new HelpCommand(this),
                new LsCommand(this),
                new CatCommand(this),
                new ExitCommand(this),
                new JavaCommand(this),
                new JavaCCommand(this)
        );
    }

    public String getUsername() {
        return username;
    }

    public String getHostname() {
        return hostname;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    private void playIntro() {
        try {
            for (Supplier<String> supplier : intro) {
                String line = supplier.get();
                inOut.println(line);
                Thread.sleep(line.length() * 1);
            }
        } catch (InterruptedException ex) {

        }
    }

    @Override
    public void run() {
        running = true;
        playIntro();
        while (running) {
            printPrompt();
            final String input = inOut.nextLine();
            if (input == null || input.isBlank()) continue;
            final String[] args = input.split(" ");
            final Command command = commandMap.get(args[0]);
            if (command == null) {
                inOut.println(String.format("%s: command not found", args[0]));
                if (helpRequired) {
                    helpRequired = false;
                    inOut.println(ANSI_BLUE + "Tippe help ein und bestätige mit Enter um eine Übersicht aller Befehle zu erhalten" + ANSI_RESET);
                }
                continue;
            }
            command.execute(Arrays.copyOfRange(args, 1, args.length));
        }
    }

    public void onGameLost() {
        escapeRoom.onGameLost(this);
        running = false;
    }

    public void onGameWon() {
        escapeRoom.onGameWon(this);
        running = false;
    }

    public void printPrompt() {
        inOut.print(makePrompt(root ? "root" : username, hostname));
    }

    public void printChatMessage(String username, String message, String ansiColor) {
        inOut.println(ansiColor + makeChatMessage(getDate(), username, message) + ANSI_RESET);
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public Map<String, File> getFileMap() {
        return fileMap;
    }

    public InOut getInOut() {
        return inOut;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Date getDate() {
        return new Date(System.currentTimeMillis() + timeOffset);
    }

    public long getTimeLeft() {
        return timeUntilLoose - System.currentTimeMillis();
    }

    public Map<String, Riddle> getRiddleMap() {
        return riddleMap;
    }
}
