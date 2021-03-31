package de.der_riddler.game;

import java.util.Date;

public class File implements Named {
    final protected Executable executable;
    final private String content;
    final private String name;
    final private Date createdAt;

    public File(String name, String content, Date createdAt, Executable executable) {
        this.name = name;
        this.content = content;
        this.createdAt = createdAt;
        this.executable = executable;
    }

    public int getBytes() {
        return content.length();
    }

    public String getContent() {
        return content;
    }

    @Override
    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Executable getExecutable() {
        return executable;
    }
}
