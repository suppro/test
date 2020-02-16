package com.company;

import java.io.*;
import java.util.ArrayList;

import static com.company.Results.COMPARE_BY_RESULT;

public class Main {

    public static ArrayList<String> FileList() {
        ArrayList<String> listFile = new ArrayList<String>();
        try {
            FileInputStream fstream = new FileInputStream("/home/anon/IdeaProjects/test/src/com/company/names.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                listFile.add(strLine);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
        return listFile;
    }

    public static ArrayList<Results> ResultList(ArrayList<String> listFile, int numberOfRunners) {
        ArrayList<Results> listResult = new ArrayList<Results>();
        int age; int finish; String fullName;
        try{
            for (int i = 0; i < numberOfRunners; ++i)
            {
                age = rnd(18, 80);
                fullName = FullName(listFile);
                finish = FindTimeOfFinish(age);
                if(finish <= 0)
                    throw new IndefiniteTimeOfFinishExeption();
                listResult.add(new Results(fullName, age, finish));
            }
        }catch (IndefiniteTimeOfFinishExeption e) {
            System.out.println("Некоректно сгенерирован результат");
        }
        return listResult;
    }
    public static String FullName(ArrayList<String> listFile){
        String fullName = "";
        int skip = rnd(0, listFile.size());
        fullName = listFile.get(skip);
        return fullName;
    }
    public static int FindTimeOfFinish(int age){
        int time;
        if (age >= 18 && age <= 35)
            time = rnd(1680, 5400);
        else if (age > 35 && age <= 50)
            time = rnd(2000, 5400);
        else if (age > 50 && age <= 60)
            time = rnd(2500, 5400);
        else if (age > 60 && age <= 70)
            time = rnd(3000, 6400);
        else if (age > 70 && age <= 80)
            time = rnd(4000, 7000);
        else
            time = 0;
        return time;
    }

    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static String getTime(int s){
        int hours = s / 3600;
        int minutes = (s % 3600) / 60;
        int seconds = s % 60;
        return  hours + ":" + minutes + ":" + seconds;
    }

    public static void ShowResult(ArrayList<Results> listResult){
        listResult.sort(COMPARE_BY_RESULT);
        System.out.printf("%-25s%-11s%-25s%n","Имя спортсмена","Возраст","Результат");
        for (Results results : listResult) {
            String fullName = results.getFullName();
            int age = results.getAge();
            int finish = results.getTimeOfFinish();
            System.out.printf("%-25s%-11d%-25s%n", fullName, age, getTime(finish));
        }
    }

    public static void main(String[] args) {
            int numberOfRunners = rnd(50, 100);
            ArrayList<String> listFile = FileList();
            ArrayList<Results> listResult = ResultList(listFile, numberOfRunners);
            ShowResult(listResult);
            int countFile = listFile.size();
            int count = listResult.size();
            System.out.println("Кол-во строк в результатах " + count);
            System.out.println("Кол-во спортсменов " + numberOfRunners + ", кол-во строк в списке файла " + countFile);
    }
}