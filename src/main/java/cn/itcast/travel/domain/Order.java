package cn.itcast.travel.domain;

public class Order {
    private String order_id;
    private String username;
    private String content;
    private String email;
    private String price;
    private String status;

    public Order() {
    }

    public Order(String order_id, String username, String content, String email, String price, String status) {
        this.order_id = order_id;
        this.username = username;
        this.content = content;
        this.email = email;
        this.price = price;
        this.status = status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", email='" + email + '\'' +
                ", price='" + price + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
