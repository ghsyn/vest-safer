package com.example.vestsafer;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MagerSafetyAdapter extends BaseAdapter {
    private Activity parentActivity;
    private Context context;
    private List<Bluetooth> bluetoothList;
    private List<Bluetooth> saveList;

    public MagerSafetyAdapter(Context context, List<Bluetooth> bluetoothList, Activity parentActivity,List<Bluetooth> saveList){
        this.context=context;
        this.bluetoothList=bluetoothList;
        this.parentActivity = parentActivity;
        this.saveList = saveList;
    }
    @Override
    public int getCount() {
        return bluetoothList.size();
    }

    @Override
    public Object getItem(int i) {
        return bluetoothList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v= View.inflate(context, R.layout.mgr_saftyuser, null);
        TextView str_btn =(TextView) v.findViewById(R.id.str_btn);
        TextView userId = (TextView) v.findViewById(R.id.userID);
        TextView str_hum=(TextView) v.findViewById(R.id.str_hum);
        TextView str_temp=(TextView) v.findViewById(R.id.str_temp);
        TextView str_dust=(TextView) v.findViewById(R.id.str_dust);
        TextView str_co=(TextView) v.findViewById(R.id.str_co);

        userId.setText(bluetoothList.get(i).getUserID());
        str_btn.setText(bluetoothList.get(i).getStr_btn());
        str_hum.setText(bluetoothList.get(i).getStr_hum());
        str_dust.setText(bluetoothList.get(i).getStr_dust());
        str_temp.setText(bluetoothList.get(i).getStr_temp());
        str_co.setText(bluetoothList.get(i).getStr_co());
        v.setTag(bluetoothList.get(i).getUserID());

        return v;
    }
}
