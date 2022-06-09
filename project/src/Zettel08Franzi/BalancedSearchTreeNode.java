package Zettel08Franzi;

import Zettel07Franzi.TreeNode;

public class BalancedSearchTreeNode<K extends Comparable<K>> {
    public K value;
    public BalancedSearchTreeNode<K> left;
    public BalancedSearchTreeNode<K> right;
    public BalancedSearchTreeNode<K> dad;

    public BalancedSearchTreeNode(K value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.dad = null;
    }
}
