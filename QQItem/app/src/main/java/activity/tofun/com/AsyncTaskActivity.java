package activity.tofun.com;

import android.animation.ObjectAnimator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ryg.slideview.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
    }

    public void doGet(View view) {
        new GetAsyncTask().execute(new Object[]{});
    }

    public void doPost(View view) {
        new PostAsyncTask().execute(new Object[]{});
    }

    class PostAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            String result = "";
            try {
                URL url = new URL("http://192.168.67.100/test.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setConnectTimeout(6 * 1000);
                con.setRequestMethod("POST");
                con.setRequestProperty("charset", "utf-8");
                String param = "name=tofun&sex=male&age=23";
                byte[] bytes = param.getBytes();
                con.getOutputStream().write(bytes);
                InputStreamReader in = new InputStreamReader(con.getInputStream());
                BufferedReader reader = new BufferedReader(in);
                String line = "";
                if ((line = reader.readLine()) != null) {
                    result += line;
                }
                reader.close();
                con.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Object o) {
            TextView tv = (TextView) findViewById(R.id.html_tv);
            tv.setText("html:" + o.toString());
        }
    }

    class GetAsyncTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            Toast.makeText(getBaseContext(), "onPreExecute", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            String result = "html is :";
            try {
                URL url = new URL("http://192.168.67.100/test.html");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(6000);
                if (con.getResponseCode() == 200) {
                    Log.i("tofun", "responseCode:200");
                    InputStreamReader in = new InputStreamReader(con.getInputStream());
                    BufferedReader reader = new BufferedReader(in);
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                    in.close();
                }
                con.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Object o) {
            TextView tv = (TextView) findViewById(R.id.html_tv);
            tv.setText("html:" + o.toString());
        }
    }

}
