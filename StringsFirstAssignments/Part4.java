import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public String findWebLinks(String s) {        
        String s1 = s.toLowerCase();
        int youtubeIndex = s1.indexOf("youtube.com");
        if (youtubeIndex == -1) {
            return "";
        }
        else {
            int startQuot = s1.lastIndexOf("\"", youtubeIndex);
            int endQuot = s1.indexOf("\"", youtubeIndex);
            return s.substring(startQuot, endQuot + 1);
        }        
    }
    
    public void testUrl(){
        String url = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        URLResource ur = new URLResource(url);
        for (String s : ur.lines()) {            
            String you = findWebLinks(s);
            if (!you.isEmpty()) {
                System.out.println(you);
            }
        }
    }
    
    public static void main (String[] args) {
        Part4 pr = new Part4();
        pr.testUrl();
    }
}

