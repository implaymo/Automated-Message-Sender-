package com.sendit;

import java.util.Timer;
import java.util.TimerTask;

public class ClockTimer {
    int delay = 1000;
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {


        @Override
        public void run() {

        }
    };
    public void startTimer() {
        timer.schedule(task, delay);
    }
}
