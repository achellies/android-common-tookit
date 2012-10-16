package com.lurencun.android.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <ul>
 * <li><b>name : </b>		CommonPagerAdapter		</li>
 * <li><b>description :</b>	为ViewPager提供的通用Adapter				</li>
 * <li><b>author : </b>		桥下一粒砂			</li>
 * <li><b>e-mail : </b>		chenyoca@gmail.com	</li>
 * <li><b>weibo : </b>		@桥下一粒砂			</li>
 * <li><b>date : </b>		2012-10-15 下午6:36:15		</li>
 * </ul>
 */
public class CommonPagerAdapter<T> extends PagerAdapter {

	private ArrayList<T> mDataSet ;
	
	private LayoutInflater mInflater;
	
	private ViewCreator<T> mCreator;
	
	public CommonPagerAdapter(LayoutInflater inf,ViewCreator<T> creator){
		mCreator = creator;
		mInflater = inf;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View view = (View) object;
		container.removeView(view);
		mCreator.releaseView(view, mDataSet.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = mCreator.createView(mInflater, position, mDataSet.get(position));
		container.addView(view);
		return view;
	}

	@Override
	public int getCount() {
		return mDataSet == null ? 0 : mDataSet.size();
	}
	
	public void update(ArrayList<T> ds){
		mDataSet = ds;
		notifyDataSetChanged();
	}
	
	public void add(ArrayList<T> extraData){
		mDataSet.addAll(extraData);
		notifyDataSetChanged();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

}