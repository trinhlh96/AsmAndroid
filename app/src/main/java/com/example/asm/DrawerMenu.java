package com.example.asm;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.asm.fragment.FragmentManage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class DrawerMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener{
    DrawerLayout drawerLayout;
    BottomNavigationView navigationView;
    FrameLayout frameLayout;
    FragmentManage fragmentManage;
    List listFr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_menu);
        initView();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.drawer_bottom_view);
        navigationView.bringToFront();
        navigationView.setOnItemSelectedListener(this);
        frameLayout = findViewById(R.id.main_content);
        fragmentManage = new FragmentManage();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, fragmentManage, FragmentManage.class.getName())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.list:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, listFr, List.class.getName())
                        .commit();
                break;
            case R.id.create:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragmentManage, FragmentManage.class.getName())
                        .commit();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return false;
    }

}
