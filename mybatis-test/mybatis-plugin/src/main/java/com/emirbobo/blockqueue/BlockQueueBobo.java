package com.emirbobo.blockqueue;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by xijingbo on 2016-09-02.
 */
public class BlockQueueBobo {

    static ConcurrentLinkedQueue<Integer> block = new ConcurrentLinkedQueue<Integer>();

    static BlockQueueBobo b = new BlockQueueBobo();
    final int full_size = 10;

    public static void main(String [] args){
        consumer c = b.new consumer();
        producer p = b.new producer();
        Thread tc = new Thread(c);
        Thread tp = new Thread(p);
        tc.start();
        tp.start();
        try {
            tc.join();
            tp.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void put(){
        int nextPut = new Random().nextInt(1000);
        block.add(nextPut);
        log("now add nextValue = "+nextPut);

    }
    private static void take(){
        Integer poll = block.poll();
        log("now take value = "+poll);
    }
    private class consumer implements Runnable{
        @Override
        public void run() {
            while(true) {
                synchronized (block) {
                    while (block.size() > 0) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    take();
                    notifyAll();
                }
            }
        }
    }

    private class producer implements Runnable{

        @Override
        public void run() {
            while(true) {
                synchronized (block) {
                    while (block.size() >= full_size) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    put();
                    notifyAll();
                }
            }
        }
    }

    private static void log(String v){
        System.out.println(v);
    }
}
