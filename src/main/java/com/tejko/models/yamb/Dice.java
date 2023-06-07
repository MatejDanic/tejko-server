package com.tejko.models.yamb;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Dice implements Serializable {

    private int value = 6;
    private int order;

    private Dice() {}

    private Dice(int order) {
        this.order = order;
    }

    public static Dice createDice(int order) {
        return new Dice(order);
    }

    public int getValue() {
        return this.value;
    }

    public int getOrder() {
        return this.order;
    }

    public void roll() {
        this.value = ThreadLocalRandom.current().nextInt(1, 7);
    }

}
