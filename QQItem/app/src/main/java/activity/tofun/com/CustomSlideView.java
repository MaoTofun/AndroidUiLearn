package activity.tofun.com;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Toast;

import com.ryg.slideview.R;
/**
 * Created by Administrator on 2016-5-18 0018.
 */
public class CustomSlideView extends LinearLayout {

    private Scroller mScroller;

    public CustomSlideView(Context context) {
        super(context);
        init();
    }

    public CustomSlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomSlideView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init(){
        mScroller = new Scroller(getContext());
        View.inflate(getContext(), R.layout.custom_slide_layout,this);
        setOrientation(LinearLayout.HORIZONTAL);
        smoothScrollerTo();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
//        Toast.makeText(getContext(), "onTouch", Toast.LENGTH_SHORT).show();
        smoothScrollerTo();
        return true;
    }

    private void smoothScrollerTo(){
        mScroller.startScroll(0,0,-100,0,1);
    }
}
