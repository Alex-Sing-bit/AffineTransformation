package ru.vsu.cs.baklanova.Affine;

import ru.vsu.cs.baklanova.math.vector.Vector3D;

public interface Builder {
    Builder scale(Vector3D scaleVector);

    Builder scaleByX(double num);

    Builder scaleByY(double num);

    Builder scaleByZ(double num);

    Builder rotateInRadians(AxisEnum axis, double angle);

    Builder rotateInDegrees(AxisEnum axis, double angle);

    Builder translate(Vector3D translateVector);

    Builder moveByX(double num);

    Builder moveByY(double num);

    Builder moveByZ(double num);
}
