package by.etc.karamach.restaurant.mcdonald;

import by.etc.karamach.restaurant.mcdonald.cashwindow.McdonaldCashWindow;
import by.etc.karamach.restaurant.mcdonald.customer.RestaurantVisitor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        McdonaldCashWindow cashWindow1 = new McdonaldCashWindow(lock1, "Window1");
        McdonaldCashWindow cashWindow2 = new McdonaldCashWindow(lock2, "Window2");

        RestaurantVisitor visitor1 = new RestaurantVisitor(cashWindow1, "Customer1");
        RestaurantVisitor visitor2 = new RestaurantVisitor(cashWindow1, "Customer2");
        RestaurantVisitor visitor3 = new RestaurantVisitor(cashWindow1, "Customer3");

        RestaurantVisitor visitor4 = new RestaurantVisitor(cashWindow2, "Customer4");
        RestaurantVisitor visitor5 = new RestaurantVisitor(cashWindow2, "Customer5");
        RestaurantVisitor visitor6 = new RestaurantVisitor(cashWindow2, "Customer6");

        Thread thread1 = new Thread(visitor1);
        Thread thread2 = new Thread(visitor2);
        Thread thread3 = new Thread(visitor3);


        Thread thread4 = new Thread(visitor4);
        Thread thread5 = new Thread(visitor5);
        Thread thread6 = new Thread(visitor6);


        thread1.start();
        thread2.start();
        thread3.start();

        TimeUnit.SECONDS.sleep(2);

        thread4.start();
        thread5.start();
        thread6.start();

        System.out.println("All customers came!");

    }

}
