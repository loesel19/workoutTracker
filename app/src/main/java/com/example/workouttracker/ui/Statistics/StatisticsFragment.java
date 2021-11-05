package com.example.workouttracker.ui.Statistics;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.R;
import com.example.workouttracker.databinding.FragmentNotificationsBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {
    RecyclerView recyclerStatistics;

    private StatisticsViewModel statisticsViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statisticsViewModel =
                new ViewModelProvider(this).get(StatisticsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        statisticsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                s = "Statistics";
                textView.setText(s);
            }
        });
        recyclerStatistics = getActivity().findViewById(R.id.recyclerStatistics);


        return root;
    }

    public void displayStatistics(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
/*
public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder>{

    LayoutInflater inflater;
    ArrayList<String> statList;

    public StatsAdapter(Context context, ArrayList<String> statList){
        this.inflater = LayoutInflater.from(context);
        this.statList = statList;

    }

    @NonNull
    @NotNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StatsViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class StatsViewHolder extends RecyclerView.ViewHolder{
        RecyclerView statItemView;

        public StatsViewHolder(@NonNull @NotNull View itemView, StatsAdapter adapter) {
            super(itemView);
            statItemView = itemView.findViewById(R.id.recyclerStatistics);

        }
    }

}


 */