package com.example.javaflux.counter;


import com.lei.javaFluxer.ActionHandler;
import com.lei.javaFluxer.Event;
import com.lei.javaFluxer.EventEmitter;
import com.lei.javaFluxer.Listener;

import java.util.HashMap;
import java.util.Map;

public class CounterStore extends EventEmitter implements ActionHandler<CounterAction> {
    private static final CounterStore counterStore = new CounterStore();

    public static synchronized CounterStore getInstance() {
        return counterStore;
    }

    public enum Caption {
        FIRST, SECOND, THIRD
    }

    private Map<Caption, Integer> counterValue = new HashMap<>();

    private CounterStore() {
        this.counterValue.put(Caption.FIRST, 0);
        this.counterValue.put(Caption.SECOND, 10);
        this.counterValue.put(Caption.THIRD, 30);
    }

    @Override
    public Integer registerToDispatcher() {
        return AppDispatcher.getInstance().register(this);
    }

    public Map<Caption, Integer> getCounterValue() {
        return counterValue;
    }


    private final Event CHANGE_EVENT = new Event() {
    };

    @Override
    public void handle(CounterAction action) {
        ActionType type = action.getType();
        CounterStore.Caption caption = action.getCaption();
        Integer old = this.counterValue.get(caption);
        switch (type) {
            case INCREACE:{
                Integer news = old + 1;
                this.counterValue.put(caption, news);
                emitChange();
            }
                break;
            case DECREACE:{
                Integer news = old - 1;
                this.counterValue.put(caption, news);
                emitChange();
            }
                break;
            default:
                break;
        }
    }

    public void emitChange() {
        this.emit(CHANGE_EVENT);
    }

    public void addChangeListener(Listener callback){
        this.addListener(CHANGE_EVENT, callback);
    }

    public void removeChangeListener(Listener callback) {
        this.removeListener(CHANGE_EVENT, callback);
    }


}
