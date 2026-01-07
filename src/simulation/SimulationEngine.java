package simulation;

import dispatch.Dispatcher;
import graph.*;
import java.util.*;
import model.*;

public class SimulationEngine {

    public void run() {
        Graph graph = new Graph();

        City a = new City("CityA");
        City b = new City("CityB");
        graph.addNode(a);
        graph.addNode(b);
        graph.addEdge(a, b, 5);
        graph.addEdge(b, a, 5);

        ResponseUnit ambulance = new ResponseUnit("A1", "ambulance", a);

        List<ResponseUnit> units = List.of(ambulance);

        Incident incident = new Incident("I1", 3, "ambulance", b);

        Dispatcher dispatcher = new Dispatcher();
        ResponseUnit assigned = dispatcher.dispatch(graph, units, incident);

        System.out.println(
            "Assigned unit: " + (assigned != null ? "YES" : "NO")
        );
    }
}
