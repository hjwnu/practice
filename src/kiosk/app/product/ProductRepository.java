package kiosk.app.product;

import kiosk.app.product.subproduct.Drink;
import kiosk.app.product.subproduct.Hamburger;
import kiosk.app.product.subproduct.Side;
import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    private final List<Product> products = Arrays.asList(
        new Hamburger(1, "새우버거", 3500, 500, false, 4500),
        new Hamburger(2, "치킨버거", 4000, 600, false, 5000),
        new Hamburger(3, "불고기버거", 4500, 700, false, 5500),
        new Hamburger(4, "치즈버거", 4000, 800, false, 6000),
        new Hamburger(5, "불고기치즈버거", 5000, 900, false, 6500),
        new Hamburger(6, "더블치즈버거", 5500, 1000, false, 7000),
        new Drink(7, "코카콜라", 1000, 200, true),
        new Drink(8, "제로콜라", 1000, 0, true),
        new Drink(9, "스프라이트", 1000, 200, true),
        new Drink(10, "환타", 1000, 200, true),
        new Drink(11, "아메리카노", 1000, 0, false),
        new Drink(12, "카페라떼", 1500, 100, false),
        new Side(13, "감자튀김", 1000, 300, 1),
        new Side(14, "어니언링", 1000, 300, 1),
        new Side(15, "치즈스틱", 1000, 300, 1),
        new Side(16, "치킨너겟", 2000, 400, 6),
        new Side(17, "코울슬로", 2000, 400, 0)
    );

  public List<Product> getAllProducts() {
    return products;
  }

  public Product findById(int productId) {
    for (Product product : products) {
      if (product.getId() == productId) return product;
    }
    return null;
  }

  public void addProduct(Product product) {
    products.add(product);
  }

  public void deleteProduct(Product product) {
    products.remove(product);
  }
}
