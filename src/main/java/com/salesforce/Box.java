package com.salesforce;

public class Box {
    //TODO:final discussion
    private double width;
    private double length;
    private double height;

    public Box(double width, double length, double height) { //constructor
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
