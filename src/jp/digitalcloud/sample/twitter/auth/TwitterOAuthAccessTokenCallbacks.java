package jp.digitalcloud.sample.twitter.auth;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Toast;

/**
 * LoaderCallbacks for get a access token.
 * @author R SATO
 *
 */
public class TwitterOAuthAccessTokenCallbacks implements LoaderCallbacks<AccessToken> {

    private Context mContext;
    private Twitter mTwitter;
    private String mPin;


    /**
     * Initialize LoaderCallbacks.
     * @param context Context
     * @param twitter Twitter object. It is necessary to set the Consumer keys.
     * @param pin PIN
     */
    public TwitterOAuthAccessTokenCallbacks(Context context, Twitter twitter, String pin) {
        mContext = context;
        mTwitter = twitter;
        mPin = pin;
    }

    @Override
    public Loader<AccessToken> onCreateLoader(int id, Bundle args) {
        TwitterOAuthAccessTokenLoader loader = new TwitterOAuthAccessTokenLoader(mContext, mTwitter, mPin);
        loader.forceLoad();
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<AccessToken> arg0, AccessToken accessToken) {
        if (accessToken != null) {
            // store access token
            Editor editor = mContext.getSharedPreferences(mContext.getString(R.string.shared_pref_name), Context.MODE_PRIVATE).edit();
            editor.putString(mContext.getString(R.string.shared_pref_key_twitter_access_token), accessToken.getToken());
            editor.putString(mContext.getString(R.string.shared_pref_key_twitter_access_token_secret), accessToken.getTokenSecret());
            editor.commit();
            Toast.makeText(mContext, mContext.getString(R.string.toast_oauth_complete_text), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, mContext.getString(R.string.toast_oauth_failed_text), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<AccessToken> arg0) {
        // do nothing
    }

}
