
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences(String stringa, String stringb) {
        int count = 0;
        int index = stringb.indexOf(stringa);
        while (index != -1) {
            count = count + 1;
            index = stringb.indexOf(stringa, index + 1);
        }
        if (count >= 2) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String lastPart(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        if (index == -1) {
            return stringb;
        }
        else {
            return stringb.substring(index + stringa.length());
        }
    }
    
    public void testing() {
        boolean test;
        String part;
        test = twoOccurences("stringa", "string");
        System.out.println(test);
        test = twoOccurences("by", "by Abby Long");
        System.out.println(test);
        test = twoOccurences("a", "banana");
        System.out.println(test);
        test = twoOccurences("atg", "ctgtagta");
        System.out.println(test); 
        part = lastPart("an", "banana");
        System.out.println("an " + part + " banana");
        part = lastPart("zoo", "forest");
        System.out.println("zoo " + part + " forest");
    }
    
    public static void main(String[] args) {
        Part3 pr = new Part3();
        pr.testing();
    }
}
