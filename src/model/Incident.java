package model;

import graph.Node;

public class Incident {

    private final String id;
    private final int severity;
    private final String requiredUnitType;
    private final Node location;

    public Incident(
        String id,
        int severity,
        String requiredUnitType,
        Node location
    ) {
        this.id = id;
        this.severity = severity;
        this.requiredUnitType = requiredUnitType;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public int getSeverity() {
        return severity;
    }

    public String getRequiredUnitType() {
        return requiredUnitType;
    }

    public Node getLocation() {
        return location;
    }
}
