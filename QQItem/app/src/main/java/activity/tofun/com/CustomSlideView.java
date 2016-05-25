package activity.tofun.com;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.ryg.slideview.R;

/**
 * Created by Administrator on 2016-5-18 0018.
 */
public class CustomSlideView extends LinearLayout {

    private Scroller mScroller;
    private TextView mBtnDelete;
    private float mLastX,mLastY;

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

    private void init() {
        mScroller = new Scroller(getContext());
        setOrientation(LinearLayout.HORIZONTAL);
        View.inflate(getContext(), R.layout.custom_slide_layout, this);
        mBtnDelete = (TextView) findViewById(R.id.btn_delete);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float scrollX = getScrollX();
        float newX = event.getX();
        float newY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float deltaX = newX - mLastX;
                float newScrollX = scrollX - deltaX;
                if (deltaX != 0) {
                    if (newScrollX < 0) {
                        newScrollX = 0;
                    } else if (newScrollX > mBtnDelete.getWidth()) {
                        newScrollX = mBtnDelete.getWidth();
                    }
                    this.scrollTo((int) newScrollX, 0);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
//                if(scrollX>mBtnDelete.getWidth()*0.5){
////                    smoothScrollerTo(mBtnDelete.getWidth());
//                    this.smoothScrollTo(mBtnDelete.getWidth(), 0);
//                }
                int newScrollX = 0;
                if (scrollX - mBtnDelete.getWidth() * 0.5 > 0) {
                    newScrollX = mBtnDelete.getWidth();
                }
                this.smoothScrollTo(newScrollX, 0);
                break;
            }
        }
        mLastX = event.getX();
        mLastY = event.getY();
        return true;
    }

    private void smoothScrollerTo(int deltaX) {
        int scrollX = getScrollX();
        mScroller.startScroll(scrollX, 0, deltaX, 0, 1);
        invalidate();
    }

    private void smoothScrollTo(int destX, int destY) {
        // 缓慢滚动到指定位置
        Log.i("tofun", "up");
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX, 0, delta, 0, Math.abs(delta) * 3);
        invalidate();
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
