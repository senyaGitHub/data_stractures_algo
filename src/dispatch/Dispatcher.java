package dispatch;

import algorithm.Dijkstra;
import graph.*;
import java.util.*;
import model.*;

public class Dispatcher {

    public ResponseUnit dispatch(
        Graph graph,
        List<ResponseUnit> units,
        Incident incident
    ) {
        ResponseUnit best = null;
        double bestDistance = Double.MAX_VALUE;

        for (ResponseUnit unit : units) {
            if (!unit.isAvailable()) continue;
            if (
                !unit.getType().equals(incident.getRequiredUnitType())
            ) continue;

            Map<Node, Double> distances = Dijkstra.compute(
                graph,
                unit.getLocation()
            );

            double distance = distances.get(incident.getLocation());
            if (distance < bestDistance) {
                bestDistance = distance;
                best = unit;
            }
        }

        if (best != null) {
            best.setAvailable(false);
        }
        return best;
    }
}
