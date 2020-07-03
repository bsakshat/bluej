package Story;
import java.util.*;
import edu.duke.*;

/**
 * Write a description of codonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class codonCount {
    private HashMap<String, Integer> codonHash;
    
    public codonCount() {
        codonHash = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna) {
        codonHash.clear();
        for (int i = start; (i+3) <= dna.length(); i+=3) {
            String codon = dna.substring(i, i+3);
            if (!codonHash.containsKey(codon)) {
                codonHash.put(codon, 1);
            }
            else {
                codonHash.put(codon, codonHash.get(codon) + 1);
            }
        }
    }
    
    private String getMostCommonCodon() {
        int max = 0;
        String common = "";
        for (String s : codonHash.keySet()) {
            if (codonHash.get(s) > max) {
                max = codonHash.get(s);
                common = s;
            }
        }
        return common;
    }
    
    private void printCodonCounts(int start, int end) {
        for (String s : codonHash.keySet()) {
            int codonCount = codonHash.get(s);
            if (codonCount >= start && codonCount <= end) {
                System.out.println(s + " " + Integer.toString(codonCount) + " times.");
            }
        }
    }
    
    public void testCodonCount() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        for (int i = 0; i < 3; i++) {
            System.out.println("\nFrame" + Integer.toString(i));
            buildCodonMap(i, dna);
            String common = getMostCommonCodon();
            printCodonCounts(1, 10);
            System.out.println("No. of unique codons: " + Integer.toString(codonHash.size()));
            System.out.println("Most common codon: " + common + " " + codonHash.get(common));
        }
    }
}
