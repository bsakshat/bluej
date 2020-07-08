package thirdWeek;


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
public class Tester
{
    private String filename;
    
    public Tester() {
        filename = "thirdWeek/weblog2_log";
    }
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        lg.printAll();
    }
    
    public void testCountUniqueIPs() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        System.out.println(Integer.toString(lg.countUniqueIPs()));
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        lg.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        ArrayList<String> uniqueIPsOnDay = lg.uniqueIPVisitsOnDay("Sep 24");
        for (String s : uniqueIPsOnDay) {
            System.out.println(s);
        }
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        HashMap<String, Integer> counts = lg.countVisitsPerIP();
        for (String s : counts.keySet()) {
            System.out.println(s + " = " + Integer.toString(counts.get(s)));
        }
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        HashMap<String, Integer> counts = lg.countVisitsPerIP();
        int mostNumber = lg.mostNumberVisitsByIP(counts);
        System.out.println("Most no. visits by IP = " + Integer.toString(mostNumber));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        HashMap<String, Integer> counts = lg.countVisitsPerIP();
        ArrayList<String> mostIPs = lg.IPsMostVisits(counts);
        for (String s : mostIPs) {
            System.out.println(s);
        }
    }
    
    public void testiPsForDays() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        HashMap<String, ArrayList<String>> mostDays = lg.iPsForDays();
        for (String s : mostDays.keySet()) {
            System.out.println(s + " " + Integer.toString(mostDays.get(s).size()) + " times");
        }
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        HashMap<String, ArrayList<String>> mostDays = lg.iPsForDays();
        String date = lg.dayWithMostIPVisits(mostDays);
        System.out.println(date);
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        HashMap<String, ArrayList<String>> mostDays = lg.iPsForDays();
        ArrayList<String> mostIPs = lg.iPsWithMostVisitsOnDay(mostDays, "Sep 30");
        for (String s : mostIPs) {
            System.out.println(s);
        }
    }
    
    public void testUniqueIPsInRange() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile(filename);
        System.out.println(Integer.toString(lg.uniqueIPsInRange(200, 299)));
    }
}
