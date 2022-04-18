package com.example.mypiano.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mypiano.R;
import com.example.mypiano.model.KeyModel;
import com.example.mypiano.model.SoundManager;

import java.util.ArrayList;
import java.util.List;

public class PianoView extends View {
    private static final int NUMBER_KEYS = 14;
    private Paint black, white, yellow, blackLine;
    private ArrayList<KeyModel> whites, blacks;
    private int keyWidth, keyHeight;
    private SoundManager soundManager;

    public PianoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        black = new Paint();
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);

        white = new Paint();
        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.FILL);

        yellow = new Paint();
        yellow.setColor(Color.YELLOW);
        yellow.setStyle(Paint.Style.FILL);

        blackLine = new Paint();
        blackLine.setColor(Color.BLACK);
        blackLine.setStyle(Paint.Style.FILL_AND_STROKE);

        whites = new ArrayList<>();
        blacks = new ArrayList<>();

        soundManager = SoundManager.getInstance();
        soundManager.init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        keyWidth = w / NUMBER_KEYS;
        keyHeight = h;

        int countBlack = 0;
        int index = -1;

        for (int i = 0; i < NUMBER_KEYS; i++){
            int left = i * keyWidth;
            int right = left + keyWidth;

            RectF rectF = new RectF(left, 0, right, keyHeight);

            whites.add(new KeyModel(i, rectF, i));

            if (i != 0 && i != 3 && i != 7 && i != 10){
                rectF = new RectF((float) (i-1)*keyWidth + 0.75f*keyWidth,
                                0,
                                (float) i*keyWidth + 0.25f*keyWidth,
                                0.67f*keyHeight);
                index++;
                blacks.add(new KeyModel(countBlack++, rectF, index));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<String> note = new ArrayList<>();
        note.add("Do3");note.add("Re3");
        note.add("Mi3");note.add("Fa3");
        note.add("So3");note.add("La3");
        note.add("Si3");
        note.add("Do4");note.add("Re4");
        note.add("Mi4");note.add("Fa4");
        note.add("So4");note.add("La4");
        note.add("Si4");
        Paint text = new Paint();
        text.setColor(Color.BLACK);
        text.setStyle(Paint.Style.FILL_AND_STROKE);
        text.setTextSize(40);
        int index = -1;
        for (KeyModel k : whites){
            canvas.drawRect(k.rect, k.isDown? yellow:white);
            index++;
            canvas.drawText(note.get(index),k.rect.centerX() - 25, keyHeight - 40, text);
        }
        index = -1;
        for (KeyModel k : blacks){
            text.setTextSize(30);
            text.setColor(Color.WHITE);
            note.clear();
            note.add("DB3");note.add("EB3");
            note.add("GB3");note.add("AB3");
            note.add("BB3");
            note.add("DB4");note.add("EB4");
            note.add("GB4");note.add("AB4");
            note.add("BB4");
            canvas.drawRect(k.rect, k.isDown? yellow:black);
            index++;
            canvas.drawText(note.get(index),k.rect.centerX() - 25, 0.67f*keyHeight - 30, text);
        }

        for (int i = 1; i < NUMBER_KEYS; i++){
            canvas.drawLine(i * keyWidth, 0, i * keyWidth, keyHeight, blackLine);
        }
    }

    public void playMusic(int position, boolean isBlack){
        if (!isBlack){
            switch (position){
                case 0:
                    soundManager.playSound(R.raw.c3);
                    Log.d("debug","c3");
                    return;
                case 1:
                    soundManager.playSound(R.raw.d3);
                    Log.d("debug","d3");
                    return;
                case 2:
                    soundManager.playSound(R.raw.e3);
                    Log.d("debug","e3");
                    return;
                case 3:
                    soundManager.playSound(R.raw.f3);
                    Log.d("debug","f3");
                    return;
                case 4:
                    soundManager.playSound(R.raw.g3);
                    Log.d("debug","g3");
                    return;
                case 5:
                    soundManager.playSound(R.raw.a3);
                    Log.d("debug","a3");
                    return;
                case 6:
                    soundManager.playSound(R.raw.b3);
                    Log.d("debug","b3");
                    return;
                case 7:
                    soundManager.playSound(R.raw.c4);
                    Log.d("debug","c4");
                    return;
                case 8:
                    soundManager.playSound(R.raw.d4);
                    Log.d("debug","d4");
                    return;
                case 9:
                    soundManager.playSound(R.raw.e4);
                    Log.d("debug","e4");
                    return;
                case 10:
                    soundManager.playSound(R.raw.f4);
                    Log.d("debug","f4");
                    return;
                case 11:
                    soundManager.playSound(R.raw.g4);
                    Log.d("debug","g4");
                    return;
                case 12:
                    soundManager.playSound(R.raw.a4);
                    Log.d("debug","a4");
                    return;
                case 13:
                    soundManager.playSound(R.raw.b4);
                    Log.d("debug","b4");
                    return;
            }
        }
        if (isBlack){
            switch (position) {
                case 0:
                    soundManager.playSound(R.raw.db3);
                    Log.d("debug","db3");
                    return;
                case 1:
                    soundManager.playSound(R.raw.eb3);
                    Log.d("debug","eb3");
                    return;
                case 2:
                    soundManager.playSound(R.raw.gb3);
                    Log.d("debug","gb3");
                    return;
                case 3:
                    soundManager.playSound(R.raw.ab3);
                    Log.d("debug","ab3");
                    return;
                case 4:
                    soundManager.playSound(R.raw.bb3);
                    Log.d("debug","bb3");
                    return;
                case 5:
                    soundManager.playSound(R.raw.db4);
                    Log.d("debug","db4");
                    return;
                case 6:
                    soundManager.playSound(R.raw.eb4);
                    Log.d("debug","eb4");
                    return;
                case 7:
                    soundManager.playSound(R.raw.gb4);
                    Log.d("debug","gb4");
                    return;
                case 8:
                    soundManager.playSound(R.raw.ab4);
                    Log.d("debug","ab4");
                    return;
                case 9:
                    soundManager.playSound(R.raw.bb4);
                    Log.d("debug","bb4");
                    return;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        for (KeyModel key : whites){
            key.isDown = false;
        }
        for (KeyModel key : blacks){
            key.isDown = false;
        }
        if (event.getPointerCount() > 1) {
            for (int touchIndex = 0; touchIndex < event.getPointerCount(); touchIndex++) {
                int ps = 0;
                float x = event.getX(touchIndex);
                float y = event.getY(touchIndex);
                int id = event.getPointerId(touchIndex);
                if (action == MotionEvent.ACTION_POINTER_DOWN){
                    boolean isBlack = false;
                    for (KeyModel key : blacks) {
                        if (key.rect.contains(x,y)){
                            key.isDown = true;
                            isBlack = true;
                            playMusic(key.position,true);
                            Log.d("debug", Integer.toString(key.position));
                        }
                    }
                    if (!isBlack) {
                        for (KeyModel key : whites) {
                            if (key.rect.contains(x, y)) {
                                key.isDown = true;
                                playMusic(key.position,false);
                                Log.d("debug", Integer.toString(key.position));
                            }
                        }
                    }
                } else if (action == MotionEvent.ACTION_POINTER_UP){
                    boolean isBlack = false;
                    for (KeyModel key : blacks) {
                        if (key.rect.contains(x,y)){
                            key.isDown = false;
                            isBlack = true;
                        }
                    }
                    if (!isBlack) {
                        for (KeyModel key : whites) {
                            if (key.rect.contains(x, y)) {
                                key.isDown = false;
                            }
                        }
                    }
                }
            }
        } else {

            if (action == MotionEvent.ACTION_MOVE){
                for (int touchIndex = 0; touchIndex < event.getPointerCount(); touchIndex++) {
                    boolean isBlack = false;
                    int ps = 0;
                    float x = event.getX(touchIndex);
                    float y = event.getY(touchIndex);
                    String previousColor = "";
                    for (KeyModel key : blacks) {
                        if (key.rect.contains(x, y)) {
                            key.isDown = true;
                            ps = key.position;
                            isBlack = true;
                            previousColor = "black";
                            playMusic(key.position,true);
                            Log.d("debug", Integer.toString(key.position));
                            break;
                        }
                    }
                    if (!isBlack) {
                        for (KeyModel key : whites) {
                            if (key.rect.contains(x, y)) {
                                key.isDown = true;
                                ps = key.position;
                                previousColor = "white";
                                playMusic(key.position,false);
                                Log.d("debug", Integer.toString(key.position));
                                break;
                            }
                        }
                    }
                    switch(previousColor){
                        case "black":
                            for (KeyModel key : blacks){
                                if (key.position != ps){
                                    key.isDown = false;
                                }
                            }
                        case "white":
                            for (KeyModel key : whites){
                                if (key.position != ps){
                                    key.isDown = false;
                                }
                            }
                    }
                }
            }

            if (action == MotionEvent.ACTION_DOWN) {
                float x = event.getX();
                float y = event.getY();
                boolean isBlack = false;
                for (KeyModel key : blacks) {
                    if (key.rect.contains(x, y)) {
                        key.isDown = true;
                        isBlack = true;
                        playMusic(key.position,true);
                        Log.d("debug", Integer.toString(key.position));
                    }
                }
                if (!isBlack) {
                    for (KeyModel key : whites) {
                        if (key.rect.contains(x, y)) {
                            key.isDown = true;
                            playMusic(key.position,false);
                            Log.d("debug", Integer.toString(key.position));
                        }
                    }
                }
            } else if (action == MotionEvent.ACTION_UP){
                float x = event.getX();
                float y = event.getY();
                boolean isBlack = false;
                for (KeyModel key : blacks) {
                    if (key.rect.contains(x,y)){
                        key.isDown = false;
                        isBlack = true;
                    }
                }
                if (!isBlack) {
                    for (KeyModel key : whites) {
                        if (key.rect.contains(x, y)) {
                            key.isDown = false;
                        }
                    }
                }
            }
        }

        invalidate();
        return true;
    }
}

