package ua.electron.entity;

import java.util.Objects;

public class Product{

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

    public Product() {
    }

    public int getIdOfProduct() {
        return idOfProduct;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public String getPictureURL1() {
        return pictureURL1;
    }

    public String getPictureURL2() {
        return pictureURL2;
    }

    public String getPictureURL3() {
        return pictureURL3;
    }

    public String getPictureURL4() {
        return pictureURL4;
    }

    public String getPictureURL5() {
        return pictureURL5;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public String getDescription() {
        return description;
    }

    public void setIdOfProduct(int idOfProduct) {
        this.idOfProduct = idOfProduct;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPictureURL1(String pictureURL1) {
        this.pictureURL1 = pictureURL1;
    }

    public void setPictureURL2(String pictureURL2) {
        this.pictureURL2 = pictureURL2;
    }

    public void setPictureURL3(String pictureURL3) {
        this.pictureURL3 = pictureURL3;
    }

    public void setPictureURL4(String pictureURL4) {
        this.pictureURL4 = pictureURL4;
    }

    public void setPictureURL5(String pictureURL5) {
        this.pictureURL5 = pictureURL5;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return idOfProduct == product.idOfProduct &&
                Double.compare(product.price, price) == 0 &&
                productName.equals(product.productName) &&
                category.equals(product.category) &&
                brand.equals(product.brand) &&
                pictureURL1.equals(product.pictureURL1) &&
                pictureURL2.equals(product.pictureURL2) &&
                pictureURL3.equals(product.pictureURL3) &&
                pictureURL4.equals(product.pictureURL4) &&
                pictureURL5.equals(product.pictureURL5) &&
                characteristic.equals(product.characteristic) &&
                description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfProduct, productName, category, brand, price, pictureURL1,
                pictureURL2, pictureURL3, pictureURL4, pictureURL5, characteristic, description);
    }

    @Override
    public String toString() {
        return "Product{" +
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
