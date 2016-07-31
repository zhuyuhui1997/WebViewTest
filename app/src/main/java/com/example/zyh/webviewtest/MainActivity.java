package com.example.zyh.webviewtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
       public  static  String url1="http://www.baidu.com";
    public  static  String url2="http://www.4399.com";
    final Activity activity=this;
    public  WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  setContentView(R.layout.activity_main);
        Uri uri=Uri.parse("http://www.baidu.com");
        Intent intent =new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);*/



        setContentView(R.layout.activity_main);
        webView=(WebView)findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);//启用Ｊｓ
        webView.getSettings().setBuiltInZoomControls(true);//内置缩放
        webView.getSettings().setLoadsImagesAutomatically(true);//支持自动加载图片
           webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view,int progress){
                activity.setProgress(progress*1000);
            }

        });
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient()
        {
            public  boolean shouldOverrideUrlLoading(WebView webView,String url){
                webView.loadUrl(url);
                return  true;//重写此方法，自己加载网页；
            }

        });

webView.loadUrl(url2);


    }
}
