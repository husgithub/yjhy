package com.yjhy.entity.enums;

/**
 * Created by husong on 2018/10/24.
 */
public enum LegalPersonEnum {

    FAREN("faren", "法人"),
    GUDONG("gudong", "股东");

    private String code;
    private String name;

    LegalPersonEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (LegalPersonEnum lpEnum : LegalPersonEnum.values()) {
            if (lpEnum.getCode().equals(code)) {
                return lpEnum.getName();
            }
        }
        return "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
