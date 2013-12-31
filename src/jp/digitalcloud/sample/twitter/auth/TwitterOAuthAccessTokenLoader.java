package jp.digitalcloud.sample.twitter.auth;

import twitter4j.Twitter;

import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * AsyncTaskLoader for get a access token.
 * @author R SATO
 *
 */
public class TwitterOAuthAccessTokenLoader extends AsyncTaskLoader<AccessToken> {

    private Twitter mTwitter;
    private String mPin;


    /**
     * Initialize AsyncTaskLoader.
     * @param context Context
     * @param twitter Twitter
     * @param pin PIN
     */
    public TwitterOAuthAccessTokenLoader(Context context, Twitter twitter, String pin) {
        super(context);
        mTwitter = twitter;
        mPin = pin;
    }

    @Override
    public AccessToken loadInBackground() {
        AccessToken accessToken = null;
        try {
            accessToken = mTwitter.getOAuthAccessToken(mPin);
        } catch (TwitterException e) {
            accessToken = null;
        }
        return accessToken;
    }

}
