package com.bs23.simpleapp.domain;

import java.util.UUID;

public class OrderTestSamples {

    public static Order getOrderSample1() {
        return new Order()
            .id(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa"))
            .orderDate("orderDate1")
            .deliveryAddress("deliveryAddress1");
    }

    public static Order getOrderSample2() {
        return new Order()
            .id(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367"))
            .orderDate("orderDate2")
            .deliveryAddress("deliveryAddress2");
    }

    public static Order getOrderRandomSampleGenerator() {
        return new Order().id(UUID.randomUUID()).orderDate(UUID.randomUUID().toString()).deliveryAddress(UUID.randomUUID().toString());
    }
}
