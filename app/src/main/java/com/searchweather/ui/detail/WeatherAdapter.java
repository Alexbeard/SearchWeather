package com.searchweather.ui.detail;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.searchweather.databinding.WeatherItemBinding;
import com.searchweather.model.weatherPojo.ListWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr Litvinchuck on 17.09.2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {


    private List<ListWeather> weather;
    private List<ListWeather> shownWeather;

    WeatherAdapter() {
        shownWeather = new ArrayList<>();
    }

    public void populateItems(List<ListWeather> weather) {
        this.weather = weather;
        shownWeather.addAll(weather);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        WeatherItemBinding binding = WeatherItemBinding.inflate(inflater, viewGroup, false);
        return new WeatherViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {

        ListWeather item = shownWeather.get(position);

        holder.binding.humidity.setText("Humidity: " + item.getMain().getHumidity());
        holder.binding.rainDescription.setText(item.getWeather().get(0).getDescription());
        holder.binding.minTempText.setText("Min temp: " + item.getMain().getTempMin());
        holder.binding.maxTempText.setText("Max temp: " + item.getMain().getTempMax());
        holder.binding.time.setText("Time: " + item.getDt());

        Picasso.with(holder.binding.weatherIcon.getContext())
                .load(item.getWeather().get(0).getIcon())
                .into(holder.binding.weatherIcon);

    }

    @Override
    public int getItemCount() {
        return shownWeather.size();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {


        WeatherItemBinding binding;

        WeatherViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

    }
}
