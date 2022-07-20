import kr.hs.sunrint.tree.BinaryTree;
import kr.hs.sunrint.tree.Node;

public class Main {
    public static void main(String[] args) {
        Node<String> root = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        Node<String> nodeF = new Node<>("F");
        Node<String> nodeG = new Node<>("G");

        BinaryTree<String> binaryTree = new BinaryTree<>(root);

        binaryTree.insertLeftNode(root, nodeB);
        binaryTree.insertRightNode(root, nodeC);
        binaryTree.insertLeftNode(nodeB, nodeD);
        binaryTree.insertRightNode(nodeB, nodeE);
        binaryTree.insertLeftNode(nodeE, nodeF);
        binaryTree.insertRightNode(nodeE, nodeG);

        binaryTree.traversePreorder();
        System.out.println();
        binaryTree.traverseInorder();
        System.out.println();
        binaryTree.traversePostorder();
        System.out.println();
        binaryTree.traverseLevel();
    }
}
