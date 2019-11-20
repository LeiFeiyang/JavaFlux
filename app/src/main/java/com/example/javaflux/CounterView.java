package com.example.javaflux;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.javaflux.counter.ActionType;
import com.example.javaflux.counter.AppDispatcher;
import com.example.javaflux.counter.CounterAction;
import com.example.javaflux.counter.CounterStore;
import com.lei.javaFluxer.Listener;

import java.util.Map;

public class CounterView extends LinearLayout {

    private Context context;
    private Button increaceBtn0, decreaceBtn0;
    private TextView count0;
    private CounterStore.Caption caption;

    public CounterView(Context context) {
        super(context);
        init(context, null);
    }

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context cxt, AttributeSet attrs) {
        context = cxt;
        TypedArray a = cxt.obtainStyledAttributes(attrs, R.styleable.CounterView);
        int caption = a.getInt(R.styleable.CounterView_caption, 0);
        a.recycle();
        switch (caption) {
            case 0:
                this.caption = CounterStore.Caption.FIRST;
                break;
            case 1:
                this.caption = CounterStore.Caption.SECOND;
                break;
            case 2:
                this.caption = CounterStore.Caption.THIRD;
                break;
            default:
                this.caption = CounterStore.Caption.FIRST;
                break;
        }

        View v = LayoutInflater.from(cxt).inflate(R.layout.counter, this, true);
        increaceBtn0 = v.findViewById(R.id.first_increace);
        decreaceBtn0 = v.findViewById(R.id.first_decreace);
        count0 = (TextView) v.findViewById(R.id.first_count);

        increaceBtn0.setOnClickListener(onIncreaceClickListener);
        decreaceBtn0.setOnClickListener(onDecreaceClickListener);
        onChange.on();
    }

    View.OnClickListener onIncreaceClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (caption != null) {
                AppDispatcher.getInstance().emit(new CounterAction(ActionType.INCREACE, caption));
            }
        }
    };
    View.OnClickListener onDecreaceClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (caption != null) {
                AppDispatcher.getInstance().emit(new CounterAction(ActionType.DECREACE, caption));
            }
        }
    };
    Listener onChange = new Listener() {
        @Override
        public void on() {
            Map<CounterStore.Caption, Integer> counters = CounterStore.getInstance().getCounterValue();
            if (counters != null) {
                int firstCount = counters.get(caption);
                count0.setText(caption.name() + ": " + firstCount);
            }
        }
    };

    public void attach() {
        CounterStore.getInstance().addChangeListener(onChange);
    }

    public void detach() {
        CounterStore.getInstance().removeChangeListener(onChange);
    }

}
