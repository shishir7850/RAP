package com.quadratic.rap.dataimport.parser.util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum EnumFieldType
{
	NUMBER("n"), TEXT("t"), DATE("d");
	    
    private String code;

    private EnumFieldType(String code) {
        this.code = code;
    }
    private static final Map<String, EnumFieldType> lookup =
            new HashMap<String, EnumFieldType>();

    /**
     * Stores all Enum's into lookup
     */
    static {
        for (EnumFieldType s : EnumSet.allOf(EnumFieldType.class)) {
            lookup.put(s.code, s);
        }
    }

    public static EnumFieldType get(String code) {
        code = code.toLowerCase();
        return lookup.get(code);
    }
}
