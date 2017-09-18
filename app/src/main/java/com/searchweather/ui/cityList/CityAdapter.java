package com.searchweather.ui.cityList;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.searchweather.databinding.CityItemBinding;
import com.searchweather.model.realmPojo.City;

import java.util.ArrayList;
import java.util.List;

import rx.subjects.PublishSubject;


/**
 * Created by Aleksandr Litvinchuck on 17.09.2017.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private List<City> cities = new ArrayList<>();



    PublishSubject<City> clickCity;
    PublishSubject<City> clickDelete;

    public CityAdapter() {
        clickCity = PublishSubject.create();
        clickDelete = PublishSubject.create();

    }

    public void populateCities(List<City> cities){
        this.cities = cities;
        notifyDataSetChanged();
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CityItemBinding binding = CityItemBinding.inflate(inflater, parent, false);
        return new CityViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        City item = cities.get(position);

        holder.binding.cityName.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        if (cities == null) {
            return 0;
        }
        return cities.size();
    }

    @Nullable
    public City getItem(int position) {
        if (position < cities.size()) {
            return cities.get(position);
        }
        return null;
    }

    public  class CityViewHolder extends RecyclerView.ViewHolder {

        CityItemBinding binding;


        public CityViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(v -> clickCity.onNext(getItem(getAdapterPosition())));
            binding.cityRemove.setOnClickListener(v -> clickDelete.onNext(getItem(getAdapterPosition())));
        }


    }
}