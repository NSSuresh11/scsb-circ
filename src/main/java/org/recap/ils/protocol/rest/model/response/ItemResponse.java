package org.recap.ils.protocol.rest.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import org.recap.ils.protocol.rest.model.DebugInfo;
import org.recap.ils.protocol.rest.model.ItemData;

import java.util.List;

/**
 * Created by rajeshbabuk on 7/12/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "itemData",
        "count",
        "statusCode",
        "debugInfo"
})
@Data
public class ItemResponse {

    @JsonProperty("data")
    private ItemData itemData;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("statusCode")
    private Integer statusCode;
    @JsonProperty("debugInfo")
    private List<DebugInfo> debugInfo = null;
}
