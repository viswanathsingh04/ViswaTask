package com.viswanath.task.adatper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viswanath.task.R;
import com.viswanath.task.model.Results;

import java.util.List;

/**
 * Author: VPS
 * Created on 09-10-2018
 */
public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

    private Context mContext;
    private static int sModo = 0;
    private List<Results> mDataList;
    private static int sPosition;
    private static SparseBooleanArray sSelectedItems;
    private UpdateDataClickListener sClickListener;
    private static final int SINGLE = 1;
    private static final int MULTIPLE = 0;

    public interface UpdateDataClickListener {
        void onItemCheck(int position);
        void onItemUncheck(int postion);
    }

    public LanguageAdapter(Context context, List<Results> dataList, UpdateDataClickListener clickListener,  int modo) {
        this.mContext = context;
        this.mDataList = dataList;
        this.sClickListener = clickListener;
        sSelectedItems = new SparseBooleanArray();
        sModo = modo;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        LinearLayout item_main;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            item_main = itemView.findViewById(R.id.item_main);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (sSelectedItems.get(getAdapterPosition(), false)) {
                sSelectedItems.delete(getAdapterPosition());
                item_main.setSelected(false);
                sClickListener.onItemUncheck(getAdapterPosition());
                textView.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
            } else {
                switch (sModo) {
                    case SINGLE:
                        sSelectedItems.put(sPosition, false);
                        break;
                    case MULTIPLE:
                    default:
                        break;
                }
                textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                sSelectedItems.put(getAdapterPosition(), true);
                item_main.setSelected(true);
                sClickListener.onItemCheck(getAdapterPosition());
            }
        }
    }

    public void setOnItemClickListener(UpdateDataClickListener clickListener) {
        sClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_language_adapter, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Results entity = mDataList.get(position);
        holder.textView.setText(entity.getName());
        if (sSelectedItems.get(position)) {
            holder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else {
            holder.textView.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
        }
        holder.item_main.setSelected(sSelectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    public void selected(int position) {
        switch (sModo) {
            case SINGLE:
                sPosition = position;
                notifyDataSetChanged();
                break;
            case MULTIPLE:
            default:
                break;
        }

    }

    public void changeMode(int modo) {
        sModo = modo;
        sSelectedItems.clear();
        notifyDataSetChanged();
    }
}
