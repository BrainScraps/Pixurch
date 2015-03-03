package com.codepath.pixurch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.pixurch.R;
import com.codepath.pixurch.models.ImageResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    public ArrayList<ImageResult> results;

    public ResultAdapter(Context context, ArrayList<ImageResult> results){
       this.context = context;
        inflater = LayoutInflater.from(context);
        this.results = results;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.result_card_layout, viewGroup, false);

        ResultViewHolder holder = new ResultViewHolder(this, view, this.context);
        return holder;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int i) {
        ImageResult result = results.get(i);
        holder.setItem(i);
//        holder.title.setText(Html.fromHtml(result.title));
        Picasso.with(context).load(result.fullUrl).into(holder.ivResult);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


}
