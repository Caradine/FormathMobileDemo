package be.formath.formathmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        WebView webView = (WebView) findViewById(R.id.display_tutorial);
        //webView.setText(Html.fromHtml("tutorial_text"));
        webView.loadData(getString(R.string.tutorial_text), "text/html", null);
    }
}
