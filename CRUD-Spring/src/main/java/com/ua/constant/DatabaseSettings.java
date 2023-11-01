package com.ua.constant;

public enum DatabaseSettings {
	DRIVER("org.postgresql.Driver"),
    URL("jdbc:postgresql://localhost:5436/postgres"),
    USER("postgres"),
    PASSWORD("password"),
    DIALECT("org.hibernate.dialect.PostgreSQLDialect");

    private String value;

    DatabaseSettings(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}