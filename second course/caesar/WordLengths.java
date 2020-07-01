import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    private void countWordLengths (FileResource resource, int[] counts) {
        for (String s : resource.words()) {
            if (!Character.isLetter(s.charAt(0))) {
                s = s.substring(1);
            }
            if (!Character.isLetter(s.charAt(s.length() - 1))) {
                s = s.substring(0, s.length() - 1);
            }
            int sLength = s.length();
            if (sLength > 30) {
                counts[31]++;
            }
            else {
                counts[sLength]++;
            }
        }
    }
    
    private int indexOfMax(int[] values) {
        int largestIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[largestIndex]) {
                largestIndex = i;
            }
        }
        return largestIndex;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        int largestIndex = indexOfMax(counts);
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 0) continue;
            System.out.println("No. of " + Integer.toString(i) + " words " + counts[i]);
        }
        System.out.println("Max index: " + Integer.toString(largestIndex));
    }
}
