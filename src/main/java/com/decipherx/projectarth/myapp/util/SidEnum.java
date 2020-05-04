//package com.decipherx.projectarth.webapp.util;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//public enum SidEnum {
//    USER("USR"),
//    ACCOUNT("ACC");
//
//    private final String prefix;
//
//    SidEnum(String prefix) {
//        this.prefix = prefix;
//    }
//
//    public String getPrefix() {
//        return this.prefix;
//    }
//
//    public static Optional<SidEnum> getEnum(String value) {
//        return Arrays.stream(values())
//                .filter(sidEnum -> sidEnum.prefix.equals(value))
//                .findFirst();
//    }
//
//}
//
//
