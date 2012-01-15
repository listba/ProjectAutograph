package autograph;
import java.io.*;

public class Autograph {

    public static void main(String [] args) {
        Graph graph = new Graph("");
        Node node = new Node("NodeA", "My First Node", 
            Node.NodeShape.CIRCLE, Node.NodeStyle.SOLID);
        graph.mAddNode(node);
        System.out.println(node.mGetId());
    }
}
