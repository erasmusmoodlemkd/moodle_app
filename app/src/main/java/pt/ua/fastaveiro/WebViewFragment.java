package pt.ua.fastaveiro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import pt.edu.ukim.fzf.moodlefzf.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class WebViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private MoodleApplication application = null;
    private WebView webView;

    public WebViewFragment() {
        // Required empty public constructor
    }

    public static WebViewFragment newInstance() {
        WebViewFragment fragment = new WebViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.application = ((MoodleApplication)getContext().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        webView = (WebView) view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(this.getContext().getString(R.string.moodle_url));
        try {
            MoodleHttpClient client = new MoodleHttpClient(getActivity(),application.externalAssetsRepository);
            webView.setWebViewClient(client);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean canGoBack() {
        return this.webView != null && this.webView.canGoBack();
    }

    public void goBack() {
        if(this.webView != null) {
            this.webView.goBack();
        }
    }

}