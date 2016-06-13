package activity.tofun.com;

import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ryg.slideview.R;

public class RenrenActivity extends Activity implements View.OnTouchListener{

    //菜单左侧margin
    private int mLeftMargin;
    //菜单状态
    private boolean mIsMenuShow;
    private LinearLayout mMenu;
    private LinearLayout mContent;
    //菜单左侧最大margin
    private int mLeftEdage;
    //c菜单属性
    private LinearLayout.LayoutParams mMenuParams;
    //坐标
    private int mXDown;
    private int mXMove;
    private int mXUp;
    //函数执行一次
    private boolean mHasMeasured;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renren);
        init();
    }

    private void init() {
        mMenu = (LinearLayout) findViewById(R.id.menu);
        mContent = (LinearLayout) findViewById(R.id.content);
        mMenuParams = (LinearLayout.LayoutParams) mMenu.getLayoutParams();
        mHasMeasured = false;
        ViewTreeObserver vto = mMenu.getViewTreeObserver();
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        final int width = dm.widthPixels;
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if(!mHasMeasured){
                    mLeftMargin = -mMenu.getMeasuredWidth();
                    mLeftEdage = -mLeftMargin;
                    mMenuParams.leftMargin = mLeftMargin;
                    mHasMeasured = true;
                    mContent.getLayoutParams().width = width;
                    Log.i("tofun","mLeftMargin:"+mLeftMargin);
                }
                return true;
            }
        });
        mIsMenuShow = false;
        mContent.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mXDown = (int) event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = (int)event.getRawX();
                int distancX = mXMove-mXDown;
                if(mIsMenuShow){

                }else{
                    //菜单未显示，则从左侧显示出来
                    mLeftMargin += distancX;
                    if(mLeftMargin>=0){
                        mLeftMargin = 0;
                    }
                    if(mLeftMargin<-mLeftEdage){
                        mLeftMargin = -mLeftEdage;
                    }
                    Log.i("tofun","mLeftMargin:"+mLeftMargin);
                    mMenuParams.leftMargin = mLeftMargin;
                }
                mMenu.setLayoutParams(mMenuParams);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
