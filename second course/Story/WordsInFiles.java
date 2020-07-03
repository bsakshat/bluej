package Story;
import java.util.*;
import edu.duke.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsList;
    
    public WordsInFiles() {
        wordsList = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        Scanner inputFile = null;
        ArrayList<String> wordList;
        String word;
        try {
            inputFile = new Scanner(f);
        }
        catch (Exception e) {
            System.out.println("File not found");
            return;
        }
        while (inputFile.hasNext()) {
            word = inputFile.next();
            if (!wordsList.containsKey(word)) {
                wordList = new ArrayList<String>();
                wordsList.put(word, wordList);
                wordsList.get(word).add(f.getName());
            }
            else {
                if (!wordsList.get(word).contains(f.getName())) {
                    wordsList.get(word).add(f.getName());
                }
            }
        }        
    }
    
    private void buildWordFileMap() {
        wordsList.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int max = 0;
        for (String s : wordsList.keySet()) {
            int count = wordsList.get(s).size();
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> countWords = new ArrayList<String>();
        int count = 0;
        for (String s : wordsList.keySet()) {
            count = wordsList.get(s).size();
            if (count == number) {
                countWords.add(s);
            }
        }
        return countWords;
    }
    
    private void printFilesIn(String word) {
        for (String s : wordsList.get(word)) {
            System.out.println(s);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int count = 0;
        int max = maxNumber();
        ArrayList<String> maxCountWords = wordsInNumFiles(max);
        for (String s : maxCountWords) {
            System.out.println("Word in max files: " + s);
            //printFilesIn(s);
            count++;
        }
        printFilesIn("laid");
        System.out.println("Total no. of words in files is " + Integer.toString(wordsList.size()));
        System.out.println("No. of words in max files is " + Integer.toString(count)); 
        System.out.println("Max no. of files any word is in " + Integer.toString(max));        
    }
}
