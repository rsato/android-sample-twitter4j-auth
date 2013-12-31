package jp.digitalcloud.sample.twitter.auth;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

    private Twitter mTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAuth = (Button) findViewById(R.id.buttonAuth);
        buttonAuth.setOnClickListener(this);
        Button buttonSendPin = (Button) findViewById(R.id.buttonSendPin);
        buttonSendPin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAuth:
                mTwitter = new TwitterFactory().getInstance();
                mTwitter.setOAuthConsumer(getString(R.string.twitter_consumer_key), getString(R.string.twitter_consumer_key_secret));
                mTwitter.setOAuthAccessToken(null);
                TwitterOAuthRequestTokenCallbacks oAuthRequestTokenCallbacks = new TwitterOAuthRequestTokenCallbacks(this, mTwitter);
                getLoaderManager().initLoader(0, null, oAuthRequestTokenCallbacks);
                break;
            case R.id.buttonSendPin:
                EditText editTextPin = (EditText) findViewById(R.id.editTextPin);
                if (editTextPin.getText().length() > 0 && mTwitter != null) {
                    getLoaderManager().initLoader(1, null, new TwitterOAuthAccessTokenCallbacks(this, mTwitter, editTextPin.getText().toString()));
                }
                break;
            default:
                break;
        }
    }

}
