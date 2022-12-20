package fr.istic.aco.editor.memento;

/** Provide A Pair of two given Object Type
 *
 * @param <A1> A first type of Element, the key of the Pair
 * @param <A2>  The value of the Pair
 */

public class Pair<A1,A2> {
    private A1 key;
    private A2 value;

    public Pair(A1 key, A2 value){
        this.key = key;
        this.value = value;
    }

    public A1 getKey() {    return this.key;     }

    //public void setKey(A1 key) {    this.key = key;     }

    public A2 getValue() {      return this.value;       }

    //public void setValue(A2 value) {    this.value = value;     }
}
