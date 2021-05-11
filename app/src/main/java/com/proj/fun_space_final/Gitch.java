package com.proj.fun_space_final;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class Gitch {
    private final int SIZE=13;
    private boolean []b;
    private Bitmap image;
    private float []x,y;
    private float x1,y1;
    private int i=0;
    public Gitch(Bitmap image){
        b=new boolean[SIZE];
        x=new float [SIZE];
        y=new float [SIZE];
        this.image=image;
    }
    public void define(){
        for (int i = 0; i < SIZE; i++) {
            b[i] = true;
        }
    }
    public void draw(Canvas canvas){
        for(int j=0;j<SIZE;j++) {
            image = Bitmap.createScaledBitmap(image, 50, 50, false);
            canvas.drawBitmap(image, x[j], y[j], null);
        }
    }
    public void update(float x_character,float y_character){
        if(b[i]) {
            if(i%2==0) {
                x[i] = x_character - 13;
            }else{
                x[i] = x_character + 105;
            }
            y[i] = y_character + 45;
            y1 = y_character + 45;
            b[i]=false;
        }
        y[i]+=20;
        if(y[i]-100>=y1||(x[i]!=x_character - 13&&i%2==0)||(x[i]!=x_character+105&&i%2!=0)){
            b[i]=true;
        }
        i++;

        if(i==SIZE){
            i=0;
        }
    }
}
