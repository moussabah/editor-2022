package fr.istic.aco.editor.Client;

//Le receiver sera l'engine
//NE PAS METTRE LES MSG D'ERREUR DANS SYSOUT

import fr.istic.aco.editor.Command.Command;
import fr.istic.aco.editor.Invoker.Invoker;

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

    public String getText() {
        this.text = inputReader();
        return text;
    }

    /** Add a couple of key-value to the Hashmap
     *
     * @param key the key of the map couple
     * @param value the value corresponding to the key
     */
    public void addCommands(String key, Command value){
        commands.put(key, value);
    }

    /** Find the value of a defined key in the HashMap
     *
     * @param key
     */
    public void executeCommand(String key){
        Command command = commands.get(key);
        if (command != null){
            command.execute();
        }
    }

    /** Provide the input stream of the user as a String
     *
     * @return the entry stream of the user
     */
    public String inputReader() {
        InputStream inputStream = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String readValue = null;
        try {
            readValue = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readValue;
    }

    /** To get index of the first element of the selection by the user
     *
     * @return Integer: the index of the first element of the selection
     */
    public Integer getBeginIndex() {
        Integer getBeginIndex;
        do {
            System.out.println("Enter a positif value : ");
            //To convert the input stream of the user to Int
            getBeginIndex = Integer.parseInt(this.inputReader());
        }
        while (getBeginIndex <0 || getBeginIndex> getText().length());
        return getBeginIndex;
    }

    /** To get index of the first element of the selection by the user
     *
     * @return Integer: the index of the last element of the selection
     */
    public Integer getEndIndex() {
        Integer getEndIndex;
        do {
            //To convert the input stream of the user to Int
            getEndIndex = Integer.parseInt(this.inputReader());
        }
        while(getEndIndex < this.getBeginIndex() || getEndIndex > getText().length());
        return getEndIndex;
    }
}
