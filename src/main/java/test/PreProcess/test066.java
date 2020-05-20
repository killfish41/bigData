package test.PreProcess;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test066 {
    public static void main(String[] args) throws Exception{
        File file = new File("H:\\安装资料\\任务\\任务\\zpsjj\\jobs6.csv");
        String fileout = "H:\\安装资料\\任务\\任务\\zpsjj\\jobs6-processed.csv";
        CsvWriter cw = new CsvWriter(fileout, ',',Charset.forName("utf-8"));
//        BufferedWriter bw = new BufferedWriter(new FileWriter("H:\\安装资料\\任务\\任务\\zpsjj\\jobs5-processed.csv"));
        InputStream in = new FileInputStream(file);
        CsvReader cr = new CsvReader(in, Charset.forName("utf-8"));
        cr.readHeaders();
//        int num = 0;
        while (cr.readRecord()){
            if ("company_financing_stage".equals(cr.get(0))){
                continue;
            }


            String location = cr.get(2);
            if(location.contains("：")){
                location=location.split("：")[1];
            }

            String s1=cr.get(5);
//            System.out.println(s1);
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(s1);
            s1 = m.replaceAll("");
            s1 = s1.replace(",","，");
            String s0 = cr.get(1);
            m = p.matcher(s0);
            s0 = m.replaceAll("");
            s0 = s0.replace(",","，");
            int people_min = 0;
            int people_max = 0;


            String people = cr.get(6);
            if(people.contains("：")){
                people=people.split("：")[1];
            }else if (people.contains("－")){
                people_min=Integer.parseInt(people.split("－")[0]);
                people_max=Integer.parseInt(people.split("－")[1].split("人")[0]);
            }else if ("".equals(people)){

            }
            else if (people.contains("以上"))
            {
                people_min= Integer.parseInt(people.split("人")[0]);
                people_max = -1;
            } else if (people.contains(("以下"))){
                people_min= 0;
                people_max = Integer.parseInt(people.split("人")[0]);
            }
            int experience_max = 0;
            int experience_min = 0;
            String experience = cr.get(8);
//            System.out.println(experience);
            if ("经验应届生".equals(experience)){
                experience_max = -1;
                experience_min = -1;
            } else if ("经验在读".equals(experience)){
                experience_max = -2;
                experience_min = -2;
            }
            else {
                experience_max = -1;
                experience_min = Integer.parseInt(experience.split("验")[1].split("年")[0]);
            }
            String salary = cr.get(11);
            double salary_min = 0;
            double salary_max = 0;
            if ("面议".equals(salary)){
                salary_min=-1;
                salary_max=-1;
            }else if (salary.contains("-")){
                salary_min = Integer.parseInt(salary.split("-")[0]) / 1000.0;
                salary_max = Integer.parseInt(salary.split("-")[1]) / 1000.0;
            }

            String s2=cr.get(9);
            p = Pattern.compile("\\s*|\t|\r|\n");
            m = p.matcher(s2);
            s2 = m.replaceAll("");
            s2 = s2.replace(",","，");
//            System.out.println(cr.getColumnCount());
            String line = cr.get(0)+","+s0+","+location+","+cr.get(3)+","+cr.get(4)+","+s1+","+people_min+","+people_max+","+cr.get(7)+","+experience_min+","+experience_max+","+s2+","+cr.get(10)+","+String.format("%.2f",salary_min)+","+String.format("%.2f",salary_max)+","+cr.get(12)+","+cr.get(13);
//            bw.write(line);
//            bw.newLine();
            String wea = cr.get(13);
            wea = wea.replace(",","，");
            String[] l = {cr.get(0),s0,location,cr.get(3),cr.get(4),s1, String.valueOf(people_min), String.valueOf(people_max),cr.get(7), String.valueOf(experience_min), String.valueOf(experience_max),s2,cr.get(10),String.format("%.2f",salary_min),String.format("%.2f",salary_max),cr.get(12)+","+wea};
            System.out.println(line);
            cw.writeRecord(l);
//            System.out.println(++num);
//            System.out.println(s1+"*****"+s2);
//            System.out.println("-------------");
        }
        /*int a = 0;
        System.out.println(cr.getHeader(12));
        System.out.println(Arrays.toString(cr.getHeaders()));
        cr.readRecord();
        String rawRecord = cr.getRawRecord();
        int columnCount = cr.getColumnCount();
        System.out.println(columnCount);*/
//        bw.close();
        cw.close();
        in.close();
    }
}
