package test.PreProcess;

import com.csvreader.CsvReader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test044 {
    public static void main(String[] args) throws Exception{
        File file = new File("H:\\安装资料\\任务\\任务\\jobs4.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter("H:\\安装资料\\任务\\任务\\zpsjj\\jobs4-processed.csv"));
        InputStream in = new FileInputStream(file);
        CsvReader cr = new CsvReader(in, Charset.forName("utf-8"));
        cr.readHeaders();
//        int num = 0;
        while (cr.readRecord()){
            if ("company_financing_stage".equals(cr.get(0))){
                continue;
            }


            String location = cr.get(2);
            if(!("".equals(location))){
                location=location.split("：")[1];
            }

            String s1=cr.get(5);
//            System.out.println(s1);
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(s1);
            s1 = m.replaceAll("");

            String s0 = cr.get(1);
            m = p.matcher(s0);
            s0 = m.replaceAll("");
            int people_min = 0;
            int people_max = 0;


            String people = cr.get(6);
            if (people.contains("-")){
                people_min=Integer.parseInt(people.split("-")[0]);
                people_max=Integer.parseInt(people.split("-")[1].split("人")[0]);
            }else if ("".equals(people)){

            } else if (people.contains("以上")){
                people_min= Integer.parseInt(people.split("人")[0]);
                people_max = -1;
            }
            else
            {
                people_max= Integer.parseInt(people.split("于")[1].split("人")[0]);
                people_min = -1;
            }
            int experience_max = 0;
            int experience_min = 0;
            String experience = cr.get(8);
            if ("".equals(experience)){

            }
            else if ("无工作经验".equals(experience)){
                experience_max = 0;
                experience_min = 0;
            } else if (experience.contains("-")){
                experience_min = Integer.parseInt(experience.split("-")[0]);
                experience_max = Integer.parseInt(experience.split("-")[1].split("年")[0]);
            }
            else {
                String min_year = experience.split("年")[0];
                experience_min=Integer.parseInt(min_year);
                experience_max=-1;
            }
            String salary = cr.get(11);
            double salary_min = 0;
            double salary_max = 0;
            if ("".equals(salary)){
                salary_min=-1;
                salary_max=-1;
            }else if (salary.contains("-")){
                if (salary.contains("万")){
                    salary_min = Double.parseDouble(salary.split("-")[0]) * 10;
                    salary_max = Double.parseDouble(salary.split("-")[1].split("万")[0]) * 10;
                }else if (salary.contains("千")){
                    salary_min = Double.parseDouble(salary.split("-")[0]);
                    salary_max = Double.parseDouble(salary.split("-")[1].split("千")[0]);
                }
            }else if (salary.contains("月")){
                if (salary.contains("万")){
                    if (salary.contains("以上")){
                        salary_min = Double.parseDouble(salary.split("万")[0])*10;
                        salary_max = -1;
                    }
                    if (salary.contains("以下")){
                        salary_min = -1;
                        salary_max = Double.parseDouble(salary.split("万")[0])*10;
                    }
                } else if (salary.contains("千")){
                    if (salary.contains("以上")){
                        salary_min = Double.parseDouble(salary.split("千")[0]);
                        salary_max = -1;
                    }
                    if (salary.contains("以下")){
                        salary_min = -1;
                        salary_max = Double.parseDouble(salary.split("千")[0]);
                    }
                }

            }else if (salary.contains("年")){
                if (salary.contains("万")){
                    if (salary.contains("以上")){
                        salary_min = Double.parseDouble(salary.split("万")[0])*10/12;
                        salary_max = -1;
                    }
                    if (salary.contains("以下")){
                        salary_min = -1;
                        salary_max = Double.parseDouble(salary.split("万")[0])*10/12;
                    }
                } else if (salary.contains("千")){
                    if (salary.contains("以上")){
                        salary_min = Double.parseDouble(salary.split("千")[0])/12;
                        salary_max = -1;
                    }
                    if (salary.contains("以下")){
                        salary_min = -1;
                        salary_max = Double.parseDouble(salary.split("千")[0])/12;
                    }
                }
            }


            String s2=cr.get(9);
            p = Pattern.compile("\\s*|\t|\r|\n");
            m = p.matcher(s2);
            s2 = m.replaceAll("");
//            System.out.println(cr.getColumnCount());
            String line = cr.get(0)+","+s0+","+location+","+cr.get(3)+","+cr.get(4)+","+s1+","+people_min+","+people_max+","+cr.get(7)+","+experience_min+","+experience_max+","+s2+","+cr.get(10)+","+String.format("%.2f",salary_min)+","+String.format("%.2f",salary_max)+","+cr.get(12)+","+cr.get(13);
            bw.write(line);
            bw.newLine();
                        System.out.println(line);
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
        bw.close();
    }
}
