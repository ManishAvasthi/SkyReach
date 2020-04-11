package com.example.skyreach;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


public class Side_Navigation extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    BottomNavigationView bottomappbar;
    Fragment f=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidenavigation);
        //f=new HomeFragment();
        drawerLayout =(DrawerLayout) findViewById(R.id.navigation_drawer);
        Toolbar tb=(Toolbar) findViewById(R.id.top_bar);
        setSupportActionBar(tb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,tb,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navdraw_Achieve:
                        f=new AchievementFragment();
                        break;

                    case R.id.navdraw_staff:
                        f=new StaffFragment();
                        break;

                    case R.id.navdraw_fees:
                        f=new FeesFragment();
                        break;

                    case R.id.navdraw_developer:
                        f=new DeveloperFragment();
                        break;

                    case R.id.navdraw_about:
                        f=new AboutusFragment();
                        break;

                    case R.id.navdraw_branches:
                        f=new BranchesFragment();
                        break;

                    case R.id.navdraw_feed:
                        f=new FeedbackFragment();
                        break;

                    case R.id.navdraw_logout:
                        logout();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return loadFragment(f);
            }

        });





        //bottom
        bottomappbar=(BottomNavigationView) findViewById(R.id.bottom_navigation);
       bottomappbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()) {
                   case R.id.nav_home:
                       f = new HomeFragment();
                       break;

                   case R.id.nav_attendance:
                       f = new AttendanceFragment();
                       break;

                   case R.id.nav_contact:
                       f = new ContactFragment();
                       break;

                   case R.id.nav_notes:
                       f = new NotesFragment();
                       break;
               }
               return loadFragment(f);
           }
       });
       if(savedInstanceState==null)
       {
           bottomappbar.setSelectedItemId(R.id.nav_home);
       }
    }

    private void logout()
    {
        FirebaseAuth.getInstance().signOut();
        Intent i=new Intent(getApplicationContext(),Sign_in.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    private boolean loadFragment(Fragment fragment)
    {
        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
            return true;
        }
        return false;
    }


    public void onBackPressed () {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }



//        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
//        bnv.setOnNavigationItemSelectedListener(navListner);
//       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//    }
//

   /* private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    f = new HomeFragment();
                    break;

                case R.id.nav_attendance:
                    f = new AttendanceFragment();
                    break;

                case R.id.nav_contact:
                    f = new ContactFragment();
                    break;

                case R.id.nav_notes:
                    f = new NotesFragment();
                    break;
            }
            return loadFragment(f);
        }
    };*/

        /*@Override
        public boolean onNavigationItemSelected (@NonNull MenuItem menuItem){
            switch (menuItem.getItemId()) {
                case R.id.navdraw_Achieve:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AchievementFragment()).commit();
                    break;

                case R.id.navdraw_staff:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StaffFragment()).commit();
                    break;

                case R.id.navdraw_fees:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeesFragment()).commit();
                    break;

                case R.id.navdraw_about:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutusFragment()).commit();
                    break;

                case R.id.navdraw_developer:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BranchesFragment()).commit();
                    break;

                case R.id.navdraw_feed:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedbackFragment()).commit();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }*/

    }
