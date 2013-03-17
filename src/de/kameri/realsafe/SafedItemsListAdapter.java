package de.kameri.realsafe;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

class SafedItemsListAdapter extends BaseAdapter {

	private final LayoutInflater mInflater;
	private ArrayList<SafedItem> mSafedItemsArray;

	public SafedItemsListAdapter() {
		mInflater = (LayoutInflater) AppGlobals.getInstance().getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mSafedItemsArray = new ArrayList<SafedItem>();
		
		mSafedItemsArray.add(new SafedItem("Albanien", ""));
		mSafedItemsArray.add(new SafedItem("Deutschland", ""));
		mSafedItemsArray.add(new SafedItem("Polen", ""));
		mSafedItemsArray.add(new SafedItem("Groß Britanien", ""));
		mSafedItemsArray.add(new SafedItem("Irland", ""));
		mSafedItemsArray.add(new SafedItem("Italien", ""));
		mSafedItemsArray.add(new SafedItem("Frankreich", ""));
		mSafedItemsArray.add(new SafedItem("Spanien", ""));
		mSafedItemsArray.add(new SafedItem("Schweiz", ""));
		mSafedItemsArray.add(new SafedItem("Kanada", ""));
		mSafedItemsArray.add(new SafedItem("Australien", ""));
		mSafedItemsArray.add(new SafedItem("Brasilien", ""));
	}

	public int getCount() {
		return mSafedItemsArray.size();
	}

	public SafedItem getItem(int position) {
		return mSafedItemsArray.get(position);
	}

	public long getItemId(int position) {
		return (long) position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout itemView = (LinearLayout) mInflater.inflate(
				R.layout.list_item, parent, false);
		bindView(itemView, position);
		return itemView;
	}

	private void bindView(LinearLayout view, int position) {
		SafedItem datensatz = getItem(position);
		view.setId((int) getItemId(position));
//		TextView nummerTextView = (TextView) view.findViewById(R.id.Nummer);
//		TextView datumTextView = (TextView) view.findViewById(R.id.Datum);
		TextView nameTextView = (TextView) view.findViewById(R.id.Name);
//		nummerTextView.setText(String.valueOf(position));
//		datumTextView.setText(datensatz.datum);
		nameTextView.setText(datensatz.getName());
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// Meldung ausgeben oder Intent bauen und Activity starten
		SafedItem gewaehlterDatensatz = mSafedItemsArray.get(position);
	}
}