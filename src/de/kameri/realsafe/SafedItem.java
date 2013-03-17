package de.kameri.realsafe;

public class SafedItem {
	
	public static final int SAFED_ITEM_TYPE_IMAGE = 1;
	
	private String mName;
	private String mOriginLocation;
	private int mType = SAFED_ITEM_TYPE_IMAGE;
	public byte[] mEncodedData;
	
	public SafedItem(String name, String originLocation){
		mName = name;
		mOriginLocation = originLocation;
	}

	public String getName(){
		return mName;
	}
	
	public String mOriginLocation(){
		return mOriginLocation;
	}
}
