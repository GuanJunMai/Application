package com.example.myplay.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.myplay.adapter.FragmentAdapter;
import com.example.myplay.R;
import com.example.myplay.fragment.AppFragment;
import com.example.myplay.fragment.CategoryFragment;
import com.example.myplay.fragment.GameFragment;
import com.example.myplay.fragment.HomeFragment;
import com.example.myplay.fragment.HotSpotFragment;
import com.example.myplay.fragment.RecommendFragment;
import com.example.myplay.fragment.SubjectFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawlayout)
    DrawerLayout drawlayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new HomeFragment());
        fragmentAdapter.addFragment(new AppFragment());
        fragmentAdapter.addFragment(new GameFragment());
        fragmentAdapter.addFragment(new SubjectFragment());
        fragmentAdapter.addFragment(new RecommendFragment());
        fragmentAdapter.addFragment(new CategoryFragment());
        fragmentAdapter.addFragment(new HotSpotFragment());
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    /**
     * 设置Toolbar
     */
    private void setToolbar() {
        //让toolbar替换为当前的ActionBar
        setSupportActionBar(toolbar);
        //1.得到toolbar进行一些设置
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.app_name));
        //2.作用：显示home按钮
        actionBar.setDisplayShowHomeEnabled(true);//启用home按钮
        actionBar.setDisplayHomeAsUpEnabled(true);//显示home按钮
        //3.让返回箭头变身为汉堡包按钮，就是三条线
        drawerToggle = new ActionBarDrawerToggle(this, drawlayout, toolbar, 0, 0);
        drawerToggle.syncState();
        drawlayout.addDrawerListener(drawerToggle);
    }
}

