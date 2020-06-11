import edu.duke.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder encrypted = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(phrase.charAt(i))) {
                encrypted.setCharAt(i, ch);
            }
        }
        return encrypted.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder encrypted = new StringBuilder(phrase);
        int index = 0;
        while (true) {
            index = phrase.indexOf(ch, index);
            if (index == -1) {
                break;
            }
            if (index % 2 == 0) {
                encrypted.setCharAt(index, '*');
            }
            else{
                encrypted.setCharAt(index, '+');
            }
            index++;
        }
        return encrypted.toString();
    }
}
