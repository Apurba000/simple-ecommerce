package com.bs23.simpleapp.domain;

import java.util.UUID;

public class CategoryTestSamples {

    public static Category getCategorySample1() {
        return new Category().id(UUID.fromString("23d8dc04-a48b-45d9-a01d-4b728f0ad4aa")).categoryName("categoryName1");
    }

    public static Category getCategorySample2() {
        return new Category().id(UUID.fromString("ad79f240-3727-46c3-b89f-2cf6ebd74367")).categoryName("categoryName2");
    }

    public static Category getCategoryRandomSampleGenerator() {
        return new Category().id(UUID.randomUUID()).categoryName(UUID.randomUUID().toString());
    }
}
