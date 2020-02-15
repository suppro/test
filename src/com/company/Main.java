package com.company;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

class Results {
    private String FullName;
    private int Age;
    private int TimeOfFinish;

    public Results(String FullName, int Age, int TimeOfFinish){
        this.FullName = FullName;
        this.Age = Age;
        this.TimeOfFinish = TimeOfFinish;
    }
    public String getFullName() {
        return  FullName;
    }
    public int getAge() {
        return  Age;
    }
    public int getTimeOfFinish() {
        return  TimeOfFinish;
    }
}

public class Main {

    public static ArrayList<Results> ResultList(int numberOfRunners){
        ArrayList<String> listFile = new ArrayList<String>();
        ArrayList<Results> listResult = new ArrayList<Results>();
        listFile.removeAll(listFile);
        listResult.removeAll(listResult);
        int age; int finish; String fullName;
        try{
            FileInputStream fstream = new FileInputStream("/home/anon/IdeaProjects/test/src/com/company/names.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
                listFile.add(strLine);
            }
        }catch (IOException e) {
            System.out.println("Ошибка");
        }
        for (int i = 0; i < numberOfRunners; ++i)
        {
            age = rnd(18, 80);
            fullName = FullName(listFile);
            finish = FindTimeOfFinish(age);
            listResult.add(new Results(fullName, age, finish));
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
        int time = 0;
        if (age > 18 && age < 35){
            time = rnd(1680, 5400);
        }
        else if (age > 36 && age < 50){
            time = rnd(2000, 5400);
        }
        else if (age > 51 && age < 60){
            time = rnd(2500, 5400);
        }
        else if (age > 61 && age < 70){
            time = rnd(3000, 6400);
        }
        else if (age > 71 && age < 80){
            time = rnd(4000, 7000);
        }
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
        System.out.printf("%-25s%-11s%-25s%n","Имя спортсмена","Возраст","Результат");
        for (Results results : listResult) {
            String fullName = results.getFullName();
            int age = results.getAge();
            int finish = results.getTimeOfFinish();
            System.out.printf("%-25s%-11d%-25s%n", fullName, age, getTime(finish));
        }
    }

    public static void main(String[] args) {
        int numberOfRunners = rnd(10, 50);
        ArrayList<Results> listResult = ResultList(numberOfRunners);
	    ShowResult(listResult);
    }
}