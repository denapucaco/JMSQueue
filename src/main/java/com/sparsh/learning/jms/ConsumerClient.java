package com.sparsh.learning.jms;

/**
 * @author prashant.swamy
 *
 */
public class ConsumerClient {

    public static void main(String... args) throws InterruptedException {

        new Thread(new Consumer()).start();

        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();

        Thread.sleep(1000);

        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();

        Thread.sleep(2000);

        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();

        Thread.sleep(3000);

        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();

    }

}
