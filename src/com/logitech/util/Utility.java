package com.logitech.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.logitech.view.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Nithya since 10/22/2015
 */
public class Utility {

	/**
	 * Shows the progress bar on the screen.
	 * 
	 * @param context
	 *            : activity context
	 * @param title
	 *            : title to be displayed
	 * @param message
	 *            : message to be displayed
	 * @return the progress bar instance
	 */
	public static ProgressDialog showProgressBar(Context context, String title,
			String message) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setTitle(title);
		progressDialog.setMessage(message);
		progressDialog.setCancelable(false);
		progressDialog.setIndeterminate(true);
		try {
			progressDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return progressDialog;
	}

	/**
	 * Dismiss the progress that is shown on the screen.
	 * 
	 * @param progressDialog
	 *            : progress bar to be dismissed
	 */
	public static void dismissProgressBar(ProgressDialog progressDialog) {
		try {
			if (null != progressDialog) {
				progressDialog.dismiss();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Displays information dialog with single button.
	 * 
	 * @param context
	 *            : activity context
	 * @param title
	 *            : title to be displayed
	 * @param messsage
	 *            : message to be displayed
	 */
	public static void showInfoDialogSingleButton(Context context,
			String title, String messsage) {

		AlertDialog.Builder prompt = new AlertDialog.Builder(context);
		prompt.setTitle(title);
		prompt.setMessage(messsage);
		prompt.setCancelable(true);

		prompt.setNeutralButton(context.getString(R.string.ok_lbl),
				new DialogInterface.OnClickListener() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * android.content.DialogInterface.OnClickListener#onClick
					 * (android.content.DialogInterface, int)
					 */
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});

		prompt.create().show();

	}

	/**
	 * Displays information dialog with double button.
	 * 
	 * @param context
	 *            : activity context
	 * @param title
	 *            : title to be displayed
	 * @param messsage
	 *            : message to be displayed
	 * @param infoType
	 *            : type of info
	 */
	public static void showInfoDialogDoubleButton(final Context context,
			String title, String messsage, final int infoType) {

		AlertDialog.Builder prompt = new AlertDialog.Builder(context);
		prompt.setTitle(title);
		prompt.setMessage(messsage);
		prompt.setCancelable(true);

		prompt.setPositiveButton(context.getString(R.string.ok_lbl),
				new DialogInterface.OnClickListener() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * android.content.DialogInterface.OnClickListener#onClick
					 * (android.content.DialogInterface, int)
					 */
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

					}

				});

		prompt.setNegativeButton(context.getString(R.string.cancel_lbl),
				new DialogInterface.OnClickListener() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * android.content.DialogInterface.OnClickListener#onClick
					 * (android.content.DialogInterface, int)
					 */
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});

		prompt.create().show();

	}

	/**
	 * Checks if the given string is null or empty.
	 * 
	 * @param str
	 *            : string to be checked
	 * @return true if string is empty false otherwise
	 */
	public static boolean isEmpty(String str) {
		boolean isEmpty = false;

		if (str == null || str.trim().equalsIgnoreCase("")) {
			isEmpty = true;
		}

		return isEmpty;
	}

	/**
	 * Check Internet is available or not. If not then shows a pop-up.
	 * 
	 * @param context
	 *            : activity context
	 * @return true if network available else false and shows info pop-up
	 */
	public final static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}

		}
		showInfoDialogSingleButton(context, "",
				context.getString(R.string.network_unavailable_message));
		return false;
	}

	/**
	 * Checks if the given string is a valid json object.
	 * 
	 * @param test
	 *            : string to test
	 * @return true if valid json object else false
	 */
	public static boolean isValidJSON(String test) {
		boolean valid = false;
		try {
			new JSONObject(test);
			valid = true;
		} catch (JSONException ex) {
			valid = false;
		}
		return valid;
	}

}
