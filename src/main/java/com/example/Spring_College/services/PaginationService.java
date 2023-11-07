package com.example.Spring_College.services;

import org.springframework.stereotype.Service;
import com.example.Spring_College.dto.PaginationResponse;
import org.springframework.data.domain.Page;

@Service
public class PaginationService<T> {
	
    public PaginationResponse<T> getDetails(Page<T> page) {
        PaginationResponse<T> paginationResponse = new PaginationResponse<>();
        
        paginationResponse.setPageNumber(page.getNumber());
        paginationResponse.setPageSize(page.getSize());
        paginationResponse.setTotalElements(page.getTotalElements());
        paginationResponse.setTotalPages(page.getTotalPages());
        paginationResponse.setLastPage(page.isLast());
        paginationResponse.setContent(page.getContent());
        
        return paginationResponse;
    }
}


