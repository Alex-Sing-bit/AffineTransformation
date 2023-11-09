package ru.vsu.cs.baklanova.objrealisation;

public class PathReadException extends Exception {
    public PathReadException() {
        super("Error in file path, pls check it");
    }
}