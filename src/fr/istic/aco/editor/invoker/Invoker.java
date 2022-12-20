package fr.istic.aco.editor.invoker;

import fr.istic.aco.editor.command.Command;
import java.io.InputStream;

/**
 * Used to execute Commands
 */
public interface Invoker {
    /**
     * @return A String value of the text given by the user
     */
    String getText();

    /** Change the saved string value
     *
     * @param text the String to be saved
     */
    void setText(String text);

    /** Allow toAdd a couple of key-value to the Set of the commands
     *
     * @param key the key of the map couple
     * @param value the value corresponding to the key
     */
    void addCommand(String key, Command value);

    /** Allow to execute a command given by it key
     *
     * @param key A String value of the command to execute
     */
    void executeCommand(String key);

    /** Provide the index of the first character of the selection by the user
     *
     * @return An Integer of the index of the first element of the selection
     */
    Integer getBeginIndex();

    /** Provide the index of the first "virtual" character after the user's selection
     *
     * @return Integer: the index of the last element of the selection
     */
    Integer getEndIndex();

    /** Provide the input stream of the user as a String
     *
     * @return the entry stream of the user
     */
    void setReadStream(InputStream inputStream);

    /**
     * Display all the saved Commands
     */
    void displayCommands();
}
