package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileConvertorToJson {

    private File file = new File("src/main/resources/file.txt");

    private List<UserDto> users = new ArrayList<>();

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

    private void getPeopleFromText() {
        if (this.file.exists()) {
            String text=convertToString();
            text=text.replaceAll("name"," ");
            text=text.replaceAll("age"," ");
            text=text.replaceAll("\n|\r"," ");
            String[] spiltedinfo = text.split("\s+");
            for (int i = spiltedinfo.length-1; i > 0; i-=2) {
                String name = spiltedinfo[i-1];
                int age = Integer.valueOf(spiltedinfo[i]);
                this.users.add(new UserDto(name, age));
            }

        }
    }

    public void convertToJson() {
        getPeopleFromText();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);
        try {
            OutputStream fos = new FileOutputStream("user.json");
            fos.write(json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
