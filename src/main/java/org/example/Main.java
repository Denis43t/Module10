package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        PhoneValidator phoneValidator=new PhoneValidator();
//        phoneValidator.validPhone();
//        WordCounter wordCounter=new WordCounter();
//        wordCounter.countWord();
        FileConvertorToJson fileConvertorToJson=new FileConvertorToJson();
        fileConvertorToJson.convertToJson();
    }
}