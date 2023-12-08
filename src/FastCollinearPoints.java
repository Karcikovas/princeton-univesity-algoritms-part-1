import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;


    public FastCollinearPoints(Point[] points){
        checkForNull(points);
        checkDuplicate(points);
        Point[] sortedPoints = points.clone();
        MergeX.sort(sortedPoints);
        List<LineSegment> list = new LinkedList<>();

        for (int p = 0 ; p < points.length ; p++) {
            Point point = sortedPoints[p];
            Point[] sortedBySlope = sortedPoints.clone();
            MergeX.sort(sortedBySlope, point.slopeOrder());

            for(int i = 1; i < points.length;) {
                LinkedList<Point> segments = new LinkedList<>();
                final double slopeRef = point.slopeTo(sortedBySlope[i]);

                do {
                    segments.add(sortedBySlope[i++]);
                } while (i < points.length && point.slopeTo(sortedBySlope[i]) == slopeRef);


                if (segments.size() >= 3 && point.compareTo(segments.peek()) < 0) {
                    Point min = point;
                    Point max = segments.removeLast();
                    list.add(new LineSegment(min, max));
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
