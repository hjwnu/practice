package kiosk.app.discount;

import kiosk.app.discount.discountCondition.DiscountCondition;

public class Discount {

  private DiscountCondition[] discountConditions;

  public Discount(DiscountCondition[] discountConditions) {
    this.discountConditions = discountConditions;
  }

  public Discount() {
  }

  public void checkAllDiscountConditions() {
    for (DiscountCondition discountCondition : discountConditions) {
      discountCondition.checkDiscountCondition();
    }
  }

  public int discount(int price) {

    int discountedPrice = price;

    for (DiscountCondition discountCondition : discountConditions) {
      if (discountCondition.isSatisfied()) discountedPrice = discountCondition.applyDiscount(discountedPrice);
    }

    return discountedPrice;
  }
}
