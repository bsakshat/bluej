
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
   public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String dna1 = dna.toUpperCase();
        int s = dna1.indexOf(startCodon);
        if (s == -1) {
            return "";
        }
        int e = dna1.indexOf(stopCodon, s + 3);
        if (e == -1) {
            return "";
        }
        if ( (e + 3 - s) % 3 == 0) {
            return dna.substring(s, e + 3);
        }
        else {
            return "";
        }
   }    
   public void testSimpleGene() {
        String dna = "cgattctggcgagttgaagtttactggga";
        System.out.println("String: " + dna);
        String gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene: " + gene);
        
        dna = "atggatcctccatatacaacggtatctccacctcaggtttagat";
        System.out.println("String: " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene: " + gene);
        
        dna = "aaatcgttctttttattaa";
        System.out.println("String: " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene: " + gene);
        
        dna = "atgcaatcgttctttttattaa";
        System.out.println("String: " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene: " + gene);
        
        dna = "atggaaaatctgtaa".toUpperCase();
        System.out.println("String: " + dna);
        gene = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("Gene: " + gene);    
   }
   public static void main (String[] args) {
        Part2 pr = new Part2();
        pr.testSimpleGene();
   }
}
