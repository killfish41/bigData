/*
package test.PreProcess;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;

public class aaa {
    public static void main(String[] args) {
        String srcPath = "H:\\安装资料\\任务\\任务\\zpsjj\\jobs2.csv";
        String charset = "utf-8";
        try (CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(new File(srcPath)), charset))).build()) {
            Iterator<String[]> iterator = csvReader.iterator();
            while (iterator.hasNext()) {
                Arrays.stream(iterator.next()).forEach(System.out::print);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
*/
