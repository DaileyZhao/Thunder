package com.android.thunder.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thunder.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright(c) 2016 All Rights Reserved.
 * Author: ZhaoCunming
 * Time: 16-12-16 12:03
 * E-mail: zhaocunming@dayima.com
 * Version：V1.0.1
 * ProjectName: Thunder
 * Description: TODO
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    public static ArrayList<String> getSampleData(int size)
    {
        ArrayList<String> sampleData = new ArrayList<String>(size);
        for(int i = 0; i < size; i++)
        {
            //  每一项数据后面都有相应的序号
            sampleData.add("新的列表项<" + i + ">");
        }

        return sampleData;
    }
    private Context context;
    private List<String> datas=getSampleData(20);
    public RecyclerAdapter(Context context){
        this.context=context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,parent,false);
        return new RecyclerViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        String rowdata=datas.get(position);
        holder.item_text.setText(rowdata);
        holder.itemView.setTag(rowdata);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //  删除指定的Item
    public void removeData(int position)
    {
        datas.remove(position);
        //  通知RecyclerView控件某个Item已经被删除
        notifyItemRemoved(position);

    }
    //  在指定位置添加一个新的Item
    public void addItem(int positionToAdd)
    {
        datas.add(positionToAdd,"新的列表项" + new Random().nextInt(10000));
        //  通知RecyclerView控件插入了某个Item
        notifyItemInserted(positionToAdd);
    }
    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView item_text;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            item_text= (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}
