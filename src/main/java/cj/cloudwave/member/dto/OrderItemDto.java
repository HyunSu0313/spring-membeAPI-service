package cj.cloudwave.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Long itemId;
    private int orderPrice;
    private int count;
}
