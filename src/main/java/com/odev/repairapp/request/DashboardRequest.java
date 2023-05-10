package com.odev.repairapp.request;

public record DashboardRequest(Boolean paymentStatus, Long statusId, Long priorityId) {
}
