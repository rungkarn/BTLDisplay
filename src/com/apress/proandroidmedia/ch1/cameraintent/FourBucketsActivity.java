/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.apress.proandroidmedia.ch1.cameraintent;


import java.io.File;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * <p>
 * </p><p>
 * Created on 10 Aug 2010
 * </p>
 *
 * @author Jason Morris
 */
public class FourBucketsActivity extends Activity {
	private GridView m_flowerList;
    private ViewGroup m_container;
    private ScrollView m_flowerInfo;
    private LinearLayout m_flowerLayout;
    private ImageView m_imageView;
    private FruitAdapter flowerAdapt;
    
    @Override
    protected void onCreate(final Bundle istate) {
        super.onCreate(istate);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Don't show the title bar.

        setContentView(R.layout.four_buckets);

        m_flowerList = (GridView) findViewById(android.R.id.list);
        //m_flowerInfo = (ScrollView) findViewById(R.id.infoContainer);
        m_container = (ViewGroup) findViewById(R.id.container);
       m_imageView = (ImageView) findViewById(R.id.picture);
       m_flowerLayout = (LinearLayout) findViewById(R.id.infoLayout);
       
      
//        final LayoutInflater inflater = getLayoutInflater();
//        final GridView view = (GridView)inflater.inflate(
//        		android.R.id.list,null);
   //        final GridView view = (GridView)inflater.inflate(
//                R.layout.four_buckets,
//                null);
        // Prepare the ListView flower
        //get correct path of sd card
        File sdDir = Environment.getExternalStorageDirectory();
        String strPath = sdDir.getAbsolutePath()+"/beeTheLion/";
        flowerAdapt = new FruitAdapter();
        flowerAdapt.add(new FruitItem("ดอกอะไร?",strPath + "apple.jpg"));
        flowerAdapt.add(new FruitItem("แพงพวย",strPath+ "banana.jpg"));
        flowerAdapt.add(new FruitItem("แคททรียา",strPath+"blackberry.jpg"));
        flowerAdapt.add(new FruitItem("บัว", strPath +"cherries.jpg"));
        flowerAdapt.add(new FruitItem("เยอบีรา",strPath + "coconut.jpg"));
        flowerAdapt.add(new FruitItem("จำปี", strPath +"grapes.jpg"));
        flowerAdapt.add(new FruitItem("บานชื่น", strPath +"kiwi.jpg"));
        flowerAdapt.add(new FruitItem("อเมซอน", strPath +"lemon.jpg"));
        flowerAdapt.add(new FruitItem("โป๊ยเซียน", strPath +"lime.jpg"));
        flowerAdapt.add(new FruitItem("แสงอาทิตย์",strPath + "orange.jpg"));
        flowerAdapt.add(new FruitItem("พุทธรักษา", strPath +"peach.jpg"));
        flowerAdapt.add(new FruitItem("อัญชัญ", strPath + "strawberry.jpg"));
		flowerAdapt.add(new FruitItem("เอื้องแปรงสีฟัน",strPath + "watermelon.jpg"));
        
//		view.setAdapter(flowerAdapt);
//		setContentView(view);
//
//        view.setOnItemClickListener(new OnItemClickListener()  {
		m_flowerList.setAdapter(flowerAdapt);
       m_flowerList.setOnItemClickListener(new OnItemClickListener()  {

        	
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
				// TODO Auto-generated method stub
				String strUri = flowerAdapt.getItemUri(position);
				 m_imageView.setImageURI(Uri.fromFile(new File(strUri)));  //new File("/sdcard/cats.jpg")

				applyRotation(position, 0, 90);
			}
        	});
     // Prepare the flower information
       m_flowerLayout.setClickable(true);
        m_flowerLayout.setFocusable(true);
        //m_flowerLayout.setFocusableInTouchMode(true);
        m_flowerLayout.setOnClickListener(new OnClickListener() 
        {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 applyRotation(-1, 360, 270);
			}
        
        });

//        // Since we are caching large views, we want to keep their cache
//        // between each animation
        
        m_container.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
        applyInfoRotation(-1, 180, 90);
       
    }
    private void startGame(int i) {
		Log.d("Test","clicked on " + i);
//		Start game here...
		Intent x = new Intent(this,About.class);
		startActivity(x);
	}
    /**
     * Setup a new 3D rotation on the container view.
     *
     * @param position the item that was clicked to show a picture, or -1 to show the list
     * @param start the start angle at which the rotation must begin
     * @param end the end angle of the rotation
     */
    private void applyRotation(int position, float start, float end) {
        // Find the center of the container
        final float centerX = m_container.getWidth() / 2.0f;
        final float centerY = m_container.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(position));

        m_container.startAnimation(rotation);
    }
    private void applyInfoRotation(int position, float start, float end) {
        // Find the center of the container
        final float centerX = m_container.getWidth() / 2.0f;
        final float centerY = m_container.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
       
        //rotation.setFillAfter(true);
        //rotation.setInterpolator(new AccelerateInterpolator());
        //rotation.setAnimationListener(new DisplayNextView(position));

        m_flowerLayout.startAnimation(rotation);
    }
    /**
     * This class listens for the end of the first half of the animation.
     * It then posts a new action that effectively swaps the views when the container
     * is rotated 90 degrees and thus invisible.
     */
    private final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;

        private DisplayNextView(int position) {
            mPosition = position;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            m_container.post(new SwapViews(mPosition));
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /**
     * This class is responsible for swapping the views and start the second
     * half of the animation.
     */
    private final class SwapViews implements Runnable {
        private final int mPosition;

        public SwapViews(int position) {
            mPosition = position;
        }

        public void run() {
            final float centerX = m_container.getWidth() / 2.0f;
            final float centerY = m_container.getHeight() / 2.0f;
            Rotate3dAnimation rotation;
            
            if (mPosition > -1) {
                m_flowerList.setVisibility(View.GONE);
                m_flowerLayout.setVisibility(View.VISIBLE);
                m_flowerLayout.requestFocus();

                rotation = new Rotate3dAnimation(270, 360, centerX, centerY, 310.0f, false);
              
            } else {
                m_flowerLayout.setVisibility(View.GONE);
                m_flowerList.setVisibility(View.VISIBLE);
                m_flowerList.requestFocus();

                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
            }

            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());

            m_container.startAnimation(rotation);
        }
    }

}
