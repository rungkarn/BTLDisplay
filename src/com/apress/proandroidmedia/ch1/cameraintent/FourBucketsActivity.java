/*
 * To change this template, choose Tools | Templates
 * && open the template in the editor.
 */
package com.apress.proandroidmedia.ch1.cameraintent;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
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

    @Override
    protected void onCreate(final Bundle istate) {
        super.onCreate(istate);

        final LayoutInflater inflater = getLayoutInflater();
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Don't show the title bar.
        final GridView view = (GridView)inflater.inflate(
                R.layout.four_buckets,
                null);
        view.setAdapter(new FruitAdapter(
                new FruitItem("ดอกอะไร?", R.drawable.apple),
                new FruitItem("แพงพวย", R.drawable.banana),
                new FruitItem("แคททรียา", R.drawable.blackberry),
                new FruitItem("บัว", R.drawable.cherries),
                new FruitItem("เยอบีรา", R.drawable.coconut),
                new FruitItem("จำปี", R.drawable.grapes),
                new FruitItem("บานชื่น", R.drawable.kiwi),
                new FruitItem("อเมซอน", R.drawable.lemon),
                new FruitItem("โป๊ยเซียน", R.drawable.lime),
                new FruitItem("แสงอาทิตย์", R.drawable.orange),
                new FruitItem("พุทธรักษา", R.drawable.peach),
                new FruitItem("อัญชัญ", R.drawable.strawberry),
                new FruitItem("เอื้องแปรงสีฟัน", R.drawable.watermelon)));

       
        
        
        
        
        
        setContentView(view);

        view.setOnItemClickListener(new OnItemClickListener()  {
//
        	
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				startGame(5);
			}
        	});
    }
    private void startGame(int i) {
		Log.d("Test","clicked on " + i);
//		Start game here...
		Intent x = new Intent(this,About.class);
		startActivity(x);
	}
        

}
