package Cunsumer;

public class Product {
    private final int itemno;
    private final String name;
    private double price;
    private int qty;
    public Product(int itemno, String name, double price){
        this.itemno = itemno;
        this.name = name;
        this.price = price;
        this.qty = 0;
    }
    public Product(int itemno, String name, double price, int qty){
        this.itemno = itemno;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }
    public int getItemno() {
        return itemno;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
}
