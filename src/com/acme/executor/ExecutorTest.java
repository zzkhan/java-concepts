package com.acme.executor;

import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: zzkhan
 * Date: 02/07/2014
 * Time: 18:54
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorTest {

    static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
            2,
            1, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1));

    static LinkedList<String> linkedList = new LinkedList<String>();

    public static void main(String[] args) {

        for(int i=0;i<10;i++){
            linkedList.offer(String.valueOf(i));
        }

        Executors.newFixedThreadPool(1).execute(new Runnable() {
            @Override
            public void run() {
                boolean doPoll = true;
                String value = null;
                while(!linkedList.isEmpty() || !doPoll){
                    if(doPoll){
                        value = linkedList.poll();
                    }
                    try{
                        threadPoolExecutor.submit(work(value));
                        System.out.println(String.format("task %s submitted successfully.",value));
                        doPoll = true;
                        value = null;
                    }catch (RejectedExecutionException e){
//                        System.out.println(String.format("task %s rejected, sleep and re-try....",value));
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }finally {
                            doPoll = false;
                            continue;
                        }
                    }
                }
                System.out.println("end of while loop");
                System.out.println("queue empty" + linkedList.isEmpty());
            }
        });
        System.out.println("end of outter thread");
    }

    private static Runnable work(final String value) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("working hard...." + value);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        };
    }
}
