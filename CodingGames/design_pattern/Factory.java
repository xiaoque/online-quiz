package CodingGames.design_pattern;

enum Type {
    Type1,
    Type2
}

abstract class Product {
}

class ProductType1 extends Product {

}

class ProductType2 extends Product {

}

/*
 * More general way, use one single class as factory to create all types of
 * instances
 */
public class Factory {
    // either use enum to control type or just different func
    public Product createProduct(Type type) {
        return switch (type) {
            case Type1 -> new ProductType1();
            case Type2 -> new ProductType2();
        };
    }
}

/*
 * Fine-grained manipulation, create factory class for each type
 */

abstract class ProductFactory {
    abstract Product createProduct();
}

class ProductFactoryType1 extends ProductFactory {
    Product createProduct() {
        return new ProductType1();
    }
}

class ProductFactoryType2 extends ProductFactory {
    Product createProduct() {
        return new ProductType2();
    }
}