package driver.bookstore.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedBook {
    private int id;
    private String name;
    private  int price;
    private int quantity;
    private int total;

    public int getTotal() {
        return  price * quantity;
    }
}
