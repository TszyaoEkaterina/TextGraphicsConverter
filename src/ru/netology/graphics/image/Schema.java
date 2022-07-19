package ru.netology.graphics.image;

public class Schema implements TextColorSchema {
    public char convert(int color) {
        char x = '`';
        if (color < 31) {
            x = '#';
        } else if (color < 63) {
            x = '$';
        } else if (color < 95) {
            x = '@';
        } else if (color < 128) {
            x = '%';
        } else if (color < 160) {
            x = '*';
        } else if (color < 192) {
            x = '+';
        } else if (color < 224) {
            x = '-';
        }
        return x;
    }
}
