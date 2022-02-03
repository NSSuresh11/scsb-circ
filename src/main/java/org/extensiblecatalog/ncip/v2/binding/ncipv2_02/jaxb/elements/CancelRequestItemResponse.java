package org.extensiblecatalog.ncip.v2.binding.ncipv2_02.jaxb.elements;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"content"}
)
@XmlRootElement(
        name = "CancelRequestItemResponse"
)
public class CancelRequestItemResponse {
    @XmlElementRefs({@XmlElementRef(
            name = "ItemId",
            namespace = "http://www.niso.org/2008/ncip",
            type = ItemId.class
    ), @XmlElementRef(
            name = "RequestId",
            namespace = "http://www.niso.org/2008/ncip",
            type = RequestId.class
    ), @XmlElementRef(
            name = "UserId",
            namespace = "http://www.niso.org/2008/ncip",
            type = UserId.class
    ), @XmlElementRef(
            name = "ResponseHeader",
            namespace = "http://www.niso.org/2008/ncip",
            type = ResponseHeader.class
    ), @XmlElementRef(
            name = "Problem",
            namespace = "http://www.niso.org/2008/ncip",
            type = Problem.class
    ), @XmlElementRef(
            name = "ItemOptionalFields",
            namespace = "http://www.niso.org/2008/ncip",
            type = ItemOptionalFields.class
    ), @XmlElementRef(
            name = "Ext",
            namespace = "http://www.niso.org/2008/ncip",
            type = Ext.class
    ), @XmlElementRef(
            name = "UserOptionalFields",
            namespace = "http://www.niso.org/2008/ncip",
            type = UserOptionalFields.class
    )})
    protected List<Object> content;

    public CancelRequestItemResponse() {
    }

    public List<Object> getContent() {
        if (this.content == null) {
            this.content = new ArrayList();
        }

        return this.content;
    }
}
