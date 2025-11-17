package com.cgvsu;

import com.cgvsu.math.NormalsCalculator;
import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {

        // Путь к OBJ-файлу можно подправить под свою структуру проекта
        Path fileName = Path.of("../../3DModels/Faceform/WrapHead.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Загрузка модели из файла: " + fileName);
        Model model = ObjReader.read(fileContent);

        System.out.println("Количество вершин: " + model.vertices.size());
        System.out.println("Количество текстурных вершин: " + model.textureVertices.size());
        System.out.println("Количество нормалей, считанных из файла: " + model.normals.size());
        System.out.println("Количество полигонов: " + model.polygons.size());

        // Пересчитываем нормали целиком на основе геометрии
        System.out.println("Пересчёт нормалей модели...");
        NormalsCalculator.recalculateNormals(model);

        System.out.println("Количество пересчитанных нормалей: " + model.normals.size());
        System.out.println("Пересчёт нормалей завершён.");
    }
}
