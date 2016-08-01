package com.example.zyh.webviewtest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public  static  String url1="http://www.baidu.com";
    public  static  String url2="http://www.4399.com";
    public  static  String url3="http://my.csdn.net";
    final Activity activity=this;
    public  WebView webView;
    public  WebSettings webSetting;
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  setContentView(R.layout.activity_main);
        Uri uri=Uri.parse("http://www.baidu.com");
        Intent intent =new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);　　*/

        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        webView=(WebView)findViewById(R.id.web);
        webSetting=webView.getSettings();


        webSetting.setJavaScriptEnabled(true);//启用Ｊｓ建议开启
        webSetting.setBuiltInZoomControls(true);//内置缩放大小功能，建议开启
        webSetting.setLoadsImagesAutomatically(true);//支持自动加载图片　　　建议开启
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setTextZoom(200);//设置文本大小缩放程度，默认１００，不缩放，启用<100缩放可能可以看到更多文字
        webSetting.setUseWideViewPort(true);   //设置视窗，开启可以看到更多信息，有点类似以电脑状态访问界面
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);//自适应屏幕，有四个值，目前没发现区别，先使用Normal
        webSetting.setSaveFormData(false);//保存表单数据

        /*
        * 重写此方法，自己加载网页；返回true当前主机程序处理，
        * return false当前Webview处理
        */
        webView.setWebViewClient(new WebViewClient()
        {
            public  boolean shouldOverrideUrlLoading(WebView webView,String url){

                return  false;
            }

        });

        webView.loadUrl("file:///android_asset/demo.html");


        webView. addJavascriptInterface(new JsObject(),"lala");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        webView.loadUrl("javascript:fillContent()");
                    }
                });
    }


    /*
    *开启返回功能，即按下返回键返回上一个网页，
    *若没用，则退出当前Activity
    *监听按妞事件，这里监听　Back键

     */
    public boolean onKeyDown(int KeyCode, KeyEvent event){
        if (KeyCode==KeyEvent.KEYCODE_BACK){
            if(webView.canGoBack()){
                webView.goBack();
                return true;
            }
            else {
                MainActivity.this.finish();
            }
        }
        return super.onKeyDown(KeyCode,event);
    }


    public  class  JsObject {
        @JavascriptInterface
        public void startMap() {
            Intent mIntent = new Intent();
            ComponentName component = new ComponentName("com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity");
            mIntent.setComponent(component);

            startActivity(mIntent);
        }
    }
}
