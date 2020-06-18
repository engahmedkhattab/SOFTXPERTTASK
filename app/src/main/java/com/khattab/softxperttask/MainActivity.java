package com.khattab.softxperttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import android.os.Bundle;
import android.widget.Toast;
import com.khattab.softxperttask.cars.CarsAdapter;
import com.khattab.softxperttask.cars.CarsViewModel;
import com.khattab.softxperttask.cars.pojo.Cars;
import com.khattab.softxperttask.cars.pojo.Datum;
import com.khattab.softxperttask.common.aPIsUtils.InjectorUtils;
import com.khattab.softxperttask.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding iBinding;

    private CarsViewModel iViewModel;
    private List<Datum> iListOfCars = new ArrayList<>();

    private CarsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initView();
        obtainViewModel();
        subscribeToUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iViewModel.loadCars("1");
    }

    private void initView() {

        iBinding.lCarRV.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new CarsAdapter(this, iListOfCars);
        iBinding.lCarRV.setItemAnimator(new DefaultItemAnimator());
        iBinding.lCarRV.setAdapter(mAdapter);
    }


    private void obtainViewModel() {
        iViewModel = ViewModelProviders.of(this).get(CarsViewModel.class);
        iViewModel.setWebService(InjectorUtils.provideWinchWebService());
    }

    private void subscribeToUI() {

        iViewModel.getmList().observe(this, new Observer<Cars>() {
            @Override
            public void onChanged(Cars cars) {
                mAdapter.setList(cars.getData());
            }
        });

        iViewModel.getmHandleError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
