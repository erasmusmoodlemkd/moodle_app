package mk.edu.ukim.fzf.moodlefzf;

import android.content.ContextWrapper;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;


public class MoodleHttpClient extends WebViewClient {

    private ContextWrapper contextWrapper;
    private ExternalAssetsRepository externalAssetsRepository;

    public MoodleHttpClient( ContextWrapper contextWrapper, ExternalAssetsRepository externalAssetsRepository) {
        this.contextWrapper = contextWrapper;
        this.externalAssetsRepository = externalAssetsRepository;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }


    @Override
    public void onPageFinished(WebView webView, String url) {
        injectCSS(webView);
    }

    private void injectCSS(WebView webView) {
        webView.loadUrl("javascript:(function() {" +
                "var parent = document.getElementsByTagName('head').item(0);" +
                "var style = document.createElement('link');" +
                "style.rel = 'stylesheet';" +
                "style.type = 'text/css';" +
                "style.href = '"+this.contextWrapper.getString(R.string.moodle_custom_css)+"?ver=0126';" +
                "parent.appendChild(style)" +
                "})()");
    }
}
