package kiosk.app.discount.discountPolicy;

public class FixedAmountDiscountPolicy implements DiscountPolicy {

    private int discountAmount = 500;

    public int calculateDiscountedPrice(int price) {
        return price - discountAmount;
    }
}
