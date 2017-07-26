package lrc.com.testup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.youtu.Youtu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import static com.common.Config.APP_ID;
import static com.common.Config.SECRET_ID;
import static com.common.Config.SECRET_KEY;

public class MainActivity extends AppCompatActivity {

    String TAG = "main";

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            TextView tv = (TextView) findViewById(R.id.tv_res);
            tv.setText(msg.obj.toString());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = (ImageView) findViewById(R.id.img_pic1);
        ImageView img2 = (ImageView) findViewById(R.id.img_pic2);
        img.setImageResource(R.drawable.multi2);
        img2.setImageResource(R.drawable.multi3);



        new Thread() {
            public void run() {
                Youtu faceYoutu = new Youtu(APP_ID, SECRET_ID, SECRET_KEY, Youtu.API_YOUTU_END_POINT);
                Bitmap bitmapA = BitmapFactory.decodeResource(getResources(), R.drawable.multi2);
                Bitmap bitmapB = BitmapFactory.decodeResource(getResources(), R.drawable.multi3);
                JSONObject respose;
                try {
                    respose = faceYoutu.FaceCompare(bitmapA, bitmapB);
                    Message msg = new Message();
                    msg.obj = respose;
                    handler.sendMessage(msg);
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            };
        }.start();


    }
}
