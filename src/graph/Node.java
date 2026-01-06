package graph;

public abstract class Node {
    private final String id;

    protected Node(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        return id.equals(((Node) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
