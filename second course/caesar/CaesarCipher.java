import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            char newChar;
            if (Character.isLowerCase(currChar)) {
                newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
            }
            else if (Character.isUpperCase(currChar)) {
                newChar = shiftedAlphabet.charAt(idx);
            }
            else {
                newChar = currChar;
            }
            encrypted.setCharAt(i, newChar);
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String shiftedkey1 = encrypt(input, key1);
        String shiftedkey2 = encrypt(input, key2);
        for (int i = 0; i < input.length(); i++) {
            char newChar;
            if (i % 2 == 0) {
                newChar = shiftedkey1.charAt(i);
            }
            else {
                newChar = shiftedkey2.charAt(i);
            }
            encrypted.setCharAt(i, newChar);
        }
        return encrypted.toString();
    }
    
    public void testCaeser(String message, int key1, int key2) {
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        String decrypted = encrypt(message, 26-key1);
        System.out.println(decrypted);
    }
}
