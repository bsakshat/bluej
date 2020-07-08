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
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
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
         
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry lg : records) {
             String ip = lg.getIpAddress();
             if (!counts.containsKey(ip)) {
                 counts.put(ip, 1);
             }
             else {
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int max = 0;
         for (Integer i : counts.values()) {
             if (i > max) {
                 max = i;
                }
        }
        return max;
     }
     
     public ArrayList<String> IPsMostVisits(HashMap<String, Integer> counts) {
         int maxVisits = mostNumberVisitsByIP(counts);
         ArrayList<String> iPsMostVisits = new ArrayList<>();
         for (String s : counts.keySet()) {
             if (counts.get(s) == maxVisits) {
                 iPsMostVisits.add(s);
             }
            }
         return iPsMostVisits;
     }
        
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> iPDays = new HashMap<String, ArrayList<String>>();
         ArrayList<String> iPs;
         for (LogEntry lg : records) {
             String date = lg.getAccessTime().toString();
             date = date.substring(4, 10);
             if (!iPDays.containsKey(date)) {
                 iPs = new ArrayList<>();
                 iPs.add(lg.getIpAddress());
                 iPDays.put(date, iPs);
             }
             else {
                 ArrayList<String> newIPs = iPDays.get(date);
                 newIPs.add(lg.getIpAddress());
                 iPDays.put(date, newIPs);
             }
            }
         return iPDays;
     }
        
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPDays) {
         String maxDay = "";
         int max = 0;
         for (String s : iPDays.keySet()) {
             if (iPDays.get(s).size() > max) {
                 max = iPDays.get(s).size();
                 maxDay = s;
                }
            }
         return maxDay;
     }
        
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPDays, String date) {
         //ArrayList<String> mostVisitsIPs = new ArrayList<>();
         ArrayList<String> iPs = iPDays.get(date);
         HashMap<String, Integer> dayVisits = new HashMap<>();
         for (String s : iPs) {
             if (!dayVisits.containsKey(s)) {
                 dayVisits.put(s, 1);
                }
             else {
                 dayVisits.put(s, dayVisits.get(s) + 1);
                }
         }
         return IPsMostVisits(dayVisits);
     }
         
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
}
