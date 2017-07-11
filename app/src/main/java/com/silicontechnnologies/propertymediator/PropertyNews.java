package com.silicontechnnologies.propertymediator;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class PropertyNews extends Activity {

	private WebView webview;
	private static final String TAG = "Main";
	private ProgressDialog progressBar;
	TextView home;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.property);
		setTitle("Property News");
		this.webview = (WebView) findViewById(R.id.webView1);
		home = (TextView) findViewById(R.id.textView1);
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						PropertyMediatorMainActivity.class));
			}
		});
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

		progressBar = ProgressDialog.show(PropertyNews.this, "",
				"Please wait...");
		progressBar.setCancelable(false);
		progressBar.setOnCancelListener(new OnCancelListener() {

			public void onCancel(DialogInterface arg0) {
				if (progressBar.isShowing())
					progressBar.dismiss();
				finish();
			}
		});
		webview.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.i(TAG, "Processing webview url click...");
				view.loadUrl(url);
				return true;
			}

			public void onPageFinished(WebView view, String url) {
				Log.i(TAG, "Finished loading URL: " + url);

				if (progressBar.isShowing()) {
					progressBar.dismiss();
				}
			}

			@SuppressWarnings("deprecation")
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Log.e(TAG, "Error: " + description);
				Toast.makeText(PropertyNews.this, "Oh no! " + description,
						Toast.LENGTH_SHORT).show();
				alertDialog.setTitle("Error");
				alertDialog.setMessage(description);
				alertDialog.setButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// return;
							}
						});
				alertDialog.show();
			}
		});
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}

		webview.loadUrl("http://www.livechennai.com/propertyratesinchennai.asp");
	}
}
