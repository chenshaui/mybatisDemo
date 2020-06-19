package config;

/**
 * @ClassName MapperStatement
 * @Description statement定义
 * @Author chenshuai
 * @Date 2020/6/18 16:46
 * @Version 1.0
 */
public class MapperStatement {
    private String namespace;

    private String id;

    private String resultType;

    private String sql;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
