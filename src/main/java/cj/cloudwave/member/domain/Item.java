package cj.cloudwave.member.domain;

import cj.cloudwave.member.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private String category;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private ItemImg itemImg;

    private Item(String name, int price, int stockQuantity, String category) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public static Item createItem(String name, int price, int stockQuantity, String category, ItemImg itemImg) {
        Item item = new Item(name, price, stockQuantity, category);
        item.setItemImg(itemImg);
        return item;
    }

    public void updateItemDetails(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    private void setItemImg(ItemImg itemImg) {
        this.itemImg = itemImg;
        itemImg.setItem(this);
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
