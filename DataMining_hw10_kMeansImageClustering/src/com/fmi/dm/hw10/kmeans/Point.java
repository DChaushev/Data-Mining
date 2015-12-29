package com.fmi.dm.hw10.kmeans;

import java.util.List;

/**
 *
 * @author Dimitar
 */
public interface Point {

    double distanceTo(Point other);

    void changeValue(Point other);

    Point centroidOf(List<Point> points);
}
