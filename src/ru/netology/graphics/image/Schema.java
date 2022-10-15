package ru.netology.graphics.image;

public class Schema implements TextColorSchema {
    char[] symbols = {'`', '#', '$', '@', '%', '*', '+', '-'};
    public char convert(int color) {
        return symbols[(int) Math.floor(color / 256. * symbols.length)];
    }
}
