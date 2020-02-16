package com.company;

import java.io.Serializable;
import java.util.Comparator;

class Results implements Serializable {
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

    public static final Comparator<Results> COMPARE_BY_RESULT = new Comparator<Results>() {
        @Override
        public int compare(Results lhs, Results rhs) {
            return lhs.getTimeOfFinish() - rhs.getTimeOfFinish();
        }
    };
}
