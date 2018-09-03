package com.jinglebiscuits.carcontroller.model;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;

public class ConnectThread extends Thread {

    private final BluetoothAdapter mBluetoothAdapter;
    private final BluetoothDevice mDevice;
    private InputStream mInStream;
    private OutputStream mOutStream;
    private byte[] mBuffer; // mmBuffer store for the stream
    private BluetoothSocket mSocket;
    private final String SERVICE_ID = "00001101-0000-1000-8000-00805f9b34fb";
    private final String TAG = "ConnectThread";

    public ConnectThread (BluetoothAdapter bluetoothAdapter, BluetoothDevice device) {
        mDevice = device;
        mBluetoothAdapter = bluetoothAdapter;
        // Use a temporary object that is later assigned to mmSocket
        // because mmSocket is final.
        BluetoothSocket tmp = null;

        try {
            // Get a BluetoothSocket to connect with the given BluetoothDevice.
            // MY_UUID is the app's UUID string, also used in the server code.
            tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(SERVICE_ID));
            mInStream = tmp.getInputStream();
            mOutStream = tmp.getOutputStream();
        } catch (IOException e) {
            Log.e(TAG, "Socket's create() method failed", e);
        }
        mSocket = tmp;
    }

    public boolean isConnected() {
        return mSocket.isConnected();
    }

    public void run() {
        Log.d(TAG, "connecting...");
        mBuffer = new byte[2];
        int numBytes; // bytes returned from read()
        if (!mSocket.isConnected()) {
            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mSocket.connect();
                Log.d(TAG, "connected");
            } catch (IOException connectException) {
                // Unable to connect; close the socket and return.
                try {
                    mSocket.close();
                } catch (IOException closeException) {
                    Log.e(TAG, "Could not close the client socket", closeException);
                }
                return;
            }
        }
        if (mSocket.isConnected()) {
            while (true) {
                try {
                    // Read from the InputStream.
                    Log.d(TAG, "reading...");
                    numBytes = mInStream.read(mBuffer);
                    // Send the obtained bytes to the UI activity.
                    Log.d(TAG, Arrays.toString(mBuffer));
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    Log.d(TAG, "Input stream was disconnected", e);
                    break;
                }
            }
        }
    }

    // Call this from the main activity to send data to the remote device.
    public void write(byte[] bytes) {
        Log.d(TAG, "sending " + bytes.length + " values " + Arrays.toString(bytes));
        try {
            mOutStream.write(bytes);

            // Share the sent message with the UI activity.
//            Message writtenMsg = mHandler.obtainMessage(
//              MessageConstants.MESSAGE_WRITE, -1, -1, mmBuffer);
//            writtenMsg.sendToTarget();
        } catch (IOException e) {
            Log.e(TAG, "Error occurred when sending data", e);

            // Send a failure message back to the activity.
//            Message writeErrorMsg =
//              mHandler.obtainMessage(MessageConstants.MESSAGE_TOAST);
            Bundle bundle = new Bundle();
            bundle.putString("toast",
              "Couldn't send data to the other device");
//            writeErrorMsg.setData(bundle);
//            mHandler.sendMessage(writeErrorMsg);
        }
    }

    // Closes the client socket and causes the thread to finish.
    public void cancel() {
        try {
            mSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the client socket", e);
        }
    }
}
