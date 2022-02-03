package org.extensiblecatalog.ncip.v2.binding.ncipv2_02.jaxb.dozer;

import org.extensiblecatalog.ncip.v2.binding.jaxb.dozer.BaseJAXBElementSchemeValuePairConverter;
import org.extensiblecatalog.ncip.v2.binding.ncipv2_02.jaxb.elements.SchemeValuePair;
import org.extensiblecatalog.ncip.v2.service.RequestIdentifierType;

public class RequestIdentifierTypeJAXBElementSchemeValuePairConverter extends BaseJAXBElementSchemeValuePairConverter<SchemeValuePair, RequestIdentifierType> {
    public RequestIdentifierTypeJAXBElementSchemeValuePairConverter() {
        super(SchemeValuePair.class, RequestIdentifierType.class);
    }
}
