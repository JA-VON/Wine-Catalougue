package com.jwray.jwraywines.classes;

import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * @author Javon
 * Class Structure for a Wine
 */
public class Wine extends WineBase
{
	private MediaPlayer voicePronounciation;
	private int id;
	private int columnId;
	private Drawable picture;
	private boolean favorite = false;
	private String pronounciation;
	private String occasion;
	private String type;
	private String meal;
	
	public Wine(int wineID) 
	{
		setId(wineID);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	private void setId(int id) {
		this.id = id;
	}
	
	private Bitmap drawableToBitmap (Drawable drawable) {
	    if (drawable instanceof BitmapDrawable) {
	        return ((BitmapDrawable)drawable).getBitmap();
	    }
	    
	    Bitmap bitmap;
	    try
	    {
		    bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
		    Canvas canvas = new Canvas(bitmap); 
		    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		    drawable.draw(canvas);
	    }
	    catch(java.lang.NullPointerException e)
	    {
	    	return null;
	    }

	    return bitmap;
	}

	/**
	 * @return the picture
	 */
	public Bitmap getPicture(Context context) {
		
		String mDrawableName = "id"+getId();
    	int resID = context.getResources().getIdentifier(mDrawableName , "drawable", context.getPackageName());

    	try
    	{
    		picture = context.getResources().getDrawable(resID);	
    	}
    	catch(android.content.res.Resources.NotFoundException e)
    	{
    		Log.e("Resource not found stack", context.getPackageName());
    		Log.e("name", mDrawableName);
    		Log.e("wine name",getName());
    		Log.e("id", resID+"");
    	}
    	catch(OutOfMemoryError e)
    	{
    		Log.e("application out of memory", "Consider using disk caching");
    		throw new OutOfMemoryError("Out of memory on image loading");
    	}
		return drawableToBitmap(picture);
		
	}
	
	
	@Override
	public String toString()
	{
		String id = "ID:"+getId();
		String name ="name:"+getName();
		String brand = "brand:"+getBrand();
		String country = "country:"+getCountry();
		
		return id+"\n"+name+"\n"+brand+"\n"+country;
	}

	/**
	 * @return the favorite
	 */
	public boolean isFavorite() {
		return favorite;
	}

	/**
	 * @param favorite the favorite to set
	 */
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	/**
	 * @return the pronounciation
	 */
	public String getPronounciation() {
		return pronounciation;
	}

	/**
	 * @param pronounciation the pronounciation to set
	 */
	public void setPronounciation(String pronounciation) {
		this.pronounciation = pronounciation;
	}

	/**
	 * @return the occasion
	 */
	public String getOccasion() {
		return occasion;
	}

	/**
	 * @param occasion the occasion to set
	 */
	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the meal
	 */
	public String getMeal() {
		return meal;
	}

	/**
	 * @param meal the meal to set
	 */
	public void setMeal(String meal) {
		this.meal = meal;
	}

	public MediaPlayer getVoicePronounciation(Context context) {
		String mRawFile = "sound_"+getName().replaceAll("\\s+","").toLowerCase(Locale.US);
    	int resID = context.getResources().getIdentifier(mRawFile , "raw", context.getPackageName());

    	try
    	{
    		voicePronounciation =  MediaPlayer.create(context, resID);
    	}
    	catch(android.content.res.Resources.NotFoundException e)
    	{
    		Log.e("Resource not found stack", context.getPackageName());
    		Log.e("name", mRawFile);
    		Log.e("wine name",getName());
    		Log.e("id", resID+"");
    		return null;
    	}
		return voicePronounciation;
	}

	/**
	 * @return the columnId
	 */
	public int getColumnId() {
		return columnId;
	}

	/**
	 * @param columnId the columnId to set
	 */
	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

}
