package fourthWeek;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {    
    private String sliceString(String message, int whichSlice, int totalSlices) {
        String slice = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slice += message.charAt(i);
        }
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            int singleKey = cc.getKey(slice);
            key[i] = singleKey;
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String[] langs = {"English", "Danish", "Dutch", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        for (int i = 0; i < langs.length; i++) {
            FileResource df = new FileResource("fourthWeek/dictionaries/" + langs[i]);
            HashSet<String> dict = readDictionary(df);
            languages.put(langs[i], dict);
            System.out.println(langs[i] + " done.");
        }
        System.out.println();
        String answer = breakForAllLanguages(message, languages);
        System.out.println(answer);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<>();
        for (String s : fr.lines()) {
            dict.add(s.toLowerCase());
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dict) {
        int count = 0;
        for (String s : message.split("\\W+")) {
            if (dict.contains(s.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dict) {
        int max = 0;
        String original = null;
        int maxKey = 0;
        char mostCommon = mostCommonCharIn(dict);
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(encrypted, i , mostCommon);
            VigenereCipher vg = new VigenereCipher(key);
            String answer = vg.decrypt(encrypted);
            int count = countWords(answer, dict);
            if (count > max) {
                max = count;
                original = answer;
                maxKey = key.length;
            }
        }
        System.out.println("Key Length: " + Integer.toString(maxKey));
        System.out.println(max);
        return original;
    }        
    
    public char mostCommonCharIn(HashSet<String> dict) {
        HashMap<Character, Integer> alphabets = new HashMap<>();
        for (String s: dict) {
            for (int i = 0; i < s.length(); i++) {
                char c = Character.toLowerCase(s.charAt(i));
                if (!alphabets.containsKey(c)) {
                    alphabets.put(c, 1);
                }
                else {
                    alphabets.put(c, alphabets.get(c) + 1);
                }
            }
        }
        int max = 0;
        char maxLetter = ' ';
        for (char letter: alphabets.keySet()) {
            if (alphabets.get(letter) > max) {
                max = alphabets.get(letter);
                maxLetter = letter;
            }
        }
        return maxLetter;
    }
    
    public String breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages) {
        String maxLanguage = null;
        String original = null;
        int max = 0;
        for (String language : languages.keySet()) {     
            System.out.println(language);
            String answer = breakForLanguage(encrypted, languages.get(language));
            int count = countWords(answer, languages.get(language));
            if (count > max) {
                max = count;
                original = answer;
                maxLanguage = language;
            }
        }
        System.out.println("\n" + maxLanguage + "\n");    
        return original;
    }
}
