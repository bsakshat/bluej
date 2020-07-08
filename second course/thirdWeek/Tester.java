package thirdWeek;


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("thirdWeek/short-test_log");
        lg.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("thirdWeek/weblog1_log");
        lg.countUniqueIPs();
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("thirdWeek/weblog1_log");
        lg.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("thirdWeek/weblog1_log");
        ArrayList<String> uniqueIPsOnDay = lg.uniqueIPVisitsOnDay("Mar 17");
        for (String s : uniqueIPsOnDay) {
            System.out.println(s);
        }
    }
    
    public void testUniqueIPsInRange() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile("thirdWeek/weblog1_log");
        System.out.println(Integer.toString(lg.uniqueIPsInRange(300, 399)));
    }
}
