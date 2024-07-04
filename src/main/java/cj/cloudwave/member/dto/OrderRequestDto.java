package cj.cloudwave.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private Long memberId;
    private List<OrderItemDto> orderItems;
}
