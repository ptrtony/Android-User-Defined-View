package com.sinata.androidlearnhencoder;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        CircleView circleView = findViewById(R.id.circleView);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(circleView,"radius",50,100);
//        objectAnimator.setDuration(1000);
//        objectAnimator.start();
//        ImageView cameraView = findViewById(R.id.cameraview);
//        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(cameraView,"bottomFlip",30);
//        bottomFlipAnimator.setDuration(3000);
////        bottomFlipAnimator.start();
//
//        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(cameraView,"flipRotation",270);
//        flipRotationAnimator.setDuration(3000);
////        flipRotationAnimator.start();
//
//        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(cameraView,"topFlip",-30);
//        topFlipAnimator.setDuration(3000);
////        topFlipAnimator.start();
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(bottomFlipAnimator,flipRotationAnimator,topFlipAnimator);
//        animatorSet.setDuration(1000);
//        animatorSet.start();

        /**
         * 多个属性动画同时做动画
         */
//        PropertyValuesHolder bottomFlipViewHolder = PropertyValuesHolder.ofFloat("bottomFlip", 30);
//        PropertyValuesHolder flipRotationViewHolder = PropertyValuesHolder.ofFloat("flipRotation", 270);
//        PropertyValuesHolder topFlipViewHolder = PropertyValuesHolder.ofFloat("topFlip", -30);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(cameraView, bottomFlipViewHolder, flipRotationViewHolder, topFlipViewHolder);
//        objectAnimator.setStartDelay(1000);
//        objectAnimator.setDuration(1000);
//        objectAnimator.start();


        /**
         * 关键帧
         */
//        float distance = Utils.dp2px(300);
//        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
//        Keyframe keyframe2 = Keyframe.ofFloat(0.1f, 0.4f * distance);
//        Keyframe keyframe3 = Keyframe.ofFloat(0.9f, 0.6f * distance);
//        Keyframe keyframe4 = Keyframe.ofFloat(1, distance);
//        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("translationX",keyframe1,
//                keyframe2,keyframe3,keyframe4);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(cameraView,propertyValuesHolder);
//        objectAnimator.setStartDelay(3000);
//        objectAnimator.setDuration(3000);
//        objectAnimator.start();


    }
}