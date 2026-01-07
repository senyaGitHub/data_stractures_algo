package simulation;

import dispatch.Dispatcher;
import graph.*;
import java.util.*;
import model.*;

public class SimulationEngine {

    public void run() {
        System.out.println("=== SIMULATION START ===");

        Graph graph = new Graph();
        System.out.println("[INIT] Creating city graph");

        City a = new City("CityA");
        City b = new City("CityB");

        graph.addNode(a);
        graph.addNode(b);

        graph.addEdge(a, b, 5);
        graph.addEdge(b, a, 5);

        System.out.println(
            "[GRAPH] Nodes added: " + a.getId() + ", " + b.getId()
        );
        System.out.println("[GRAPH] Edge CityA -> CityB (distance 5)");
        System.out.println("[GRAPH] Edge CityB -> CityA (distance 5)");

        ResponseUnit ambulance = new ResponseUnit("A1", "ambulance", a);
        List<ResponseUnit> units = List.of(ambulance);

        System.out.println("[UNITS] Available response units:");
        for (ResponseUnit unit : units) {
            System.out.println(
                "  - Unit ID: " +
                    unit.getId() +
                    ", Type: " +
                    unit.getType() +
                    ", Location: " +
                    unit.getLocation().getId()
            );
        }

        Incident incident = new Incident("I1", 3, "ambulance", b);
        System.out.println("[INCIDENT] New incident reported:");
        System.out.println(
            "  - ID: " +
                incident.getId() +
                ", Severity: " +
                incident.getSeverity() +
                ", Required type: " +
                incident.getRequiredUnitType() +
                ", Location: " +
                incident.getLocation().getId()
        );

        Dispatcher dispatcher = new Dispatcher();
        System.out.println("[DISPATCH] Attempting to assign response unit...");

        ResponseUnit assigned = dispatcher.dispatch(graph, units, incident);

        if (assigned != null) {
            System.out.println("[RESULT] Unit assigned successfully!");
            System.out.println(
                "  - Assigned Unit ID: " +
                    assigned.getId() +
                    " (" +
                    assigned.getType() +
                    ")"
            );
            System.out.println(
                "  - From: " +
                    assigned.getLocation().getId() +
                    " → To: " +
                    incident.getLocation().getId()
            );
        } else {
            System.out.println("[RESULT] No suitable unit could be assigned.");
        }

        System.out.println("=== SIMULATION END ===");
    }
}
