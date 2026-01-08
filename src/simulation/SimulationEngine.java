package simulation;

import dispatch.Dispatcher;
import graph.*;
import java.util.*;
import model.*;

public class SimulationEngine {

    public void run() {
        System.out.println("=== EMERGENCY RESPONSE SIMULATION START ===");

        /* ---------------- GRAPH SETUP ---------------- */
        Graph graph = new Graph();
        System.out.println("[INIT] Creating UK city graph");

        City london = new City("London");
        City manchester = new City("Manchester");
        City birmingham = new City("Birmingham");
        City bristol = new City("Bristol");
        City leeds = new City("Leeds");

        graph.addNode(london);
        graph.addNode(manchester);
        graph.addNode(birmingham);
        graph.addNode(bristol);
        graph.addNode(leeds);

        graph.addEdge(london, birmingham, 2);
        graph.addEdge(birmingham, manchester, 2);
        graph.addEdge(manchester, leeds, 1);
        graph.addEdge(bristol, london, 2);
        graph.addEdge(bristol, birmingham, 2);

        System.out.println("[GRAPH] Cities added:");
        System.out.println(
            "  - London, Birmingham, Manchester, Leeds, Bristol"
        );

        System.out.println("[GRAPH] Roads added between cities");

        /* ---------------- UNITS ---------------- */
        ResponseUnit ambulance1 = new ResponseUnit(
            "AMB-1",
            "ambulance",
            london
        );
        ResponseUnit ambulance2 = new ResponseUnit(
            "AMB-2",
            "ambulance",
            manchester
        );
        ResponseUnit fireTruck = new ResponseUnit("FIRE-1", "fire", birmingham);

        List<ResponseUnit> units = List.of(ambulance1, ambulance2, fireTruck);

        System.out.println("[UNITS] Available response units:");
        for (ResponseUnit unit : units) {
            System.out.println(
                "  - " +
                    unit.getId() +
                    " | Type: " +
                    unit.getType() +
                    " | Location: " +
                    unit.getLocation().getId()
            );
        }

        /* ---------------- INCIDENTS ---------------- */
        List<Incident> incidents = List.of(
            new Incident("INC-1", 3, "ambulance", leeds),
            new Incident("INC-2", 2, "fire", bristol),
            new Incident("INC-3", 5, "ambulance", birmingham)
        );

        Dispatcher dispatcher = new Dispatcher();

        /* ---------------- SIMULATION LOOP ---------------- */
        for (Incident incident : incidents) {
            System.out.println("\n[INCIDENT] New incident reported:");
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

            System.out.println("[DISPATCH] Searching for suitable unit...");
            ResponseUnit assigned = dispatcher.dispatch(graph, units, incident);

            if (assigned != null) {
                System.out.println("[RESULT] Unit assigned:");
                System.out.println(
                    "  - " + assigned.getId() + " (" + assigned.getType() + ")"
                );
                System.out.println(
                    "  - Route: " +
                        assigned.getLocation().getId() +
                        " → " +
                        incident.getLocation().getId()
                );
            } else {
                System.out.println(
                    "[RESULT] No suitable unit available for this incident."
                );
            }
        }

        System.out.println("\n=== SIMULATION END ===");
    }
}
