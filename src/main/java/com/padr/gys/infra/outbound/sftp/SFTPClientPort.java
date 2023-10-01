package com.padr.gys.infra.outbound.sftp;

public interface SFTPClientPort {
    
    public void put(String path, byte[] content);

    public byte[] get(String path);

    public void delete(String path);
}
