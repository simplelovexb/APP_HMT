package cn.edu.scau.hometown.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import cn.edu.scau.hometown.R;

/**
 * Created by laisixiang on 2015/10/11.
 */
public class PushCommendAcitivity extends Activity implements View.OnClickListener{

    private EditText et_check;
    private EditText et_homeword;
    private EditText et_exam;
    private EditText et_scroe;
    private EditText et_user;
    private EditText et_select;
    private EditText et_commend_time;
    private EditText et_ex;
    private CheckBox cb_named;
    private Button bt_push;

    private String string_check;
    private String string_homeword;
    private String string_exam;
    private String string_score;
    private String string_ex;
    private String string_time;
    private String string_username;
    private String string_select_time;

    private Spinner spinner=null;
    private ArrayAdapter<CharSequence> adapter_spinner=null;
    private String[] data_spinner={"2014-2015 第一学期","2014-2015 第一学期","2014-2015 第一学期"};

    private Handler handler=null;
    private String timeInString="";
    private Time t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview_only1);

        findView();
        setListener();
        initSpinner();
    }

    public void findView(){
        et_check = (EditText) findViewById(R.id.et_check);
        et_homeword = (EditText) findViewById(R.id.et_homeword);
        et_scroe = (EditText) findViewById(R.id.et_score);
        et_ex = (EditText) findViewById(R.id.et_ex);
        et_exam = (EditText) findViewById(R.id.et_exam);
        cb_named = (CheckBox) findViewById(R.id.cb_named_selector);
        bt_push = (Button) findViewById(R.id.bt_push);
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    public void setListener(){
        bt_push.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_push :
                push_bt();
        }
    }

    public void push_bt(){
        string_check = et_check.getText().toString();
        string_homeword = et_homeword.getText().toString();
        string_score = et_scroe.getText().toString();
        string_exam = et_exam.getText().toString();
        string_ex = et_ex.getText().toString();
        string_username = getUserName();
        string_time = getTime(2);
        string_select_time = spinner.getSelectedItem().toString();


        Toast.makeText(this,string_select_time+string_check+string_ex+string_exam+string_homeword+string_score+string_time+string_username+"",Toast.LENGTH_SHORT).show();

    }

    public void initSpinner(){
        t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month;

        adapter_spinner = new ArrayAdapter<CharSequence>(
                this,android.R.layout.simple_spinner_dropdown_item);

        if (month>8){
            for(int i=0;i<3;i++){
                adapter_spinner.add((year-1-i) +"-"+(year-i)+"上学期");
                adapter_spinner.add((year-1-i) +"-"+(year-i)+"下学期");
            }
        }else {
            for(int i=0;i<3;i++){
                adapter_spinner.add((year-i) +"-"+(year-i+1)+"上学期");
                adapter_spinner.add((year-i) +"-"+(year-i+1)+"下学期");
            }
        }

        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_spinner);
    }

    /**
     * 获取时间
     * @param type   1代表网络时间，2代表本地系统时间
     * @return
     */
    public String getTime(int type){
        if (type == 1){
            Runnable getOnLineTime=new Runnable(){
                @Override
                public void run() {
                    URL url;
                    try {
                        url = new URL("http://www.baidu.com");
                        URLConnection uc = url.openConnection();
                        uc.connect();
                        long ld = uc.getDate();
                        Date date = new Date(ld);
                        Log.d("laisx", date + ", " + date.getHours() + "时" + date.getMinutes()
                                + "分" + date.getSeconds() + "秒");

                        timeInString = date + ", " + date.getHours() + "时" + date.getMinutes()
                                + "分" + date.getSeconds() + "秒";

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.post(getOnLineTime);
        }
        else{
            t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
            t.setToNow(); // 取得系统时间。
            int year = t.year;
            int month = t.month;
            int date = t.monthDay;
            int hour = t.hour; // 0-23
            int minute = t.minute;
            int second = t.second;
            timeInString = year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
        }

        return timeInString;
    }

    private String getUserName(){
        return cb_named.isChecked()?"匿名":"小小鸟";
    }

}
