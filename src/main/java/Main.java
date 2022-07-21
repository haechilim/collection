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
        Node<String> nodeH = new Node<>("H");
        Node<String> nodeI = new Node<>("I");
        Node<String> nodeJ = new Node<>("J");

        BinaryTree<String> binaryTree = new BinaryTree<>(root);

        binaryTree.insertLeftNode(root, nodeB);
        binaryTree.insertRightNode(root, nodeC);
        binaryTree.insertLeftNode(nodeB, nodeD);
        binaryTree.insertRightNode(nodeB, nodeE);
        binaryTree.insertLeftNode(nodeE, nodeF);
        binaryTree.insertRightNode(nodeE, nodeG);
//        binaryTree.insertLeftNode(nodeC, nodeH);
//        binaryTree.insertRightNode(nodeC, nodeI);
//        binaryTree.insertLeftNode(nodeI, nodeJ);

        binaryTree.traversePreorderRecursively();
        System.out.println(binaryTree.getTraversalNodes());

        binaryTree.traverseInorderRecursively();
        System.out.println(binaryTree.getTraversalNodes());

        binaryTree.traversePostorderRecursively();
        System.out.println(binaryTree.getTraversalNodes());

        binaryTree.traverseLevel();
        System.out.println(binaryTree.getTraversalNodes());
    }
}
