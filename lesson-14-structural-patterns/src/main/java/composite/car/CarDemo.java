package composite.car;

import composite.car.german.Audi;
import composite.car.german.Bmw;
import composite.car.italiano.Ferrari;
import composite.car.italiano.Lamborghini;
import composite.car.usa.Cadillac;
import composite.car.usa.Ford;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CarDemo {

    public static void main(String... args) {
        /*
         У нас есть магазин немецких автомобилей, который продаёт только
         марки автомобили немецких марок
         */
        CarStockComposite germanCarStockComposite = new CarStockComposite();
        germanCarStockComposite.add(new Audi("RS3"));
        germanCarStockComposite.add(new Audi("Q7"));
        germanCarStockComposite.add(new Bmw("M5"));
        germanCarStockComposite.add(new Bmw("i3"));
        // Мы можем проверить наличие машин на всей площадке
        System.out.println("-- German stock --");
        germanCarStockComposite.checkAvailability();

        System.out.println();

        /*
         У нас есть магазин американских автомобилей, который продаёт только
         марки автомобили американских марок
         */
        CarStockComposite usaCarStockComposite = new CarStockComposite();
        usaCarStockComposite.add(new Ford("Transit"));
        usaCarStockComposite.add(new Ford("Mondeo"));
        usaCarStockComposite.add(new Cadillac("Eldorado"));
        usaCarStockComposite.add(new Cadillac("De Ville"));
        System.out.println("\n-- USA stock --");
        usaCarStockComposite.checkAvailability();

        System.out.println();

        /*
         Теперь мы создаём площадку, где продаются поддержанные автомобили
         только итальянских марок
         */
        CarStockComposite secondHandlCarStockComposite = new CarStockComposite();
        secondHandlCarStockComposite.add(new Ferrari("F40"));
        secondHandlCarStockComposite.add(new Ferrari("Enzo"));
        secondHandlCarStockComposite.add(new Lamborghini("Miura"));
        secondHandlCarStockComposite.add(new Lamborghini("Countach"));
        System.out.println("\n-- Second Hand stock --");
        usaCarStockComposite.checkAvailability();

        System.out.println();

        /*
         Мы решаем разрешить продавать также поддержанные автомобили немецких и
         американских марок. Причём нам не нужно везти каждую отдельную машину
         на нашу площадку. Мы откроем филиал прямо других магазинах.
         Т.к. CarStockComposite - то при проверке наличия автомобиля, с ним
         можно работать также, как и с отдельным автомобилем?
         */
        secondHandlCarStockComposite.add(germanCarStockComposite);
        secondHandlCarStockComposite.add(usaCarStockComposite);
        System.out.println("\n-- Second Hand stock --");
        secondHandlCarStockComposite.checkAvailability();
    }

}
