package fr.istic.aco.editor.invoker;

import fr.istic.aco.editor.command.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Client implements Invoker {
    private String text = "";

    //List all the commands
    private Map<String,Command> commands = new HashMap<>();
    private BufferedReader bufferedReader;

    private int endIndex;
    private int beginIndex;

    public String getText() {
        try {
            this.text = this.bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    /** Add a couple of key-value to the Hashmap
     *
     * @param key the key of the map couple
     * @param value the value corresponding to the key
     */
    public void addCommand(String key, Command value){
        commands.put(key, value);
    }

    /** Execute a giving command
     * @param key the command to execute
     */
    public void executeCommand(String key){
        if (this.commands.containsKey(key)){
            this.commands.get(key).execute();
        }
        //}
    }

    /** Provide the input stream of the user as a String
     *
     * @return the entry stream of the user
     */
    public void setReadStream( InputStream inputStream) {
        if(inputStream == null) {
            throw new IllegalArgumentException("null inputStream");
        }
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * @return the user's entry flow
     */
    public String getBufferReadline() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** To get index of the first element of the selection by the user
     *
     * @return Integer: the index of the first element of the selection
     */
    public Integer getBeginIndex() {
        System.out.println("Enter a begin index : ");
        this.beginIndex = Integer.parseInt(getBufferReadline());
        System.out.println("begin = " + beginIndex);
        return beginIndex;
    }

    /** To get index of the first element of the selection by the user
     *
     * @return Integer: the index of the last element of the selection
     */
    public Integer getEndIndex() {
        System.out.println("Enter an end index : ");
        this.endIndex = Integer.parseInt(getBufferReadline());
        System.out.println("end = " + endIndex);
        return endIndex;
    }

    /**
     * Provide the key values of the commands
     */
    public void displayCommands(){
        System.out.println("Enter some command among those:" );
        for (String key: this.commands.keySet()) {
            System.out.print( key + " ");
        }
        System.out.println();
    }
}
