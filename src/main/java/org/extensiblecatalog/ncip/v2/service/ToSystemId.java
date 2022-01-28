//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.extensiblecatalog.ncip.v2.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToSystemId extends SchemeValuePair {
    private static final Logger logger = LoggerFactory.getLogger(ToSystemId.class);
    private static final List<ToSystemId> VALUES_LIST = new CopyOnWriteArrayList();

    public ToSystemId(String scheme, String value) {
        super(scheme, value);
        VALUES_LIST.add(this);
    }

    public ToSystemId(String value) {
        super(value);
        VALUES_LIST.add(this);
    }

    public static ToSystemId find(String scheme, String value) throws ServiceException {
        return (ToSystemId)find(scheme, value, VALUES_LIST, ToSystemId.class);
    }
}
