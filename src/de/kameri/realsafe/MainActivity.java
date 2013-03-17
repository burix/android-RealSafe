package de.kameri.realsafe;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.GetChars;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements OnItemClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();

	String[] countries = new String[] { "Alabina", "Germany", "Sri Lanka",
			"China", "Bangladesh", "Nepal", "Afghanistan", "North Korea",
			"South Korea", "Japan", "France", "UK", "Ungarn" };

	/**
	 * The number of pages (wizard steps) to show in this demo.
	 */
	// private static final int NUM_PAGES = 5;

	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next wizard steps.
	 */
	// private ViewPager mPager;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	// private PagerAdapter mPagerAdapter;

	SafedItem mTestSafedItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		AppGlobals.getInstance().setContext(this.getApplicationContext());

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		setContentView(R.layout.activity_main);

		SafedItemsListAdapter adapter = new SafedItemsListAdapter();

		if (savedInstanceState == null) {
			FragmentManager fragmentManager = getFragmentManager();
			// Or: FragmentManager fragmentManager = getSupportFragmentManager()
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			SafedItemsListFragment fragment = new SafedItemsListFragment(
					adapter, this);
			fragmentTransaction.add(R.id.fragment_container, fragment);
			fragmentTransaction.commit();
		}

		mTestSafedItem = new SafedItem("Test safed item",
				"mnt/sdcard/Camera/DCIM/...");

		try {
			InputStream is = getAssets().open("test_safed_image.jpg",
					AssetManager.ACCESS_UNKNOWN);
			mTestSafedItem.mEncodedData = new byte[is.available()];
			is.read(mTestSafedItem.mEncodedData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Instantiate a ViewPager and a PagerAdapter.
		// mPager = (ViewPager) findViewById(R.id.pager);
		// mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
		// mPager.setAdapter(mPagerAdapter);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.i(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "onResume");
		Log.i(TAG, "Intent: " + getIntent().toString());

		// Get intent, action and MIME type
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();

	    if (Intent.ACTION_SEND.equals(action) && type != null) {
	        if (type.startsWith("image/")) {
	            handleSendImage(intent); // Handle single image being sent
	        }
	    } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
	        if (type.startsWith("image/")) {
	            handleSendMultipleImages(intent); // Handle multiple images being sent
	        }
	    }

		super.onResume();
	}

	void handleSendImage(Intent intent) {
	    Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
	    if (imageUri != null) {
	        Log.i(TAG, "handleSendImage::intentImageUri: " + imageUri.getEncodedPath());
	        
			try {
				InputStream in = getContentResolver().openInputStream(imageUri);
				mTestSafedItem.mEncodedData = new byte[in.available()];
				in.read(mTestSafedItem.mEncodedData);
			} catch (IOException e) {
				e.printStackTrace();
			}

			pushFragment(new ImageViewFragment(this.mTestSafedItem));
			
	    }
	}

	void handleSendMultipleImages(Intent intent) {
	    ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
	    if (imageUris != null) {
	        for( Uri imageUri : imageUris ){
		        Log.i(TAG, "handleSendMultipleImages::intentImageUri: " + imageUri.getEncodedPath());
	        }
	    }
	}

	@Override
	protected void onStart() {
		Log.i(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.i(TAG, "onStop");
		super.onStop();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Log.i(TAG, "onItemSelected");
		// TODO retrieve SafedItem by view.getTag(key)
		pushFragment(new ImageViewFragment(this.mTestSafedItem));

	}

	private void pushFragment(Fragment newFrag) {
		getFragmentManager()
				.beginTransaction()
				.addToBackStack("BLA")
				.setCustomAnimations(R.animator.slide_in_right,
						R.animator.slide_out_left, R.animator.slide_in_left,
						R.animator.slide_out_right)
				.replace(R.id.fragment_container, newFrag).commit();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.i(TAG, "onConfigurationChanged");

	}

	/**
	 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects,
	 * in sequence.
	 */
	// private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
	// public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fm)
	// {
	// super(fm);
	// }
	//
	// @Override
	// public Fragment getItem(int position) {
	// return new SafedItemsListFragment();
	// }
	//
	// @Override
	// public int getCount() {
	// return NUM_PAGES;
	// }
	// }
}
