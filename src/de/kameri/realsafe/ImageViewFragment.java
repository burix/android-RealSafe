package de.kameri.realsafe;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ImageViewFragment extends Fragment {

	private ScaleImageView mImageView;
	private SafedItem mSafedItem;

	public ImageViewFragment(SafedItem item) {
		mSafedItem = item;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.image_view, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		/*
		 * Get image view object where are going to show the decoded image
		 * later.
		 */
		mImageView = (ScaleImageView) view.findViewById(R.id.imageview);

		/*
		 * Start decoding the encoded image, publish the progress and finally
		 * show it if the decode process was successful.
		 */
		new AsyncTask<SafedItem, String, Bitmap>() {
			@Override
			protected void onPreExecute() {
				// TODO show progress circle
				super.onPreExecute();
			}

			@Override
			protected Bitmap doInBackground(SafedItem... params) {
				return BitmapFactory.decodeByteArray(mSafedItem.mEncodedData, 0,
						mSafedItem.mEncodedData.length);
			}

			@Override
			protected void onProgressUpdate(String... values) {
				// TODO rotate progress circle
				super.onProgressUpdate(values);
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				mImageView.setImageBitmap(result);
				super.onPostExecute(result);
			}
		}.execute(mSafedItem);
	}

}
