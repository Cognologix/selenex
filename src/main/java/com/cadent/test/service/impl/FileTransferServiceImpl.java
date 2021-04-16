package com.cadent.test.service.impl;

import com.cadent.test.annotation.PageFragment;
import com.cadent.test.dto.FileTransferDetailDTO;
import com.cadent.test.service.IFileTransferService;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  Using Jsh , connecting to the SFTP server . Maintain the single session . It picks the properties from the profile passed by the user.
 *
 * */
@PageFragment
public class FileTransferServiceImpl implements IFileTransferService {

    private static final Logger LOGGER = LoggerFactory.getLogger (FileTransferServiceImpl.class);

    private static final Integer TIMEOUT = 10000;
    private static final Properties SFTP_CONFIG = new Properties ();
    private static final String SFTP = "sftp";
    private static final Map<Long, Session> sessions    = new ConcurrentHashMap<> ();

    private Long id;

    static {

        SFTP_CONFIG.put ("StrictHostKeyChecking", "no");
    }

    @Autowired
    FileTransferDetailDTO dtoObj;

    /**
     * This method is responsible to establish the connection with SFTP server.
     * */

    @Override
    public ChannelSftp sftpConnection() throws JSchException {

        LOGGER.info ("Testing Data Transfer Connection via SSH");
        ChannelSftp channelSftp = null;
        final JSch jsch = new JSch ();
        try {
            final Session session = jsch.getSession (dtoObj.getSftpUser (), dtoObj.getSftpServer (), dtoObj.getSftpPort ());
            session.setPassword (dtoObj.getSftpPassword ());
            session.setTimeout (TIMEOUT);
            session.setConfig (SFTP_CONFIG);
            session.connect (TIMEOUT);
            channelSftp = (ChannelSftp) session.openChannel (SFTP);
            channelSftp.connect ();

            if(dtoObj.getId()!=null){

                sessions.put (dtoObj.getId (),session);
            }

        } catch (final JSchException exception) {

            LOGGER.error ("Exception occurred while connecting to SFTP over SSH", exception);
            throw exception;
        }
        LOGGER.info ("SFTP SSH connection established...");


        return channelSftp;
    }

    /**
     *  Call this method to disconnect SFTP connection
     * */
    private void disconnect (ChannelSftp channelSftp) {

        try {
            if (channelSftp != null) {

                channelSftp.disconnect ();
            }

            if (!channelSftp.isClosed ()) {

                channelSftp.exit ();
            }
        } catch (final Exception e) {

            LOGGER.error ("Failed to disconnect SFTP Server", e);
        }
    }

    /**
     * This methods is responsible to check weather the file exists under provided SFTP Dir.
     * */

    public boolean doesFileExist(ChannelSftp sftp,String path,String fileDir,String fileName) throws SftpException {

        boolean flag = false;
        try {
                try {

                    final StringBuilder pathBuilder = new StringBuilder ();
                    pathBuilder.append (path).append (fileDir);
                    LOGGER.info ("Searching for the path ",pathBuilder);
                    final SftpATTRS sftpATTRS = sftp.stat (path+fileDir);
                    LOGGER.info (sftpATTRS.toString ());

                    final Vector<ChannelSftp.LsEntry> v = sftp.ls(pathBuilder.toString ());
                    final Enumeration<ChannelSftp.LsEntry> entries = v.elements();
                    while (entries.hasMoreElements()) {

                        final ChannelSftp.LsEntry entry = entries.nextElement();
                        final SftpATTRS sftpAttrs = entry.getAttrs();
                        LOGGER.info (entry.getFilename ());

                        if (entry.getFilename().equals(".") || entry.getFilename().equals("..")) {
                            continue;
                        }

                        if(entry.getFilename ().equals (fileName)) {
                            LOGGER.info ("File found in SFTP diretory..",entry.getFilename ());
                            flag = true;
                        }
                    }

                }catch (final Exception e){
                    flag = false;
                    LOGGER.error ("Error get all SFTP file ",e);
                }
        }catch (Exception e){
            LOGGER.error ("File not found on SFTP server ",e);
            throw e;
        }

        return flag;
    }



}
