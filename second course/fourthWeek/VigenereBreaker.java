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
            System.out.println(singleKey);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int[] key = tryKeyLength(message, 4, 'e');
        VigenereCipher vg = new VigenereCipher(key);
        String answer = vg.decrypt(message);
        System.out.println(answer);
    }
    
}
