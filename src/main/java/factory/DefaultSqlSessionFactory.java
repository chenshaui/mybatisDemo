package factory;/**
 * Created by ASUS on 2020/6/18.
 */

import config.Configuration;
import config.MapperStatement;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;
import sqlsession.DefaultSqlSession;
import sqlsession.SqlSession;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * @ClassName DefaultSqlSessionFactory
 * @Description 加载配置文件
 * @Author chenshuai
 * @Date 2020/6/18 16:51
 * @Version 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private static Logger log = Logger.getLogger(DefaultSqlSessionFactory.class.getClass());
    //配置类
    private final Configuration configuration = new Configuration();
    //指定mapper文件存放位置
    private final String MAPPER_CONFIG_LOCATION = "mappers";
    //指定数据库相关信息存放位置
    private final String DATASOURCE_CONFIG_FILE = "db.properties";

    public DefaultSqlSessionFactory() {
        loadDBInfo();
        loadMapperInfo();
    }
    /**
     * @Author chenShuai
     * @Description 加载数据库配置信息
     * @Date  2020/6/18  17:19
     * @Param []
     * @return void
     **/
    private void loadDBInfo() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(DATASOURCE_CONFIG_FILE);
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            log.info("读取数据库配置信息成功！！！！");
        } catch (IOException e) {
            log.error("读取数据库配置信息失败！！！！");
            e.printStackTrace();
        }
        configuration.setJdbcDriver(properties.get("jdbc.driver").toString());
        configuration.setJdbcUrl(properties.get("jdbc.url").toString());
        configuration.setJdbcUsername(properties.get("jdbc.username").toString());
        configuration.setJdbcPassword(properties.get("jdbc.password").toString());
    }
    /**
     * @Author chenShuai
     * @Description  获取mapper的xml文件并解析
     * @Date  2020/6/18  17:23
     * @Param []
     * @return void
     **/
    private void loadMapperInfo() {
        URL resources = null;
        resources = this.getClass().getClassLoader().getResource(MAPPER_CONFIG_LOCATION);
        File xmlFile = new File(resources.getFile());
        if (xmlFile.isDirectory()) {
            File[] files = xmlFile.listFiles();
            for (File file : files) {
                loadMapperInfo(file);
            }
        }
    }

    /**
     * @Author chenShuai
     * @Description 解析mapper对应的xml文件
     * @Date  2020/6/18  17:25
     * @Param [file]
     * @return void
     **/
    private void loadMapperInfo(File file) {
        SAXReader reader = new SAXReader();
        //读取文件至document对象中
        Document document = null;

        try {
            reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            document = reader.read(file);
            log.info("读取" + file.getName() + "文件成功！");
        } catch (DocumentException e) {
            log.error("读取" + file.getName() + "文件失败！");
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attribute("namespace").getData().toString();
        List<Element> selects = rootElement.elements("select");
        List<Element> updates = rootElement.elements("update");
        List<Element> inserts = rootElement.elements("insert");
        List<Element> deletes = rootElement.elements("delete");
        List<Element> allElement = new ArrayList();
        allElement.addAll(selects);
        allElement.addAll(updates);
        allElement.addAll(inserts);
        allElement.addAll(deletes);

        //解析节点，封装进MapperStatement对象

        for (Element e : allElement) {
            MapperStatement mapperStatement = new MapperStatement();
            //为清晰分步写
            String id = (String) e.attribute("id").getData();
            mapperStatement.setId(id);
            String resultType = (String) e.attribute("resultType").getData();
            mapperStatement.setResultType(resultType);
            String sql = e.getData().toString().trim();
            System.out.println(sql);
            mapperStatement.setSql(sql);
            mapperStatement.setNamespace(namespace);
            //解析后的数据放入mapperStatement中封装，然后放入配置中
            configuration.getMappedStatement().put(namespace + "." + id, mapperStatement);

        }

    }
    @Override
    public SqlSession openSession() {
        // openSession 方法创建一个 DefaultSqlSession，configuration 配置类作为 构造函数参数传入
        return new DefaultSqlSession(configuration);
    }
}
