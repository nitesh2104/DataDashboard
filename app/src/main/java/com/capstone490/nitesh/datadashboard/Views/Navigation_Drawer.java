package com.capstone490.nitesh.datadashboard.Views;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone490.nitesh.datadashboard.R;

public class Navigation_Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_DISCOVERABLE_DEVICE_SECURE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_navigation__drawer);
        this.registerReceiver(mReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView welcome_element = (TextView) findViewById(R.id.welcome_user);
        welcome_element.setText("Welcome "+ getIntent().getExtras().getString("Username") + "!");

        Button bluetooth_scan = (Button) findViewById(R.id.bluetooth_scan);
        bluetooth_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
                    Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                    startActivityForResult(discoverableIntent,REQUEST_DISCOVERABLE_DEVICE_SECURE);
                }
                else {
                    Intent serverIntent = new Intent(Navigation_Drawer.this, MainActivity.class);
                    startActivityForResult(serverIntent,REQUEST_CONNECT_DEVICE_SECURE);
                }
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE_SECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, true);
                }
                break;
        }
    }

    private void connectDevice(Intent data, boolean secure) {
        // Get the device MAC address
        String address = data.getExtras()
                .getString(MainActivity.EXTRA_DEVICE_ADDRESS);
        // Get the BluetoothDevice object
        BluetoothDevice device = btAdapter.getRemoteDevice(address);
        // TODO: Setup Connections
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation__drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(Navigation_Drawer.this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.power_graph) {
            Toast.makeText(getApplicationContext(),"Combined Data Analytics", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Navigation_Drawer.this, GraphActivity.class);
            startActivity(intent);
        }else if (id == R.id.rotating_panel_graph) {
            Toast.makeText(getApplicationContext(),"Rotating Panel Data Analytics", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Navigation_Drawer.this, Rotating_panel_graph.class);
            startActivity(intent);
        } else if (id == R.id.fixed_panel_graph) {
            Toast.makeText(getApplicationContext(),"Fixed Panel Data Analytics", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Navigation_Drawer.this, Fixed_panel_graph.class);
            startActivity(intent);
        } else if (id == R.id.data_analytics) {
            Toast.makeText(getApplicationContext(),"Data Analytics", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Drawer.this, CombinedAnalytics.class);
            startActivity(intent);
        } else if (id == R.id.user_profile) {
            Toast.makeText(getApplicationContext(),"User Profile", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Navigation_Drawer.this, UserProfile.class);
            intent.putExtra("Username",getIntent().getExtras().getString("Username"));
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Logout?");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            Intent intent = new Intent(Navigation_Drawer.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
