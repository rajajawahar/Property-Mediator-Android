package com.silicontechnnologies.propertymediator.category;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.silicontechnnologies.propertymediator.R;

import java.io.File;

public class Viewer extends Activity {

	private LinearLayout container;
	private int currentX;
	private int currentY;
	File mfile;
	String pathname;
	ImageView viewnow;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.now);
		pathname = getIntent().getExtras().getString("fine");
		pathname = pathname.toString();
		container = (LinearLayout) findViewById(R.id.Container);
		//container.scrollTo(220, 400);
		viewnow = (ImageView) findViewById(R.id.ImageView01);
		mfile = new File(pathname);
		if (mfile.exists()) {
			Bitmap picture = BitmapFactory.decodeFile(mfile.getAbsolutePath());
			viewnow.setImageBitmap(picture);
		}
	}


/*	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			currentX = (int) event.getRawX();
			currentY = (int) event.getRawY();
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			int x2 = (int) event.getRawX();
			int y2 = (int) event.getRawY();
			container.scrollBy(currentX - x2, currentY - y2);
			currentX = x2;
			currentY = y2;
			break;
		}
		case MotionEvent.ACTION_UP: {
			break;
		}
		}
		return true;
	}*/
}