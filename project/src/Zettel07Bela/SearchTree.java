package Zettel07Bela;

import java.util.ArrayList;
import java.util.List;

public class SearchTree<K extends Comparable<K>> {
    public TreeNode<K> root;

    public SearchTree() {
        this.root = null;
    }

    public void insert(K value) {
        if (this.root == null) {
            this.root = new TreeNode(value);
        } else {
            this.insert(this.root, value);
        }
    }

    private void insert(TreeNode<K> node, K value) {
        if (value.compareTo((K) node.value) < 0) {
            if (node.left == null) {
                node.left = new TreeNode(value);
            } else {
                this.insert(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(value);
            } else {
                this.insert(node.right, value);
            }
        }
    }

    public void delete(K value) {
        this.delete(this.root, value);
    }

    private void delete(TreeNode<K> node, K value) {
        if (node == null) {
            return;
        }

        if (value.compareTo(node.value) < 0) {
            this.delete(node.left, value);
        } else if (value.compareTo((K) node.value) > 0) {
            this.delete(node.right, value);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                TreeNode<K> min = this.findMin(node.right);
                node.value = min.value;
                this.delete(node.right, min.value);
            }
        }
    }

    public boolean search(K value) {
        return this.search(this.root, value);
    }

    private boolean search(TreeNode node, K value) {
        if (node == null) {
            return false;
        }

        if (value.compareTo((K) node.value) < 0) {
            return this.search(node.left, value);
        } else if (value.compareTo((K) node.value) > 0) {
            return this.search(node.right, value);
        } else {
            return true;
        }
    }

    public TreeNode<K> findMin(TreeNode<K> node) {
        if (node.left == null) {
            return node;
        } else {
            return this.findMin(node.left);
        }
    }

    public TreeNode<K> findMax(TreeNode<K> node) {
        if (node.right == null) {
            return node;
        } else {
            return this.findMax(node.right);
        }
    }

    public K min(){
        return this.findMin(this.root).value;
    }

    public K max(){
        return this.findMax(this.root).value;
    }

    public K[] toSortedArray() {
        List<K> list = new ArrayList<>();
        this.toSortedArray(this.root, list);
        return (K[]) list.toArray();
    }

    private void toSortedArray(TreeNode<K> node, List<K> list) {
        if (node == null) {
            return;
        }
        this.toSortedArray(node.left, list);
        list.add(node.value);
        this.toSortedArray(node.right, list);
    }

}
