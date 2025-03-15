package kiosk.app.discount.discountCondition;

import java.util.Scanner;
import kiosk.app.discount.discountPolicy.DiscountPolicy;

public class MemberDiscountCondition implements DiscountCondition {

  private boolean isSatisfied;
  private DiscountPolicy discountPolicy;

  public MemberDiscountCondition(DiscountPolicy discountPolicy) {
    this.discountPolicy = discountPolicy;
  }

  public boolean isSatisfied() {
    return isSatisfied;
  }

  private void setSatisfied(boolean satisfied) {
    isSatisfied = satisfied;
  }

  public void checkDiscountCondition() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("회원이십니까? (1)_예 (2)_아니오");
    String input = scanner.nextLine();

    if (input.equals("1")) setSatisfied(true); // TODO : 회원 도메인 추가 및 검증 로직 추가
    else if (input.equals("2")) setSatisfied(false);
  }

  public int applyDiscount(int price) {
    return discountPolicy.calculateDiscountedPrice(price);
  }
}
