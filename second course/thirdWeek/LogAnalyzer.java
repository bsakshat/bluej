package thirdWeek;


/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         WebLogParser wb = new WebLogParser();
         for (String s : fr.lines()) {
             LogEntry record = wb.parseEntry(s);
             records.add(record);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for (LogEntry lg : records) {
             String ip = lg.getIpAddress();
             if (!uniqueIPs.contains(ip)) {
                 uniqueIPs.add(ip);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry lg : records) {
            int code = lg.getStatusCode();
            if (code > num) {
                System.out.println(lg);
            }
         }
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
}
