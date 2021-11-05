package com.example.workouttracker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workouttracker.ui.dashboard.DashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.workouttracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    TextView txtTimer;
    TimerThread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide title and action bars
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        txtTimer = findViewById(R.id.txtTimer);
//        Log.d("timer", txtTimer.toString());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
    /**
     * this method will be called when the start button on the dashboard fragment is clicked.
     * it will create a new TimerThread object and pass in the current time, and then start
     * that object, which takes care of the rest of the logic for us.
     * @param view
     */
    public void startTimer(View view) throws InterruptedException {
        timer = new TimerThread(System.currentTimeMillis(),true);
        timer.join();
        timer.start();
    }
    public void stopTimer(View view){
        Toast.makeText(this,"stop clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * To properly implement the timer we need to make a Thread class
     * we will override this Threads run method for to add the timer logic.
     * Basically we use the @param startTime to subtract from the current time and
     * increment our timer if a second has passed
     * --we could try something like Thread.sleep(1000), increment counter, but after a few
     * iterations this method would become innacurate due to the uncertainty of Thread timing
     * Since this class is contained within the Fragment class we have access to the Views.
     */
    class TimerThread extends Thread{
        long startTime;
        boolean running;
        int hours;
        int minutes;
        int seconds;

        public TimerThread(long startTime, boolean running){
            this.startTime = startTime;
            this.running = running;
            hours = 0;
            minutes = 0;
            seconds = 0;
        }

        /**
         * this toString method implements logic for changing the time we record into a
         * string that we can display in our textView
         * @return
         */
        public String toString(){
            String time = "";
            if (hours > 10)
                time = time + hours + ":";
            else
                time = time + "0" + hours + ":";
            if (minutes > 10)
                time = time + minutes + ":";
            else
                time = time + "0" + minutes + ":";
            if (seconds > 10)
                time = time + seconds;
            else
                time = time + "0" + seconds + ":";
            return time;
        }
        /**
         * we override the run method of the thread to implement timer logic
         * The logic will be to enter a loop that will run until running is changed to false,
         * this will be done when a user hits the end button. to display the time we will get
         * the current system time in milliseconds when we create an instance of this class,
         * and in the loop we will keep getting the current time, if the currentTime -
         * startTime > 60,000 we add a second. If it is greater than 3,600,000 we add an hour
         */
        @Override
        public void run(){
            while(running){
                long passedTime = startTime - System.currentTimeMillis();
                //hours
                if (passedTime > 3600000){
                    hours = ((int) passedTime/3600000);
                    //now subtract the number of hours(in millis) from passed time
                    passedTime = passedTime - (hours * 3600000);
                } //minutes
                if(passedTime > 60000){
                    minutes = ((int) passedTime/60000);
                    passedTime = passedTime - (minutes * 60000);
                }//seconds
                if (passedTime > 1000){
                    seconds = ((int) passedTime/1000);
                    //dont worry about incrementing passedTime anymore
                }
                //now we have to display the time to the textView
                txtTimer.setText(1+"");
            }
        }
    }

}