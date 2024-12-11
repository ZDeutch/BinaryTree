import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 *
 * @author: Zander Deutch
 * @version: 12/9/24
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     *
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for (int i = 0; i < nodes.size() - 1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size() - 1));
    }

    /**
     * A function that searches for a value in the tree
     *
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        return searchHelp(root, val);
    }

    public boolean searchHelp(BSTNode node, int val) {
        if (node == null) {
            return false;
        }

        if (node.getVal() == val) {
            return true;
        }

        if (val < node.getVal()) {
            return searchHelp(node.getLeft(), val);
        } else {
            return searchHelp(node.getRight(), val);
        }
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder() {
        ArrayList<BSTNode> result = new ArrayList<>();
        inOrderHelper(root, result);
        return result;
    }

    private void inOrderHelper(BSTNode node, ArrayList<BSTNode> result) {
        if (node != null) {
            inOrderHelper(node.getLeft(), result);
            result.add(node);
            inOrderHelper(node.getRight(), result);
        }
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder() {
        ArrayList<BSTNode> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }

    private void preOrderHelper(BSTNode node, ArrayList<BSTNode> result) {
        if (node != null) {
            result.add(node);
            preOrderHelper(node.getLeft(), result);
            preOrderHelper(node.getRight(), result);
        }
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder() {
        ArrayList<BSTNode> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    private void postOrderHelper(BSTNode node, ArrayList<BSTNode> result) {
        if (node != null) {
            postOrderHelper(node.getLeft(), result);
            postOrderHelper(node.getRight(), result);
            result.add(node);
        }
    }


    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     *
     * @param val The value ot insert
     */
    public void insert(int val) {
        insertHelper(root, val);
    }

    private void insertHelper(BSTNode node, int val) {
        if (val > node.getVal()) {
            if (node.getRight() == null) {
                node.setRight(new BSTNode(val));
            } else {
                insertHelper(node.getRight(), val);
            }
        }
        if (val < node.getVal()) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode(val));
            } else {
                insertHelper(node.getLeft(), val);
            }
        }
    }


    /**
     * Determines if the current BST is
     * a valid BST.
     *
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTHelper(BSTNode node, int min, int max) {
        if(node == null) {
            return true;
        }
        if(node.getVal() <= min || node.getVal() >= max) {
            return false;
        }
        return isBSTHelper(node.getLeft(), min, node.getVal()) && isBSTHelper(node.getRight(), node.getVal(), max);



    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
