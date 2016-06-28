package com.gky.okhttpdeme.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.gky.okhttpdeme.R;
import com.gky.okhttpdeme.config.ConfigDef;
import com.gky.okhttpdeme.presenter.ArticleListPresenterImpl;
import com.gky.okhttpdeme.ui.fragments.ArticleListFragment;

/**
 * Created by 凯阳 on 2016/6/27.
 */
public class ArticlesFrgAdapter extends FragmentPagerAdapter{

    private Context mContext;

    public ArticlesFrgAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("gky", getClass().getName()+":getItem::"+position);
        switch (position){
            case 0:
            {
                ArticleListFragment androidFrg = new ArticleListFragment();
                Bundle data = new Bundle();
                data.putString("ArticleType", ConfigDef.ARTICLE_TYPE_ANDROID);
                androidFrg.setArguments(data);
                ArticleListPresenterImpl.newInstance(androidFrg, ConfigDef.ARTICLE_TYPE_ANDROID);
                return androidFrg;
            }
            case 1:
            {
                ArticleListFragment iosFrg = new ArticleListFragment();
                Bundle data = new Bundle();
                data.putString("ArticleType", ConfigDef.ARTICLE_TYPE_IOS);
                iosFrg.setArguments(data);
                ArticleListPresenterImpl.newInstance(iosFrg, ConfigDef.ARTICLE_TYPE_IOS);
                return iosFrg;
            }
            case 2:
            {
                ArticleListFragment webFrg = new ArticleListFragment();
                Bundle data = new Bundle();
                data.putString("ArticleType", ConfigDef.ARTCILE_TYPE_WEB);
                webFrg.setArguments(data);
                ArticleListPresenterImpl.newInstance(webFrg, ConfigDef.ARTCILE_TYPE_WEB);
                return webFrg;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getString(R.string.article_type_android);
            case 1:
                return mContext.getString(R.string.article_type_ios);
            case 2:
                return mContext.getString(R.string.article_type_web);
            default:
                throw new IllegalArgumentException("Unkown position:"+position);
        }
    }
}
