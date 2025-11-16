package com.cgvsu.math;

// Вспомогательный класс для вычисления нормалей.
public class NormalsCalculator {

    /**
     * Вычисляет нормаль треугольника по трём вершинам.
     * Важно: порядок вершин должен совпадать с порядком в OBJ-файле,
     * чтобы нормаль «смотрела» в правильную сторону.
     */
    public static Vector3f computeFaceNormal(Vector3f v0, Vector3f v1, Vector3f v2) {
        // Два ребра треугольника
        Vector3f edge1 = v1.subtract(v0);
        Vector3f edge2 = v2.subtract(v0);

        // Ненормированная нормаль — векторное произведение ребер
        Vector3f n = Vector3f.cross(edge1, edge2);

        // Возвращаем нормализованный вектор
        return n.normalize();
    }
}
