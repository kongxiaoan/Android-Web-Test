package com.android.AndroidWebTest.web;

import com.android.AndroidWebTest.bean.Users;
import com.android.AndroidWebTest.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/mydb")
public class DBController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/register")
    public String register() {
        Users users = new Users();
        users.setPassWord("123456dfg");
        users.setUserName("job");
        int result = insertObject("user", users);
        return result + "";
    }

    public int insertObject(String table, Object object) {
        int result = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM");
        String tname = table + "_" + format.format(new Date());
        result = saveObj(jdbcTemplate, table, object);
        return result;
    }


    private int saveObj(JdbcTemplate jdbcTemplate, String table, Object object) {
        int result = 0;
        String sql = " insert into " + table + " (";
        LinkedHashMap<String, String> map = ObjectUtil.getProperty(object);
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            sql += (entry.getKey() + ",");
        }

        Set<String> set = map.keySet();
        sql = sql.substring(0, sql.length() - 1);
        sql += ") values ( ";
        for (String key : set) {
            sql += ("'" + map.get(key) + "',");
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += (")");
        System.out.println("数据库语句" + sql);
        result = jdbcTemplate.update(sql);
        return result;
    }


    private boolean getAllTableName(JdbcTemplate jdbcTemplate, String table, Object object) {
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            String[] type = {"TABLE"};
            resultSet = metaData.getTables(null, null, table, type);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    @RequestMapping("/getUsers")
    public ModelAndView getDbType() {
        String sql = "select * from user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> map : list) {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            if (entries != null) {
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                }
            }
        }
        Map<String, Object> mapq = new HashMap<String, Object>();
        mapq.put("1", "数据库连接成功");
        if (list.isEmpty()) {
            list.add(mapq);
        }
        ModelAndView modelAndView = new ModelAndView("/Users/mr.kong/AllProject/Android-Web-Test/src/main/webapp/WEB-INF/jsp/android-web-test-register-jsp.jsp");
        modelAndView.addObject("list", list);
        return modelAndView;
    }


}
