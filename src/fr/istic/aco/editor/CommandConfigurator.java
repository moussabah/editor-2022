package fr.istic.aco.editor;

import fr.istic.aco.editor.command.Replay;
import fr.istic.aco.editor.command.Start;
import fr.istic.aco.editor.command.Stop;
import fr.istic.aco.editor.commandOriginator.*;
import fr.istic.aco.editor.enginecore.Engine;
import fr.istic.aco.editor.enginecore.EngineImpl;
import fr.istic.aco.editor.invoker.Client;
import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.recorder.Recorder;

import java.util.logging.Logger;

public class CommandConfigurator {

   private Invoker invoker;
   private Engine receiver;
   private Recorder recorder;

   public static void main(String lineArgs[]) {

      CommandConfigurator client = new CommandConfigurator();
      client.run();

   }
   
    private void run() {
        Logger.getGlobal().info("Starting...");
        this.invoker = new Client();
        this.receiver = new EngineImpl();
        this.recorder = new Recorder();

        configureCommands();
        while(true) {
            invoker.setReadStream(System.in);
            String key = invoker.getText();
            invoker.executeCommand(key);
        }
    }
    private void configureCommands() {
        //Instanciate the concret Commands
        invoker.addCommand("Insert", new Insert(receiver,invoker,recorder));
        invoker.addCommand("Copy", new Copy(receiver, recorder));
        invoker.addCommand("Cut", new Cut(receiver, recorder));
        invoker.addCommand("Delete", new Delete(receiver, recorder));
        invoker.addCommand("Paste", new Paste(receiver,recorder));
        invoker.addCommand("Select", new MoveSelection(receiver,invoker, recorder));
        invoker.addCommand("Replay", new Replay(recorder));
        invoker.addCommand("Start", new Start(recorder));
        invoker.addCommand("Stop", new Stop(recorder));

        invoker.displayCommands();

        invoker.addCommand("Test", () -> System.err.println("Test : "+this.toString()));
    }

}
