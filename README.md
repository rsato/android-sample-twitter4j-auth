android-sample-twitter4j-auth
=============================

## About this sample
This is sample project using Twitter4J on Android.

## Problem on Android
Networking operation on Main thread(e.g. "onCreate"), Android throws "android.os.NetworkOnMainThreadException." 
Writing code below to ignoring Exception, but should limit it to be used on a test.

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);

## Move networking operation to other tasks
This sample is uging AsyncTaskLoader and LoaderCallbacks(Added in API level 11).
