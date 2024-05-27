package com.sagar.springSecurity3JWT.service;

import com.sagar.springSecurity3JWT.common.pojo.request.TicketCategoryRequest;
import com.sagar.springSecurity3JWT.entity.TicketCategory;
import com.sagar.springSecurity3JWT.repository.TicketCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;
    private final TicketTopicService ticketTopicService;

    @Override
    @Transactional
    public void saveTicketCategory(TicketCategoryRequest ticketCategoryRequest) {
        TicketCategory ticketCategory = TicketCategory
                .builder()
                .category(ticketCategoryRequest.getCategory())
                .ticketTopic(ticketTopicService.getTopicById(ticketCategoryRequest.getTicketTopic()))
                .build();
        ticketCategoryRepository.save(ticketCategory);
    }

    @Override
    @Transactional
    public TicketCategory updateTicketCategory(TicketCategoryRequest ticketCategoryRequest, int id) {
        TicketCategory existingTicketCategory = getCategoryById(id);
        TicketCategory updatedTicketCategory = TicketCategory
                .builder()
                .id(existingTicketCategory.getId())
                .category(ticketCategoryRequest.getCategory())
                .ticketTopic(ticketTopicService.getTopicById(ticketCategoryRequest.getTicketTopic()))
                .build();
        return ticketCategoryRepository.save(updatedTicketCategory);
    }

    @Override
    public TicketCategory getCategoryById(int id) {
        return ticketCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket Category not found."));
    }

    @Override
    public Page<TicketCategory> getAllCategory(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("category").ascending());
        return ticketCategoryRepository.findAll(pageable);
    }
}
