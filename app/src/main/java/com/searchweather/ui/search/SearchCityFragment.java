package com.searchweather.ui.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.searchweather.R;
import com.searchweather.databinding.FragmentSearchBinding;
import com.searchweather.ui.detail.DetailActivity;

/**
 * Created by Aleksandr Litvinchuck on 15.09.2017.
 */

public class SearchCityFragment extends Fragment {

    FragmentSearchBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        initViews();

        return binding.getRoot();
    }

    private void initViews() {

        binding.searchBtn.setOnClickListener(v -> {
            if (!binding.searchEdt.getText().toString().isEmpty()){

                DetailActivity.start(getActivity(),binding.searchEdt.getText().toString().trim());

            }
        });

    }


}
