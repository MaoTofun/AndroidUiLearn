package activity.tofun.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ryg.slideview.R;

public class AnimationActivity extends Activity {
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        image = (ImageView) findViewById(R.id.image);
        Animation anim_set = AnimationUtils.loadAnimation(this,R.anim.animation_set);
        anim_set.setDuration(5000);
        image.startAnimation(anim_set);
        /*
        Animation anim_alpha= AnimationUtils.loadAnimation(this,R.anim.animation_alpha);
        anim_alpha.setDuration(1000);
        image.startAnimation(anim_alpha);
        Animation anim_translate = AnimationUtils.loadAnimation(this,R.anim.animation_translate);
        anim_translate.setDuration(1000);
        image.startAnimation(anim_translate);
        Animation anim_rotate = AnimationUtils.loadAnimation(this,R.anim.animation_rotate);
        anim_rotate.setDuration(1000);
        image.startAnimation(anim_rotate);
        Animation anim_scale = AnimationUtils.loadAnimation(this,R.anim.animation_scale);
        anim_scale.setDuration(1000);
        image.startAnimation(anim_scale);
        */
    }
}
