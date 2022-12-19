package fr.istic.aco.editor.recorder;

import fr.istic.aco.editor.commandOriginator.CommandOriginator;
import fr.istic.aco.editor.memento.Memento;
import fr.istic.aco.editor.memento.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Recorder {

    /*At the application launch there are no recording or replaying */
    private boolean isRecording = false;
    private boolean isReplaying = false;

    //The list of all the commands
    private List< Pair<CommandOriginator, Optional<Memento>> > storeCommands = new ArrayList<>();

    /** Save the given command to the records
     * @param commandOriginator the command to save for the record
     */
    public void save(CommandOriginator commandOriginator){
        if (this.isRecording()){ // If we start the recording
            Optional<Memento> memento = commandOriginator.getMemento();
            this.storeCommands.add(new Pair<>(commandOriginator, memento));
            System.out.println();
            this.stop();
        }
    }

    /** To launch the save */
    public void start(){
        System.out.println("Save start...");
        this.isRecording = true;
    }
    /** To stop the save */
    public void stop(){
        this.isRecording = false;
        System.out.println("...Finish to save");
    }

    /** Launch the replay of the saved data commands */
    public void replay(){
        this.stop();
        this.isReplaying = true;
        System.out.println("Replaying started...");
        //Each command of the pair list is executed
        storeCommands.forEach( cmdMem-> {
            CommandOriginator key = cmdMem.getKey();

            //Seuls les Insert et Selection ont le setMemento ? Comment faire ?

            Optional<Memento> value = cmdMem.getValue();
            key.setMemento(value.get());
            key.execute();
        });
        /*
        for (Command command:this.commands) {
            command.execute();
        }   */
    }

    /*Getters*/
    public boolean isRecording() {
        return this.isRecording;
    }

    public boolean isReplaying() {  return isReplaying; }
}
