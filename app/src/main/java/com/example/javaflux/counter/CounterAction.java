package com.example.javaflux.counter;


import com.lei.javaFluxer.Action;

public class CounterAction implements Action<ActionType> {
    private ActionType type;
    private CounterStore.Caption caption;

    public CounterAction(ActionType type, CounterStore.Caption caption) {
        this.type = type;
        this.caption = caption;
    }

    @Override
    public ActionType getType() {
        return type;
    }

    public CounterStore.Caption getCaption() {
        return caption;
    }
}
