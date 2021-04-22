package com.test.dto;

import com.test.annotation.PageFragment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 *  Responsible to get the sql connection data source. do apply setter and getter.
 * */
@PageFragment
@Getter
@Setter
public class SqlConnectorDTO {

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.url}")
    private String dbServerUrl;
}
