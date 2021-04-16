package com.cadent.test.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;

/**
 *  Interface  for SFTP connection and services
 */

@FunctionalInterface
public interface IFileTransferService {

    ChannelSftp sftpConnection() throws JSchException;

}
