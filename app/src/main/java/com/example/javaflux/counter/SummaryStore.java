package com.example.javaflux.counter;


import com.lei.javaFluxer.ActionHandler;
import com.lei.javaFluxer.Event;
import com.lei.javaFluxer.EventEmitter;
import com.lei.javaFluxer.Listener;

import java.util.Map;

public class SummaryStore extends EventEmitter implements ActionHandler<CounterAction> {
    private static final SummaryStore summaryStore = new SummaryStore();

    public static synchronized SummaryStore getInstance() {
        return summaryStore;
    }

    private SummaryStore() {

    }

    private final Event CHANGE_EVENT = new Event() {
    };

    @Override
    public Integer registerToDispatcher() {
        return AppDispatcher.getInstance().register(this);
    }

    public Integer getSummary() {
        Map<CounterStore.Caption, Integer> counters = CounterStore.getInstance().getCounterValue();
        if (counters != null) {
            int firstCount = counters.get(CounterStore.Caption.FIRST);
            int secoudCount = counters.get(CounterStore.Caption.SECOND);
            int thirdCount = counters.get(CounterStore.Caption.THIRD);
            return firstCount + secoudCount + thirdCount;
        }
        return 0;
    }

    @Override
    public void handle(CounterAction action) {
        ActionType type = action.getType();
        switch (type) {
            case INCREACE:
            case DECREACE:
                AppDispatcher.getInstance().waitFor(CounterStore.getInstance().getDispatchToken());
                emitChange();
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
