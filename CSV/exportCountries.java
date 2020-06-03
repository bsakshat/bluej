import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of exportCountries here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class exportCountries {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String cInfo = countryInfo(parser, "Nauru");
        System.out.println(cInfo);
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        int numberGold = numberOfExporters(parser, "cocoa");
        System.out.println("Cocoa Number: " + numberGold);
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String name = record.get("Country");
            if (name.equals(country)) {
                String info = (record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
                return info;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2) ) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count = count + 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (amount.length() < value.length()) {
                System.out.println(record.get("Country") + " " + value);
            }
        }        
    }
}
