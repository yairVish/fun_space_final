package com.proj.fun_space_final;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import static com.proj.fun_space_final.MainActivity.*;

public class Shoot {
    private final int SIZE=10;
    private float []x,y;
    private boolean[]run;
    private boolean []back;
    private Bitmap image;
    private ImageView button;
    private int countShoots=0,countShoots2=0;
    private int z=0;

    public Shoot(Bitmap image, ImageView button){
        back=new boolean[SIZE];
        run=new boolean[SIZE];
        x=new float[SIZE];
        y=new float[SIZE];
        this.image=image;
        this.button = button;
    }
    public void define(){
        for (int i = 0; i < SIZE; i++) {
                x[i] = screenWidth * 10;
                y[i] = screenHeight * 10;
                run[i] = false;
                back[i] = false;
        }
    }

    public void draw(Canvas canvas){
        image = Bitmap.createScaledBitmap(image, 30, 40, false);
        for(int i=0;i<SIZE;i++){
            canvas.drawBitmap(image, x[i], y[i], null);
        }
    }
    public void update(EnemyAstroid enemyAstroid) {
        for (int i = 0; i < SIZE; i++){
            if(run[i]) {
                y[i] -= 20;
            }
            if(y[i]<=-20){
                back[i]=true;
            }
            if (enemyAstroid.getABoolean2(i)||back[i]){
                x[i] = screenWidth*10;
                y[i] = screenHeight*10;
                run[i]=false;
            }
        }
    }

    public void clickedOnLayoutDraw(CharacterSprite characterSprite,EnemyAstroid enemyAstroid){
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                countShoots++;
                if(countShoots==SIZE){
                    countShoots=0;
                }
                for(int i=countShoots2;i<countShoots;i++) {
                    z=i;
                    x[i] = characterSprite.getX() + 53;
                    y[i] = characterSprite.getY();
                    run[i]=true;
                    enemyAstroid.setABoolean2(i,false);
                    back[i]=false;
                }
                countShoots2++;
                if(countShoots2==SIZE){
                    countShoots2=0;
                }

            }
        });
    }
    public ImageView getButton() {
        return button;
    }

    public int getSIZE() {
        return SIZE;
    }

    public float getX(int index) {
        return x[index];
    }

    public float getY(int index) {
        return y[index];
    }
}
