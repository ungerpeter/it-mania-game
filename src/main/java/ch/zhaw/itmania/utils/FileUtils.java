package ch.zhaw.itmania.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * ch.zhaw.itmania.utils
 * Created by Peter Unger on 12.12.2015.
 */
public class FileUtils {

    public static String loadFileAsString(String path) {
        StringBuilder strBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                strBuilder.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strBuilder.toString();
    }

    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
