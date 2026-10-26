public class OrderItem {
    final MenuItem item;
    int qty;

    OrderItem(MenuItem item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    double subtotal() {
        return item.price * qty;
    }
}