package com.proj.fun_space_final;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean b_flie=false;
    private boolean se=true;
    RelativeLayout layout_joystick,mainLayout,backgroundJoystick;
    ImageView image_joystick, image_border;
    private Bitmap bitmapPlayer;
    private Bitmap bitmaPlayerNoFire;
    private Bitmap bitmapStar;
    private Bitmap bitmapShoot;
    private Bitmap bitmapGitch;
    private Bitmap []bitmapAsteroids = new Bitmap[18];
    private Bitmap []bitmapExplosions = new Bitmap[5];

    private int maxWidth,maxHeight;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView11;
    private TextView textView22;
    private TextView textView33;
    private TextView textView44;
    private TextView textView55;
    private ImageView button;
    ImageView b;
    public int xr;
    public int xr2;
    boolean b23=true;
    public static boolean b1=false;
    public static final String TAG="Main";
    public int screenWidth= Resources.getSystem().getDisplayMetrics().widthPixels;
    public int screenHeight=Resources.getSystem().getDisplayMetrics().heightPixels;
    private GameView gameView;
    private EnemyAstroid enemyAstroid;
    private StarMove starMove;
    private Shoot shoot;
    private Gitch gitch;
    private CharacterSprite characterSprite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bitmapPlayer=BitmapFactory.decodeResource(getResources(),R.drawable.player);
        bitmaPlayerNoFire = BitmapFactory.decodeResource(getResources(),R.drawable.player_no_fire);
        bitmapStar = BitmapFactory.decodeResource(getResources(),R.drawable.star);
        bitmapShoot=BitmapFactory.decodeResource(getResources(),R.drawable.shoot);
        bitmapGitch=BitmapFactory.decodeResource(getResources(),R.drawable.gitch4);
        b = (ImageView) findViewById(R.id.button);
        button=new ImageView(this);
        button.setLayoutParams(b.getLayoutParams());
        button.setImageResource(R.drawable.buttonshoot);

        layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);
        mainLayout = (RelativeLayout)findViewById(R.id.layout);

        final RelativeLayout.LayoutParams params_jos = new RelativeLayout.LayoutParams
                (layout_joystick.getWidth(), layout_joystick.getHeight());
        final RelativeLayout.LayoutParams params_button = new RelativeLayout.LayoutParams
                (screenWidth*27/100, screenHeight*23/100);

        params_button.rightMargin = screenWidth *35/100;
        params_button.topMargin=screenHeight*37/100;

        params_jos.rightMargin =screenWidth*72/100;
        params_jos.topMargin = screenHeight*50/100;

        backgroundJoystick=new RelativeLayout(this);
        backgroundJoystick.setLayoutParams(params_jos);
        backgroundJoystick.setBackground(layout_joystick.getBackground());
        backgroundJoystick.setBackgroundResource(R.drawable.joistick_layout);
        maxWidth = checkMaxWidth();
        maxHeight = checkMaxHeight();
        defineGame();
        mainLayout.addView(gameView);
        mainLayout.addView(backgroundJoystick);
        mainLayout.addView(button);
        backgroundJoystick.setX(screenWidth*100);
        button.setX(screenWidth*100);
        starMove.define();
        enemyAstroid.define();
        shoot.define();
        mainLayout.setOnTouchListener(
            new View.OnTouchListener(){
                @Override
                public boolean onTouch(View arg0, MotionEvent arg1) {
                    if (gameView.isLose()) {
                        gameView.setB_file(true);
                        backgroundJoystick.setX(screenWidth * 5 / 100);
                        button.setX(screenWidth * 85 / 100);
                        starMove.define();
                        shoot.define();
                        gitch.define();
                        //oK = true;
                        enemyAstroid.define();
                        gameView.setLegal(true);
                        characterSprite.setX(screenWidth * 27 / 100);
                        characterSprite.setY(screenHeight * 46 / 100);
                        gameView.setLose(false);
                        gameView.setB132(true);

                        if (b23) {
                            b23 = false;
                        }
                        b1 = true;
                    } else {
                        se=false;
                    }
                    return false;
                }
            });
    }
    private void defineGame(){
         bitmapAsteroids[0]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid1);
         bitmapAsteroids[1]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid12);
         bitmapAsteroids[2]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid13);
         bitmapAsteroids[3]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid14);
         bitmapAsteroids[4]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid15);
         bitmapAsteroids[5]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid16);
         bitmapAsteroids[6]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid17);
         bitmapAsteroids[7]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid18);
         bitmapAsteroids[8]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid19);
         bitmapAsteroids[9]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid120);
         bitmapAsteroids[10]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid121);
         bitmapAsteroids[11]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid122);
         bitmapAsteroids[12]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid123);
         bitmapAsteroids[13]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid124);
         bitmapAsteroids[14]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid125);
         bitmapAsteroids[15]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid126);
         bitmapAsteroids[16]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid127);
         bitmapAsteroids[17]=BitmapFactory.decodeResource(getResources(),R.drawable.astroid128);

         bitmapExplosions[0]=BitmapFactory.decodeResource(getResources(),R.drawable.explosions);
         bitmapExplosions[1]=BitmapFactory.decodeResource(getResources(),R.drawable.explosions2);
         bitmapExplosions[2]=BitmapFactory.decodeResource(getResources(),R.drawable.explosions3);
         bitmapExplosions[3]=BitmapFactory.decodeResource(getResources(),R.drawable.explosions4);
         bitmapExplosions[4]=BitmapFactory.decodeResource(getResources(),R.drawable.explosions5);
         
        enemyAstroid = new EnemyAstroid(bitmapAsteroids,bitmapExplosions);
        starMove = new StarMove(bitmapStar);
        shoot = new Shoot(bitmapShoot, button);
        gitch = new Gitch(bitmapGitch);
        characterSprite=new CharacterSprite(bitmaPlayerNoFire,bitmapPlayer,true);
        gameView = new GameView(MainActivity.this,backgroundJoystick,button
                ,enemyAstroid,starMove,shoot,characterSprite,gitch);
        gameView.startGameView();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public int checkMaxWidth(){
        Display display=getWindowManager().getDefaultDisplay();
        Point size =new Point();
        display.getSize(size);
        return size.x;
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public int checkMaxHeight() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }
}