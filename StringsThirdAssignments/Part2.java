
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex;
        int start = startIndex;
        while (true) {
            stopIndex = dna.indexOf(stopCodon, start);
            if (stopIndex == -1){
                break;
            }
            if ((stopIndex - startIndex) % 3 == 0) {
                break;
            }
            start = stopIndex + 1;
        }
        if (stopIndex == -1){
                return dna.length();
        }
        else {            
            return stopIndex;
        }
    }

    public String findGene(String dna, int where) {
        String dna1 = dna.toUpperCase();
        int atgIndex = dna1.indexOf("ATG", where);
        if (atgIndex == -1) {
            return "";
        }
        //System.out.println("startIndex: " + atgIndex);
        int taaIndex = findStopCodon(dna1, atgIndex, "TAA");
        //System.out.println("taaIndex: " + taaIndex);
        int tagIndex = findStopCodon(dna1, atgIndex, "TAG");
        //System.out.println("tagIndex: " + tagIndex);
        int tgaIndex = findStopCodon(dna1, atgIndex, "TGA");        
        //System.out.println("tgaIndex: " + tgaIndex);
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
       
    public void testFindGene(){
        String s = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAGC";
        int startIndex = 0;
        while (true) {
            String gene = findGene(s, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            startIndex = s.indexOf(gene, startIndex) + gene.length(); 
        }
    }
    
    public static void main(String[] args) {
        Part2 pr = new Part2();
        pr.testFindGene();
    }
}
