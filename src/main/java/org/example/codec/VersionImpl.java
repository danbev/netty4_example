package org.example.codec;

public class VersionImpl implements Version {
    
    private byte version;

    public VersionImpl(final byte version) {
        this.version = version;
    }

    @Override
    public byte getByteValue() {
        return version;
    }

}
