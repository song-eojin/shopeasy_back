package com.eojin.shopeasy_back.user.entity;

public enum UserRole {
    USER(Authority.USER),  // 소비자 권한
    MANAGER(Authority.SELLER), // 판매자 권한
    ADMIN(Authority.MANAGER);  // 관리자 권한

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String SELLER = "ROLE_SELLER";
        public static final String MANAGER = "ROLE_MANAGER";
    }
}
