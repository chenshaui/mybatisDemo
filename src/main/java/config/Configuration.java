package config;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Configuration
 * @Description 数据库jdbc连接配置类
 * @Author chenshuai
 * @Date 2020/6/18 16:47
 * @Version 1.0
 */
public class Configuration {
    private String jdbcDriver;

    private String jdbcUrl;

    private String jdbcPassword;

    private String jdbcUsername;

    private Map<String, MapperStatement> mappedStatement = new HashMap<>();

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public Map<String, MapperStatement> getMappedStatement() {
        return mappedStatement;
    }

    public void setMappedStatement(Map<String, MapperStatement> mappedStatement) {
        this.mappedStatement = mappedStatement;
    }
}
