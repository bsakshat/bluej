import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for (Point pt : s.getPoints()) {
            count = count + 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double avg = getPerimeter(s) / getNumPoints(s);
        return avg;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double max = 0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if (currDist > max) {
                max = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return max;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double max = s.getLastPoint().getX();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double x = currPt.getX();
            // Update totalPerim by currDist
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double max = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double p = getPerimeter(s);
            if (p > max) {
                max = p;
            }
        }
        return max;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double max = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double p = getPerimeter(s);
            if (p > max) {
                max = p;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int count = getNumPoints(s);
        System.out.println("no. of points: " + count);
        double avg = getAverageLength(s);
        System.out.println("Average: " + avg);
        double largest = getLargestSide(s);
        System.out.println("Largest Side: " + largest);
        double largest_x = getLargestX(s);
        System.out.println("Largest X: " + largest_x);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largest = getLargestPerimeterMultipleFiles();
        System.out.println("Largest: " + largest);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String large_f = getFileWithLargestPerimeter();
        System.out.println("Largest: " + large_f);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeterMultipleFiles();
    }
}
