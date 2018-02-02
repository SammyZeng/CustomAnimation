package com.example.zengcanwen.zdyanimation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.Random;

/**
 * Y轴为cos曲线，X轴为sin曲线的动画轨迹
 * Created by zengcanwen on 2018/1/9.
 */

public class MyAnimation extends Animation implements Animation.AnimationListener {
    private Matrix matrix;
    private Random random;
    private float startDegreesX;
    private float startDegreesY;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    private int flag;
    private static final int SCALETIMEX = 3;
    private static final int SCALETIMEY = 3;
    private float dx;
    private float dy;
    private float startDegrees360X;


    public MyAnimation(int flag) {
        this.flag = flag;
        random = new Random();
        setAnimationListener(this);
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        dx = 0;
        dy = 0;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        //获取当前需要设定的角度
        float degreesPIy = (float) (interpolatedTime * Math.PI * 2);
        float degreesPIx = (float) (interpolatedTime * Math.PI);
        float degrees360X = interpolatedTime * 360  ;


        float distanceX = (float) Math.sin(degreesPIx + startDegreesX);
        float distanceY = (float) Math.sin(degreesPIy + startDegreesY);
        //当X轴的值为负数时，结束动画，避免回退现象
        if (distanceX < 0) {
            cancel();
            reset();
            start();
        }

        if (flag == LEFT) {  //保证X为正，左边的都向右移
            dx = SCALETIMEX * distanceX + dx;
        } else if (flag == RIGHT) {//保证X为负，右边的都向左移
            dx = -SCALETIMEX * distanceX + dx;
        } else {
            dx = 0;
        }
        dy = SCALETIMEY * distanceY + dy;

        matrix = t.getMatrix();
        matrix.postTranslate(dx, dy);

        //根据x轴的变化改变透明度，使在动画结束时透明度为0
        t.setAlpha(1 - (degrees360X / (180 - startDegrees360X)));

    }

    @Override
    public void onAnimationStart(Animation animation) {

        //设置随机初始X轴角度（控制在0~135） ， 可以随机X轴的移动速度
        startDegrees360X = random.nextInt(135);
        startDegreesX = (float) Math.toRadians(startDegrees360X);
        //设置随机初始Y轴角度（控制在0~360） ， 可以随机X轴的移动速度
        startDegreesY = (float) Math.toRadians(random.nextInt(360));

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
