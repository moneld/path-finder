package me.moneld;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPathFinder {
    public static List<Path> findPossiblePaths(Point origin, Point destination, List<Route> routes) {
        return findPossiblePaths(origin, destination, routes, new ArrayList<>());
    }

    private static List<Path> findPossiblePaths(Point origin, Point destination, List<Route> routes, List<Route> currentPath) {
        List<Path> possiblePaths = new ArrayList<>();

        if (origin == destination) {
            possiblePaths.add(new Path(currentPath));
            return possiblePaths;
        }

        for (Route route : routes) {
            if (route.origin == origin) {
                List<Route> updatedPath = new ArrayList<>(currentPath);
                updatedPath.add(route);

                Point nextOrigin = route.destination;
                List<Route> remainingRoutes = routes.stream()
                        .filter(r -> r != route)
                        .collect(Collectors.toList());

                possiblePaths.addAll(findPossiblePaths(nextOrigin, destination, remainingRoutes, updatedPath));
            }
        }

        return possiblePaths;
    }

    public static List<Path> filterPathsByStop(List<Path> paths, List<Integer> stop) {
        return paths.stream()
                .filter(path -> path.getStops().contains(stop))
                .collect(Collectors.toList());
    }

    public static Path findShortestPath(Point origin, Point destination, List<Route> routes, List<Integer> stops) {
        List<Path> possiblePaths = findPossiblePaths(origin, destination, routes);
      /*  List<Path> filteredPaths = filterPathsByStop(possiblePaths, stops);

        if (filteredPaths.isEmpty()) {
            throw new IllegalArgumentException("Aucun chemin disponible avec les arrêts donnés.");
        }*/

        return Collections.min(possiblePaths, Comparator.comparingDouble(Path::getLength));
    }


    public static void main(String[] args) {

        Point origin = new Point(0, 0.5, 10.2);
        Point destination = new Point(2, 10.5, 20.3);
        List<Integer> stops = Arrays.asList(1);
        List<Route> routes = new ArrayList<>();
        routes.add(new Route(origin, new Point(1, 10.1, 10.5)));
        routes.add(new Route(new Point(1, 10.1, 10.5), new Point(2, 10.5, 20.3)));
        routes.add(new Route(origin, destination));


        Path shortestPath = findShortestPath(origin, destination, routes, stops);


        for (Route route : shortestPath.routes) {
            System.out.print(route.origin.id + " ");
        }
        System.out.println(destination.id);
    }
}
