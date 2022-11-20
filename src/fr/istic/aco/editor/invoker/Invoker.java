package fr.istic.aco.editor.Invoker;

import fr.istic.aco.editor.Command.Command;

public interface Invoker {
    public String getText();
    public void addCommands(String key, Command value);
    public void executeCommand(String key);

}
