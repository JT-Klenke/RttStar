public class Polygon {
    double[][] outline;
    double[] lowerLeft;
    double[] upperRight;
    Polygon(double[][] points){
        outline = new double[points.length+1][2];
        lowerLeft = points[0];
        upperRight = points[0];
        int i = 0;
        for (double[] point : points){
            outline[i++] = point;
            upperRight[0] = Math.max(upperRight[0], point[0]);
            upperRight[1] = Math.max(upperRight[1], point[1]);
            lowerLeft[0] = Math.min(lowerLeft[0], point[0]);
            lowerLeft[1] = Math.min(lowerLeft[1], point[1]);
        }
        outline[i] = points[0];
    }
    private boolean doesLineIntersect(double[][] line){
        for (int i = 0; i < outline.length-1; i++){
            double[][] polyLine = {outline[i], outline[i+1]};
            if (lineIntersect(line, polyLine)) return true;
        }
        return false;
    }

    private boolean doesPointIntersect(double[] point){
        if (point[0] > upperRight[0] || point[1] > upperRight[1] || point[0] < lowerLeft[0] || point[1] < lowerLeft[1]){
            return false;
        }

        return true;
    }

    /** takes 3 co-linear points p, q, r
     * returns true if q is on the line from p to r
     */
    private boolean onSegment(double[] p, double[] q, double[] r)
    {
        return q[0] <= Math.max(p[0], r[0]) && q[0] >= Math.min(p[0], r[0]) &&
                q[1] <= Math.max(p[1], r[1]) && q[1] >= Math.min(p[1], r[1]);
    }

    /** takes points p, q, r
     * returns:
     * 0: p, q and r are collinear
     * +1: Clockwise
     * -1: Counterclockwise
     */
   private int orientation(double[] p, double[] q, double[] r)
    {
        double o = (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
        if (o == 0) return 0;
        if (o < 0) return -1;
        return 1;
    }

    /** returns true if line1 and line2 intersect
     */
    private boolean lineIntersect(double[][] line1, double[][] line2)//Point p1, Point q1, Point p2, Point q2)
    {
        int o1 = orientation(line1[0], line2[0], line1[1]);
        int o2 = orientation(line1[0], line2[0], line2[1]);
        int o3 = orientation(line1[1], line2[1], line1[0]);
        int o4 = orientation(line1[1], line2[1], line2[0]);

        if (o1 != o2 && o3 != o4)
            return true;

        if (o1 == 0 && onSegment(line1[0], line1[1], line2[0])) return true;
        if (o2 == 0 && onSegment(line1[0], line2[1], line2[0])) return true;
        if (o3 == 0 && onSegment(line1[1], line1[0], line2[1])) return true;
        return o4 == 0 && onSegment(line1[0], line2[0], line2[1]);
    }

}
