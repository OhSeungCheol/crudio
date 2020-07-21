package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class PostController {

    @Autowired
    DataSource dataSource;

    @RequestMapping("/create")
    public String create() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getMetaData().getUserName());
        System.out.println(connection.getMetaData().getURL());
        return "Success!!";
    }
}
