package com.odev.repairapp.model;

public enum Authority {

    ACCESS_DASHBOARD("Dashboard Access"),
    MANAGE_REPAIR_ORDER("Manage Orders"),
    EDIT_REPAIR_ORDER("Edit Orders"),
    REMOVE_REPAIR_ORDER("Remove Orders"),
    MANAGE_DEVICE("Manage Devices"),
    MANAGE_DEFECT("Manage Defects"),
    MANAGE_BRAND("Manage Brands"),
    MANAGE_QUICK_REPLY("Manage Quick Replies"),
    MANAGE_REPAIR_STATUS("Manage Statuses"),
    MANAGE_REPAIR_PRIORITY("Manage Priorities"),
    ACCESS_REPORT("Access Report"),
    MANAGE_USER("Manage Users"),
    GENERAL_SETTING("General Setting"),
    OUTGOING_MAIL("Outgoing Mail"),
    SMS_GATEWAY("SMS Gateway"),
    CURRENCY_CONFIGURATION("Currency Configuration"),
    AUTHENTICATION("Authentication"),
    LOCALIZATION("Localization"),
    DATABASE_BACKUP("Database Backup");

    private final String label;

    Authority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }


}
