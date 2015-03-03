package com.codepath.pixurch.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.pixurch.ImageDetail;
import com.codepath.pixurch.R;

/**
* Created by isaac on 3/2/15.
*/
public class ResultViewHolder extends RecyclerView.ViewHolder {

    private ResultAdapter resultAdapter;
    TextView title;
    int position;
    ImageView ivResult;
    Context parentContext;
    OnItemClickListener mClickListener;
    public ResultViewHolder(ResultAdapter resultAdapter, View itemView, Context parentContext) {

        super(itemView);
        this.resultAdapter = resultAdapter;
//            title = (TextView) itemView.findViewById(R.id.tvTitle);
        ivResult = (ImageView) itemView.findViewById(R.id.ivResult);

    }
    public void setItem(int i){
       this.position = i;
    }


    public interface OnItemClickListener{
        public void OnItemClick(View view, int position);
    }

}
