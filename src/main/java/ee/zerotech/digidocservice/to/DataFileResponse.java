package ee.zerotech.digidocservice.to;

import ee.zerotech.digidocservice.gen.DataFileData;
import lombok.Data;

@Data
public class DataFileResponse {
    private String status;
    private DataFileData dataFileData;
}
