package fr.istic.aco.editor.originator;

import fr.istic.aco.editor.memento.Memento;

import java.util.Optional;

/**
 * Provide the access to the Memento
 */

public interface Originator {

    /**
     * Creates a memento containing the information to reproduce something.
     *
     * @return the memento or an optional Empty if the command did not have use on the Memento.
     */
    Optional<Memento> getMemento();

    /**
     * Given a memento, Change/Set the surrounding elements with the information contained inside the param.
     *
     * @param memento the memento with the information.
     */

    void setMemento(Memento memento);
}
