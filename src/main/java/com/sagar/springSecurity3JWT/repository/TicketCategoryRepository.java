package com.sagar.springSecurity3JWT.repository;

import com.sagar.springSecurity3JWT.entity.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Integer> {
}
