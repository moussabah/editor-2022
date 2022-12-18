package fr.istic.aco.editor.invoker;

import fr.istic.aco.editor.command.Command;

import java.io.InputStream;

public interface Invoker {
    public String getText();
    public void addCommand(String key, Command value);
    public void executeCommand();
    public Integer getBeginIndex();
    public Integer getEndIndex();

    void setReadStream(InputStream inputStream);
    public void displayCommands();
    void setText(String text);
}
