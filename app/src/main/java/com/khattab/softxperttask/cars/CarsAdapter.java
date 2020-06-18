package com.khattab.softxperttask.cars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.khattab.softxperttask.R;
import com.khattab.softxperttask.cars.pojo.Datum;

import java.util.List;

/**
 * Created by yousif on 30/04/18.
 */

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    private List<Datum> iListOfCars;
    Context context;



    public CarsAdapter(Context context, List<Datum> iListOfCars) {
        this.iListOfCars = iListOfCars;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cars_item, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        viewHolder.brand.setText(iListOfCars.get(position).getBrand());
        viewHolder.isUsed.setText(iListOfCars.get(position).getIsUsed().toString());
        viewHolder.constructionYear.setText(iListOfCars.get(position).getConstructionYear());
        String url;

              url = iListOfCars.get(position).getImageUrl();

        Glide.with(context).load(url).into(viewHolder.imageView);



    }


    @Override
    public int getItemCount() {
        return iListOfCars == null ? 0 : iListOfCars.size();
    }

    public void setList(List<Datum> iListOfCars) {
        this.iListOfCars = iListOfCars;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView brand,isUsed,constructionYear;

        private ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            brand = itemView.findViewById(R.id.brand);
            isUsed = itemView.findViewById(R.id.isUsed);
            constructionYear = itemView.findViewById(R.id.constructionYear);
        }

    }

}