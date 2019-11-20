package com.example.javaflux.counter;


import com.lei.javaFluxer.Dispatcher;

public class AppDispatcher extends Dispatcher {
    private static final AppDispatcher dispatcher = new AppDispatcher();

    private AppDispatcher(){

    }

    public static synchronized AppDispatcher getInstance() {
        return dispatcher;
    }

}
