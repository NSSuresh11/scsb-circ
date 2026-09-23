package org.recap.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.recap.model.AbstractResponseItem;

import java.util.List;
import java.util.Optional;


@Data
public class RequestStatusResponse  {
    private List<String> itemBarcodes;
    private String titleIdentifier;
    private String itemOwningInstitution = "";
    private String patronBarcode = "";
    private String emailAddress = "";
    private String requestingInstitution = "";
    private String requestType = "";
    private String deliveryLocation = "";
    public String getDeliveryLocation() {
        return Optional.ofNullable(deliveryLocation).orElse("");
    }
    private String requestNotes = "";
    private String trackingId;
    private String errorMessage;
    private String requestId;
    private String itemBarcode;
    private String screenMessage;
    private boolean success;


    @Override
    public String toString() {
        return "ItemRequestInformation{" +
                "itemBarcodes=" + itemBarcodes +
                ", titleIdentifier='" + titleIdentifier + '\'' +
                ", itemOwningInstitution='" + itemOwningInstitution + '\'' +
                ", patronBarcode='" + patronBarcode + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", requestingInstitution='" + requestingInstitution + '\'' +
                ", requestType='" + requestType + '\'' +
                ", deliveryLocation='" + deliveryLocation + '\'' +
                ", requestNotes='" + requestNotes + '\'' +
                ", trackingId='" + trackingId + '\'' +
                '}';
    }
}