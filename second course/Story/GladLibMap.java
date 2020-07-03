package Story;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> wordsList;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategories;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        wordsList = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        wordsList = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", 
                                "time", "verb", "fruit"};
        ArrayList<String> wordList;
        for (String s : categories) {
            wordList = readIt(source+"/" + s + ".txt");
            wordsList.put(s, wordList);
        }        
        usedList = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (wordsList.containsKey(label)) {
            if (!usedCategories.contains(label)) {
                usedCategories.add(label);
            }
            return randomFrom(wordsList.get(label));
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedList.contains(sub)) {
            sub = getSubstitute(w.substring(first+1,last));
        }        
        usedList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap() {
        int count = 0;
        for (String s : wordsList.keySet()) {
            count += wordsList.get(s).size();
        }
        return count;
    }
    
    private int totalWordsConsidered() {
        int count = 0;
        for (String s : usedCategories) {
            count += wordsList.get(s).size();
        }
        return count;
    }
    
    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nNo. of words replaced: " + Integer.toString(usedList.size()));
        System.out.println("Total no. of words: " + Integer.toString(totalWordsInMap()));
        System.out.println("Total no. of words considered: " + Integer.toString(totalWordsConsidered()));
    }
    


}
