package com.fmi.dm.hw10;

import com.fmi.dm.hw10.kmeans.ImageClassifier;
import com.fmi.dm.hw10.kmeans.Point;
import com.fmi.dm.hw10.kmeans.PointRGB;
import com.fmi.dm.hw10.utils.ImageFilesOperator;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

/**
 *
 * @author Dimitar
 */
public class Main {

    private static final String INPUT_IMAGE_PATH = "C:\\Users\\Dimitar\\Desktop\\ColorfulBird.jpg";
    private static final String OUPUT_IMAGE_PATH = "C:\\Users\\Dimitar\\Desktop\\ColorfulBird-new.jpg";
    private static final int NUMBER_OF_CLUSTERS = 3;

    private static final String JPG_FORMAT = "jpg";

    /**
     * Chooses K random Points for the initial centroids.
     *
     * @param k number of centroids
     * @param originalPoints the original set of points. We are choosing the
     * initial centroids to be k random points from the existing ones
     * @return List<Point> the list with the centroids
     */
    private static final BiFunction<Integer, List<Point>, List<Point>> generateRandomPoints = (k, originalPoints) -> {
        Random rand = new Random(1);

        List<Point> points = new ArrayList<>(k);

        final int[] ints = rand.ints(0, originalPoints.size()).distinct().limit(k).toArray();

        for (int x : ints) {
            points.add(originalPoints.get(x));
        }

        return points;
    };

    public static void main(String[] args) {
        BufferedImage image = ImageFilesOperator.loadImage(INPUT_IMAGE_PATH);
        List<Point> points = extractsPoints(image);

        List<Point> clusteredPoints = new ImageClassifier().classify(points, NUMBER_OF_CLUSTERS, generateRandomPoints);

        overridePoints(clusteredPoints, image);
        ImageFilesOperator.saveImage(image, JPG_FORMAT, OUPUT_IMAGE_PATH);
    }

    private static void overridePoints(List<Point> clusteredPoints, BufferedImage image) {
        for (Point p : clusteredPoints) {
            PointRGB point = (PointRGB) p;
            image.setRGB(point.getImageX(), point.getImageY(), point.getRGB());
        }
    }

    private static List<Point> extractsPoints(BufferedImage image) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color c = new Color(image.getRGB(j, i));
                PointRGB point = new PointRGB(c.getRed(), c.getGreen(), c.getBlue());
                point.setImageX(j);
                point.setImageY(i);
                points.add(point);
            }
        }
        return points;
    }
}
