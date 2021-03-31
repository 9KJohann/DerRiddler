package de.der_riddler.game.command;

import de.der_riddler.game.Executable;
import de.der_riddler.game.Named;

public abstract class Command implements Executable, Named {
    private final String name;
    private final String usage;
    private final String description;

    public Command(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }
}
