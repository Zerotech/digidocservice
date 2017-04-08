package ee.zerotech.digidocservice;

import ee.zerotech.digidocservice.gen.DataFileData;
import ee.zerotech.digidocservice.gen.DataFileDigestList;
import ee.zerotech.digidocservice.to.*;

/**
 * Digidoc service SOAP interface, as is - plain types mapping, no complex Java types. 
 * For conveninent usage where possible see for EnhancedDigidocService interface.
 */
public interface DigidocService {

    StartSessionResponse startSession(
            String signingProfile,
            String sigDocXML,
            boolean bHoldSession,
            DataFileData datafile);

    /**
     * Terminates communication with DigiDoc WebService
     *
     * @param sesscode  An identifier of the active session
     * @return          Status. If the request is successful, the value will be "OK".
     */
    String closeSession(int sesscode);

    /**
     * If an application desires to define the format and version of the container, the CreateSignedDoc request will
     * be used for creating a new container.
     * After the CreateSignedDoc request takes place the AddDataFile request for adding the data.
     * @param sesscode
     * @param format
     * @param version
     * @return
     */
    CreateSignedDocResponse createSignedDoc(int sesscode, String format, String version, String signingProfile);

    /**
     * AddDataFile request enables to add an additional data file to a DigiDoc container which is in session.
     * If one datafile is added within the StartSession, but the user would like to sign a few more data files
     * in a DigiDoc container, then using this method the rest of the data files will be added before signing.
     * NB! Adding a data file is possible in the DigiDoc file with no signatures only.
     * @param sesscode			An identifier of the active session
     * @param fileName			Name of the data file without the path
     * @param mimeType			Type of the datafile
     * @param contentType		Data file’s content type (HASHCODE, EMBEDDED_BASE64)
     * @param size				The actual size of data file in bytes
     * @param digestType		Hashcode type. So far the SHA1 algorythm  is used (parameter value sha1). Required for HASHCODE content type only.
     * @param digestValue		The value of data file’s hash* in Base64 encoding. Required for HASHCODE content type only.
     * @param content			The content of data file
     * @return
     */
    AddDataFileResponse addDataFile(
            int sesscode,
            String fileName,
            String mimeType,
            String contentType,
            int size,
            String digestType,
            String digestValue,
            byte[] content);

    /**
     * Removes a DataFile given a DataFile id
     * @param sesscode
     * @param dataFileId
     * @return
     */
    RemoveDataFileResponse removeDataFile(int sesscode, String dataFileId);

    /**
     * Returns the signed document as XML String.
     *
     * @param sesscode  An identifier of the active session
     * @return          XML of signed document. NB! Returned String should not be formated
     */
    SignedDocResponse getSignedDoc(int sesscode);

    /**
     * The getSignedDocInfo method shall be used to retrieve status
     * information and the actual (signed) document from the
     * current signing session.
     *
     * @param sesscode  An identifier of the active session
     * @return
     */
    SignedDocInfoResponse getSignedDocInfo(int sesscode);

    /**
     * Within GetSignedDoc request a digitally signed document is
     * sent back from webservice. The result is in HTML encoded
     * format. If there is a will to get the structured document
     * information in addition to the document, a GetSignedDocInfo
     * request will be used.
     * @param sesscode
     * @param dataFileId
     * @return
     */
    DataFileResponse getDataFile(int sesscode, String dataFileId);

    /**
     * Return the signer's certificate given the signature Id within the signed document
     * @param sesscode
     * @param signatureId
     * @return
     */
    CertificateResponse getSignersCertificate(int sesscode, String signatureId);

    /**
     * As a result of the request a validity confirmation signer’s certificate of the signature is returned
     * (OCSP server’s certificate)
     * @param sesscode
     * @param signatureId
     * @return
     */
    CertificateResponse getNotarysCertificate(int sesscode, String signatureId);

    /**
     * The request returns the validity confirmation of the certain signature.
     * @param sesscode
     * @param signatureId
     * @return
     */
    NotaryResponse getNotary(int sesscode, String signatureId);

    /**
     * The request returns the time stamping authority (TSA) certificate.
     * This method is useful when signed document contains RFC 3161 timestamps.
     * NB! In the current version of service only the interface of the method is implemented, but not the functionality.
     * @param sesscode
     * @param timestampId
     * @return
     */
    TSACertificateResponse getTSACertificate(int sesscode, String timestampId);

    /**
     * The request returns the timestamp in base64 encoding.
     * This method is useful when signed document contains RFC 316 timestamps
     * NB! In the current version of service only the interface of the method is implemented, but not the functionality.
     * @param sesscode
     * @param timestampId
     * @return
     */
    /*
    TimestampResponse getTimestamp(int sesscode, String timestampId);
    */
    /**
     * The request returns the certificate revocation list
     * NB! In the current version of service only the interface of the method is implemented, but not the functionality.
     * @param sesscode
     * @param signatureId
     * @return
     */
    CRLResponse getCRL(int sesscode, String signatureId);

    /**
     * A request for downloading the neccessary modules for digital signing with smartcard.
     * @param sesscode  An identifier of the active session
     * @param platform  A platform which needs the signing modules: LINUX-MOZILLA, WIN32-MOZILLA, WIN32-IE
     * @param phase     A step for the modules are being downloaded: PREPARE, FINALIZE
     * @param type      Defines which modules are returned: FILE, HMTL, ALL
     * @return
     */
    /*
    SignatureModulesResponse getSignatureModules(int sesscode, String platform, String phase, String type);
    */
    /**
     * Digital signing preparation if signing with smartcard
     *
     * @param signingProfile        Signature creating and verifying profile.For default setting use “” value.
     * @param sesscode              An identifier of the active session
     * @param signersCertificate    signer’s certificate transferred to HEX string format
     * @param signersTokenID        identifier of the private key’s slot on a smartcard
     * @param role                  The text of the role or resolution defined by the user
     * @param city                  Name of the city, where it’s signed
     * @param state                 Name of the state, where it’s signed
     * @param postalCode            Postal code of the signing location
     * @param country               Name of the country, where it’s signed
     * @return                      the unique identifier of the signature and
     *                              the hash to be signed as a hexadecimal string.
     */
    PrepareSignatureResponse prepareSignature(
            int sesscode,
            String signersCertificate,
            String signersTokenID,
            String role,
            String city,
            String state,
            String postalCode,
            String country,
            String signingProfile);

    /**
     * A digitally signed signature is added to DigiDoc file and an OCSP validity confirmation is taken
     *
     * @param sesscode          An identifier of the active session
     * @param signatureId       the unique identifier of the signature
     * @param signatureValue    value of the signature (signed hash) as a HEX string
     * @return
     */
    FinalizeSignatureResponse finalizeSignature(int sesscode, String signatureId, String signatureValue);

    /**
     * Requests Digidoc Service to remove signature identified by parameter signatureId
     * @param sesscode
     * @param signatureId
     * @return
     */
    RemoveSignatureResponse removeSignature(int sesscode, String signatureId);

    /**
     * The request enables to check the service and to get to know it's version number
     * @return
     */
    VersionResponse getVersion();

    /**
     * The MobileSign method invokes mobile signing of a DDOC/BDOC file in the current session. For using the MobileSign
     * method, at least one datafile shall be in DDOC/BDOC container.
     * @param sesscode			An identifier of the active session
     * @param signerIdCode		Identification number of the signer (personal national ID number).
     * @param signerCountry		Country which issued the personal national ID number (e.g. "EE")
     * @param signerPhone		Phone number of the signer with the country code in format +xxxxxxxxx
     * @param serviceName		Name of the service – previously agreed with Application Provider and DigiDocService operator
     * @param additionalData	Additional text shown to the signer.
     * @param language			Language of the message displayed to the signer’s phone. (for exaple EST, LIT)
     * @param role				The text of the role or resolution defined by the user. Optional.
     * @param city				Name of the city, where it’s signed. Optional.
     * @param state				Name of the state/province, where it’s signed. Optional.
     * @param postalCode		Postal code of the signing location. Optional.
     * @param countryName		Name of the country, where it’s signed. Optional.
     * @param signingProfile	Specifies the signature creating profile. Default is ""
     * @param messagingMode		Determines the mode how the response of the MobileSign is returned.
     * @return
     */
    MobileSignResponse mobileSign(
            int sesscode,
            String signerIdCode,
            String signersCountry,
            String signerPhoneNo,
            String serviceName,
            String additionalDataToBeDisplayed,
            String language,
            String role,
            String city,
            String stateOrProvince,
            String postalCode,
            String countryName,
            String signingProfile,
            String messagingMode,
            int asyncConfiguration,
            boolean returnDocInfo,
            boolean returnDocData);

    /**
     * GetStatusInfo request is for getting the information about the document
     * in session (signed) and it’s status.
     *
     * @param sesscode
     *            An identifier of the active session
     * @param returnDocInfo
     *            If the value is „true”, in response SignedDocInfo is set.
     * @param waitSignature
     *            If the value is „true“, response is not sent before message
     *            from mobile phone is received or error condition is detected.
     *
     */
    StatusInfoResponse getStatusInfo(int sesscode, boolean returnDocInfo, boolean waitSignature);

    MobileAuthenticateResponse mobileAuthenticate(
            String IDCode,
            String countryCode,
            String phoneNo,
            String language,
            String serviceName,
            String messageToDisplay,
            String SPChallenge,
            String messagingMode,
            int asyncConfiguration,
            boolean returnCertData,
            boolean returnRevocationData);

    MobileAuthenticateStatusResponse getMobileAuthenticateStatus(int sesscode, boolean waitSignature);

    /**
     * This request is used for creating additional signature for the DDOC/BDOC file
     * @param sesscode
     * @param signerIdCode
     * @param signersCountry
     * @param signerPhoneNo
     * @param language
     * @param serviceName
     * @param messageToDisplay
     * @param role
     * @param city
     * @param stateOrProvince
     * @param postalCode
     * @param countryName
     * @param signingProfile
     * @param dataFiles
     * @param format
     * @param version
     * @param signatureId
     * @return
     */
    MobileCreateSignatureResponse mobileCreateSignature(
            String idCode,
            String signersCountry,
            String phoneNo,
            String language,
            String serviceName,
            String messageToDisplay,
            String role,
            String city,
            String stateOrProvince,
            String postalCode,
            String countryName,
            String signingProfile,
            DataFileDigestList dataFiles,
            String format,
            String version,
            String signatureId,
            String messagingMode,
            int asyncConfiguration);

    /**
     * The method is used to query status information when using asynchClientServer mobile signing mode.
     * @param sesscode
     * @param waitSignature
     * @return
     */
    MobileCreateSignatureStatusResponse getMobileCreateSignatureStatus(int sesscode, boolean waitSignature);

    /**
     * Meetod Mobiil-ID teenuse olemasolu ja sertifikaatide info pärimiseks
     * @param idCode
     * @param country
     * @param phoneNo
     * @param returnCertData
     * @return
     */
    MobileCertificateResponse getMobileCertificate(
            String idCode,
            String country,
            String phoneNo,
            String returnCertData);

    CheckCertificateResponse checkCertificate(String certificate, boolean returnRevocationData);

}
