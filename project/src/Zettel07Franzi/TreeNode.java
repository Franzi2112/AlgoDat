package Zettel07Franzi;

public class TreeNode<K extends Comparable<K>> {
    public K value;
    public TreeNode<K> left;
    public TreeNode<K> right;

    public TreeNode(K value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
