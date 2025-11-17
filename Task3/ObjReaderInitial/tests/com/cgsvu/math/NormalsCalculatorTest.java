package com.cgvsu.math;

import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Простой тест для проверки пересчёта нормалей на плоском квадрате.
class NormalsCalculatorTest {

    @Test
    public void testRecalculateNormalsForSimpleQuad() {
        Model model = new Model();

        // Квадрат в плоскости z = 0
        model.vertices.add(new Vector3f(0, 0, 0)); // 0
        model.vertices.add(new Vector3f(1, 0, 0)); // 1
        model.vertices.add(new Vector3f(1, 1, 0)); // 2
        model.vertices.add(new Vector3f(0, 1, 0)); // 3

        // Два треугольника: (0,1,2) и (0,2,3)
        Polygon p1 = new Polygon();
        p1.getVertexIndices().add(0);
        p1.getVertexIndices().add(1);
        p1.getVertexIndices().add(2);

        Polygon p2 = new Polygon();
        p2.getVertexIndices().add(0);
        p2.getVertexIndices().add(2);
        p2.getVertexIndices().add(3);

        model.polygons.add(p1);
        model.polygons.add(p2);

        // Пересчитываем нормали
        NormalsCalculator.recalculateNormals(model);

        Vector3f expected = new Vector3f(0, 0, 1);

        for (Vector3f actual : model.normals) {
            Assertions.assertTrue(
                    actual.equals(expected),
                    "Ожидалась нормаль (0, 0, 1), но получено: ("
                            + actual.x + ", " + actual.y + ", " + actual.z + ")"
            );
        }
    }
}
