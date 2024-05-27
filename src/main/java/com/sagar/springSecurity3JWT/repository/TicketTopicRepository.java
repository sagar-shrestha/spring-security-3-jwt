package com.sagar.springSecurity3JWT.repository;

import com.sagar.springSecurity3JWT.entity.TicketTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTopicRepository extends JpaRepository<TicketTopic, Integer> {
}
