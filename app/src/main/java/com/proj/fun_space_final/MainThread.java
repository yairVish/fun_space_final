package com.proj.fun_space_final;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    private Canvas canvas;
    public MainThread(SurfaceHolder surfaceHolder,GameView gameView){
        super();
        this.gameView=gameView;
        this.surfaceHolder=surfaceHolder;
    }
    public void setRunning(boolean isRunning){
        running=isRunning;
    }
    @Override
    public void run(){
        while (running){
            canvas=null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder){
                    this.gameView.update();
                    this.gameView.draw(canvas);

                }
            }catch (Exception e){
            }     finally{
                if(canvas!=null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){e.printStackTrace();}
                }
            }
        }
    }
}