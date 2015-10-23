package com.logitech.clientrequest;

import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.HttpParams;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.Volley;

/**
 * @author Nithya since 10/22/2015
 */
public class VolleyHelper {
	private static VolleyHelper INSTANCE;
	private RequestQueue requestQueue;
	private Context context;

	private VolleyHelper(Context context) {
		this.context = context;
		requestQueue = getRequestQueue();
	}

	public static synchronized VolleyHelper getInstance(Context context) {

		INSTANCE = new VolleyHelper(context);
		return INSTANCE;
	}

	/**
	 * Requesting the server for the data using volley
	 * @return RequestQueue
	 */
	public RequestQueue getRequestQueue() {
		if (requestQueue == null) {
			// HttpsTrustManager.trustEveryone();
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpParams params = httpClient.getParams();
			HttpClientParams.setRedirecting(params, false);
			System.setProperty("http.keepAlive", "true");
			httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));

			requestQueue = Volley.newRequestQueue(context.getApplicationContext(), new HttpClientStack(httpClient));
		}
		return requestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}
}