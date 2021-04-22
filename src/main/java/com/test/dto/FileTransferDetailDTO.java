package com.test.dto;

import com.test.annotation.Page;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Getting values from the profile and apply setter and getter on teh fields.
 * */

@Lazy
@Page
@Getter
@Setter
public class FileTransferDetailDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                id;

    @Value ("${sftp.user}")
    private String sftpUser;

    @Value ("${sftp.password}")
    private String sftpPassword;

    @Value ("${sftp.port}")
    private int sftpPort;

    @Value ("${sftp.server}")
    private String sftpServer;



}
