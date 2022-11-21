package fr.istic.aco.editor.invoker;

//Le receiver sera l'engine
//NE PAS METTRE LES MSG D'ERREUR DANS SYSOUT

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
    private InputStream inputStream;
    private BufferedReader bufferedReader;

    private int endIndex = 0;
    private int beginIndex = 0;

    public String getText() {
        try {
            this.text = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    /** Add a couple of key-value to the Hashmap
     *
     * @param key the key of the map couple
     * @param value the value corresponding to the key
     */
    public void addCommand(String key, Command value){
        commands.put(key, value);
    }

    /** Execute a command if the list contains its key
     *
     */
    public void executeCommand(){
        System.out.println("Enter some command : ");
        while (true){
            //Provide the stream of the user
            String key = getBufferReadline();
            if (this.commands.containsKey(key)){
                this.commands.get(key).execute();
            }
        }
    }

    /** Provide the input stream of the user as a String
     *
     * @return the entry stream of the user
     */
    public void setReadStream( InputStream inputStream) {
        if(inputStream == null) {
            throw new IllegalArgumentException("null inputStream");
        }
        this.inputStream = inputStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String getBufferReadline() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** To get index of the first element of the selection by the user
     *
     * @return Integer: the index of the first element of the selection
     */
    public Integer getBeginIndex() {
        do {
            System.out.println("Enter a begin index : ");
            //To convert the input stream of the user to Int
            beginIndex = Integer.parseInt(getBufferReadline());
        }
        while (beginIndex <0 || beginIndex > getText().length());
        return beginIndex;
    }

    /** To get index of the first element of the selection by the user
     *
     * @return Integer: the index of the last element of the selection
     */
    public Integer getEndIndex() {
       /* /**do {
            System.out.println("Enter an end index : ");
            //To convert the input stream of the user to Int
            endIndex = Integer.parseInt(getBufferReadline());
            System.out.println("Cool");
        } while(endIndex < this.beginIndex || endIndex > getText().length());
        while(endIndex >= this.beginIndex && endIndex <= getText().length()){
            System.out.println("Enter an end index : ");
            endIndex = Integer.parseInt(getBufferReadline());
        }*/
       //
        System.out.println("Enter an end index : ");
        return endIndex = Integer.parseInt(getBufferReadline());
    }
}
