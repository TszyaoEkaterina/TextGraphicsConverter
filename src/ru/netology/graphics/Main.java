package ru.netology.graphics;

import ru.netology.graphics.image.Converter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

//        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
//        converter.setMaxHeight(256);
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//        String url2 = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
//        String imgTxt2 = converter.convert(url2);
//        System.out.println(imgTxt2);
    }
}
