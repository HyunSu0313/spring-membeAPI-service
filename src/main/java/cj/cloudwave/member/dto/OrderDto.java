package cj.cloudwave.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long memberId;
    private List<OrderItemDto> orderItems;
}
