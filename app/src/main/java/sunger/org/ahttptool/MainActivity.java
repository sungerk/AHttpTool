package sunger.org.ahttptool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.sunger.lib.http.asyn.AsyncHttpCliect;
import org.sunger.lib.http.asyn.TextResponseHandler;
import org.sunger.lib.http.download.delegate.DownloadDelegate;
import org.sunger.lib.http.download.handler.DownloadHandler;

public class MainActivity extends Activity {


    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncHttpCliect cliect = new AsyncHttpCliect();
        cliect.get("https://www.baidu.com/", new TextResponseHandler() {
            @Override
            public void onSuccess(int stateCode, String data) {
                Log.d("sunger", data);
            }
        });

        DownloadDelegate downloadDelegate = new DownloadDelegate(
                this,
                "http://tp2.sinaimg.cn/5703290681/180/5737259999/1",
                "/sdcard/a/", new DownloadHandler() {
            @Override
            protected void onFinish(String msg) {

            }

            @Override
            public void onError(String msg) {

            }

            @Override
            protected void onSpeed(String speed) {

            }
        });
        // 开始下载
        downloadDelegate.start();

    }

}
