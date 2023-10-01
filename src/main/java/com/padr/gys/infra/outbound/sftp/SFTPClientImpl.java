package com.padr.gys.infra.outbound.sftp;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

class SFTPClientImpl implements SFTPClientPort {

    private final String host;
    private final Integer port;
    private final String username;
    private final String password;

    private final JSch jsch;
    private ChannelSftp channelSftp;
    private Session session;

    public SFTPClientImpl(String host, Integer port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;

        this.jsch = new JSch();

        this.authenticate();
    }

    public void put(String path, byte[] content) {
        try {
            createRemoteDirectory(path);

            channelSftp.put(new ByteArrayInputStream(content), path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] get(String path) {
        byte[] content = null;

        try {
            content = channelSftp.get(path).readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return content;
    }

    public void delete(String path) {
        try {
            channelSftp.rm(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void authenticate() {
        try {
            session = jsch.getSession(username, host, port);

            var config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.setPassword(password);
            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createRemoteDirectory(String remotePath) throws SftpException {
        List<String> splittedPath = Arrays.asList(remotePath.split("/"));

        String temp = "";

        for (int i = 0; i < splittedPath.size() - 1; i++) {
            temp = temp.concat(splittedPath.get(i)).concat("/");

            try {
                channelSftp.stat(temp);
            } catch (Exception e) {
                channelSftp.mkdir(temp);
            }
        }
    }

}
