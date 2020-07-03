package Story;

import java.util.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> myChars;
    private ArrayList<Integer> myFreqs;
    
    public CharactersInPlay() {
        myChars = new ArrayList<String> ();
        myFreqs = new ArrayList<Integer> ();
    }
    
    private void update(String person) {
        int index = myChars.indexOf(person);
        if (index == -1) {
            myChars.add(person);
            myFreqs.add(1);
        }
        else {
            int value = myFreqs.get(index);
            myFreqs.set(index, value + 1);
        }
    }
    
    private void findAllCharacters() {
        myChars.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        String name = "";
        for (String line : fr.lines()){
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '.') {
                    name = line.substring(0, i);
                    update(name);
                }
            }
        }
    }
    
    private void charactersWithNumParts(int num1, int num2) {
        if (num1 > num2) {
            System.out.println("Invalid parameters");
            return;
        }
        for (int index = 0; index < myChars.size(); index++) {
            if (myFreqs.get(index) >= num1 && myFreqs.get(index) <= num2) {
                System.out.println("Character is " + myChars.get(index) + " with number parts");
            }
        }
    }

    public void tester() {
        findAllCharacters();
        for (int index = 0; index < myChars.size(); index++) {
            if (myFreqs.get(index) > 2) {
                System.out.println("Main character is " + myChars.get(index) + " " + 
                Integer.toString(myFreqs.get(index)) + " parts.");
            }
        }
        charactersWithNumParts(10, 15);
    }
}
