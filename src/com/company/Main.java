package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public ArrayList<String> FileList() {
        ArrayList<String> listFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/home/anon/IdeaProjects/test/src/com/company/names.txt")))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                listFile.add(strLine);
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
        return listFile;
    }

    public ArrayList<Results> ResultList(ArrayList<String> listFile, int numberOfRunners) {
        ArrayList<Results> listResult = new ArrayList<>();
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
    public String FullName(ArrayList<String> listFile) {
        return listFile.get(rnd(0, listFile.size() - 1));
    }

    public int FindTimeOfFinish(int age){
        if (age >= 18 && age <= 35)
            return rnd(1680, 5400);
        else if (age > 35 && age <= 50)
            return rnd(2000, 5400);
        else if (age > 50 && age <= 60)
            return rnd(2500, 5400);
        else if (age > 60 && age <= 70)
            return rnd(3000, 6400);
        else if (age > 70 && age <= 80)
            return rnd(4000, 7000);
        else
            return 0;
    }

    public int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public String getTime(int s){
        int hours = s / 3600;
        int minutes = (s % 3600) / 60;
        int seconds = s % 60;
        String minText = Integer.toString(minutes);
        String secText = Integer.toString(seconds);
        if(minText.length() == 1 && secText.length() == 1)
            return "0" + hours + ":" + "0" + minutes + ":" + "0" + seconds;
        else if(minText.length() == 1 && secText.length() > 1)
            return "0" + hours + ":" + "0" + minutes + ":" + seconds;
        else if(minText.length() > 1 && secText.length() == 1)
            return "0" + hours + ":" + minutes + ":" + "0" + seconds;
        else
            return "0" + hours + ":" + minutes + ":" + seconds;
    }

    public void ShowResult(ArrayList<Results> listResult){
        listResult.sort(Results.COMPARE_BY_RESULT);
        System.out.printf("%-25s%-11s%-25s%n","Имя спортсмена","Возраст","Результат");
        for (Results results : listResult) {
            String fullName = results.getFullName();
            int age = results.getAge();
            int finish = results.getTimeOfFinish();
            System.out.printf("%-25s%-11d%-25s%n", fullName, age, getTime(finish));
        }
    }

    public static void main(String[] args) {
        Main base = new Main();
        int numberOfRunners = base.rnd(50, 100);
        ArrayList<String> listFile = base.FileList();
        ArrayList<Results> listResult = base.ResultList(listFile, numberOfRunners);
        base.ShowResult(listResult);
        int countFile = listFile.size();
        int count = listResult.size();
        System.out.println("Кол-во строк в результатах " + count);
        System.out.println("Кол-во спортсменов " + numberOfRunners + ", кол-во строк в списке файла " + countFile);
    }
}