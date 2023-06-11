package me.moneld;

public class Route {
    Point origin;
    Point destination;

    public Route(Point origin, Point destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public double getLength() {
        double x1 = origin.lat;
        double y1 = origin.lon;
        double x2 = destination.lat;
        double y2 = destination.lon;

        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
