package com.driver.Model;


public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        int time = 0;
        String s1 = deliveryTime.substring(0,2);
        time = Integer.parseInt(s1)*60;
        String s2 = deliveryTime.substring(3,5);
        time = time + Integer.parseInt(s2);
        this.id = id;
        this.deliveryTime = time;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
