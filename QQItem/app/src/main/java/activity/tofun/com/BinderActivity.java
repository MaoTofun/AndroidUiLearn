package activity.tofun.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.ryg.slideview.R;

public class BinderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
    }

    public void openActivity(View view){
        Intent intent = new Intent("com.tofun.MyApp", Uri.parse("tofun://www.tofun.com:80"));
        try{
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
