package jp.digitalcloud.sample.twitter.auth;

import twitter4j.Twitter;
import twitter4j.auth.RequestToken;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;

/**
 * LoaderCallbacks for get a request token.
 * @author R SATO
 *
 */
public class TwitterOAuthRequestTokenCallbacks implements LoaderCallbacks<RequestToken> {

    private Context mContext;
    private Twitter mTwitter;


    /**
     * Initialize LoaderCallbacks.
     * @param context Context
     * @param twitter Twitter object. It is necessary to set the Consumer keys.
     */
    public TwitterOAuthRequestTokenCallbacks(Context context, Twitter twitter) {
        mContext = context;
        mTwitter = twitter;
    }

    @Override
    public Loader<RequestToken> onCreateLoader(int id, Bundle args) {
        TwitterOAuthRequestTokenLoader loader = new TwitterOAuthRequestTokenLoader(mContext, mTwitter);
        loader.forceLoad();
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<RequestToken> arg0, RequestToken requestToken) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthorizationURL()));
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        mContext.startActivity(intent);
    }

    @Override
    public void onLoaderReset(Loader<RequestToken> arg0) {
        // do nothing
    }
}
