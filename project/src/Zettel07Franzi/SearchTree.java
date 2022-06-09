package Zettel07Franzi;


import Zettel08Bela.BSTNode;

import java.util.ArrayList;
import java.util.List;

public class SearchTree <K extends Comparable <K>> {
    public TreeNode<K> root;

    public SearchTree() {
        this.root = null;
    }

    public void insert(TreeNode<K> node, K value) {
        if (value.compareTo((K) node.value) < 0) {
            if (node.left == null) {
                node.left = new TreeNode(value);
            } else {
                this.insert(node.left, value);
            }
        } else { // da ich rechts auch nochmal den selben wert einfügen kann ohne Probleme
            if (node.right == null) {
                node.right = new TreeNode(value);
            } else {
                this.insert(node.right, value);
            }
        }
    }

    public void delete(TreeNode<K> node, K value) {
        if (node == null) {
            return;
        } else if (value.compareTo((K) node.value) < 0) {
            this.delete(node.left, value);
        } else if (value.compareTo((K) node.value) > 0) {
            this.delete((node.right), value);
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

    public TreeNode search(TreeNode<K> node, K value) {
        if (node == null) {
            return null;
        } else if (value.compareTo((K) node.value) < 0) {
            return this.search(node.left, value);
        } else if (value.compareTo((K) node.value) > 0) {
            return this.search(node.right, value);
        } else {
            return node;
        }
    }

    public TreeNode<K> findMin(TreeNode<K> node) {
        if (node.left == null) {
            return node;
        } else {
            return this.findMin(node.left);
        }
    }

    public K min() {
        return this.findMin(this.root).value;
    }

    public TreeNode<K> findMax(TreeNode<K> node) {
        if (node.right == null) {
            return node;
        } else {
            return this.findMax(node.right);
        }
    }

    public K max() {
        return this.findMax(this.root).value;
    }

    public void toSortedArray(TreeNode<K> node, List<K> list) {
        if (node == null) {
            return;
        } // dachte eventuell mit min suche aber dann immer eins weiter nach oben gehen wenn man baum nachher nicht merh braucht könnte man immer min löschen und dann wieder min
        /*while(node != null){
            TreeNode<K> next = this.findMin(node);
            list.add(next.value);
            this.delete(node, next.value);
            this.toSortedArray(node, list);
        }
        list.add(node.value);
        */
        this.toSortedArray(node.left, list);
        list.add(node.value);
        this.toSortedArray(node.right, list);
    }

    public K[] toSortedArray() {
        List<K> list = new ArrayList<>();
        this.toSortedArray(this.root, list);
        return (K[]) list.toArray();
    }


}
