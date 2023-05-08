package com.example.androidwebviewtest3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.RestrictionsManager;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.security.Permissions;

public class MainActivity extends AppCompatActivity {

    WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA
        , Manifest.permission.MODIFY_AUDIO_SETTINGS}, 101);
        setContentView(R.layout.activity_main);
        WebView.setWebContentsDebuggingEnabled(true);
        webView = findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUserAgentString(WebSettings.getDefaultUserAgent(this) + " ViverseApp/1.0 HTCVRSDET");
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

        });
        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onPermissionRequest(PermissionRequest request) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        request.grant(request.getResources());
                    }
                });

            }
        });
        CookieManager m = CookieManager.getInstance();
        m.setAcceptCookie(true);

        webView.loadUrl("https://hiukim.github.io/mind-ar-js-doc/face-tracking-samples/minimal.html");

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(webView != null) {
            webView.pauseTimers();
            webView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(webView != null) {
            webView.resumeTimers();
            webView.onResume();
        }
    }
}