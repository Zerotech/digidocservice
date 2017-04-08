package ee.zerotech.digidocservice.to;

import lombok.Data;

/**
 * Represents a DataFile instance that contains the most basic information
 *
 */
@Data
public class SimpleDataFile {

    /**
     * Id of a DataFile must start with letter "D" followed the number of the DataFile
     * ("D0" for the first DataFile and so on)
     * @param id
     */
    private String id;
    private String name;
    private String mimeType;
    private int size;
    private byte[] body;
}
