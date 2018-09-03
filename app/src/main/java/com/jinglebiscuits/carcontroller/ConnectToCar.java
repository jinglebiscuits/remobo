package com.jinglebiscuits.carcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Set;

public class ConnectToCar extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mBluetoothDevice;
    private final int REQUEST_ENABLE_BT = 1;
    private final String MY_DEVICE_NAME= "";
    private final String LOG_TAG = this.getLocalClassName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_car);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectToCar();
            }
        });

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            new AlertDialog.Builder(this).setTitle("No bluetooth")
              .setMessage("You need a bluetooth enabled device for this app.")
              .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                      finish();
                  }
              })
              .show();
        }
    }

    private boolean isConnected(Set<BluetoothDevice> pairedDevices) {
        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.d(LOG_TAG, "Device name: " + deviceName);
                if (TextUtils.equals(deviceName, MY_DEVICE_NAME)) {
                    mBluetoothDevice = device;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_connect_to_car, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void connectToCar() {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (isConnected(pairedDevices)) {

            }
        }
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_ENABLE_BT) {
            connectToCar();
        } else {
            Log.d("ConnectToCar", "User doesn't want bluetooth on");
        }
    }
}
