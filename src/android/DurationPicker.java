package com.warrenjoe92.cordova;

import android.app.Activity;
import android.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.provider.OpenableColumns;
import android.database.Cursor;
import android.content.Context;
import android.widget.TimePicker;

import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DurationPicker extends CordovaPlugin implements DurationPickerFragment.Delegate{

    private static final String TAG = "DurationPicker";
    private static final String ACTION_SHOW = "show";
    private static final int PICK_FILE_REQUEST = 1;
    CallbackContext callback;

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {

        //get arguments
        int hours = args.getInt(0);
        if (hours < 0){
            hours = 0;
        }
        int minutes = args.getInt(1);
        if (minutes < 0){
            minutes = 0;
        }

        Log.d(TAG, "got arguments " + hours + " " + minutes);

        if (action.equals(ACTION_SHOW)) {
            showPicker(callbackContext, hours, minutes);
            return true;
        }

        return false;
    }

    public void showPicker(CallbackContext callbackContext, int hours, int minutes) {

        Log.d(TAG, "show picker");

        DurationPickerFragment fragment = new DurationPickerFragment();
        fragment.setDelegate(this);
        fragment.setTime(hours, minutes);

        //Context c = this.cordova.getActivity().getApplicationContext();
        fragment.show(this.cordova.getActivity().getFragmentManager(), "timePicker");

        callback = callbackContext;


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

    @Override
    public void timeSetCallback(int hrs, int min) {
        Log.d(TAG, "time set callback " + hrs + " " + min);
        try{
            JSONObject json = new JSONObject();
            json.put("hrs", hrs);
            json.put("min", min);
            callback.success(json);
        }
        catch(JSONException e){
            callback.error("JSON error");
        }
    }

    @Override
    public void cancelCallback(){
        callback.error("user cancelled");
    }

    // @Override
    // public void onActivityResult(int requestCode, int resultCode, Intent data) {
    //
    //     if (requestCode == PICK_FILE_REQUEST && callback != null) {
    //       Log.d(TAG, "activity result");
    //     }
    // }

}
