package com.example.workouttracker.ui.dashboard;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.workouttracker.MainActivity;
import com.example.workouttracker.R;
import com.example.workouttracker.databinding.FragmentDashboardBinding;

import org.w3c.dom.Text;

import java.util.Timer;

public class DashboardFragment extends Fragment {

    private static FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;
    TextView txtTimer;
    View root;
    Button btnStart;
    Button btnEnd;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("debug", "created");
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        txtTimer = root.findViewById(R.id.txtTimer);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static FragmentDashboardBinding getBinding(){
        return binding;
    }


}