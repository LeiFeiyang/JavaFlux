package com.example.javaflux;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.javaflux.counter.SummaryStore;
import com.lei.javaFluxer.Listener;

import androidx.annotation.Nullable;

public class SummaryView extends TextView {

    public SummaryView(Context context) {
        super(context);
        init();
    }

    public SummaryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SummaryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        onChange.on();
    }

    Listener onChange = new Listener() {
        @Override
        public void on() {
            Integer sum = SummaryStore.getInstance().getSummary();
            if (sum != null) {
                SummaryView.this.setText("Sum: " + sum);
            }
        }
    };

    public void attach() {
        SummaryStore.getInstance().addChangeListener(onChange);
    }

    public void detach() {
        SummaryStore.getInstance().removeChangeListener(onChange);
    }


}
