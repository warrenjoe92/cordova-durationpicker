package com.warrenjoe92.cordova;

import android.app.Activity;
import android.app.DialogFragment;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.provider.OpenableColumns;
import android.database.Cursor;
import android.content.Context;

import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

public class DurationPicker extends CordovaPlugin {

    private static final String TAG = "DurationPicker";
    private static final String ACTION_SHOW = "show";
    private static final int PICK_FILE_REQUEST = 1;
    CallbackContext callback;

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {

        if (action.equals(ACTION_SHOW)) {
            showPicker(callbackContext);
            return true;
        }

        return false;
    }

    public void showPicker(CallbackContext callbackContext) {

        Log.d(TAG, "show picker");

        DialogFragment newFragment = new DurationPickerFragment();
        //Context c = this.cordova.getActivity().getApplicationContext();
        newFragment.show(this.cordova.getActivity().getFragmentManager(), "timePicker");


        // type and title should be configurable

        // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // intent.setType("*/*");
        // intent.addCategory(Intent.CATEGORY_OPENABLE);
        // intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        //
        // Intent chooser = Intent.createChooser(intent, "Select File");
        // cordova.startActivityForResult(this, chooser, PICK_FILE_REQUEST);
        //
        // PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
        // pluginResult.setKeepCallback(true);
        // callback = callbackContext;
        // callbackContext.sendPluginResult(pluginResult);
    }

    // @Override
    // public void onActivityResult(int requestCode, int resultCode, Intent data) {
    //
    //     if (requestCode == PICK_FILE_REQUEST && callback != null) {
    //       Log.d(TAG, "activity result");
    //     }
    // }

}
