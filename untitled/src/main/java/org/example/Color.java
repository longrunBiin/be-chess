package org.example;

public enum Color {
    WHITE("White"),
    BLACK("Black");

    String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
