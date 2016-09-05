package com.bosphorusinteractive;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.batterydetails_demo.R;

/**
 * Created by livinglab on 5.09.2016.
 */

public class SpecsAdapter extends RecyclerView.Adapter<SpecsAdapter.SpecsViewHolder> {


    @Override
    public SpecsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SpecsViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SpecsViewHolder extends RecyclerView.ViewHolder {

         private TextView specsText;
         private TextView specsValue;

         public SpecsViewHolder(View itemView) {
             super(itemView);
             specsText = (TextView) itemView.findViewById(R.id.specsText);
             specsValue = (TextView) itemView.findViewById(R.id.specsValue);
         }
     }
}
