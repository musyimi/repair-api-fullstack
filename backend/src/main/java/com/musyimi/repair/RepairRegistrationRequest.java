package com.musyimi.repair;

public record RepairRegistrationRequest(
        String name,
        String title,
        String brand,
        String issue,
        String phoneNumber
) {

}
