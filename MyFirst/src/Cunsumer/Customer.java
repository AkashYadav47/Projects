package Cunsumer;

public class Customer {
    private final int customerId;
    private final String name;
    private String address;
    private String phone;
    public Customer(int customerId, String name){
        this.customerId = customerId;
        this.name = name;
    }
    public Customer(int customerId, String name,String phone){
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
    }
    public Customer(int customerId, String name,String address,String phone){
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
