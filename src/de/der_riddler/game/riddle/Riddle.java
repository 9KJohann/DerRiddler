package de.der_riddler.game.riddle;

import de.der_riddler.game.Executable;
import de.der_riddler.game.Named;

public abstract class Riddle implements Executable, Named {
    private final String name;
    private final String sourceCode;

    public Riddle(String name, String sourceCode) {
        this.name = name;
        this.sourceCode = sourceCode;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getSourceCode() {
        return sourceCode;
    }
}
