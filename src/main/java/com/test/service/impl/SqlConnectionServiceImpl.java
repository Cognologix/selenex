package com.test.service.impl;

import com.test.dto.SqlConnectorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *  This class has been deprecated as using default data source feature of spring boot.
 * */
public class SqlConnectionServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger (SqlConnectionServiceImpl.class);

    @Autowired
    private SqlConnectorDTO connectorDTO;

    public Statement mySqlConnection(){

         Statement statement = null;

        try {
            Connection connection = DriverManager.getConnection (connectorDTO.getDbServerUrl (),connectorDTO.getDbUser (),connectorDTO.getDbPassword ());
            statement =  connection.createStatement ();
                LOGGER.info ("Database Connected..");
        } catch (SQLException exception) {
            LOGGER.error ("Database is not getting connected..",exception);

        }
    return statement;
    }

}
