package org.recap.repository;


import org.recap.model.jpa.RequestStatusEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by hemalathas on 22/6/16.
 */
public interface RequestItemStatusDetailsRepository extends PagingAndSortingRepository<RequestStatusEntity, Integer> {

    /**
     * Find by request status code request status entity.
     *
     * @param requestStatusCode the request status code
     * @return the request status entity
     */
    RequestStatusEntity findByRequestStatusCode(String requestStatusCode);

    List<RequestStatusEntity> findByRequestStatusCodeIn(List<String> requestStatusCode);
}
