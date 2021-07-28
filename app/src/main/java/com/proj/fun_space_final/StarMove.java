package com.proj.fun_space_final;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import static com.proj.fun_space_final.MainActivity.*;

public class StarMove {
    private int rscore = 0;
    private int []x,y,yr;
    private final int SIZE=20;
    private int update_y=6;
    private Bitmap image;
    private int count=0;
    public StarMove(Bitmap image){
        x=new int[SIZE];
        y=new int[SIZE];
        yr=new int[SIZE];
        this.image=image;
    }
    public void define(){
        for (int i = 0; i < SIZE; i++) {
                x[i] = 100 + i * 87;
                y[i] = (int) (Math.random() * -1100) - 1;
                yr[i] = y[i];
        }
    }

    public void draw(Canvas canvas){
        for(int i=0;i<SIZE;i++) {
            image=Bitmap.createScaledBitmap(image, 100, 100, false);
            canvas.drawBitmap(image, x[i], y[i], null);
        }
    }

    public void update(int score){
        if(score>rscore+100&&update_y<22){
            update_y++;
            rscore += 100;
            count++;
        }
        for (int i=0;i<SIZE;i++) {
            y[i] += update_y;
            if (y[i] > screenHeight) {
                y[i]=(int)(Math.random() * -200)-1;
            }
        }
    }

    public void setRscore(int rscore) {
        this.rscore = rscore;
    }

    public void setUpdate_y(int update_y) {
        this.update_y = update_y;
    }

}
