package com.github.funthomas424242.examples.vavr;

public class AsyncRandomGenerator implements Runnable{

    protected double result;


    public AsyncRandomGenerator(){

    }

    public double get(){
        return result;
    }

    @Override
    public void run() {
        result = Math.random();
    }

}
