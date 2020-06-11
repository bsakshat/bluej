import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyBirths {
    public void printNames (FileResource fr) {
        for (CSVRecord rec: fr.getCSVParser(false)) {
            System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) +
                " Num Born " + rec.get(2));
        }       
    }

    public int countNames (int year, String gender) {
        int totalBoys = 0;
        int totalGirls = 0;
        int girlNames = 0;
        int boyNames = 0;
        FileResource  fr = new FileResource("us_babynames_by_year\\yob" + Integer.toString(year)
        + ".csv");
        for (CSVRecord rec: fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                boyNames++;
            }
            else {
                totalGirls += numBorn;
                girlNames++;
            }
        }
        if (gender.equals("M")) {
            return boyNames;
        }
        else {
            return girlNames;
        }
            
    }

    public int getRank(int year, String name, String gender) {
        int rank = 0;
        boolean present = false;
        FileResource  fr = new FileResource("us_babynames_by_year\\yob" + Integer.toString(year)
        + ".csv");
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                    present = true;
                    break;
                }
            }
        }
        if (!present) {
            return -1;
        }
        return rank;
    }

    public String getName(int year, int rank, String gender) {
        int count = 0;
        String name = "";
        FileResource  fr = new FileResource("us_babynames_by_year\\yob" + Integer.toString(year)
        + ".csv");
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                count++;
                if (count == rank) {
                    name = rec.get(0);
                    break;
                }
            }
        }
        if (name == "") {
            return "NO NAME";
        }
        return name;
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = 0;
        int count = 0;
        String newName = "";
        boolean present = false;
        FileResource  fr = new FileResource("us_babynames_by_year\\yob" + Integer.toString(year) 
        + ".csv");
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                    present = true;
                    break;
                }
            }
        }
        FileResource  newFr = new FileResource("us_babynames_by_year\\yob"
        + Integer.toString(newYear) + ".csv");
        for (CSVRecord rec: newFr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                count++;
                if (count == rank) {
                    newName = rec.get(0);
                    break;
                }
            }
        }
        System.out.println(name + " born in " + Integer.toString(year) + " would be named " 
        + newName + " if born in " + Integer.toString(newYear));
    }
    
    public String yearOfHighestRank(String name, String gender) {
        int rank = 0;
        int maxRank = Integer.MAX_VALUE;
        String maxRankYear = "";
        boolean present = false;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            for (CSVRecord rec: fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    rank++;
                    if (rec.get(0).equals(name)) {
                        present = true;
                        if (rank < maxRank) {
                            maxRank = rank;
                            maxRankYear = f.getName();
                        }
                    }                
                }              
            }
            rank = 0;
            present = false;
        }
        return maxRankYear;
    }
    
    public double getAverageRank(String name, String gender) {
        int rank = 0;
        int total = 0;
        double count = 0;
        double average = 0;
        String maxRankYear = "";
        boolean present = false;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            for (CSVRecord rec: fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    rank++;
                    if (rec.get(0).equals(name)) {
                        present = true;
                        total += rank;
                        count++;
                    }                
                }              
            }
            if (!present) {
                return -1;
            }
            rank = 0;
            present = false;
        }
        average = total / count;
        return average;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int count = 0;
        boolean present = false;
        FileResource  fr = new FileResource();
        for (CSVRecord rec: fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) { 
                if (name.equals(rec.get(0))) {
                    present = true;
                    break;
                }
                count += Integer.parseInt(rec.get(2));
            }
        }
        if (!present) {
            return -1;
        }
        return count;
    }
    
    public void testPrintNames () {
        FileResource fr = new FileResource();
        printNames(fr);
    }
}
