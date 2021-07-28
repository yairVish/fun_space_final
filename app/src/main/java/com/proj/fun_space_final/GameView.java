package com.proj.fun_space_final;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import static com.proj.fun_space_final.MainActivity.*;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;
import static android.graphics.Color.BLACK;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private boolean b132;
    private Paint paint = new Paint();
    private boolean lose,lose2,b_file;
    private MainThread thread;
    private EnemyAstroid enemyAstroid;
    private StarMove starMove;
    private CharacterSprite characterSprite;
    private ViewGroup layout;
    private Shoot shoot;
    private Gitch gitch;
    private Context context;
    private ImageView button;
    private Joystick joystick;
    private int score = 0;
    private boolean b_timeForAnimation=true;
    private long timerScore,time,timeForAnimation1,timeForAnimation2;
    private long timer2Score,time2;
    private boolean b_timerScore=true;
    private boolean checkForAnimation=false;
    private boolean b_time=false;
    private FileOutputStream fos=null;
    private BufferedReader fis=null;
    private FileOutputStream out=null;
    private BufferedReader reader=null;
    private boolean isLegal=false;
    private Dt dt=new Dt();
    public GameView(Context context, ViewGroup layout, ImageView button, EnemyAstroid enemyAstroid, StarMove starMove
            , Shoot shoot, CharacterSprite characterSprite, Gitch gitch){
        super(context);
        this.context=context;
        thread = new MainThread(getHolder(), this);
        this.layout = layout;
        this.button = button;
        this.enemyAstroid=enemyAstroid;
        this.starMove=starMove;
        this.characterSprite=characterSprite;
        this.shoot=shoot;
        this.gitch=gitch;
        lose=true;
        lose2=true;
        Log.d("TAG", "Width: "+screenWidth);
    }
    public void startGameView(){
        dt.start();
        joystick = new Joystick(context, layout, R.drawable.joistick);
        joystick.setStickSize(200, 200);
        joystick.setLayoutSize(420, 420);
        joystick.setLayoutAlpha(150);
        joystick.setStickAlpha(100);
        joystick.setOffset(90);
        joystick.setMinimumDistance(50);

        layout.setOnTouchListener(
                new OnTouchListener() {
                    public boolean onTouch(View arg0, MotionEvent arg1) {
                        //int []a=new int[-1];
                        joystick.drawStick(arg1);
                        if(arg1.getAction() == MotionEvent.ACTION_DOWN
                                || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                            //  MainActivity.textView11.setText("X : " + String.valueOf(joystick.getX()));
                            //MainActivity.textView22.setText("Y : " + String.valueOf(joystick.getY()));
                            //MainActivity.textView33.setText("Angle : " + String.valueOf(joystick.getAngle()));
                            //MainActivity.textView44.setText("Distance : " + String.valueOf(joystick.getDistance()));
                            int direction = joystick.get8Direction();
                            if(direction == Joystick.STICK_UP) {
                                //  textView5.setText("Direction : Up");
                            } else if(direction == Joystick.STICK_UPRIGHT){
                                //textView5.setText("Direction : Up Right");
                            } else if(direction == Joystick.STICK_RIGHT){
                                //textView5.setText("Direction : Right");
                            } else if(direction == Joystick.STICK_DOWNRIGHT){
                                //textView5.setText("Direction : Down Right");
                            } else if(direction == Joystick.STICK_DOWN){
                                //textView5.setText("Direction : Down");
                            } else if(direction == Joystick.STICK_DOWNLEFT){
                                //  textView5.setText("Direction : Down Left");
                            } else if(direction == Joystick.STICK_LEFT){
                                //  textView5.setText("Direction : Left");
                            } else if(direction == Joystick.STICK_UPLEFT){
                                //  textView5.setText("Direction : Up Left");
                            } else if(direction == Joystick.STICK_NONE){
                                //  textView5.setText("Direction : Center");
                            }
                        } else if(arg1.getAction() == MotionEvent.ACTION_UP){
                    /*textView1.setText("X :");
                    textView2.setText("Y :");
                    textView3.setText("Angle :");
                    textView4.setText("Distance :");
                    textView5.setText("Direction :");*/
                        }
                        return true;
                    }
                });
        setFocusable(true);
        getHolder().addCallback(this);
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Log.d("TAG", "surfaceChanged: format: "+format+"width :"+width+"height: "+height);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry=true;
        while (retry){
            try{
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry=false;
        }
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(lose) {
            starMove.draw(canvas);
            enemyAstroid.draw(canvas);
            String text_file = "0";
            try {
                fis = new BufferedReader(new InputStreamReader(context.openFileInput("ex.txt")));
                String line;
                StringBuffer text = new StringBuffer();
                while ((line = fis.readLine()) != null){
                    text.append(line + "\n");
                    text_file=line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String text_file2="0";
            try {
                reader = new BufferedReader(new InputStreamReader(context.openFileInput("exr.txt")));
                String line;
                StringBuffer text = new StringBuffer();
                while ((line = reader.readLine()) != null){
                    text.append(line + "\n");
                    text_file2=line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(100);
                paint.setAlpha(200);
                canvas.drawText("SCORE: " + text_file, screenWidth * 60 / 100, screenHeight * 30 / 100, paint);
                canvas.drawText("BEST: " + text_file2, screenWidth * 20 / 100, screenHeight * 30 / 100, paint);
                canvas.drawText("TAP TO START", screenWidth * 35 / 100, screenHeight * 50 / 100, paint);
            characterSprite.setImage(characterSprite.getBitmapPlayer());
            score=0;

        }else{
            starMove.draw(canvas);
            shoot.clickedOnLayoutDraw(characterSprite,enemyAstroid);
            shoot.draw(canvas);
            characterSprite.draw(canvas);
            gitch.draw(canvas);
            enemyAstroid.draw(canvas);
            if(!b_timeForAnimation) {
                timeForAnimation1 = System.currentTimeMillis();
            }
            if(checkForAnimation){
                paint.setColor(BLACK);
                time = System.currentTimeMillis();
                checkForAnimation = false;
                b_timeForAnimation=true;
            }else{
                time2 = System.currentTimeMillis();
                if (time2 - time > 400) {
                    if (b_time) {
                        checkForAnimation = true;
                        b_time = false;
                    }
                    paint.setColor(Color.WHITE);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setTextSize(70);
                    canvas.drawText("SCORE: " + score, screenWidth * 78 / 100, screenHeight * 10 / 100, paint);
                    if(b_timeForAnimation) {
                        timeForAnimation2 = System.currentTimeMillis();
                        b_timeForAnimation=false;
                    }
                }
            }
        }
    }

    public void update() {
        starMove.update(score);
        shoot.update(enemyAstroid);
        enemyAstroid.update(this,characterSprite,shoot);
        characterSprite.update((float) (joystick.getX()*0.7), (float) (joystick.getY()*0.7));
        gitch.update(characterSprite.getX(),characterSprite.getY());
        if(b_timerScore){
            timerScore=System.currentTimeMillis();
            b_timerScore=false;
        }
        timer2Score=System.currentTimeMillis();
        if(timer2Score-timerScore>93) {
            score++;
            b_timerScore=true;
        }
    }

    public boolean isLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }

    public void setLose2(boolean lose2) {
        this.lose2 = lose2;
    }

    public void setB_file(boolean b_file) {
        this.b_file = b_file;
    }

    public void setB132(boolean b132) {
        this.b132 = b132;
    }

    public int getScore() {
        return score;
    }

    public void setB_time(boolean b_time) {
        this.b_time = b_time;
    }

    public void setCheckForAnimation(boolean check) {
        this.checkForAnimation = check;
    }



    public boolean isLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        isLegal = legal;
    }

    class Dt extends Thread{
        public Dt(){

        }
        @Override
        public void run(){
            while(true) {
                if (lose2) {
                    if(b_file) {
                        try {
                            String s = "" + score;
                            fos = context.openFileOutput("ex.txt", MODE_PRIVATE);
                            fos.write(s.getBytes());
                            fos.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String text_file = "0";
                        try {
                            reader = new BufferedReader(new InputStreamReader(context.openFileInput("exr.txt")));
                            String line;
                            StringBuffer text = new StringBuffer();
                            while ((line = reader.readLine()) != null) {
                                text.append(line + "\n");
                                text_file = line;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int a = Integer.parseInt(text_file);
                        try {
                            String s = "" + score;
                            String s2 = "" + a;
                            out = context.openFileOutput("exr.txt", MODE_PRIVATE);
                            if (score > a) {
                                out.write(s.getBytes());
                            } else {
                                out.write(s2.getBytes());
                            }
                            out.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    checkForAnimation = false;
                    b_time = false;
                    b_timeForAnimation = true;
                    b_timerScore = true;
                    b132 = true;
                    enemyAstroid.setRscore(0);
                    starMove.setRscore(0);
                    enemyAstroid.setUpdate_y(5);
                    starMove.setUpdate_y(10);
                    layout.setX(screenWidth * 100);
                    shoot.getButton().setX(screenWidth * 100);
                    lose2 = false;
                    isLegal = false;
                }
            }
        }
    }
}
