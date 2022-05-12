package com.mashibing.juc.c_32_AliQuestions;

import com.mashibing.util.SleepHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @auther LXY
 * @create 2022/5/11 17:10
 * @Description
 * 问题：可以归为分布式事务失败回滚的手工实现
 * 代码不完善，需要根据使用场景进一步完善
 */
public class T06_05_AliQestionAboutCF_3 {

    //任务执行结束的三种状态
    private static enum Result {
         SUCCESS, FAIL, CANCELLED
    }

    static List<MyTask> tasks= new ArrayList<>();

    public static void main(String[] args) throws  Exception{
        MyTask task1 = new MyTask("task1",3,Result.SUCCESS);
        MyTask task2 = new MyTask("task2",4,Result.SUCCESS);
        MyTask task3 = new MyTask("task3",1,Result.FAIL);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        for (MyTask task : tasks) {
            //异步方式去调用task并接收result、回调callback
           CompletableFuture f = CompletableFuture.supplyAsync(()->task.runTask()).thenAccept((result)->callback(result,task));
        }
        TimeUnit.SECONDS.sleep(10);
    }

    private static Result callback(Result result,MyTask task){
        if (Result.FAIL == result){
            for (MyTask ta: tasks) {
                if (ta != task){
                    ta.cancel();
                }
            }
        }
        return result;
    }

    private static class MyTask{

        private String name;
        private int timeInSeconds;
        private  Result ret;

        private boolean cancelling = false;
        private volatile boolean cancelled = false;

        public MyTask(String name, int timeInSeconds, Result ret) {
            this.name = name;
            this.timeInSeconds = timeInSeconds + 1000;
            this.ret = ret;
        }

        public Result runTask(){
            int interval = 100;
            int total = 0;
            try {
                for (;;) {
                    Thread.sleep(interval);
                    if (cancelling)continue;
                    total += interval;
                    if (total >= timeInSeconds) break;
                    if (cancelled) return Result.CANCELLED;
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(name +" end !");
            return ret;
        }

        /**
         * cancel过程需要自己实现
         */
        public void  cancel() {
            cancelling = true;
            synchronized (this){
                try {
                    System.out.println( name + " cancelling");
                    //SleepHelper.sleepMill(50);
                   // Thread.sleep(50);

                    System.out.println( name + " cancelled");
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
            cancelled = true;
        }
    }



    }
