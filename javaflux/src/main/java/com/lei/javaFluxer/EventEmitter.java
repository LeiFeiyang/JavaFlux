package com.lei.javaFluxer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EventEmitter {

    private Map<Event, List<Listener>> eventListeners = new HashMap<>();
    private Integer dispatchToken;

    public EventEmitter() {
        dispatchToken = registerToDispatcher();
    }

    public abstract Integer registerToDispatcher();

    public void emit(Event e) {
        List<Listener> listeners = eventListeners.get(e);
        if (listeners != null && !listeners.isEmpty()){
            for (Listener l : listeners) {
                l.on();
            }
        }
    }

    public void addListener(Event e, Listener l) {
        List<Listener> listeners = eventListeners.get(e);
        if (listeners == null) {
            listeners = new ArrayList<>();
        }
        listeners.add(l);
        eventListeners.put(e, listeners);
    }

    public void removeListener(Event e, Listener l) {
        List<Listener> listeners = eventListeners.get(e);
        if (listeners == null || listeners.isEmpty()) {
            return;
        }
        listeners.remove(l);
    }

    public Integer getDispatchToken() {
        return dispatchToken;
    }


}
