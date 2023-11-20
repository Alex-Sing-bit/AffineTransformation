package ru.vsu.cs.baklanova.AffineTransformation;

public class TranslateException extends Exception {
    public TranslateException(String errorMessage) {
        super("Translation exception: " + errorMessage);
    }
}
