package kiosk.app;

import kiosk.Cart;
import kiosk.Menu;
import kiosk.app.discount.Discount;
import kiosk.app.discount.discountCondition.DiscountCondition;
import kiosk.app.discount.discountCondition.KidDiscountCondition;
import kiosk.app.discount.discountCondition.MemberDiscountCondition;
import kiosk.app.discount.discountPolicy.FixedAmountDiscountPolicy;
import kiosk.app.discount.discountPolicy.FixedRateDiscountPolicy;
import kiosk.app.order.Order;
import kiosk.app.product.ProductRepository;

public class AppConfig {

  private static final ProductRepository productRepository = new ProductRepository();
  private static final Menu menu = new Menu(productRepository);
  private static final Cart cart = new Cart(productRepository, menu);
  private static final Discount discount = new Discount(new DiscountCondition[]{
      new MemberDiscountCondition(new FixedRateDiscountPolicy()),
      new KidDiscountCondition(new FixedAmountDiscountPolicy())
  });
  private static final Order order = new Order(cart, discount);


  public ProductRepository productRepository() {
    return productRepository;
  }

  public Menu menu() {
    return menu;
  }

  public Cart cart() {
    return cart;
  }

  public Order order() {
    return order;
  }

  public Discount discount(){
    return discount;
  }
}
