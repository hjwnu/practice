package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import kiosk.app.product.Product;
import kiosk.app.product.ProductRepository;
import kiosk.app.product.subproduct.BurgerSet;
import kiosk.app.product.subproduct.Drink;
import kiosk.app.product.subproduct.Hamburger;
import kiosk.app.product.subproduct.Side;

public class Cart {

    private List<Product> items = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private ProductRepository productRepository;

    private Menu menu;

    public Cart(ProductRepository productRepository, Menu menu) {
      this.productRepository = productRepository;
      this.menu = menu;
    }

    public void printCart() {
      System.out.println("🧺 장바구니");
      System.out.println("-".repeat(60));
      printCartItemDetails();
      System.out.println("-".repeat(60));
      System.out.printf("합계 : %d원\n", calculateTotalPrice());

      System.out.println("이전으로 돌아가려면 엔터를 누르세요. ");
      scanner.nextLine();
    }

    public void printCartItemDetails() {

      for (Product product : items) {
        if (product instanceof BurgerSet) {
          BurgerSet burgerSet = (BurgerSet) product;
          System.out.printf(
              "  %s %6d원 (%s(케첩 %d개), %s(빨대 %s))\n",
              product.getName(),
              product.getPrice(),
              burgerSet.getSide().getName(),
              burgerSet.getSide().getKetchup(),
              burgerSet.getDrink().getName(),
              burgerSet.getDrink().hasStraw() ? "있음" : "없음"
          );
        }
        else if (product instanceof Hamburger) {
          System.out.printf(
              "  %-8s %6d원 (단품)\n",
              product.getName(),
              product.getPrice()
          );
        }
        else if (product instanceof Side) {
          System.out.printf(
              "  %-8s %6d원 (케첩 %d개)\n",
              product.getName(),
              product.getPrice(),
              ((Side) product).getKetchup()
          );
        }
        else if (product instanceof Drink) {
          System.out.printf(
              "  %-8s %6d원 (빨대 %s)\n",
              product.getName(),
              product.getPrice(),
              ((Drink) product).hasStraw() ? "있음" : "없음"
          );
        }
      }
    }

    public int calculateTotalPrice() {
      int totalPrice = 0;
      for (Product product : items) {
        totalPrice += product.getPrice();
      }
      return totalPrice;
    }

    public void addToCart(int productId) {

      Product product = productRepository.findById(productId);
      chooseOption(product);

      if (product instanceof Hamburger) {
        Hamburger hamburger = (Hamburger) product;
        if (hamburger.isBurgerSet()) product = composeSet(hamburger);
      }

      Product newProduct;
      if (product instanceof Hamburger) newProduct = new Hamburger((Hamburger) product);
      else if (product instanceof Side) newProduct = new Side((Side) product);
      else if (product instanceof Drink) newProduct = new Drink((Drink) product);
      else newProduct = new BurgerSet((BurgerSet) product);

      items.add(newProduct);

      System.out.printf("[📣] %s를(을) 장바구니에 담았습니다.\n", product.getName());
    }

    private void chooseOption(Product product) {

      String input;

      if (product instanceof Hamburger) {
        System.out.printf(
            "단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
            product.getPrice(),
            ((Hamburger) product).getBurgerSetPrice()
        );
        input = scanner.nextLine();
        if (input.equals("2")) ((Hamburger) product).setIsBurgerSet(true);
      }
      else if (product instanceof Side) {
        System.out.println("케첩은 몇개가 필요하신가요?");
        input = scanner.nextLine();
        ((Side) product).setKetchup(Integer.parseInt(input));
      }
      else if (product instanceof Drink) {
        System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니오");
        input = scanner.nextLine();
        if (input.equals("2")) ((Drink) product).setHasStraw(false);
      }
    }

    private BurgerSet composeSet(Hamburger hamburger) {
      System.out.println("사이드를 골라주세요");
      menu.printSides(false);

      String sideId = scanner.nextLine();
      Side side = (Side) productRepository.findById(Integer.parseInt(sideId));
      chooseOption(side);

      System.out.println("음료를 골라주세요.");
      menu.printDrinks(false);

      String drinkId = scanner.nextLine();
      Drink drink = (Drink) productRepository.findById(Integer.parseInt(drinkId));
      chooseOption(drink);

      String name = hamburger.getName() + "세트";
      int price = hamburger.getBurgerSetPrice();
      int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

      return new BurgerSet(name, price, kcal, hamburger, side, drink);
    }
}
