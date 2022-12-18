package fr.istic.aco.editor.memento;

public class Pair<A1,A2> {
    private A1 key;
    private A2 value;

    public Pair(A1 key, A2 value){
        this.key = key;
        this.value = value;
    }

    public A1 getKey() {    return key;     }

    public void setKey(A1 key) {    this.key = key;     }

    public A2 getValue() {      return value;       }

    public void setValue(A2 value) {    this.value = value;     }
}
