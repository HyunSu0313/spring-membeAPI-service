package cj.cloudwave.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private String name;
    private int price;
    private int stockQuantity;
    private String category;
    private String imageUrl;
}
