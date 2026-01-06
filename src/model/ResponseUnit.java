package model;

import graph.Node;

public class ResponseUnit {
    private final String id;
    private final String type;
    private Node location;
    private boolean available = true;

    public ResponseUnit(String id, String type, Node location) {
        this.id = id;
        this.type = type;
        this.location = location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Node getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }
}
