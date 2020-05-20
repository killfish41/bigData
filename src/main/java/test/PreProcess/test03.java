package test.PreProcess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class test03 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader("H:\\安装资料\\任务\\任务\\zpsjj\\jobs3.csv"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("H:\\安装资料\\任务\\任务\\zpsjj\\jobs3-processed.csv"));
        String s = null;
        String tmp ="";
        int num = 0;
        boolean flag = false;
        while ((s=bf.readLine())!=null){
            if (s.startsWith("company_financing_stage")){
                continue;
            }
            if (s.split(",").length<14) {
                flag = true;
                tmp += s;
            }
//            System.out.println(s.split(",").length);
            if(s.split(",").length==14||tmp.split(",").length==14){
                if (flag&&tmp.split(",").length==14){
                    num++;
                    String[] strs = tmp.split(",");
                    System.out.println(strs.length);
                    String s1 = strs[6].split("人")[0];
                    int people_max = 0;
                    int people_min = 0;
                    if(s1.contains("-")){
                        String[] nums = s1.split("-");
                        people_min = Integer.parseInt(nums[0]);
                        people_max = Integer.parseInt(nums[1]);

                    } else if (s1.contains("少于")){
                        String nummm = s1.split("少于")[1].split("人")[0];
                        people_max = Integer.parseInt(nummm);
                    }
                    else {
                        people_min = Integer.parseInt(s1);
                        people_max = -1;
                    }


                    String s2 = strs[8];
                    int experience_max = 0;
                    int experience_min = 0;
                    if(s2.contains("-")){
                        String[] experience = s2.split("-");
                        experience_min = Integer.parseInt(experience[0]);
                        experience_max = Integer.parseInt(experience[1].substring(0,1));
                    }else if ("应届毕业生".equals(s2)){
                        experience_min = -1;
                        experience_max = -1;
                    }else if (s2.contains("内")){
                        experience_max = 1;
                    }
                    int salary_min = 0;
                    int salary_max = 0;
                    String[] salaries = strs[11].split("-");
                    if (strs[11].contains("以上")){
                        salary_min = Integer.parseInt(salaries[0].split("k")[0]);
                        salary_max = -1;
                    }else if(strs[11].contains("k")){
                        salary_min = Integer.parseInt(salaries[0].split("k")[0]);
                        salary_max = Integer.parseInt(salaries[1].split("k")[0]);
                    }else if (strs[11].contains("K")){
                        salary_min = Integer.parseInt(salaries[0].split("K")[0]);
                        salary_max = Integer.parseInt(salaries[1].split("K")[0]);
                    }

                    String line = strs[0]+","+strs[1]+","+strs[2]+","+strs[3]+","+strs[4]+","+strs[5]+","+people_min+","+people_max+","+strs[7]+","+experience_min+","+experience_max+","+strs[11]+","+strs[9]+","+strs[10]+","+salary_min+","+salary_max+","+strs[12]+","+strs[13];
//                    bw.write(line);
//                    bw.newLine();
                    System.out.println(line);
                    flag = false;
                    tmp = "";
                    if (s.split(",").length<14)
                        continue;
                }
                num++;
                String[] strs = s.split(",");
                String s1 = strs[6].split("人")[0];
                int people_max = 0;
                int people_min = 0;
                if(s1.contains("-")){
                    String[] nums = s1.split("-");
                    people_min = Integer.parseInt(nums[0]);
                    people_max = Integer.parseInt(nums[1]);

                } else if (s1.contains("少于")){
                    String nummm = s1.split("少于")[1].split("人")[0];
                    people_max = Integer.parseInt(nummm);
                }
                else {
                    people_min = Integer.parseInt(s1.split("人")[0]);
                    people_max = -1;
                }


                String s2 = strs[8];
                int experience_max = 0;
                int experience_min = 0;
                if(s2.contains("-")){
                    String[] experience = s2.split("-");
                    experience_min = Integer.parseInt(experience[0]);
                    experience_max = Integer.parseInt(experience[1].substring(0,1));
                }else if ("应届毕业生".equals(s2)){
                    experience_min = -1;
                    experience_max = -1;
                }else if (s2.contains("内")){
                    experience_max = 1;
                }
                int salary_min = 0;
                int salary_max = 0;
                String[] salaries = strs[11].split("-");
                if (strs[11].contains("以上")){
                    salary_min = Integer.parseInt(salaries[0].split("k")[0]);
                    salary_max = -1;
                }else if(strs[11].contains("k")){
                    salary_min = Integer.parseInt(salaries[0].split("k")[0]);
                    salary_max = Integer.parseInt(salaries[1].split("k")[0]);
                }else if (strs[11].contains("K")){
                    salary_min = Integer.parseInt(salaries[0].split("K")[0]);
                    salary_max = Integer.parseInt(salaries[1].split("K")[0]);
                }

                String line = strs[0]+","+strs[1]+","+strs[2]+","+strs[3]+","+strs[4]+","+strs[5]+","+people_min+","+people_max+","+strs[7]+","+experience_min+","+experience_max+","+strs[11]+","+strs[9]+","+strs[10]+","+salary_min+","+salary_max+","+strs[12]+","+strs[13];
//                bw.write(line);
//                bw.newLine();
                System.out.println(line);
            }
        }
        bf.close();
//        bw.close();
        System.out.println(num);

    }
}
