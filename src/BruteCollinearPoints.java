import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;


public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)   {
        checkForNull(points);
        checkDuplicate(points);
        Point[] sortedPoints = points.clone();
        Merge.sort(sortedPoints);
        List<LineSegment> list = new LinkedList<>();

        for (int a = 0; a < points.length - 3; a++) {
            Point ptA = sortedPoints[a];

            for (int b = a + 1; b < points.length - 2; b++) {
                Point ptB = sortedPoints[b];
                double slopeAB = ptA.slopeTo(ptB);

                for (int c = b + 1; c < points.length - 1; c++) {
                    Point ptC = sortedPoints[c];
                    double slopeAC = ptA.slopeTo(ptC);
                    if (slopeAB == slopeAC) {

                        for (int d = c + 1; d < points.length; d++) {
                            Point ptD = sortedPoints[d];
                            double slopeAD = ptA.slopeTo(ptD);
                            if (slopeAB == slopeAD) {
                                list.add(new LineSegment(ptA, ptD));
                            }
                        }
                    }
                }
            }
        }
        lineSegments = list.toArray(new LineSegment[0]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return this.lineSegments;
    }

    private void checkForNull(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Got null instead expected array of points");
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Did not expect point be null");
            }
        }
    }

    private void checkDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate(s) found.");
            }
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            System.out.print(x);

            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
