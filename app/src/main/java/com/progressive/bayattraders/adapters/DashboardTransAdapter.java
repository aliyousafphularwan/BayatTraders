package com.progressive.bayattraders.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.progressive.bayattraders.R;
import com.progressive.bayattraders.models.DashboardTransactionDetails;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardTransAdapter extends RecyclerView.Adapter<DashboardTransAdapter.TransactionViewHolder> {

    private ArrayList<DashboardTransactionDetails> list;

    public DashboardTransAdapter(ArrayList<DashboardTransactionDetails> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.custom_dash_recycler_layout, parent, false);

        return new TransactionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

        String name = list.get(position).getName();
        String amount = list.get(position).getAmount();
        String pin = list.get(position).getPin();
        String date = list.get(position).getDate();

        holder.name.setText(name);
        holder.amount.setText(amount);
        holder.pin.setText(pin);
        holder.date.setText(date);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        TextView name, amount, pin, date;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.dash_trans_name);
            amount = itemView.findViewById(R.id.dash_trans_amount);
            pin  = itemView.findViewById(R.id.dash_trans_pin);
            date = itemView.findViewById(R.id.txt_dash_trans_date);

        }
    }
}

