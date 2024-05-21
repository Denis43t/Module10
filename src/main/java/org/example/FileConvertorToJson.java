package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileConvertorToJson {

    private File file = new File("src/main/resources/file.txt");

    private List<UserDto> users =new ArrayList<>();

    private void convertFromFile() {
        if (this.file.exists()) {
            try (InputStream fis = new FileInputStream(this.file);
                 Scanner scanner = new Scanner(fis)) {
                fis.skip(10);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] spiltedinfo=line.split("\s");
                    String name=spiltedinfo[0];
                    int age=Integer.valueOf(spiltedinfo[1]);
                    this.users.add(new UserDto(name,age));
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void convertToJson(){
        convertFromFile();
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json=gson.toJson(users);
        try {
            OutputStream fos=new FileOutputStream("user.json");
            fos.write(json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
