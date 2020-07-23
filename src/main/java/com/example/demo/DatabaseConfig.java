package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseConfig implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE TESTTABLE(ID INTEGER NOT NULL, VALUE VARCHAR(255), PRIMARY KEY (ID) )";
            statement.executeUpdate(sql);

            String sql2 = "INSERT INTO TESTTABLE VALUES(1, 'value')";
            statement.execute(sql2);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
