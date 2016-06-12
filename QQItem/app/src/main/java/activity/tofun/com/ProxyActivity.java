package activity.tofun.com;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.ryg.slideview.R;
import com.tofun.proxy.InvokeImpl;
import com.tofun.proxy.Operate;
import com.tofun.proxy.OperateImpl;

import java.lang.reflect.Proxy;

public class ProxyActivity extends Activity {
    private TextView tv;
    private Operate operate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        tv = (TextView) findViewById(R.id.tv);
        InvokeImpl invokeHandler = new InvokeImpl(new OperateImpl());
        operate = (Operate) Proxy.newProxyInstance(Operate.class.getClassLoader(),new Class[]{Operate.class},invokeHandler);
    }

    public void operateMethod1(View view){
        tv.setText((String)operate.method1("方法1"));
    }

    public void operateMethod2(View view){
        tv.setText((String)operate.method2("方法2"));
    }

}
