package ru.vsu.cs.baklanova.Affine.AffineBuilder;

import ru.vsu.cs.baklanova.Affine.AffineMatrix;
import ru.vsu.cs.baklanova.Affine.AxisEnum;
import ru.vsu.cs.baklanova.math.vector.Vector3D;

public class Scale {
    AffineBuilder builder;

    public Scale(AffineBuilder builder) {
        this.builder = builder;
    }

    public Scale byVector(Vector3D scaleVector) {
        builder.addScale(AffineMatrix.scaleMatrix(scaleVector));

        return this;
    }

    public Scale byAxis(AxisEnum axisEnum, double num) {
        switch (axisEnum) {
            case X -> byX(num);
            case Y -> byY(num);
            case Z -> byZ(num);
        }

        return this;
    }
    public Scale byX(double num) {
        byVector(new Vector3D(num, 1, 1));
        return this;
    }


    public Scale byY(double num) {
        byVector(new Vector3D(1, num, 1));
        return this;
    }


    public Scale byZ(double num) {
        byVector(new Vector3D(1, 1, num));
        return this;
    }

    public AffineBuilder close() {
        return builder;
    }
}
