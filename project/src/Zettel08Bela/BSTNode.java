package Zettel08Bela;

public class BSTNode<K extends Comparable<K>> {
    public K value;
    public BSTNode<K> left;
    public BSTNode<K> right;

    public BSTNode(K value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }


}
