package com.sagar.springSecurity3JWT.common.pojo.response;

import com.sagar.springSecurity3JWT.entity.TicketCategory;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    private Long id;
    private TicketCategory ticketCategory;
    private String description;
    private List<Integer> imageId;
}
