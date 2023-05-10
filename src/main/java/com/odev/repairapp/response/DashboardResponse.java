package com.odev.repairapp.response;


public record DashboardResponse(double amount, double cost, double profit,
                                long orderCount, long brandCount, long defectCount, long deviceCount) {
}
