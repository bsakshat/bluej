
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int index = dna.indexOf(stopCodon, startIndex);
        if ((index - startIndex) % 3 == 0) {
            return index;
        }
        else {
            return dna.length();
        }
    }
    
    public String findGene(String dna) {
        String dna1 = dna.toUpperCase();
        int atgIndex = dna1.indexOf("ATG");
        if (atgIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna1, atgIndex, "TAA");
        int tagIndex = findStopCodon(dna1, atgIndex, "TAG");
        int tgaIndex = findStopCodon(dna1, atgIndex, "TGA");
        int min = taaIndex;
        if (tagIndex < min) {
            min = tagIndex;    
        }
        if (tgaIndex < min) {
            min = tgaIndex;
        }
        if (min == dna.length()) {
            return "";
        }
        else {
            return dna.substring(atgIndex, min + 3);
        }
    }
    
    public void testFindStopCodon() {
        int index; 
        index = findStopCodon("ATGATGATGATGATGTAA", 0, "TAA");
        System.out.println(index);
        index = findStopCodon("ATGATGATGATGATGTAA", 1, "TAA");
        System.out.println(index);
    }
    
    public void testFindGene() {
        String s = "ATATATATATATATATATAT";        
        String gene = findGene(s);
        System.out.println(s + " Gene: " + gene);
        s = "ATGATGATAGATAGATAGATAG";
        gene = findGene(s);
        System.out.println(s + " Gene: " + gene);
        s = "ATGATGATAGATAGATAAATAG";
        gene = findGene(s);
        System.out.println(s + " Gene: " + gene);
        s = "ATGATGATAGATAGATAGATAG";
        gene = findGene(s);
        System.out.println(s + " Gene: " + gene);
        s = "atgattgataattag";
        gene = findGene(s);
        System.out.println(s + " Gene: " + gene);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna.substring(startIndex));
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            startIndex = startIndex + gene.length();
        }
        return;
    }
    
    public static void main(String[] args) {
        Part1 pr = new Part1();
        pr.testFindGene();
    }
}
