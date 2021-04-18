package mk.edu.ukim.fzf.moodlefzf;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    private SplashScreenFragment splashScreenFragment;
    /* access modifiers changed from: private */
    public WebViewFragment webViewFragment;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        if (this.webViewFragment == null) {
            this.webViewFragment = WebViewFragment.newInstance();
        }
        if (this.splashScreenFragment == null) {
            this.splashScreenFragment = SplashScreenFragment.newInstance();
        }
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add((int) R.id.fragment_container_view, (Fragment) this.splashScreenFragment, (String) null).commit();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    MainActivity.this.getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace((int) R.id.fragment_container_view, (Fragment) MainActivity.this.webViewFragment, (String) null).commit();
                }
            }, 3000);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (keyCode == KeyEvent.KEYCODE_BACK && this.webViewFragment != null && this.webViewFragment.canGoBack()) {
                        this.webViewFragment.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
