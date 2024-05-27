package com.sagar.springSecurity3JWT.service;

import com.sagar.springSecurity3JWT.common.pojo.request.TicketCategoryRequest;
import com.sagar.springSecurity3JWT.entity.TicketCategory;
import org.springframework.data.domain.Page;

public interface TicketCategoryService {
    void saveTicketCategory(TicketCategoryRequest ticketCategoryRequest);

    TicketCategory updateTicketCategory(TicketCategoryRequest ticketCategoryRequest, int id);

    TicketCategory getCategoryById(int id);

    Page<TicketCategory> getAllCategory(int pageNo, int pageSize);
}
