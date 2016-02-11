package com.warrenjoe92.cordova;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by warrenjoe92 on 9/02/2016.
 */
public class DurationPickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private static final String TAG = "DurationPicker";
    private Delegate delegate;
    private TimePickerDialog timePicker;
    private int hrs = 0;
    private int min = 0;

    public void setDelegate (Delegate delegate){
        this.delegate = delegate;
    }

    public interface Delegate{
        public void timeSetCallback(int hrs, int min);
        public void cancelCallback();
    }

    public void setTime(int hrs, int min){
        this.hrs = hrs;
        this.min = min;
    }

    public Dialog onCreateDialog(Bundle saveInstanceState){
        // Use the current time as the default values for the picker
//        final Calendar c = Calendar.getInstance();
//        int hour = c.get(Calendar.HOUR_OF_DAY);
//        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        timePicker = new TimePickerDialog(getActivity(), this, this.hrs, this.min,
                DateFormat.is24HourFormat(getActivity()));
        return timePicker;
    }

    @Override
    public void onTimeSet(TimePicker view, int hrs, int min) {
        //Log.d(TAG, "time set");
        delegate.timeSetCallback(hrs, min);
    }

    @Override
    public void onCancel(DialogInterface dialog){
        delegate.cancelCallback();
    }
}
