package com.musyimi.repair;

public record RepairUpdateRequest(
        String name,
        String title,
        String brand,
        String phoneNumber,
        String issue
) {
}
