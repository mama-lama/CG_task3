package com.cgvsu.math;

import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;

import java.util.ArrayList;

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

    /**
     * Пересчёт нормалей для всей модели.
     *
     * Алгоритм:
     * 1. Инициализируем для каждой вершины нулевую нормаль.
     * 2. Для каждого полигона считаем нормаль (по первым трём вершинам).
     * 3. Добавляем нормаль полигона ко всем вершинам этого полигона.
     * 4. Нормализуем накопленные нормали и записываем в model.normals.
     * 5. Для каждого полигона проставляем normalIndices так,
     *    чтобы индекс нормали совпадал с индексом вершины.
     */
    public static void recalculateNormals(Model model) {
        int vertexCount = model.vertices.size();

        // Список накопленных нормалей для каждой вершины
        ArrayList<Vector3f> vertexNormals = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            vertexNormals.add(new Vector3f(0.0f, 0.0f, 0.0f));
        }

        // Проходим по всем полигонам и считаем нормали
        for (Polygon polygon : model.polygons) {
            ArrayList<Integer> vertexIndices = polygon.getVertexIndices();
            if (vertexIndices.size() < 3) {
                // Меньше трёх вершин — нормаль не определена, пропускаем
                continue;
            }

            int i0 = vertexIndices.get(0);
            int i1 = vertexIndices.get(1);
            int i2 = vertexIndices.get(2);

            Vector3f v0 = model.vertices.get(i0);
            Vector3f v1 = model.vertices.get(i1);
            Vector3f v2 = model.vertices.get(i2);

            // Нормаль текущего полигона
            Vector3f faceNormal = computeFaceNormal(v0, v1, v2);

            // Добавляем нормаль ко всем вершинам полигона
            for (int vertexIndex : vertexIndices) {
                Vector3f oldNormal = vertexNormals.get(vertexIndex);
                vertexNormals.set(vertexIndex, oldNormal.add(faceNormal));
            }
        }

        // Очищаем старые нормали и записываем новые (нормализованные)
        model.normals.clear();
        for (int i = 0; i < vertexCount; i++) {
            Vector3f n = vertexNormals.get(i).normalize();
            model.normals.add(n);
        }

        // Индексы нормалей делаем равными индексам вершин
        for (Polygon polygon : model.polygons) {
            polygon.getNormalIndices().clear();
            for (Integer vertexIndex : polygon.getVertexIndices()) {
                polygon.getNormalIndices().add(vertexIndex);
            }
        }
    }
}
