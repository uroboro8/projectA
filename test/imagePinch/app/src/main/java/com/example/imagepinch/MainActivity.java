package com.example.imagepinch;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements
        ScaleGestureDetector.OnScaleGestureListener {

    private ImageView myImage;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImage = findViewById(R.id.myImage);

        scaleGestureDetector = new ScaleGestureDetector(this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if(mScaleFactor >= 1.0f) {
            mScaleFactor *= detector.getScaleFactor();
            myImage.setScaleX(mScaleFactor);
            myImage.setScaleY(mScaleFactor);
        }
        else{
            mScaleFactor = 1.0f;
        }

        Log.i("mScaleFactor",String.valueOf(mScaleFactor));


        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
    }
}