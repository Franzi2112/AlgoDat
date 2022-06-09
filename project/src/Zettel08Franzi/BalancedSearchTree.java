package Zettel08Franzi;

import Zettel07Franzi.TreeNode;
import Zettel08Bela.BSTNode;

import java.util.ArrayList;
import java.util.List;

public class BalancedSearchTree <K extends Comparable <K>>{
    public BalancedSearchTreeNode<K> root;
    public int alpha;

    public BalancedSearchTree(int alpha) {
        this.root = null;
        this.alpha = alpha;
    }

    private int numberOfNodes(BalancedSearchTreeNode<K> node) {
        if (node == null) {
            return 0;
        }
        else {
            return numberOfNodes(node.left) + numberOfNodes(node.right) + 1;
        }
    }

    private double rootBalance(BalancedSearchTreeNode<K> node){
        return((numberOfNodes(node.left)+1)/(numberOfNodes(node.right)+1));
    }

    private void RightRotate(BalancedSearchTreeNode<K> node) {
        BalancedSearchTreeNode<K> y = node.left;
        node.left = y.right;
        /*y.right = node;
        node = y;*/
        if(y.right != null){
            y.right.dad = node;
        }
        y.dad = node.dad;
        if(node.dad == null){
            this.root = y;
        } else if(node == node.dad.right){
            node.dad.right = y;
        } else {
            node.dad.left = y;
        }
        y.right = node;
        node.dad = y;
    }

    private void LeftRotate(BalancedSearchTreeNode<K> node) {
        BalancedSearchTreeNode<K> y = node.right;
        node.right = y.left;
        /*y.right = node;
        node = y;*/
        if(y.left != null){
            y.left.dad = node;
        }
        y.dad = node.dad;
        if(node.dad == null){
            this.root = y;
        } else if(node == node.dad.left){
            node.dad.left = y;
        } else {
            node.dad.right = y;
        }
        y.left = node;
        node.dad = y;
    }

    private void DoubleLeftRotate(BalancedSearchTreeNode<K> node) {
        RightRotate(node.right);
        LeftRotate(node);
    }

    private void DoubleRightRotate(BalancedSearchTreeNode<K> node) {
        LeftRotate(node.left);
        RightRotate(node);
    }

    private void rebalance(BalancedSearchTreeNode<K> node) {
        if (node == null) {
            return;
        }
        if (rootBalance(node) < alpha) {
            if (rootBalance(node.right) <= 1/(2-alpha)) {
                LeftRotate(node);
            } else {
                DoubleLeftRotate(node);
            }
        } else if (rootBalance(node) > 1-alpha) {
            if (rootBalance(node.left) >= 1-1/(2-alpha)) {
                RightRotate(node);
            } else {
                DoubleRightRotate(node);
            }
        }
    }

    public void insert(BalancedSearchTreeNode<K> node, K value) {
        if (value.compareTo((K) node.value) < 0) {
            if (node.left == null) {
                node.left = new BalancedSearchTreeNode(value);
            } else {
                this.insert(node.left, value);
            }
        } else if( node == null){
            return;
        } else { // da ich rechts auch nochmal den selben wert einfügen kann ohne Probleme
            if (node.right == null) {
                node.right = new BalancedSearchTreeNode(value);
            } else {
                this.insert(node.right, value);
            }
        }
        rebalance(node);
    }

    public void delete(BalancedSearchTreeNode<K> pNode, BalancedSearchTreeNode<K> node, K value) {
        if (node == null) {
            return;
        }

        if(pNode== null &&node.value==value) {
            this.root=null;
            return;
        }

        if (value.compareTo(node.value) < 0) {
            this.delete(node, node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            this.delete(node, node.right, value);
        } else {
            if (node.left == null && node.right == null) {
                if (pNode.left==node) {
                    pNode.left=null;
                } else {
                    pNode.right=null;
                }
            } else if (node.left == null) {
                if (pNode.left==node) {
                    pNode.left=node.right;
                } else {
                    pNode.right=node.right;
                }
            } else if (node.right == null) {
                if (pNode.left==node) {
                    pNode.left=node.left;
                } else {
                    pNode.right=node.left;
                }
            } else {
                BalancedSearchTreeNode<K> min = this.findMin(node.right);
                node.value = min.value;
                this.delete(node, node.right, min.value);
            }
        }
    }


    public BalancedSearchTreeNode<K> findMin(BalancedSearchTreeNode<K> node) {
        if (node.left == null) {
            return node;
        } else {
            return this.findMin(node.left);
        }
    }

    public boolean search(K value) {
        return this.search(this.root, value);
    }

    private boolean search(BalancedSearchTreeNode<K> node, K value) {
        if (node == null) {
            return false;
        }
        if (value.compareTo(node.value) < 0) {
            return this.search(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            return this.search(node.right, value);
        } else {
            return true;
        }
    }
    public BalancedSearchTreeNode<K> findMax(BalancedSearchTreeNode<K> node) {
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

    public ArrayList<K> toSortedArray() {
        ArrayList<K> list = new ArrayList<>();
        this.toSortedArray(this.root, list);
        return list;
    }

    private void toSortedArray(BalancedSearchTreeNode<K> node, List<K> list) {
        if (node == null) {
            return;
        }
        this.toSortedArray(node.left, list);
        list.add(node.value);
        this.toSortedArray(node.right, list);
    }




}
