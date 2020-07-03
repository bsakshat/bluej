import java.util.*;
import edu.duke.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String> ();
        myFreqs = new ArrayList<Integer> ();
    }
    
    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String s : fr.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }
    
    private int findIndexOfMax() {
        int maxIndex = 0;
        int max = myFreqs.get(0);
        for (int i = 1; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > max) {
                maxIndex = i;
                max = myFreqs.get(i);
            }
        }
        return maxIndex;
    }
        
    public void tester() {
        findUnique();
        int maxIndex = findIndexOfMax();
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(Integer.toString(myFreqs.get(i)) + " " + myWords.get(i));
        }
        System.out.println("Number of unique words: " + Integer.toString(myWords.size()));
        System.out.println("Most occuring word is " + myWords.get(maxIndex) + " " + Integer.toString(myFreqs.get(maxIndex)) + " times.");
    }
}
