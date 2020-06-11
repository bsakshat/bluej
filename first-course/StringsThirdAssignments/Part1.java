import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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

    public double cgRatio(String dna) {
        int count = 0;        
        String dna1 = dna.toUpperCase();
        int startIndex = 0;
        int len = dna.length();
        for (int i = 0; i < len; i++) {
            if (dna1.charAt(i) == 'C' || dna1.charAt(i) == 'G') {
                count = count + 1;
            }
        }                
        double ratio = ((float)count) / len;
        return ratio;
    }

    public int countCTG(String dna) {
        String dna1 = dna.toUpperCase();
        int startIndex = 0;
        int count = 0;
        while (true) {
            int index = dna1.indexOf("CTG", startIndex);
            if (index == -1) {
                break;
            }
            count = count + 1;
            startIndex = index + 3;
        }
        return count;
    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return;
    }

    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource genes = new StorageResource();
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            genes.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return genes;
    }

    public void processGenes(StorageResource sr) {
        int count9 = 0;
        int countCG = 0;
        int longest = 0;
        int count60 = 0;
        for (String s: sr.data()) {
            if (s.length() > 9) {
                //System.out.println("Longer than 9:" + s);
                count9 = count9 + 1;
            }            
            if (cgRatio(s) > 0.35) {
                //System.out.println("CG higher than 0.35:" + s);
                countCG = countCG + 1;
            }
            if (s.length() > longest) {
                longest = s.length();
            }
            if (s.length() > 60) {
                //System.out.println("Longer than 60:" + s);
                count60 = count60 + 1;
            }         
        }
        System.out.println("No. of longer than 60: " + count60);
        System.out.println("No of CG higher than 0.35: " + countCG);
        System.out.println("Longest length: " + longest);
    }

    public void testGetAllGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();             
        StorageResource genes = getAllGenes(dna);
        for (String sg: genes.data()) {
            System.out.println("Gene: " + sg);
        }        
    }

    public void testProcessGenes() {
        StorageResource sr = new StorageResource();
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        sr = getAllGenes(dna);
        System.out.println("No. of genes: " + sr.size());
        processGenes(sr); 
        System.out.println("No. of CTG: " + countCTG(dna));
    }
    
    
    public static void main(String[] args) {
        Part1 pr = new Part1();
        pr.testProcessGenes();
    }
}
