package com.sagar.springSecurity3JWT.service;

import com.sagar.springSecurity3JWT.common.pojo.request.TicketRequest;
import com.sagar.springSecurity3JWT.common.pojo.response.TicketResponse;
import com.sagar.springSecurity3JWT.common.util.GenericFileUtil;
import com.sagar.springSecurity3JWT.entity.Ticket;
import com.sagar.springSecurity3JWT.entity.TicketImage;
import com.sagar.springSecurity3JWT.helper.TicketHelper;
import com.sagar.springSecurity3JWT.repository.TicketImageRepository;
import com.sagar.springSecurity3JWT.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final GenericFileUtil genericFileUtil;
    private final TicketRepository ticketRepository;
    private final TicketImageRepository ticketImageRepository;
    private final TicketCategoryService ticketCategoryService;
    private final TicketHelper ticketHelper;

    @Override
    @Transactional
    public void saveTicket(TicketRequest ticketRequestPojo) throws Exception {
        Ticket ticket = ticketRepository.save(Ticket
                .builder()
                .ticketCategory(ticketCategoryService.getCategoryById(ticketRequestPojo.getTicketCategory()))
                .description(ticketRequestPojo.getDescription())
                .build());
        for (MultipartFile image : ticketRequestPojo.getImages()) {
            String imagePath = genericFileUtil.saveFile(ticketRequestPojo.getImages()
                    .get(ticketRequestPojo.getImages().indexOf(image)));
            TicketImage ticketImage = TicketImage
                    .builder()
                    .image(imagePath)
                    .ticket(ticket)
                    .build();
            ticketImageRepository.save(ticketImage);
        }
    }

    @Override
    @Transactional
    public void updateTicket(TicketRequest ticketRequest, Long id) throws Exception {
        TicketImage existingTicketImage = null;
        try {
            TicketResponse existingTicket = this.getTicketById(id);
            Ticket updatedTicket = Ticket.builder()
                    .id(id)
                    .description(ticketRequest.getDescription())
                    .ticketCategory(ticketCategoryService.getCategoryById(ticketRequest.getTicketCategory()))
                    .build();
            if (!(ticketRequest.getImages() == null)) {
//            for (int i = 0; i < ticketRequest.getImages().size(); i++) {
//                TicketImage updatedTicketImage = null;
//                for (int j = i; j < existingTicket.getImageId().size(); j++) {
//                    Integer imageId = existingTicket.getImageId().get(j);
//                    TicketImage existingTicketImage = ticketImageRepository.findById(imageId).orElseThrow(() -> new RuntimeException("Ticket Image Not Found"));
//                    String imagePath = genericFileUtil.updateFile(ticketRequest.getImages().get(i), existingTicketImage.getImage());
//                    updatedTicketImage = TicketImage.builder()
//                            .id(imageId)
//                            .image(imagePath)
//                            .ticket(ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("d")))
//                            .build();
//                    ticketImageRepository.save(updatedTicketImage);
//                    break;
//                }
//            }

                for (int i = 0; i < ticketRequest.getImages().size(); i++) {
                    Integer imageId = existingTicket.getImageId().get(i);
                    existingTicketImage = ticketImageRepository.findById(imageId)
                            .orElseThrow(() -> new RuntimeException("Ticket Image Not Found"));
                    String imagePath = genericFileUtil.updateFile(ticketRequest.getImages().get(i), existingTicketImage.getImage());
                    TicketImage updatedTicketImage = TicketImage.builder()
                            .id(imageId)
                            .image(imagePath)
                            .ticket(ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("d")))
                            .build();
                    ticketImageRepository.save(updatedTicketImage);
                }

            }
            ticketRepository.save(updatedTicket);
        } catch (Exception e) {
            genericFileUtil.reSaveFile(existingTicketImage.getImage());
        }
    }

    @Override
    public TicketResponse getTicketById(Long id) throws MalformedURLException {
        return ticketHelper.getTicketById(id);
    }

}
