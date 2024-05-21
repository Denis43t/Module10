package org.example;

import java.io.*;
import java.util.Scanner;

public class PhoneValidator {
    private File file = new File("src/main/resources/phone.txt");
    private String pattern = "(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)[- .]?\\d{3}-?\\d{4}";

    public void validPhone() {
        if (this.file.exists()) {
            try (InputStream fis = new FileInputStream(this.file);
                 Scanner scanner = new Scanner(fis)) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (line.matches(this.pattern)) {
                        System.out.println("phone is valid : " + line);
                    }
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
