package com.example.mypiano.model;

import android.graphics.RectF;
import android.widget.TextView;

public class KeyModel {
    public int sound;
    public RectF rect;
    public boolean isDown;
    public int position;

    public KeyModel(int sound, RectF rect, int position) {
        this.sound = sound;
        this.rect = rect;
        this.position = position;
    }
}
