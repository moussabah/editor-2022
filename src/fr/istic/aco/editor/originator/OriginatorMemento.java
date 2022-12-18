package fr.istic.aco.editor.originator;

import fr.istic.aco.editor.memento.Memento;

public interface OriginatorMemento extends Originator{

    /* This interface is only used by the Concrete Commands that act on the Memento */

    void setMemento(Memento memento);
}
