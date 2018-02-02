package com.example.zengcanwen.zdyanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * 创建小球Activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.image_view1) ;
        ImageView imageView2 = findViewById(R.id.image_view2) ;
        ImageView imageView3 = findViewById(R.id.image_view3) ;
        ImageView imageView4 = findViewById(R.id.image_view4) ;
        ImageView imageView5 = findViewById(R.id.image_view5) ;
        ImageView imageView6 = findViewById(R.id.image_view6) ;
        setAnimation(imageView , MyAnimation.LEFT);
        setAnimation(imageView2 , MyAnimation.LEFT);
        setAnimation(imageView3 , MyAnimation.LEFT);
        setAnimation(imageView4 , MyAnimation.RIGHT);
        setAnimation(imageView5 , MyAnimation.RIGHT);
        setAnimation(imageView6 , MyAnimation.RIGHT);

    }

    private void setAnimation(ImageView imageView , int flag){
        MyAnimation myAnimation = new MyAnimation(flag) ;
        myAnimation.setDuration(10000);
        myAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        myAnimation.start();
        imageView.setAnimation(myAnimation);

    }
}
