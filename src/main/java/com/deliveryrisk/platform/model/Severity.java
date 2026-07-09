package com.deliveryrisk.platform.model;

public enum Severity {
    LOW(1),
    MEDIUM(2),
    HIGH(4),
    CRITICAL(8);

    private final int weight;

    Severity(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
