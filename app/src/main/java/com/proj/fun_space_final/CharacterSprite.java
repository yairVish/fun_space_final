package com.proj.fun_space_final;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static com.proj.fun_space_final.MainActivity.*;

public class CharacterSprite {
    private Bitmap image;
    private final int MAX_SPEED_X=200;
    private final int MAX_SPEED_Y=200;
    private Bitmap bitmaPlayerNoFire,bitmapPlayer;
    private float x,y;
    private float x1,y1;

    public CharacterSprite(Bitmap bitmaPlayerNoFire, Bitmap bitmapPlayer, boolean b) {
        image=bitmapPlayer;
        this.bitmaPlayerNoFire = Bitmap.createScaledBitmap(bitmaPlayerNoFire, 150, 150, false);
        this.bitmapPlayer = Bitmap.createScaledBitmap(bitmapPlayer, 150, 150, false);
        if(b) {
            x = screenWidth*27/100;
            y = screenHeight*46/100;
        }
    }
    public void draw(Canvas canvas){
        image = Bitmap.createScaledBitmap(image, 150, 150, false);
        canvas.drawBitmap(image,x,y,null);
    }

    public void update(float x1,float y1) {

        this.x1=x1;
        this.y1=y1;
        if(x1>MAX_SPEED_X)
        {
            x1=MAX_SPEED_X;
        }
        if(y1>MAX_SPEED_Y)
        {
            y1=MAX_SPEED_Y;
        }


        if((y>=10||y1>=0)&&(y<screenHeight-image.getHeight()||y1<=0)) {
            y += y1 / 12;
        }

        if((x>=10||x1>=0)&&(x<screenWidth-image.getWidth()||x1<=0)) {
            x += x1 / 12;
        }
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Bitmap getBitmaPlayerNoFire() {
        return bitmaPlayerNoFire;
    }

    public void setBitmaPlayerNoFire(Bitmap bitmaPlayerNoFire) {
        this.bitmaPlayerNoFire = bitmaPlayerNoFire;
    }

    public Bitmap getBitmapPlayer() {
        return bitmapPlayer;
    }

    public void setBitmapPlayer(Bitmap bitmapPlayer) {
        this.bitmapPlayer = bitmapPlayer;
    }
}
