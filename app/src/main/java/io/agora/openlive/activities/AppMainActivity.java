package io.agora.openlive.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import io.agora.openlive.R;
import io.agora.openlive.fragment.MainFragment;
import io.agora.openlive.fragment.MessageListFragment;
import io.agora.openlive.fragment.UserMessageFragment;
import io.agora.openlive.view.NoScrollViewPager;

public class AppMainActivity extends AppCompatActivity {

    BottomNavigationView navigation;
    NoScrollViewPager viewPager;

    MainFragment courseFragment;
    UserMessageFragment userMessageFragment;
    MessageListFragment messageListFragment;

    private List<Fragment> fragmentList;
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        navigation = findViewById(R.id.ac_main_bottom);
        viewPager = findViewById(R.id.ac_main_viewpager);

        init();
        initEvent();
    }

    private void initEvent() {
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                invalidateOptionsMenu();
                switch (item.getItemId()) {
                    case R.id.main_menu_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.main_menu_message:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.main_menu_my:
                        viewPager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigation.setSelectedItemId(R.id.main_menu_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.main_menu_message);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.main_menu_my);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void init() {
        fragmentList = new ArrayList<>();
        courseFragment = new MainFragment();
        userMessageFragment = new UserMessageFragment();
        messageListFragment = new MessageListFragment();

        fragmentList.add(courseFragment);
        fragmentList.add(messageListFragment);
        fragmentList.add(userMessageFragment);

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        viewPager.setAdapter(adapter);
    }
}
