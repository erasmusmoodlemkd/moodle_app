package mk.edu.ukim.fzf.moodlefzf;

import android.content.ContextWrapper;
import android.webkit.WebView;
import android.webkit.WebViewClient;


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
        // Several people probably worked hard on the design of this Web page, let's hope they won't see what's next
        injectCSS(webView);
    }

    // Inject CSS method: read style.css from assets folder
// Append stylesheet to document head
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
