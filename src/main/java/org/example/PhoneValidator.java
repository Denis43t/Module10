package org.example;

import java.io.*;
import java.util.Scanner;

public class PhoneValidator {
    private File file = new File("src/main/resources/phone.txt");
    private String pattern = "(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)[- .]?\\d{3}-?\\d{4}";

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

    public void validPhone() {
        if (this.file.exists()) {
            String text = convertToString();
            String[] lines=text.split("\n|\r");
            for (int i = 0; i <lines.length; i++) {
                if (lines[i].matches(this.pattern)) {
                    System.out.println("phone is valid : " + lines[i]);
                }
            }
        }
    }
}
