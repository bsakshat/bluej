import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of MaxTemp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinTemp {
    public CSVRecord getlowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar,String metric) {
        if (lowestSoFar == null) {
                lowestSoFar = currentRow;
        }
        else {
                String curr = currentRow.get(metric);
                if (!curr.equals("N/A")){
                    double current = Double.parseDouble(currentRow.get(metric));
                    double lowest = Double.parseDouble(lowestSoFar.get(metric));                    
                    if (current == -9999) {
                        return lowestSoFar;
                    }
                    if (current < lowest) {
                        lowestSoFar = currentRow;
                    }
            }
        }
        return lowestSoFar;
    }
    
    public CSVRecord coldestHour(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            lowestSoFar = getlowestOfTwo(currentRow, lowestSoFar, "TemperatureF");
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestHumidity(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            lowestSoFar = getlowestOfTwo(currentRow, lowestSoFar, "Humidity");
        }
        return lowestSoFar;
    }

    public CSVRecord coldestInManyDays() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHour(fr.getCSVParser());
            lowestSoFar = getlowestOfTwo(current, lowestSoFar,"TemperatureF");
        }
        return lowestSoFar;
    }
    
    public CSVRecord lowestInManyDays() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidity(fr.getCSVParser());
            lowestSoFar = getlowestOfTwo(current, lowestSoFar,"Humidity");
        }
        return lowestSoFar;
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHour(fr.getCSVParser());
            lowestSoFar = getlowestOfTwo(current, lowestSoFar, "TemperatureF");
        }
        return lowestSoFar.get("DateUTC");
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        int count = 0;
        double sum = 0;
        for (CSVRecord record : parser) {
            if (!(record.get("TemperatureF").equals("-9999"))) {
                count = count + 1;
                sum = sum + Double.parseDouble(record.get("TemperatureF"));
            }
        }
        return (sum / count);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        int count = 0;
        for (CSVRecord record : parser) {
            String humidity = record.get("Humidity");
            String temp = record.get("TemperatureF");
            if (!humidity.equals("N/A") && !temp.equals("-9999")) {
                if (Integer.parseInt(humidity) >= value) {
                    count = count + 1;
                    sum = sum + Double.parseDouble(temp);
                }
            }
        }
        return (sum / count);
    }
    
    public void testColdestHour() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowest = coldestHour(parser);
        System.out.println("coldest temperature was " + lowest.get("TemperatureF") + " at " + lowest.get("DateUTC"));
    }
    
    public void testLowestHumidity() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowest = lowestHumidity(parser);
        System.out.println("lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public void testcoldestInManyDays() {
        CSVRecord lowest = coldestInManyDays();
        System.out.println("coldest temperature was " + lowest.get("TemperatureF") + " at " + lowest.get("DateUTC"));
    }
    
    public void testLowestInManyDays() {
        CSVRecord lowest = lowestInManyDays();
        System.out.println("lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature() {
        System.out.println("Coldest day was in file: " + fileWithColdestTemperature());
        //testColdestHour();
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in File is: " + average);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageHighHumidity = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (averageHighHumidity == 0.0) {
            System.out.println("No Files");
        }
        else {
            System.out.println("Average temp when high humidity: " + averageHighHumidity);
        }
    }
}
