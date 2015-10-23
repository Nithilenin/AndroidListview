package com.logitech.view;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Nithya since 10/22/2015
 */

public class MyApplication extends Application {

	public static Context mycontext;

	public final static String TAG = MyApplication.class.getSimpleName();

	public static ProgressDialog pDialog;

	@Override
	public void onCreate() {
		super.onCreate();
		mycontext = this;
		MyApplication.mycontext = getApplicationContext();
	}

	public static Context getAppContext() {
		return MyApplication.mycontext;
	}

	public static boolean isNetworkAvailable(Context context) {

		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();

		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public static void createdialog(Context ctx, String msg) {

		pDialog = new ProgressDialog(ctx);
		pDialog.setMessage(msg);

	}

}
