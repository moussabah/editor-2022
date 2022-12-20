package fr.istic.aco.editor.recorder;

import fr.istic.aco.editor.commandOriginator.CommandOriginator;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.memento.Pair;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Provides access to saving operations.
 */

public class Recorder {

    /*At the application launch there are no recording or replaying */
    private boolean isRecording = false;
    private boolean isReplaying = false;

    //The list of all the commands
    private List< Pair<CommandOriginator, Optional<Memento>> > storeCommands = new ArrayList<>();

    /** Save the given command to the records
     *
     * @param commandOriginator the command to save for the record
     * @throws InvalidParameterException if the param is null
     *
     */
    public void save(CommandOriginator commandOriginator){
        if (commandOriginator == null){
            throw new InvalidParameterException("The command must not be null");
        }
        if (this.isRecording()){ // If we start the recording
            Optional<Memento> memento = commandOriginator.getMemento();
            this.storeCommands.add(new Pair<>(commandOriginator, memento));
        }
    }

    /** Trigger the record, it's empty if first */
    public void start(){
        System.out.println("Save start...");
        this.isRecording = true;
        this.storeCommands.clear();
    }
    /** Finish the save */
    public void stop(){
        this.isRecording = false;
    }

    /** Replay the saved actions */
    public void replay(){
        this.stop();
        this.isReplaying = true;
        System.out.println("Replaying started...");
        //Each command of the pair list is executed
        storeCommands.forEach( cmdMem-> {
            CommandOriginator key = cmdMem.getKey();
            //Only the Insert and Selection have the setMemento
            Optional<Memento> value = cmdMem.getValue();
            if (value.isPresent()){
                key.setMemento(value.get());
            }
            key.execute();
        });
        this.isReplaying = false;
    }

    /* Getters */
    public boolean isRecording() {
        return this.isRecording;
    }

    public boolean isReplaying() {  return isReplaying; }

    /**
     * @return an int value of the List of the stored commands
     */
    public int getSizeOfStoreCommands(){
        return this.storeCommands.size();
    }
}
