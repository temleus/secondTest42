package com.task;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.viewpagerindicator.TabPageIndicator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * @author Leus Artem
 * @since 17.06.13
 */
public class MainActivity extends SherlockFragmentActivity {

    private SimpleFragmentPagerAdapter adapter;
    private ViewPager pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        printKeyHash();

        adapter = new SimpleFragmentPagerAdapter(this);

        adapter.addFragment("User", UserFragment.class, null);
        adapter.addFragment("Friends", FriendListFragment.class, null);
        adapter.addFragment("About", AboutFragment.class, null);

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        pager.setCurrentItem(0);
    }



    private void printKeyHash(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.task", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {}
    }


    public static class SimpleFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private final Context mContext;
        private final ArrayList<FragmentInfo> mFragments = new ArrayList<FragmentInfo>();

        static final class FragmentInfo {
            private final String title;
            private final Class<?> clss;
            private final Bundle args;

            FragmentInfo(String _title, Class<?> _class, Bundle _args) {
                title = _title;
                clss = _class;
                args = _args;
            }
        }

        public SimpleFragmentPagerAdapter(SherlockFragmentActivity activity) {
            super(activity.getSupportFragmentManager());
            mContext = activity;
        }

        public void addFragment(String title, Class<?> clss, Bundle args) {
            FragmentInfo info = new FragmentInfo(title, clss, args);
            mFragments.add(info);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            FragmentInfo info = mFragments.get(position);
            return Fragment.instantiate(mContext, info.clss.getName(), info.args);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragments.get(position).title;
        }
    }
}
