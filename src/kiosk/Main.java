package kiosk;

import kiosk.app.AppConfig;
import kiosk.app.order.OrderApp;
import kiosk.app.product.ProductRepository;

public class Main {

  public static void main(String[] args) {

    AppConfig appConfig = new AppConfig();

    OrderApp orderApp = new OrderApp(
      appConfig.productRepository(),
      appConfig.menu(),
      appConfig.cart(),
      appConfig.order()
    );

    orderApp.start();
  }
}