/*
 * Copyright (c) 2016, Samsao Development Inc. All rights reserved.
 */
package com.mjd.viper.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.Button;

import com.mjd.viper.R;
import com.mjd.viper.bluetooth.logger.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lcampos on 2016-10-18.
 */

public class MultipleStatesToggleButton extends Button {

    private List<String> states = new ArrayList<>();
    private int currentState;

    public MultipleStatesToggleButton(Context context) {
        super(context);
        init(context, null);
    }

    public MultipleStatesToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MultipleStatesToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MultipleStatesToggleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.getTheme()
                                  .obtainStyledAttributes(attrs, R.styleable.MultipleStatesToggleButtonOptions, 0, 0);
            try {
                String stateOne = a.getString(R.styleable.MultipleStatesToggleButtonOptions_state_one);
                String stateTwo = a.getString(R.styleable.MultipleStatesToggleButtonOptions_state_two);
                String stateThree = a.getString(R.styleable.MultipleStatesToggleButtonOptions_state_three);
                if (stateOne != null) {
                    states.add(stateOne);
                    updateButtonState();
                }
                if (stateTwo != null) {
                    states.add(stateTwo);
                }
                if (stateThree != null) {
                    states.add(stateThree);
                }
            } finally {
                a.recycle();
            }
        }
    }

    private void updateButtonState() {
        if (this.states.size() > 0) {
            setText(states.get(currentState));
        }
    }

    @Override
    public boolean performClick() {
        currentState = ++currentState % states.size();
        updateButtonState();
        return super.performClick();
    }

    /** PUBLIC **/

    public void setStates(String... states) {
        this.states = Arrays.asList(states);
        updateButtonState();
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        if (currentState < states.size() && currentState >= 0) {
            this.currentState = currentState;
        } else {
            Log.e("MultipleStatesToggleButton", "Cannot set state outside given states");
        }
    }
}
