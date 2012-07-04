package org.example.codec;

public class Envelope {
    
    private Version version;
    private Type type;
    private byte[] payload;
    
    public Envelope(final Version version, final Type type, final byte[] payload) {
        this.version = version;
        this.type = type;
        this.payload = payload;
    }

    public Version getVersion() {
        return version;
    }

    public Type getType() {
        return type;
    }

    public byte[] getPayload() {
        return payload;
    }
    
    public static class Builder {
        
        private byte version;
        private byte type;
        private int size;
        private byte[] payload;
        
        public Builder version(final byte version) {
            this.version = version;
            return this;
        }
        
        public Builder type(final byte type) {
            this.type = type;
            return this;
        }
        
        public Builder payloadSize(final int size) {
            this.size = size;
            return this;
        }
        
        public int payloadSize() {
            return size;
        }
        
        public Builder payload(final byte[] payload) {
            this.payload = payload;
            return this;
        }
        
        public Envelope build() {
            notNull(version, "version");
            notNull(type, "type");
            notNull(payload, "payload");
            return new Envelope(new VersionImpl(version), new TypeImpl(type), payload);
        }
        
        private void notNull(Object o, String name) {
            if (o == null) {
                throw new NullPointerException(name);
            }
            
        }
        
        
    }

}
