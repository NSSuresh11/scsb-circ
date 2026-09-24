package org.recap.model.response;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class RequestStatusResponse  {
    private List<String> itemBarcodes;
    private String itemOwningInstitution = "";
    private String requestingInstitution = "";
    private String requestType = "";
    private String deliveryLocation = "";
    public String getDeliveryLocation() {
        return Optional.ofNullable(deliveryLocation).orElse("");
    }
    private String errorMessage;
    private String requestId;
    private String itemBarcode;
    private String screenMessage;
    private boolean success;


    @Override
    public String toString() {
        return "ItemRequestInformation{" +
                "itemBarcodes=" + itemBarcodes +
                ", itemOwningInstitution='" + itemOwningInstitution + '\'' +
                ", requestingInstitution='" + requestingInstitution + '\'' +
                ", requestType='" + requestType + '\'' +
                ", deliveryLocation='" + deliveryLocation + '\'' +
                '}';
    }
}