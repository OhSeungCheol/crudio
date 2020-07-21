package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

@Component
public class DatabaseConfig implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void run(ApplicationArguments args) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

//            String sql = "CREATE TABLE USER3(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY(id))";
//            String sql = "select * from INFORMATION_SCHEMA.CATALOGS";

//            Statement statement = connection.createStatement();
//            Boolean ret = statement.execute(sql);
//            System.out.println("Return : " + ret);


            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE USER(ID INTEGER NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID) )";
            statement.executeUpdate(sql);
        } catch (Exception e){
            System.out.println(e);
        }

        jdbcTemplate.execute("INSERT INTO USER VALUES(1, 'saelobi')");
    }
}
