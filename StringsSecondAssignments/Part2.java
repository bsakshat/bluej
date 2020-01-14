
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int startIndex = 0;
        int count = 0;
        while (true) {
            int index = stringb.indexOf(stringa, startIndex);
            if (index == -1) {
                break;
            }
            startIndex = index + stringa.length();
            count = count + 1;
        }
        return count;
    }

    public void testHowMany() {
        int count = howMany("a", "aaaaaa");
        System.out.println("a " + "aaaaaa " + count);
        count = howMany("b", "aaaaaa");
        System.out.println("b " + "aaaaaa " + count);
        count = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println("GAA " + "ATGAACGAATTGAATC " + count); 
        count = howMany("AA", "ATAAAA");
        System.out.println("AA " + "ATAAAA " + count);
    }
    
    public static void main(String[] args) {
        Part2 pr = new Part2();
        pr.testHowMany();
    }
}
