package test.PreProcess;

import java.io.*;

public class test01 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader("H:\\安装资料\\任务\\任务\\zpsjj\\jobs1.csv"));
        String s=null;
        int num = 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter("H:\\安装资料\\任务\\任务\\zpsjj\\jobs1-processed.csv"));
        while ((s=bf.readLine())!=null){
            num++;
            if(s.startsWith("company_financing_stage")){
                System.out.println(s);
            }else {
                String[] strs = s.split(",");
                if (!strs[0].contains("人")){
                    String s1 = strs[6].split("人")[0];
                    int people_max = 0;
                    int people_min = 0;
                    if(s1.contains("-")){
                        String[] nums = s1.split("-");
                        people_min = Integer.parseInt(nums[0]);
                        people_max = Integer.parseInt(nums[1]);

                    }else {
                        people_min = Integer.parseInt(s1.split("人")[0]);
                        people_max = -1;
                    }

                    strs[7] = strs[7].split("：")[1];
                    String[] s2 = strs[8].split("：");
                    int experience_max = 0;
                    int experience_min = 0;
                    if(s2[1].contains("-")){
                        String[] experience = s2[1].split("-");
                        experience_min = Integer.parseInt(experience[0]);
                        experience_max = Integer.parseInt(experience[1].split("年")[0]);
                    }else if ("应届生".equals(s2[1])){
                        experience_min = -1;
                        experience_max = -1;
                    }else if (s2[1].contains("内")){
                        experience_max = 1;
                    }
                    int salary_min = 0;
                    int salary_max = 0;
                    String[] salaries = strs[11].split("-");
                    salary_min = Integer.parseInt(salaries[0].split("K")[0]);
                    salary_max = Integer.parseInt(salaries[1].split("K")[0]);
                    String line = strs[0]+","+strs[1]+","+strs[2]+","+strs[3]+","+strs[4]+","+strs[5]+","+people_min+","+people_max+","+strs[7]+","+experience_min+","+experience_max+","+strs[9]+","+strs[10]+","+salary_min+","+salary_max+","+strs[12];
                    bw.write(line);
                    bw.newLine();
                    System.out.println(line);
                }else {
                    String s1 = strs[0].split("人")[0];
                    int people_max = 0;
                    int people_min = 0;
                    if(s1.contains("-")){
                        String[] nums = s1.split("-");
                        people_min = Integer.parseInt(nums[0]);
                        people_max = Integer.parseInt(nums[1]);

                    }else {
                        people_max = Integer.parseInt(s1.split("人")[0]);
                    }

                    strs[7] = strs[7].split("：")[1];
                    String[] s2 = strs[8].split("：");
                    int experience_max = 0;
                    int experience_min = 0;
                    if(s2[1].contains("-")){
                        String[] experience = s2[1].split("-");
                        experience_min = Integer.parseInt(experience[0]);
                        experience_max = Integer.parseInt(experience[1].substring(0,1));
                    }else if ("应届生".equals(s2[1])){
                        experience_min = -1;
                        experience_max = -1;
                    }else if (s2[1].contains("内")){
                        experience_max = 1;
                    }
                    int salary_min = 0;
                    int salary_max = 0;
                    String[] salaries = strs[11].split("-");
                    salary_min = Integer.parseInt(salaries[0].split("K")[0]);
                    salary_max = Integer.parseInt(salaries[1].split("K")[0]);
                    String line = ""+","+strs[1]+","+strs[2]+","+strs[3]+","+strs[4]+","+strs[5]+","+people_min+","+people_max+","+strs[7]+","+experience_min+","+experience_max+","+strs[9]+","+strs[10]+","+salary_min+","+salary_max+","+strs[12];
                    bw.write(line);
                    bw.newLine();
                    System.out.println(line);
                }

            }

        }
        System.out.println(num);
        bf.close();
        bw.close();
    }
}
