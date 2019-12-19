package me.abir.dailycricketbd.busmodel;

/**
 * Created by Abir on 28-Oct-17.
 */

public class ScrollEventBus {
    private boolean isScrollingUp;

    public ScrollEventBus(boolean isScrollingUp) {
        this.isScrollingUp = isScrollingUp;
    }

    public boolean isScrollingUp() {
        return isScrollingUp;
    }
}
