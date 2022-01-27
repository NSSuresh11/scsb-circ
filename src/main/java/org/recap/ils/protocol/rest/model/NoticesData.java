package org.recap.ils.protocol.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


/**
 * Created by rajeshbabuk on 9/12/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "itemBarcode",
        "patronBarcode",
        "processed",
        "patron",
        "desiredDateDue",
        "@attributes",
        "CheckOutItemResponse"
})
@Data
public class NoticesData {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("itemBarcode")
    private String itemBarcode;
    @JsonProperty("patronBarcode")
    private String patronBarcode;
    @JsonProperty("processed")
    private Boolean processed;
    @JsonProperty("patron")
    private Object patron;
    @JsonProperty("desiredDateDue")
    private String desiredDateDue;
    @JsonProperty("@attributes")
    private Attributes attributes;
    @JsonProperty("CheckOutItemResponse")
    private CheckOutItemResponse checkOutItemResponse;
}
