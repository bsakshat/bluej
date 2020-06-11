import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of MaxTemp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MaxTemp {
    public CSVRecord hottestHour(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = hottestHour(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(current, largestSoFar);
        }
        return largestSoFar;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
                largestSoFar = currentRow;
        }
        else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
        }
        return largestSoFar;
    }

    public void testHottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }
}
