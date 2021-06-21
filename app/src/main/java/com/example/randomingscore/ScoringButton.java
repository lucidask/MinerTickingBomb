package com.example.randomingscore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.widget.Button;

import androidx.annotation.RequiresApi;

@SuppressLint({"AppCompatCustomView", "ViewConstructor"})
public class ScoringButton extends Button {
    private int pointer;
    private int addition;

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public int getAddition() {
        return addition;
    }

    public void setAddition(int addition) {
        this.addition = addition;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ScoringButton(Context context, int pointer, int addition) {
        super(context);
        this.pointer=pointer;
        this.addition=addition;
        this.setTextColor(Color.rgb(255,255,255));
        this.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(0,0,240)));
    }
}
