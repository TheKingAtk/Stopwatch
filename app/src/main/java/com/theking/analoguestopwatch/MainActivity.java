package com.theking.analoguestopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    StopwatchDial st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        st=findViewById(R.id.dial);
    }
    public void start(View view) {
        st.start();
    }
    public void stop(View view) {
        st.stop();
    }
    public void reset(View view) {
        st.reset();
    }
}
