package org.example.codec;

public class TypeImpl implements Type {
    
    private byte type;

    public TypeImpl(final byte type) {
        this.type = type;
    }
    
    @Override
    public byte getByteValue() {
        return type;
    }

}
