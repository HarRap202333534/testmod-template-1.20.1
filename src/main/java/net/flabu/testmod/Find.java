package net.flabu.testmod;

import java.io.File;
import java.util.Scanner;

public class Find {
    public static void main(String[] args) throws Exception {
        File myFile = new File("src/main/resources/test2.json");
        System.out.println("Attempting to read from file in: "+myFile.getCanonicalPath());

        Scanner input = new Scanner(myFile);
        String in = "";
        in = input.nextLine();
        System.out.println(in);
    }
}
