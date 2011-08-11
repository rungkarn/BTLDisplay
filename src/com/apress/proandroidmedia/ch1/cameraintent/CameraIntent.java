package com.apress.proandroidmedia.ch1.cameraintent;

import java.io.File;



//import com.apress.proandroidmedia.ch1.cameraintent.FourBucketsActivity;
//import com.apress.proandroidmedia.ch1.cameraintent.About;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class CameraIntent extends Activity {
	
	final static int CAMERA_RESULT = 0;
	ImageView imv;
	String imageFilePath;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Don't show the title bar.
        
        setContentView(R.layout.main);
        
        imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() +
        	"/myfavoritepicture.jpg";
//        imageFilePath = "/sdcard/myfavoritepicture.jpg";
//        imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
//        File imageFile = new File(imageFilePath,"myfavoritepicture.jpg");
        File imageFile = new File(imageFilePath);
        Uri imageFileUri = Uri.fromFile(imageFile);
        
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,imageFileUri);
        startActivityForResult(i, CAMERA_RESULT);
        View imgView =  findViewById(R.id.ReturnedImageView);
		imgView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startGame(5);
			}} );//.setOnClickListener((OnClickListener) this);
//    	startActivity(new Intent(
//                this,
//                FourBucketsActivity.class));
    }
    private void startGame(int i) {
		Log.d("Test","clicked on " + i);
//		Start game here...
		//Intent x = new Intent(this,About.class);
		//startActivity(x);
		startActivity(new Intent(
                this,
                FourBucketsActivity.class));
	}
//    public void OnTouchListener(View v) {
//    	switch (v.getId())
//    	{
//    	case R.id.ReturnedImageView:
//    		Intent i = new Intent(this,About.class);
//    		startActivity(i);
//    		break;
//    		//more buttons go here (if any) ...
//    	
//    	}
//    }
    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {
		super.onActivityResult(requestCode, resultCode, intent);
		if(resultCode == RESULT_OK)
		{
			imv = (ImageView) findViewById(R.id.ReturnedImageView);
			
			Display currentDisplay = getWindowManager().getDefaultDisplay();
			int dw = currentDisplay.getWidth();
			int dh = currentDisplay.getHeight();
			
			BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
			bmpFactoryOptions.inJustDecodeBounds = true;
			Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);
			
			int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/(float)dh);
			int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/(float)dw);
			
			Log.v("HEIGHTRATIO","" + heightRatio);
			Log.v("WIDTHRATIO", "" + widthRatio);
			
			if(heightRatio > 1 && widthRatio > 1)
			{
				if(heightRatio > widthRatio)
				{
					bmpFactoryOptions.inSampleSize = heightRatio;
				}
				else
				{
					bmpFactoryOptions.inSampleSize = widthRatio;
				}
			}
			
			bmpFactoryOptions.inJustDecodeBounds = false;
			bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);
			Bundle extras = intent.getExtras();
			Bitmap bmp1 = (Bitmap) extras.get("data");
			
			imv.setImageBitmap(bmp1);

		}
	}
}