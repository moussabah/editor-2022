package fr.istic.aco.editor.originator;

import fr.istic.aco.editor.memento.Memento;

import java.util.Optional;

public interface Originator {

    Optional<Memento> getMemento();

}
