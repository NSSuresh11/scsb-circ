package org.recap.ils.protocol.ncip;

import org.extensiblecatalog.ncip.v2.service.InitiationHeader;
import org.extensiblecatalog.ncip.v2.service.ItemId;
import org.extensiblecatalog.ncip.v2.service.RecallItemInitiationData;
import org.extensiblecatalog.ncip.v2.service.RecallItemResponseData;
import org.extensiblecatalog.ncip.v2.service.UserId;
import org.extensiblecatalog.ncip.v2.service.ProblemType;
import org.extensiblecatalog.ncip.v2.service.Problem;
import org.json.JSONObject;
import org.recap.common.ScsbConstants;

import java.text.SimpleDateFormat;

import static org.recap.common.ScsbConstants.REQUEST_ILS_EXCEPTION;

public class RecallItem extends ScsbNCIP {

    public RecallItemInitiationData getRecallItemInitiationData(String institution, String itemIdentifier, String patronIdentifier, String ncipAgencyId) {
        RecallItemInitiationData recallItemInitiationData = new RecallItemInitiationData();
        InitiationHeader initiationHeader = new InitiationHeader();
        initiationHeader = getInitiationHeaderwithoutScheme(initiationHeader, institution, ncipAgencyId, ncipAgencyId);
        UserId userid = new UserId();
        userid.setUserIdentifierValue(patronIdentifier);
        ItemId itemId = new ItemId();
        itemId.setItemIdentifierValue(itemIdentifier);
        recallItemInitiationData.setInitiationHeader(initiationHeader);
        recallItemInitiationData.setItemId(itemId);
        recallItemInitiationData.setUserIdDesired(Boolean.TRUE);
        recallItemInitiationData.setLocationDesired(Boolean.TRUE);

        return recallItemInitiationData;
    }

    public JSONObject getRecallItemResponse(RecallItemResponseData recallItemResponseData) {
        JSONObject returnJson = new JSONObject();
        if (recallItemResponseData != null) {
            if (!recallItemResponseData.getProblems().isEmpty()) {
                return generateNcipProblems(recallItemResponseData);
            }

            String dueDateString = "";
            if (recallItemResponseData.getDateDue() != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                formatter.setCalendar(recallItemResponseData.getDateDue());
                dueDateString = formatter.format(recallItemResponseData.getDateDue().getTime());
            }

            returnJson.put(ScsbConstants.ITEM_ID, recallItemResponseData.getItemId().getItemIdentifierValue());
            returnJson.put("userId", recallItemResponseData.getUserId());
            returnJson.put("expirationDate", dueDateString);
            return returnJson;
        }
        else {
            Problem ncipProblem = new Problem();
            ncipProblem.setProblemType(new ProblemType(REQUEST_ILS_EXCEPTION));
            ncipProblem.setProblemValue(ScsbConstants.REQUEST_ILS_NO_RESPONSE_EXCEPTION);
            returnJson.put(ncipProblem.getProblemType().toString(),ncipProblem.getProblemValue());
        }
        return returnJson;
    }
}
