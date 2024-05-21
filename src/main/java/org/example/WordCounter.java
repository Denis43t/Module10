package org.example;

import java.io.*;
import java.util.*;

public class WordCounter {
    private HashMap<String, Integer> countedWords = new HashMap();
    private File file = new File("src/main/resources/words.txt");

    Comparator comparator=new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return countedWords.get(o1).compareTo(countedWords.get(o2) > 0 ? 1 : -1);
        }
    };

    public void countWord() {
        if (this.file.exists()) {
            try (InputStream fis = new FileInputStream(this.file);
                 Scanner scanner = new Scanner(fis)) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    int index = 0;
                    String[] splitedwords = line.split("\s");
                    while (splitedwords.length>index) {
                        String word = splitedwords[index];
                        if (countedWords.containsKey(word)) {
                            int nummberofwords = countedWords.get(word);
                            countedWords.put(splitedwords[index], nummberofwords + 1);
                        } else {
                            countedWords.put(word, 1);
                        }
                        index++;
                    }
                }
                Map<String,Integer> result=new TreeMap<>(comparator.reversed());
                result.putAll(countedWords);
                System.out.println(result);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
