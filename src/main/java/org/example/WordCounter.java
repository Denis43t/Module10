package org.example;

import java.io.*;
import java.util.*;

public class WordCounter {
    private HashMap<String, Integer> countedWords = new HashMap();
    private File file = new File("src/main/resources/words.txt");

    Comparator comparator = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return countedWords.get(o1).compareTo(countedWords.get(o2)) > 0 ? 1 : -1;
        }
    };

    private String convertToString() {
        String text = "";
        try (InputStream fis = new FileInputStream(this.file);) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            for (int i = 0; i < buffer.length; i++) {
                text += (char) buffer[i];
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    public void countWord() {
        if (this.file.exists()) {
            int index = 0;
            String[] splitedWords = convertToString().split("\s+");
            while (splitedWords.length > index) {
                String word = splitedWords[index];
                if (countedWords.containsKey(word)) {
                    int nummberOfWords = countedWords.get(word);
                    countedWords.put(splitedWords[index], nummberOfWords + 1);
                } else {
                    countedWords.put(word, 1);
                }
                index++;
            }
        }
        Map<String, Integer> result = new TreeMap<>(comparator.reversed());
        result.putAll(countedWords);
        System.out.println(result);
    }
}
