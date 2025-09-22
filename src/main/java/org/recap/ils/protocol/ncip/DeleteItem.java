package org.recap.ils.protocol.ncip;

import lombok.extern.slf4j.Slf4j;
import org.extensiblecatalog.ncip.v2.service.*;
import org.json.JSONObject;
import org.recap.common.ScsbConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DeleteItem extends ScsbNCIP {

    public DeleteItemInitiationData getDeleteItemInitiationData(String itemIdentifier, String owningInstItemId, String ncipAgencyId, String ncipScheme, String itemAgencyId)  {
        log.info("owningInstItemId >>>>>>>>> " + owningInstItemId);
        log.info("itemIdentifier >>>>>>>>> " + itemIdentifier);
         DeleteItemInitiationData deleteItemInitiationData = new DeleteItemInitiationData();
        InitiationHeader initiationHeader = new InitiationHeader();
        initiationHeader = getInitiationHeaderwithoutProfile(initiationHeader, ncipScheme, itemAgencyId, ncipAgencyId);
        initiationHeader.setApplicationProfileType(new ApplicationProfileType(null,itemAgencyId));
        deleteItemInitiationData.setInitiationHeader(initiationHeader);

        ItemId itemId = new ItemId();
        itemId.setItemIdentifierValue(itemIdentifier);
        deleteItemInitiationData.setItemId(itemId);


        return deleteItemInitiationData;

    }

    public JSONObject getDeleteItemResponse(DeleteItemResponseData deleteItemResponseData) {
        JSONObject returnJson = new JSONObject();

        if (!deleteItemResponseData.getProblems().isEmpty()) {
            return generateNcipProblems(deleteItemResponseData);
        }
        returnJson.put(ScsbConstants.ITEM_BARCODE, deleteItemResponseData.getItemId().getItemIdentifierValue());
        return returnJson;
    }

}
