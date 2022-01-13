package org.recap.ils.protocol.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


/**
 * Created by Suresh.s
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "barcode",
        "process_type",
        })
@Data
public class ItemLookupData {
    @JsonProperty("barcode")
    private Object barcode;
    @JsonProperty("process_type")
    private Object processtype;
}
