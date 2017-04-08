package ee.zerotech.digidocservice.to;

import lombok.Data;

@Data
public class VersionResponse {
    private String name;
    private String version;
    private String libname;
    private String libver;
}
