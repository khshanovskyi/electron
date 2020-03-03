package ua.electron.entity.builder;

import ua.electron.entity.Product;

import java.util.Objects;

public class ProductBuilder {

    private int idOfProduct;
    private String productName;
    private String category;
    private String brand;
    private int price;
    private String pictureURL1;
    private String pictureURL2;
    private String pictureURL3;
    private String pictureURL4;
    private String pictureURL5;
    private String characteristic;
    private String description;

    public void buildIdOfProduct(int idOfProduct){
        this.idOfProduct = idOfProduct;
    }

    public void buildProductName(String productName){
        this.productName = productName;
    }

    public void buildCategory(String category){
        this.category = category;
    }

    public void buildBrand(String brand){
        this.brand = brand;
    }

    public void buildPrice(int price){
        this.price = price;
    }

    public void buildPictureURL1(String pictureURL1){
        this.pictureURL1 = pictureURL1;
    }

    public void buildPictureURL2(String pictureURL2){
        this.pictureURL2 = pictureURL2;
    }

    public void buildPictureURL3(String pictureURL3){
        this.pictureURL3 = pictureURL3;
    }

    public void buildPictureURL4(String pictureURL4){
        this.pictureURL4 = pictureURL4;
    }

    public void buildPictureURL5(String pictureURL5){
        this.pictureURL5 = pictureURL5;
    }

    public void buildCharacteristic(String characteristic){
        this.characteristic = characteristic;
    }

    public void buildDescription(String description){
        this.description = description;
    }


    public Product buildProduct(){
        Product product = new Product();

        product.setIdOfProduct(idOfProduct);
        product.setProductName(productName);
        product.setCategory(category);
        product.setBrand(brand);
        product.setPrice(price);
        product.setPictureURL1(pictureURL1);
        product.setPictureURL2(pictureURL2);
        product.setPictureURL3(pictureURL3);
        product.setPictureURL4(pictureURL4);
        product.setPictureURL5(pictureURL5);
        product.setCharacteristic(characteristic);
        product.setDescription(description);

        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBuilder that = (ProductBuilder) o;
        return idOfProduct == that.idOfProduct &&
                Double.compare(that.price, price) == 0 &&
                productName.equals(that.productName) &&
                category.equals(that.category) &&
                brand.equals(that.brand) &&
                pictureURL1.equals(that.pictureURL1) &&
                pictureURL2.equals(that.pictureURL2) &&
                pictureURL3.equals(that.pictureURL3) &&
                pictureURL4.equals(that.pictureURL4) &&
                pictureURL5.equals(that.pictureURL5) &&
                characteristic.equals(that.characteristic) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfProduct, productName, category, brand, price, pictureURL1, pictureURL2, pictureURL3, pictureURL4, pictureURL5, characteristic, description);
    }

    @Override
    public String toString() {
        return "ProductBuilder{" +
                "idOfProduct=" + idOfProduct +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", pictureURL1='" + pictureURL1 + '\'' +
                ", pictureURL2='" + pictureURL2 + '\'' +
                ", pictureURL3='" + pictureURL3 + '\'' +
                ", pictureURL4='" + pictureURL4 + '\'' +
                ", pictureURL5='" + pictureURL5 + '\'' +
                ", characteristic='" + characteristic + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
