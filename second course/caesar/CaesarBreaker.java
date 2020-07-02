import edu.duke.*;
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    private int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            Character ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex]++;
            }            
        }
        return counts;
    }
    
    private int maxIndex(int[] values) {
        int largestIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[largestIndex]) {
                largestIndex = i;
            }
        }
        return largestIndex;
    }
    
    private String halfOfString(String message, int start) {
        String half = "";
        for (int i = start; i < message.length(); i += 2) {
            half += message.charAt(i);
        }
        return half;
    }
    
    private int getKey(String s) {
        int[] counts = countLetters(s);
        int maxIn = maxIndex(counts);
        int dkey = maxIn - 4;
        if (maxIn < 4) {
            dkey = 26 - (4 - maxIn);
        }
        return dkey;
    }
    
    private String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        System.out.println("First Key: " + Integer.toString(firstKey));
        System.out.println("Second Key: " + Integer.toString(secondKey));
        return cc.encryptTwoKeys(encrypted, 26 - firstKey, 26 - secondKey);
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        String message = cc.encrypt(encrypted, 26 - dkey);
        return message;
    }
    
    public void testDecrypt() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println(decrypted);
    }
}
