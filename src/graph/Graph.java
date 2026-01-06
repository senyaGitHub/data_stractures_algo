package graph;

import java.util.*;

public class Graph {
    private final Map<Node, List<Edge>> adjacencyList = new HashMap<>();

    public void addNode(Node node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node from, Node to, double weight) {
        adjacencyList.get(from).add(new Edge(to, weight));
    }

    public Map<Node, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }
}
