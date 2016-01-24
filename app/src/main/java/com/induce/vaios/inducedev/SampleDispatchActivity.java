package com.induce.vaios.inducedev;

import com.parse.ui.ParseLoginDispatchActivity;

/**
 * Created by Vaios on 24/01/2016.
 */
public class SampleDispatchActivity extends ParseLoginDispatchActivity {
    @Override
    protected Class<?> getTargetClass() {
        return MainActivity.class;
    }
}
