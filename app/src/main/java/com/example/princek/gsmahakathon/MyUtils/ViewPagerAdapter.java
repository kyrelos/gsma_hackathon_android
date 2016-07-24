package com.example.princek.gsmahakathon.MyUtils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.princek.gsmahakathon.R;
import com.example.princek.gsmahakathon.frags.BalanceFragment;
import com.example.princek.gsmahakathon.frags.SendFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT =5;
    private String titles[];

    public ViewPagerAdapter(FragmentManager fm, String[] titles2) {
        super(fm);
        titles=titles2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            // Open FragmentTab1.java
            case 0:
                return new BalanceFragment();
            case 1:
                return new SendFragment();
            case 2:
                return new SendFragment();
            case 3:
                return new SendFragment();
            case 4:
                return new SendFragment();
        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public static class DrawerAdapter extends BaseAdapter {


    //    private List<Pair<String, Integer>> messages;
        private List<DrawerItemObject> menuItems;
        private LayoutInflater layoutInflater;

        public DrawerAdapter(Activity activity) {
            layoutInflater = activity.getLayoutInflater();
            menuItems = new ArrayList<>();
        }

        public void addItem(DrawerItemObject item) {
            menuItems.add(item);
            notifyDataSetChanged();
        }

        public void loadItems(ArrayList<DrawerItemObject> itemsArrayList) {
            for(int j=0; j<itemsArrayList.size(); j++) {
                menuItems.add(itemsArrayList.get(j));
                notifyDataSetChanged();
            }
        }


        @Override
        public int getCount() {
            return menuItems.size();
        }

        @Override
        public Object getItem(int i) {
            return menuItems.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

    //    @Override
    //    public int getItemViewType(int i) {
    //        return .get(i).getIncoming();
    //    }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
    //        int direction = getItemViewType(i);

            //show message on left or right, depending on if
            //it's incoming or outgoing
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.drawer_item, viewGroup, false);
            }

            String title = menuItems.get(i).getTitle();
            int icon = menuItems.get(i).getIcon();
            ImageView iconImage = (ImageView) convertView.findViewById(R.id.imageViewDrawerItemIcon);
            TextView titleText = (TextView) convertView.findViewById(R.id.textViewDrawerItemTitle);
            titleText.setText(title);
            iconImage.setImageResource(icon);

            return convertView;
        }
    }

    public static class DrawerItemObject {
        private String title;
        private int icon;

        public DrawerItemObject() {
        }

        public DrawerItemObject(String title, int icon) {
            this.title = title;
            this.icon = icon;
        }

        public DrawerItemObject(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "DrawerItemObject{" +
                    "title='" + title + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }
}