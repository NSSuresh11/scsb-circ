package org.recap.controller;

import lombok.extern.slf4j.Slf4j;
import org.recap.ScsbConstants;
import org.recap.ils.connector.factory.ILSProtocolConnectorFactory;
import org.recap.model.request.ItemRequestInformation;
import org.recap.request.service.ItemValidatorService;
import org.recap.request.service.RequestParamaterValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hemalathas on 10/11/16.
 */
@Slf4j
@RestController
@RequestMapping("/requestItem")
public class RequestItemValidatorController {


    /**
     * The Request paramater validator service.
     */
    @Autowired
    RequestParamaterValidatorService requestParamaterValidatorService;

    /**
     * The ILS Protocol connector factory.
     */
    @Autowired
    ILSProtocolConnectorFactory ilsProtocolConnectorFactory;

    /**
     * The Item validator service.
     */
    @Autowired
    ItemValidatorService itemValidatorService;

    /**
     * Validate item request informations response entity.
     *
     * @param itemRequestInformation the item request information
     * @return the response entity
     */
    @PostMapping(value = "/validateItemRequestInformations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity validateItemRequestInformations(@RequestBody ItemRequestInformation itemRequestInformation) {
        ResponseEntity<?> responseEntity;
        responseEntity = requestParamaterValidatorService.validateItemRequestParameters(itemRequestInformation);
        if (responseEntity == null) {
            responseEntity = itemValidatorService.itemValidation(itemRequestInformation);
            if (responseEntity.getStatusCode() == HttpStatus.OK && !ilsProtocolConnectorFactory.getIlsProtocolConnector(itemRequestInformation.getRequestingInstitution()).patronValidation(itemRequestInformation.getRequestingInstitution(), itemRequestInformation.getPatronBarcode())) {
                    responseEntity = new ResponseEntity<>(ScsbConstants.INVALID_PATRON, requestParamaterValidatorService.getHttpHeaders(), HttpStatus.BAD_REQUEST);
                }
        }
        log.info(String.format("Request Validation: %s - %s",responseEntity.getStatusCode(), responseEntity.getBody()));
        return responseEntity;
    }

    /**
     * Validate item request response entity.
     *
     * @param itemRequestInformation the item request information
     * @return the response entity
     */
    @PostMapping(value = "/validateItemRequest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity validateItemRequest(@RequestBody ItemRequestInformation itemRequestInformation) {
        ResponseEntity<?> responseEntity;
        log.info("Request Validation: Start");
        responseEntity = requestParamaterValidatorService.validateItemRequestParameters(itemRequestInformation);
        if (responseEntity == null) {
            responseEntity = itemValidatorService.itemValidation(itemRequestInformation);
        }
        log.info(String.format("Request Validation: %s - %s",responseEntity.getStatusCode(), responseEntity.getBody()));
        return responseEntity;
    }
}
