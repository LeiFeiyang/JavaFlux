package com.lei.javaFluxer;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

    private List<ActionHandler> actionHandlers = new ArrayList<>();
    private List<Integer> waitFors = new ArrayList<>();

    public <T extends Action> Integer register(ActionHandler<T> actionHandler) {
        actionHandlers.add(actionHandler);
        return actionHandlers.indexOf(actionHandler);
    }

    public void waitFor(Integer... waitFors) {
        this.waitFors.clear();
        if (waitFors != null) {
            for (Integer w : waitFors) {
                this.waitFors.add(w);
            }
        }
    }

    public void emit(Action action) {
        if (waitFors != null) {
            for (Integer w : waitFors) {
                ActionHandler handler = actionHandlers.get(w);
                handler.handle(action);
            }
        }

        for (int i = 0; i < actionHandlers.size(); i++) {
            if (!isExist(i, waitFors)) {
                actionHandlers.get(i).handle(action);
            }
        }
    }

    private static boolean isExist(Integer x, List<Integer> list) {
        if (list == null || x == null || list.isEmpty()) {
            return false;
        }
        for (Integer i : list) {
            if (i == x) {
                return true;
            }
        }
        return false;
    }
}
