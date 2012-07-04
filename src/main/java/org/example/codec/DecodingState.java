package org.example.codec;

/**
 * Message structure:
 * <pre>
 *     VERSION             - 1 byte
 *     TYPE                - 1 byte
 *     PAYLOAD_LENGTH      - 4 bytes
 *     PAYLOAD             - N bytes (depends on the PAYLOAD_LENGTH)
 * </pre>
 */
public enum DecodingState {
   VERSION, 
   TYPE, 
   PAYLOAD_LENGTH,
   PAYLOAD,
}
