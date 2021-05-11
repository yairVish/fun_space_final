package com.proj.fun_space_final;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class EnemyAstroid {
    private final int SIZE = 24;
    private final int A=1;
    private boolean[] aBoolean;
    private boolean[] aBoolean2;
    private boolean[] aBoolean3;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int[] x, y, r_x, r_y, j;
    private int update_y=3;
    private int rscore = 0;
    private int count=0;
    private boolean doOneTime=true;
    private Bitmap[][] bitmapAsteroids=new Bitmap[SIZE][18];
    private Bitmap[][] bitmapAsteroidsBack=new Bitmap[SIZE][18];
    private boolean[] b_timeDraw = new boolean[SIZE];
    private long[] timeDraw, time2Draw;
    private AnimationThread animationThread;
    private Bitmap[] bitmapExplosions;
    private Bitmap[] bitmapAsteroidsArr;

    public EnemyAstroid(Bitmap[] bitmapAsteroids, Bitmap[] bitmapExplosions) {
        this.bitmapExplosions=bitmapExplosions;
        this.bitmapAsteroidsArr=bitmapAsteroids;
        j=new int[SIZE];
        x=new int[SIZE];
        y=new int[SIZE];
        r_x = new int[SIZE];
        timeDraw = new long[SIZE];
        time2Draw = new long[SIZE];
        aBoolean=new boolean[SIZE];
        aBoolean2=new boolean[SIZE];
        aBoolean3=new boolean[SIZE];
        for(int i=0;i<SIZE;i++){
            int random = (int) (Math.random() * 100) + 100;
            for(int j=0;j<18;j++) {
                this.bitmapAsteroids[i][j] = Bitmap.createScaledBitmap(bitmapAsteroids[j]
                        , random * A, random, false);
                this.bitmapAsteroidsBack[i][j] = Bitmap.createScaledBitmap(bitmapAsteroids[j]
                        , random * A, random, false);
            }
        }
        for(int i=0;i<SIZE;i++){
            b_timeDraw[i]=true;
            r_x[i] = (int) (Math.random() * 6) + 1;
            int r_x2 = (int) (Math.random() * 2);
            if (r_x2 == 0) {
                r_x[i] = r_x[i] * -1;
            }
            for(int j=0;j<18;j++){
                if(doOneTime) {
                    y[i] = (int) (Math.random() * -1300) - 500;
                    x[i] = (int) (Math.random() * 1700) + 20;
                }
            }
            doOneTime=false;
        }
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < SIZE; i++) {
            if (b_timeDraw[i]) {
                timeDraw[i] = System.currentTimeMillis();
                b_timeDraw[i] = false;
            }
            int j = this.j[i];
            canvas.drawBitmap(bitmapAsteroids[i][j], x[i], y[i], null);
            time2Draw[i] = System.currentTimeMillis();
            if (time2Draw[i] - timeDraw[i] > 10) {
                b_timeDraw[i] = true;
                this.j[i]++;
                if (this.j[i] == 18) {
                    this.j[i] = 0;
                }
            }
        }
    }

    public int getSIZE() {
        return SIZE;
    }

    public void define() {
        for (int i = 0; i < SIZE; i++) {
            aBoolean[i]=false;
            aBoolean2[i]=false;
            aBoolean3[i]=false;
            j[i]=0;
            y[i] = (int) (Math.random() * -1300) - 500;
            x[i] = (int) (Math.random() * 1700) + 20;
        }
    }

    public void update(GameView gameView,CharacterSprite characterSprite,Shoot shoot) {
        if(count%2==0&&count!=0){

        }
        if(gameView.getScore()>rscore+100){
            if(update_y<17){
                update_y++;
                rscore += 100;
                count++;
            }
            gameView.setB_time(true);
            gameView.setCheckForAnimation(true);
        }
        for (int i = 0; i < SIZE; i++) {
            x[i] += r_x[i];
            y[i] += update_y;
            chekIfCharacterShootAsteroid(i,gameView,characterSprite);
            chekIfShootAsteroid(i,shoot,gameView);
            if (y[i] > screenHeight || x[i] > screenWidth || x[i] < -400 ||aBoolean[i]) {
                for(int j=0;j<18;j++) {
                    bitmapAsteroids[i][j] = bitmapAsteroidsBack[i][j];
                }
                r_x[i] = (int) (Math.random() * 3) + 1;
                int r_x2 = (int) (Math.random() * 2);
                if (r_x2 == 0) {
                    r_x[i] = r_x[i] * -1;
                }
                y[i] = (int) (Math.random() * -1000) - 500;
                x[i] = (int) (Math.random() * 1700) + 20;
                aBoolean3[i] =false;
                aBoolean[i]=false;
            }
        }
    }
    public void chekIfShootAsteroid(int x,Shoot shoot,GameView gameView) {
        int plus=1;
        for (int i = 0; i < shoot.getSIZE(); i++) {
            float x1 = this.x[x] - shoot.getX(i);
            float y1 = y[x] - shoot.getY(i);
            if (x1 < 0) {
                x1 = x1 * -1;
            }else{
                plus=2;
            }
            if (y1 < 0) {
                y1 = y1 * -1;
            }
            if (x1 < bitmapAsteroids[x][0].getWidth()/plus && y1 < bitmapAsteroids[x][0].getHeight()
                    &&shoot.getY(i)>y[x]&&shoot.getY(i)>y[x]+10){
                if(!aBoolean3[x]) {
                    aBoolean2[i] = true;
                    animationThread = new AnimationThread(x,false,gameView,this
                            ,null,bitmapExplosions);
                    animationThread.start();
                }
                aBoolean3[x] = true;
            }
        }
    }

    public void chekIfCharacterShootAsteroid(int index,GameView gameView,CharacterSprite characterSprite) {
        float x1 = x[index] - characterSprite.getX();
        float y1 = y[index] - characterSprite.getY();
        if (x1 < 0) {
            x1 = x1 * -1;
        }
        if (y1 < 0) {
            y1 = y1 * -1;
        }
        if ((x1 < bitmapAsteroids[index][0].getWidth()/1.5 && y1 < bitmapAsteroids[index][0].getHeight())
                &&gameView.isLegal()){
            if(!aBoolean3[index]) {
                gameView.setLegal(false);
                animationThread = new AnimationThread(index,true,gameView,this
                        ,characterSprite,bitmapExplosions);
                animationThread.start();
            }
            aBoolean3[index] = true;
        }
    }

    public void setImage(Bitmap image,int x,int y) {
        this.bitmapAsteroids[x][y] = image;
    }

    public void setaBoolean(boolean aBoolean,int index) {
        this.aBoolean[index] = aBoolean;
    }
    public void setaBoolean2(boolean aBoolean,int index) {
        this.aBoolean2[index] = aBoolean;
    }
    public void setaBoolean3(boolean aBoolean,int index) {
        this.aBoolean3[index] = aBoolean;
    }

    public void setUpdate_y(int update_y) {
        this.update_y = update_y;
    }

    public void setRscore(int rscore) {
        this.rscore = rscore;
    }

    public Bitmap[] getBitmapExplosions() {
        return bitmapExplosions;
    }

    public Bitmap[] getBitmapAsteroidsArr() {
        return bitmapAsteroidsArr;
    }

    public boolean getABoolean2(int index) {
        return aBoolean2[index];
    }
    public void setABoolean2(int index,boolean value) {
        aBoolean2[index]=value;
    }
}
