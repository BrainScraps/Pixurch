package com.codepath.pixurch.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.pixurch.R;
import com.codepath.pixurch.models.ImageResult;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<ImageResult> results;

    public ResultAdapter(Context context, ArrayList<ImageResult> results){
       this.context = context;
        inflater = LayoutInflater.from(context);
        this.results = results;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.result_card_layout, viewGroup,false);
        ResultViewHolder holder = new ResultViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int i) {
        ImageResult result = results.get(i);
//        holder.title.setText(Html.fromHtml(result.title));
        Picasso.with(context).load(result.fullUrl).into(holder.ivResult);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView ivResult;
        public ResultViewHolder(View itemView) {
            super(itemView);
//            title = (TextView) itemView.findViewById(R.id.tvTitle);
            ivResult = (ImageView) itemView.findViewById(R.id.ivResult);

        }
    }

}
