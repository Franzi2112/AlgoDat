package Zettel08Bela;


import java.util.ArrayList;
import java.util.List;

public class BalancedSearchTree<K extends Comparable<K>>  {
    BSTNode<K> root;
    int alpha;

    public BalancedSearchTree(int alpha) {
        this.root = null;
        this.alpha = alpha;
    }

    private int size(BSTNode<K> node) {
        if (node == null) {
            return 0;
        }
        else {
            return size(node.left) + size(node.right) + 1;
        }
    }

    private double p(BSTNode<K> node) {
        return (size(node.left)+1)/(size(node)+1);
    }

    private void LeftRotate(BSTNode<K> node) {
        BSTNode<K> y = node.right;
        node.right = y.left;
        y.left = node;
        node = y;
    }

    private void RightRotate(BSTNode<K> node) {
        BSTNode<K> y = node.left;
        node.left = y.right;
        y.right = node;
        node = y;
    }

    private void DLeftRotate(BSTNode<K> node) {
        RightRotate(node.right);
        LeftRotate(node);
    }

    private void DRightRotate(BSTNode<K> node) {
        LeftRotate(node.left);
        RightRotate(node);
    }

    private void rebalance(BSTNode<K> node) {
        if (node == null) {
            return;
        }
        if (p(node) < alpha) {
            if (p(node.right) <= 1/(2-alpha)) {
                LeftRotate(node);
            } else {
                DLeftRotate(node);
            }
        } else if (p(node) > 1-alpha) {
            if (p(node.left) >= 1-1/(2-alpha)) {
                RightRotate(node);
            } else {
                DRightRotate(node);
            }
        }
    }

    public boolean search(K value) {
        return this.search(this.root, value);
    }

    private boolean search(BSTNode<K> node, K value) {
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

    public void insert(K value) {
        this.root = this.insert(this.root, value);
    }

    private BSTNode<K> insert(BSTNode<K> node, K value) {
        if (node == null) {
            return new BSTNode<K>(value);
        }
        if (value.compareTo(node.value) < 0) {
            node.left = this.insert(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = this.insert(node.right, value);
        }
        rebalance(node);
        return node;
    }

    public void delete(K value) {
        this.delete(null, this.root, value);
    }

    private void delete(BSTNode<K> pNode, BSTNode<K> node, K value) {
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
                BSTNode<K> min = this.findMin(node.right);
                node.value = min.value;
                this.delete(node, node.right, min.value);
            }
        }
    }

    public BSTNode<K> findMin(BSTNode<K> node) {
        if (node.left == null) {
            return node;
        } else {
            return this.findMin(node.left);
        }
    }

    public BSTNode<K> findMax(BSTNode<K> node) {
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

    private void toSortedArray(BSTNode<K> node, List<K> list) {
        if (node == null) {
            return;
        }
        this.toSortedArray(node.left, list);
        list.add(node.value);
        this.toSortedArray(node.right, list);
    }


}
