package com.lei.javaFluxer;

public interface ActionHandler<A extends Action> {
    void handle(A action);
}
