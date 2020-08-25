package com.github.cron.master;

import com.github.cron.cmd.handler.ConfigHandler;
import com.github.cron.cmd.svc.TaskScheduler;
import com.github.cron.tasks.GitPullJob;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;

public class Controller {

    public static void main(String[] args) throws ParseException, InterruptedException, SchedulerException {
        System.out.println("🐕 starting watchdog...");
        System.out.println("🚀 initiating config handler...");
        ConfigHandler configHandler = new ConfigHandler();
        Hashtable<String, String> config = null;

        try {
            System.out.print("💫 accessing config properties... ");
            config = configHandler.readConfigProperties();

//            print the config dict
            /**
             for (Map.Entry<String, String> entry : config.entrySet()) {
             String key = entry.getKey();
             String value = entry.getValue();
             System.out.println(key + " : " + value);
             }
             */

        } catch (IOException e) {
            e.printStackTrace();
        }
        TaskScheduler taskScheduler = new TaskScheduler();
        GitPullJob gitPullJob = new GitPullJob();
        taskScheduler.runScheduler(gitPullJob);

    }
}