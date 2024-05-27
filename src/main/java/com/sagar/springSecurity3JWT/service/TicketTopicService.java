package com.sagar.springSecurity3JWT.service;

import com.sagar.springSecurity3JWT.common.pojo.request.TicketTopicRequest;
import com.sagar.springSecurity3JWT.entity.TicketTopic;
import org.springframework.data.domain.Page;

public interface TicketTopicService {

void saveTicketTopic(TicketTopicRequest ticketTopicRequest);

TicketTopic updateTicketTopic(TicketTopicRequest ticketTopicRequest, int id);

TicketTopic getTopicById(int id);

Page<TicketTopic> getAllTopic(int pageNo, int pageSize);


}
