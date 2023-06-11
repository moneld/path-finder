package me.moneld;

import java.util.ArrayList;
import java.util.List;

public class Path {
    List<Route> routes;

    public Path(List<Route> routes) {
        this.routes = routes;
    }

    public double getLength() {
        return routes.stream().mapToDouble(Route::getLength).sum();
    }

    public List<Integer> getStops() {
        List<Integer> stops = new ArrayList<>();
        for (Route route : routes) {
            stops.add(route.origin.id);
            stops.add(route.destination.id);
        }
        return stops;
    }
}
