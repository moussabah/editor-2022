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
        //Provide the stream of the user
        String key = getBufferReadline();
        if (this.commands.containsKey(key)){
            commands.get(key).execute();
        }
    }

    /** Provide the input stream of the user as a String
     *
     * @return the entry stream of the user
     */
    public void setReadStream( InputStream inputStream) {
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
        Integer getBeginIndex;
        do {
            System.out.println("Enter a positif value : ");
            //To convert the input stream of the user to Int
            getBeginIndex = Integer.parseInt(getBufferReadline());
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
            getEndIndex = Integer.parseInt(getBufferReadline());
        }
        while(getEndIndex < this.getBeginIndex() || getEndIndex > getText().length());
        return getEndIndex;
    }

    /**
    @Override
    public void setReadStream(InputStream inputStream) {
        if(inputStream == null) {
            throw new IllegalArgumentException("null inputStream");
        }
        this.inputStream = inputStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }
*/
}
