package com.logitech.view;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.logitech.view.R;
import com.logitech.adapter.DeviceAdapter;
import com.logitech.clientrequest.GsonRequest;
import com.logitech.clientrequest.VolleyHelper;
import com.logitech.constants.ApplicationConstants;
import com.logitech.model.DevicesBO;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author Nithya since 10/22/2015
 */

public class MainActivity extends Activity {

	ProgressDialog pDialog;
	ListView listDevices;
	MainActivity mainActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mainActivity = this;
		listDevices = (ListView) findViewById(R.id.lstView);

		if (MyApplication.isNetworkAvailable(mainActivity)) {
			callservice();
		} else {
			Toast.makeText(this, "No Network", Toast.LENGTH_LONG).show();
			mainActivity.finish();
		}
	}

	/**
	 * Trigger http request using volley library and callback method is
	 * implemented in case of success and failure accordingly.
	 */
	private void callservice() {

		showdialog(ApplicationConstants.MSG_LOAD);

		GsonRequest<DevicesBO> gsonObjRequest = new GsonRequest<DevicesBO>(ApplicationConstants.SERVER_HTTP_URL,
				DevicesBO.class, null, new Response.Listener<DevicesBO>() {
					@Override
					public void onResponse(DevicesBO response) {
						try {
							DeviceAdapter deviceAdapter = new DeviceAdapter(MainActivity.this, response);
							listDevices.setAdapter(deviceAdapter);
							closedialog("success");
						} catch (Exception e) {
							System.out.println("data.failed....." + response.toString());
							e.printStackTrace();
							closedialog("failes");
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// The below errors would thrown by volley in various
						// scenario respectively. We can handle our error types
						// accordingly.
						closedialog("Error occured");
						if (error instanceof NetworkError) {
						} else if (error instanceof ServerError) {
						} else if (error instanceof AuthFailureError) {
						} else if (error instanceof ParseError) {
						} else if (error instanceof NoConnectionError) {
						} else if (error instanceof TimeoutError) {
						}

					}
				});

		VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonObjRequest);
	}

	private void closedialog(String msg) {
		if (pDialog.isShowing()) {
			pDialog.hide();
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}
	}

	private void showdialog(String msg) {
		pDialog = new ProgressDialog(this);
		pDialog.setMessage(msg);
		pDialog.show();

	}

}
