package com.company;

public class IndefiniteTimeOfFinishExeption extends Exception {
    public IndefiniteTimeOfFinishExeption() {

    }

    public IndefiniteTimeOfFinishExeption(String message) {
        super("Error at finish time. " + message);
    }
}
