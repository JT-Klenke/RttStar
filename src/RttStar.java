public class RttStar {
    public Point[] findPath(Point start, Point end){



        return null;
    }

    public void makeObstacle(Point[] obstacleOutline){

    }

    private Point shortenVector(Point origin, Point vector, double radius){
        double vectorLength = Math.hypot(vector.x-origin.x, vector.y-origin.y);
        double normalizedX = (vector.x-origin.x)/vectorLength;
        double normalizedY = (vector.y-origin.y)/vectorLength;
        return new Point(normalizedX*radius + origin.x,normalizedY*radius + origin.y);
    }


}
