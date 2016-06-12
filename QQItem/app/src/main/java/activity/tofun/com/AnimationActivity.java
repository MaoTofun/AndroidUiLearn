package activity.tofun.com;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.ryg.slideview.R;

public class AnimationActivity extends Activity {
    private ImageView image;
    private int mScreenWidth, mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        image = (ImageView) findViewById(R.id.image);
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        mScreenHeight = dm.heightPixels;
        mScreenWidth = dm.widthPixels;
    }

    public void objectAnimationRun(final View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(image, "tofun", 1.0f, 0.0f).setDuration(1000);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float val = (float) animation.getAnimatedValue();
                Log.i("Tofun", "AnimationValue:" + val);
                image.setAlpha(val);
                image.setScaleX(val);
                image.setScaleY(val);
            }
        });
    }

    public void verticalAnimationRun(View view) {
        ValueAnimator anim = ValueAnimator.ofFloat(0.0f, mScreenHeight / 2 - image.getHeight() / 2);
        anim.setTarget(image);
        anim.setDuration(2000).start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }

    public void pwxAnimationRun(View view) {
        ValueAnimator anim = new ValueAnimator();
        anim.setDuration(2000);
        anim.setObjectValues(new PointF(0,0));
        anim.setInterpolator(new LinearInterpolator());
        anim.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                Log.e("tofun:", fraction * 3 + "");
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF point = (PointF) animation.getAnimatedValue();
                image.setX(point.x);
                image.setY(point.y);

            }
        });
    }
}
