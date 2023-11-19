package ru.vsu.cs.baklanova.AffineTransformation;

public class AffineTransformationExceptions extends NullPointerException {
        public AffineTransformationExceptions(String errorMessage) {
            super(": " + errorMessage);
        }
}

