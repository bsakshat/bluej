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
         for (String s : fr.lines()) {
             LogEntry record = WebLogParser.parseEntry(s);
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
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
         for (LogEntry lg : records) {
             String date = lg.getAccessTime().toString();
             if (date.indexOf(someday) != -1) {
                 String ip = lg.getIpAddress();
                 if (!uniqueIPsOnDay.contains(ip)) {
                     uniqueIPsOnDay.add(ip);
                 }
             }
         }
         return uniqueIPsOnDay;
     }
     
     public int uniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPsOnRange = new ArrayList<String>();
         for (LogEntry lg : records) {
             int code = lg.getStatusCode();
             if (code >= low && code <= high) {
                 String ip = lg.getIpAddress();
                 if (!uniqueIPsOnRange.contains(ip)) {
                     uniqueIPsOnRange.add(ip);
                    }
                }
            }
         return uniqueIPsOnRange.size();
        }
         
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
}
