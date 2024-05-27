package com.sagar.springSecurity3JWT.service;

import com.sagar.springSecurity3JWT.common.pojo.request.TicketRequest;
import com.sagar.springSecurity3JWT.common.pojo.response.TicketResponse;

import java.net.MalformedURLException;

public interface TicketService {

    void saveTicket(TicketRequest ticketRequestPojo) throws Exception;

    void updateTicket(TicketRequest ticketRequestPojo, Long id) throws Exception;

    TicketResponse getTicketById(Long id) throws MalformedURLException;
}
