package cn.com.hoonsoft.tool;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import cn.com.hoonsoft.R;

/**
 * 日期时间选择控件
 * 
 * @author 曾繁添
 * @version 1.0
 */
public class ToolDateTimePicker extends ToolDateTime {
    
    /**日期+时间选择**/
    public final static int DATE_TIME = 0;
    /**日期选择**/
    public final static int DATE = 1;
    /**时间选择**/
    public final static int TIME = 2;
    
    private Context context;
    private AlertDialog dialog;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private TextView targetView;
    private String nowDateTime;
    private int chooseType = 0;

    /***
     * 日期时间选择控件构造方法
     * @param activity activity
     * @param targetView 需要回填的TextView控件
     * @param chooseType 0：日期+时间 ；1：日期；2：时间
     */
    public ToolDateTimePicker(Context context,TextView targetView,int chooseType) {
        this.context = context;
        this.chooseType = chooseType;
        this.targetView = targetView;
    }
    
    public void show() 
    {
        Calendar c = Calendar.getInstance();
        switch (chooseType) {
            case DATE:
                new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear,int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(
                                                datePicker.getYear(), 
                                                datePicker.getMonth(),
                                                datePicker.getDayOfMonth()
                                            );
                                targetView.setText(formatDateTime(calendar.getTime(),DF_YYYY_MM_DD));
                            }
                        },
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DATE)).show();
                break;
            case TIME:
                new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(
                                                Calendar.YEAR, 
                                                Calendar.MONTH,
                                                Calendar.DAY_OF_MONTH, 
                                                timePicker.getCurrentHour(),
                                                timePicker.getCurrentMinute()
                                            );
                                targetView.setText(formatDateTime(calendar.getTime(),DF_HH_MM_SS));
                            }
                        },
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        true).show();
                break;
            default:
                LinearLayout dateTimeLayout = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.view_datetime, null);
                datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
                timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
               
                initDateTimePicker(datePicker,timePicker);
                timePicker.setIs24HourView(true);
                timePicker.setOnTimeChangedListener(mOnTimeChangedListener);

                dialog = new AlertDialog.Builder(context)
                .setTitle(nowDateTime)
                .setView(dateTimeLayout)
                .setPositiveButton( 
                                    android.R.string.ok,
                                    new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog,int whichButton)
                                        {
                                            targetView.setText(nowDateTime);
                                        }
                                    }
                ).setNegativeButton(
                                    android.R.string.cancel,
                                    new DialogInterface.OnClickListener()
                                    {
                                        public void onClick(DialogInterface dialog,int whichButton)
                                        {
                                            targetView.setText("");
                                        }
                                    }
               ).show();
                
                mOnDateChangedListener.onDateChanged(null, 0, 0, 0);
                break;
        }
    }
    
    /***
     * 初始化日期时间选择控件
     * @param datePicker 日期控件
     * @param timePicker 时间控件
     * @return
     */
    private String initDateTimePicker(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();
        nowDateTime = calendar.get(Calendar.YEAR) + "-" + 
                      calendar.get(Calendar.MONTH) + "-" +
                      calendar.get(Calendar.DAY_OF_MONTH) + " " +
                      calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                      calendar.get(Calendar.MINUTE) +
                      calendar.get(Calendar.SECOND);
        datePicker.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),mOnDateChangedListener);
        
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        
        return nowDateTime;
    }
    
    /***日期数据变更事件***/
    private OnDateChangedListener mOnDateChangedListener = new OnDateChangedListener(){

        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(   datePicker.getYear(), 
                            datePicker.getMonth(),
                            datePicker.getDayOfMonth(), 
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute()
                        );
            nowDateTime = formatDateTime(calendar.getTime(),DF_YYYY_MM_DD_HH_MM_SS);
            dialog.setTitle(nowDateTime);
        }
    };
    
    /***时间数据变更事件***/
    private OnTimeChangedListener mOnTimeChangedListener = new OnTimeChangedListener(){

        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            mOnDateChangedListener.onDateChanged(null, 0, 0, 0);
        }
    };
}
