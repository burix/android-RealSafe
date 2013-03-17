package de.kameri.realsafe;

import android.app.ListFragment;
import android.content.res.Configuration;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

/**
 * Represents the safe and encrypted items in a hierarchy structure. Means that
 * either it is an encrypted image where a click on it shows that image. Or it's
 * an category where a click on it opens the underlying items.
 * 
 * @author bki
 * 
 */
public class SafedItemsListFragment extends ListFragment {
	String[] countries = new String[] { "India", "Pakistan", "Sri Lanka",
			"China", "Bangladesh", "Nepal", "Afghanistan", "North Korea",
			"South Korea", "Japan" };
	
	private ListAdapter mMyAdapter = null;
	private OnItemClickListener mListener = null;
	
	public SafedItemsListFragment(ListAdapter adapter,OnItemClickListener listener){
		mMyAdapter = adapter;
		mListener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
//		ArrayAdapter<String> adapter = mMyAdapter;
//		if( adapter == null ){
//			/** Creating an array adapter to store the list of countries **/
//			adapter = new ArrayAdapter<String>(
//					inflater.getContext(), android.R.layout.simple_list_item_1,
//					countries);
//		}

		/** Setting the list adapter for the ListFragment */
		setListAdapter(mMyAdapter);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		Log.i("SafedItemsListFragments", "onViewCreated: " + mListener);
		getListView().setOnItemClickListener(mListener);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	
	
	
	
}
