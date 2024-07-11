package org.example.springwebtest.learning.ch4;

public class JavaButton implements Clickable{

    @Override
    public void click() {

    }

    @Override
    public void showOff() {
        Clickable.super.showOff();
    }
}
