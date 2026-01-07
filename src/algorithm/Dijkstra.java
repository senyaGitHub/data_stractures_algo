package algorithm;

import graph.*;
import java.util.*;

public class Dijkstra {

    // O((V + E) log V)
    public static Map<Node, Double> compute(Graph graph, Node source) {
        Map<Node, Double> dist = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>();

        for (Node n : graph.getAdjacencyList().keySet()) {
            dist.put(n, Double.MAX_VALUE);
        }

        dist.put(source, 0.0);
        pq.add(new NodeDistance(source, 0.0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();

            for (Edge e : graph.getAdjacencyList().get(current.node)) {
                double newDist = current.distance + e.getWeight();
                if (newDist < dist.get(e.getTo())) {
                    dist.put(e.getTo(), newDist);
                    pq.add(new NodeDistance(e.getTo(), newDist));
                }
            }
        }
        return dist;
    }

    private static class NodeDistance implements Comparable<NodeDistance> {

        Node node;
        double distance;

        NodeDistance(Node node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        public int compareTo(NodeDistance o) {
            return Double.compare(this.distance, o.distance);
        }
    }
}
