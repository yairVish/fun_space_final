package com.proj.fun_space_final;

import android.graphics.Bitmap;

public class AnimationThread extends Thread {
    private int x;
    private boolean b;
    private GameView gameView;
    private EnemyAstroid enemyAstroid;
    private CharacterSprite characterSprite;
    private Bitmap[] bitmapExplosions;
    public AnimationThread(int x, boolean b, GameView gameView, EnemyAstroid enemyAstroid
            , CharacterSprite characterSprite,Bitmap[] bitmapExplosions){
        this.x=x;
        this.b=b;
        this.gameView=gameView;
        this.enemyAstroid=enemyAstroid;
        this.characterSprite=characterSprite;
        this.bitmapExplosions=bitmapExplosions;
    }
    @Override
    public void run(){
        for (int s = 0; s < 18; s++) {
            if(!b) {
                enemyAstroid.setImage(bitmapExplosions[0],x,s);
            }else{
                characterSprite.setImage(bitmapExplosions[0]);
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
        for (int s = 0; s < 18; s++) {
            if(!b) {
                enemyAstroid.setImage(bitmapExplosions[1],x,s);
            }else{
                characterSprite.setImage(bitmapExplosions[1]);
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int s = 0; s < 18; s++) {
            if(!b) {
                enemyAstroid.setImage(bitmapExplosions[2],x,s);
            }else{
                characterSprite.setImage(bitmapExplosions[2]);
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int s = 0; s < 18; s++) {
            if(!b) {
                enemyAstroid.setImage(bitmapExplosions[3],x,s);
            }else{
                characterSprite.setImage(bitmapExplosions[3]);
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int s = 0; s < 18; s++) {
            if(!b) {
                enemyAstroid.setImage(bitmapExplosions[4],x,s);
            }else{
                characterSprite.setImage(bitmapExplosions[4]);
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!b) {
            enemyAstroid.setaBoolean(true,x);
        }else{
            gameView.setLose2(true);
            gameView.setLose(true);
        }
    }
}
