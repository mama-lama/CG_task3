package com.cgvsu.math;

// Класс трёхмерного вектора с базовыми операциями,
// используем для работы с геометрией и нормалями.
public class Vector3f {

    float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f() {
        this(0.0f, 0.0f, 0.0f);
    }

    // Сравнение двух векторов с допуском по модулю (эпсилон).
    // Нужен для тестов и проверок.
    public boolean equals(Vector3f other) {
        // TODO: при желании eps можно вынести в отдельную константу
        final float eps = 1e-7f;
        return Math.abs(x - other.x) < eps
                && Math.abs(y - other.y) < eps
                && Math.abs(z - other.z) < eps;
    }

    // Сложение векторов: this + other
    public Vector3f add(Vector3f other) {
        return new Vector3f(
                this.x + other.x,
                this.y + other.y,
                this.z + other.z
        );
    }

    // Вычитание векторов: this - other
    public Vector3f subtract(Vector3f other) {
        return new Vector3f(
                this.x - other.x,
                this.y - other.y,
                this.z - other.z
        );
    }

    // Умножение вектора на скаляр: this * scalar
    public Vector3f multiply(float scalar) {
        return new Vector3f(
                this.x * scalar,
                this.y * scalar,
                this.z * scalar
        );
    }

    // Длина (модуль) вектора
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    // Нормализация вектора (приведение к длине 1).
    // Если длина нулевая, возвращаем нулевой вектор.
    public Vector3f normalize() {
        float len = length();
        if (len == 0.0f) {
            return new Vector3f(0.0f, 0.0f, 0.0f);
        }
        return new Vector3f(
                x / len,
                y / len,
                z / len
        );
    }

    // Статический метод: векторное произведение a × b.
    // Результат перпендикулярен обоим векторам.
    public static Vector3f cross(Vector3f a, Vector3f b) {
        return new Vector3f(
                a.y * b.z - a.z * b.y,
                a.z * b.x - a.x * b.z,
                a.x * b.y - a.y * b.x
        );
    }
}
