package ee.zerotech.digidocservice;

import ee.zerotech.digidocservice.gen.*;
import ee.zerotech.digidocservice.to.*;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import java.util.Base64;
import java.util.Map;

public class DigidocServiceImpl implements DigidocService {

    private DigiDocServicePortType wsPort;

    public DigidocServiceImpl(String endpoint) {
        ee.zerotech.digidocservice.gen.DigiDocService wsDigidocService = new ee.zerotech.digidocservice.gen.DigiDocService();
        this.wsPort = wsDigidocService.getDigiDocService();
        Map<String, Object> requestContext = ((BindingProvider)wsPort).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        //requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, this.requestTimeout);
        //requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, this.connectionTimeout);
    }
    
    @Override
    public StartSessionResponse startSession(
            String signingProfile,
            String sigDocXML,
            boolean bHoldSession,
            DataFileData datafile) {

        Holder<String> status = new Holder<String>();
        Holder<Integer> sesscode = new Holder<Integer>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<SignedDocInfo>();

        wsPort.startSession(
                signingProfile,
                sigDocXML,
                bHoldSession,
                datafile,
                status,
                sesscode,
                signedDocInfo);

        StartSessionResponse ret = new StartSessionResponse();
        ret.setStatus(status.value);
        ret.setSesscode(sesscode.value);
        ret.setSignedDocInfo(signedDocInfo.value);
        return ret;
    }

    @Override
    public String closeSession(int sesscode) {
        return this.wsPort.closeSession(sesscode);
    }


    @Override
    public CreateSignedDocResponse createSignedDoc(int sesscode, String format, String version, String signingProfile) {
        Holder<String> status = new Holder<String>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<SignedDocInfo>();

        wsPort.createSignedDoc(sesscode, format, version, signingProfile, status, signedDocInfo);

        CreateSignedDocResponse ret = new CreateSignedDocResponse();
        ret.setStatus(status.value);
        ret.setSignedDocInfo(signedDocInfo.value);
        return ret;
    }


    @Override
    public MobileAuthenticateResponse mobileAuthenticate(
            String idCode,
            String countryCode,
            String phoneNo,
            String language,
            String serviceName,
            String messageToDisplay,
            String SPChallenge,
            String asynchclientserver,
            int asyncConfiguration,
            boolean returnCertData,
            boolean returnRevocationData) {

        Holder<Integer> sesscode = new Holder<Integer>();
        Holder<String> status = new Holder<String>();
        Holder<String> userIDCode = new Holder<String>();
        Holder<String> userGivenname = new Holder<String>();
        Holder<String> userSurname = new Holder<String>();
        Holder<String> userCountry = new Holder<String>();
        Holder<String> userCN = new Holder<String>();
        Holder<String> certificateData = new Holder<String>();
        Holder<String> challengeID = new Holder<String>();
        Holder<String> challenge = new Holder<String>();
        Holder<String> revocationData = new Holder<String>();

        wsPort.mobileAuthenticate(
                    idCode
                    ,countryCode
                    ,phoneNo
                    ,language
                    ,serviceName
                    ,messageToDisplay
                    ,SPChallenge
                    ,asynchclientserver
                    ,asyncConfiguration
                    ,returnCertData
                    ,returnRevocationData
                    // response fields
                    ,sesscode
                    ,status
                    ,userIDCode
                    ,userGivenname
                    ,userSurname
                    ,userCountry
                    ,userCN
                    ,certificateData
                    ,challengeID
                    ,challenge
                    ,revocationData
        );

        MobileAuthenticateResponse ret = new MobileAuthenticateResponse();
        ret.setSesscode(sesscode.value);
        ret.setStatus(status.value);
        ret.setUserIDCode(userIDCode.value);
        ret.setUserGivenname(userGivenname.value);
        ret.setUserSurname(userSurname.value);
        ret.setUserCountry(userCountry.value);
        ret.setUserCN(userCN.value);
        ret.setCertificateData(certificateData.value);
        ret.setChallengeID(challengeID.value);
        ret.setChallenge(challenge.value);
        ret.setRevocationData(revocationData.value);
        return ret;
    }

    @Override
    public MobileAuthenticateStatusResponse getMobileAuthenticateStatus(int sesscode, boolean waitSignature) {
        Holder<String> status = new Holder<String>();
        Holder<String> signature = new Holder<String>();

        wsPort.getMobileAuthenticateStatus(sesscode, waitSignature, status, signature);

        MobileAuthenticateStatusResponse ret = new MobileAuthenticateStatusResponse();
        ret.setStatus(status.value);
        ret.setSignature(signature.value);
        
        return ret;
    }

    @Override
    public PrepareSignatureResponse prepareSignature(
            int sesscode,
            String signersCertificate,
            String signersTokenID,
            String role,
            String city,
            String state,
            String postalCode,
            String countryName,
            String signingProfile) {

        Holder<String> status = new Holder<String>();
        Holder<String> signatureId = new Holder<String>();
        Holder<String> signedInfoDigest = new Holder<String>();

        wsPort.prepareSignature(sesscode, signersCertificate, signersTokenID, role, city, state, postalCode,
                    countryName, signingProfile, status, signatureId, signedInfoDigest);


        PrepareSignatureResponse ret = new PrepareSignatureResponse();
        ret.setStatus(status.value);
        ret.setSignatureId(signatureId.value);
        ret.setSignedInfoDigest(signedInfoDigest.value);
        return ret;
    }

    @Override
    public FinalizeSignatureResponse finalizeSignature(int sesscode, String signatureID,
                                                       String signatureValue) {

        Holder<String> status = new Holder<String>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<SignedDocInfo>();

        wsPort.finalizeSignature(sesscode, signatureID, signatureValue, status, signedDocInfo);

        FinalizeSignatureResponse response = new FinalizeSignatureResponse();
        response.setStatus(status.value);
        response.setSignedDocInfo(signedDocInfo.value);
        return response;
    }

    @Override
    public SignedDocResponse getSignedDoc(int sesscode) {
        Holder<String> status = new Holder<String>();
        Holder<String> signedDocData = new Holder<String>();

        wsPort.getSignedDoc(sesscode, status, signedDocData);

        SignedDocResponse response = new SignedDocResponse();
        response.setStatus(status.value);
        response.setSignedDocData(signedDocData.value);
        return response;
    }

    @Override
    public AddDataFileResponse addDataFile(int sesscode,
            String fileName,
            String mimeType,
            String contentType,
            int size,
            String digestType,
            String digestValue,
            byte[] content) {
        Holder<String> status = new Holder<String>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<SignedDocInfo>();

        wsPort.addDataFile(
                    sesscode,
                    fileName,
                    mimeType,
                    contentType,
                    size,
                    digestType,
                    digestValue,
                    content == null ? null : Base64.getEncoder().encodeToString(content),
                    status,
                    signedDocInfo);

        AddDataFileResponse response = new AddDataFileResponse();
        response.setStatus(status.value);
        response.setSignedDocInfo(signedDocInfo.value);
        return response;
    }


    @Override
    public CheckCertificateResponse checkCertificate(String certificate, boolean returnRevocationData) {
        Holder<String> status = new Holder<String>();
        Holder<String> userIDCode = new Holder<String>();
        Holder<String> userGivenname = new Holder<String>();
        Holder<String> userSurname = new Holder<String>();
        Holder<String> userCountry = new Holder<String>();
        Holder<String> userOrganisation = new Holder<String>();
        Holder<String> userCN = new Holder<String>();
        Holder<String> issuerCN = new Holder<String>();
        Holder<String> keyUsage = new Holder<String>();
        Holder<String> enhancedKeyUsage = new Holder<String>();
        Holder<String> revocationData = new Holder<String>();

        wsPort.checkCertificate(
                        certificate,
                        returnRevocationData,
                        status,
                        userIDCode,
                        userGivenname,
                        userSurname,
                        userCountry,
                        userOrganisation,
                        userCN,
                        issuerCN,
                        keyUsage,
                        enhancedKeyUsage,
                        revocationData);

        CheckCertificateResponse ret = new CheckCertificateResponse();
        ret.setStatus(status.value);
        ret.setUserIDCode(userIDCode.value);
        ret.setUserGivenname(userGivenname.value);
        ret.setUserSurname(userSurname.value);
        ret.setUserCountry(userCountry.value);
        ret.setUserOrganisation(userOrganisation.value);
        ret.setUserCN(userCN.value);
        ret.setIssuerCN(issuerCN.value);
        ret.setKeyUsage(keyUsage.value);
        ret.setEnhancedKeyUsage(enhancedKeyUsage.value);
        ret.setRevocationData(revocationData.value);
        return ret;
    }

    @Override
    public VersionResponse getVersion() {
        Holder<String> name = new Holder<String>();
        Holder<String> version = new Holder<String>();
        Holder<String> libname = new Holder<String>();
        Holder<String> libver = new Holder<String>();

        wsPort.getVersion(name, version, libname, libver);

        VersionResponse ret = new VersionResponse();
        ret.setName(name.value);
        ret.setVersion(version.value);
        ret.setLibname(libname.value);
        ret.setLibver(libver.value);
        return ret;
    }

    @Override
    public MobileSignResponse mobileSign(
            int sesscode,
            String signerIdCode,
            String signerCountry,
            String signerPhone,
            String serviceName,
            String additionalData,
            String language,
            String role,
            String city,
            String state,
            String postalCode,
            String countryName,
            String signingProfile,
            String messagingMode,
            int asyncConfiguration,
            boolean returnDocInfo,
            boolean returnDocData) {

        Holder<String> status = new Holder<String>();
        Holder<String> statusCode = new Holder<String>();
        Holder<String> challengeID = new Holder<String>();

        wsPort.mobileSign(
                    sesscode,
                    signerIdCode,
                    signerCountry,
                    signerPhone,
                    serviceName,
                    additionalData,
                    language,
                    role,
                    city,
                    state,
                    postalCode,
                    countryName,
                    signingProfile,
                    messagingMode,
                    asyncConfiguration,
                    returnDocInfo,
                    returnDocData,
                    status,
                    statusCode,
                    challengeID);

        MobileSignResponse ret = new MobileSignResponse();
        ret.setStatus(status.value);
        ret.setStatusCode(statusCode.value);
        ret.setChallengeID(challengeID.value);
        return ret;
    }

    @Override
    public StatusInfoResponse getStatusInfo(int sesscode, boolean returnDocInfo,
            boolean waitSignature) {

        Holder<String> status = new Holder<String>();
        Holder<String> statusCode = new Holder<String>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<SignedDocInfo>();

        wsPort.getStatusInfo(sesscode, returnDocInfo, waitSignature, status, statusCode, signedDocInfo);

        StatusInfoResponse ret = new StatusInfoResponse();
        ret.setStatus(status.value);
        ret.setStatusCode(statusCode.value);
        ret.setSignedDocInfo(signedDocInfo.value);
        return ret;
    }

    @Override
    public SignedDocInfoResponse getSignedDocInfo(int sesscode) {
        Holder<String> status = new Holder<String>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<SignedDocInfo>();

        wsPort.getSignedDocInfo(sesscode, status, signedDocInfo);

        SignedDocInfoResponse response = new SignedDocInfoResponse();
        response.setStatus(status.value);
        response.setSignedDocInfo(signedDocInfo.value);
        return response;
    }

    @Override
    public DataFileResponse getDataFile(int sesscode, String dataFileId) {
        Holder<String> status = new Holder<String>();
        Holder<DataFileData> dataFileData = new Holder<DataFileData>();

        wsPort.getDataFile(sesscode, dataFileId, status, dataFileData);

        DataFileResponse response = new DataFileResponse();
        response.setStatus(status.value);
        response.setDataFileData(dataFileData.value);
        return response;
    }

    @Override
    public RemoveSignatureResponse removeSignature(int sesscode, String signatureId) {
        Holder<String> status = new Holder<String>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<SignedDocInfo>();

        wsPort.removeSignature(sesscode, signatureId, status, signedDocInfo);

        RemoveSignatureResponse response = new RemoveSignatureResponse();
        response.setStatus(status.value);
        response.setSignedDocInfo(signedDocInfo.value);
        return response;
    }

    @Override
    public RemoveDataFileResponse removeDataFile(int sesscode, String dataFileId) {
        Holder<String> status = new Holder<String>();
        Holder<SignedDocInfo> signedDocInfo = new Holder<SignedDocInfo>();

        wsPort.removeDataFile(sesscode, dataFileId, status, signedDocInfo);

        RemoveDataFileResponse response = new RemoveDataFileResponse();
        response.setStatus(status.value);
        response.setSignedDocInfo(signedDocInfo.value);
        return response;
    }

    @Override
    public CertificateResponse getSignersCertificate(int sesscode,
            String signatureId) {
        Holder<String> status = new Holder<String>();
        Holder<String> certificateData = new Holder<String>();

        wsPort.getSignersCertificate(sesscode, signatureId, status, certificateData);

        CertificateResponse response = new CertificateResponse();
        response.setStatus(status.value);
        response.setCertificateData(certificateData.value);
        return response;
    }

    @Override
    public CertificateResponse getNotarysCertificate(int sesscode,
            String signatureId) {
        Holder<String> status = new Holder<String>();
        Holder<String> certificateData = new Holder<String>();

        wsPort.getNotarysCertificate(sesscode, signatureId, status, certificateData);

        CertificateResponse response = new CertificateResponse();
        response.setStatus(status.value);
        response.setCertificateData(certificateData.value);
        return response;
    }

    @Override
    public NotaryResponse getNotary(int sesscode, String signatureId) {
        Holder<String> status = new Holder<String>();
        Holder<String> ocspData = new Holder<String>();

        wsPort.getNotary(sesscode, signatureId, status, ocspData);

        NotaryResponse response = new NotaryResponse();
        response.setStatus(status.value);
        response.setOcspData(ocspData.value);
        return response;
    }

    /*
    @Override
    public SignatureModulesResponse getSignatureModules(int sesscode, String platform, String phase, String type) {
        Holder<String> status = new Holder<String>();
        Holder<SignatureModulesArray> modules = new Holder<SignatureModulesArray>();

        wsPort.getgetSignatureModules(sesscode, platform, phase, type, status, modules);

        SignatureModulesResponse response = new SignatureModulesResponse();
        response.setStatus(status.value);
        response.setModules(modules.value);
        return response;
    }
    */

    @Override
    public TSACertificateResponse getTSACertificate(int sesscode, String timestampId) {
        Holder<String> status = new Holder<String>();
        Holder<String> certificateData = new Holder<String>();

        wsPort.getTSACertificate(sesscode, timestampId, status, certificateData);

        TSACertificateResponse response = new TSACertificateResponse();
        response.setStatus(status.value);
        response.setCertificateData(certificateData.value);
        return response;
    }

    /*
    @Override
    public TimestampResponse getTimestamp(int sesscode, String timestampId) {
        Holder<String> status = new Holder<String>();
        Holder<String> timestampData = new Holder<String>();

        wsPort.getTimestamp(sesscode, timestampId, status, timestampData);

        TimestampResponse response = new TimestampResponse();
        response.setStatus(status.value);
        response.setTimestampData(timestampData.value);
        return response;
    }
    */

    @Override
    public CRLResponse getCRL(int sesscode, String signatureId) {
        Holder<String> status = new Holder<String>();
        Holder<String> CRLData = new Holder<String>();

        wsPort.getCRL(sesscode, signatureId, status, CRLData);

        CRLResponse response = new CRLResponse();
        response.setStatus(status.value);
        response.setCrlData(CRLData.value);

        return response;
    }

    @Override
    public MobileCreateSignatureResponse mobileCreateSignature(
            String IDCode,
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
            int asyncConfiguration) {

        Holder<String> status = new Holder<String>();
        Holder<String> challengeID = new Holder<String>();
        Holder<Integer> sesscode = new Holder<Integer>();

            wsPort.mobileCreateSignature(
                    IDCode,
                    signersCountry,
                    phoneNo,
                    language,
                    serviceName,
                    messageToDisplay,
                    role,
                    city,
                    stateOrProvince,
                    postalCode,
                    countryName,
                    signingProfile,
                    dataFiles,
                    format,
                    version,
                    signatureId,
                    messagingMode,
                    asyncConfiguration,
                    sesscode,
                    challengeID,
                    status);

        MobileCreateSignatureResponse response = new MobileCreateSignatureResponse();
        response.setStatus(status.value);
        response.setChallengeId(challengeID.value);
        response.setSesscode(sesscode.value);
        return response;
    }

    @Override
    public MobileCreateSignatureStatusResponse getMobileCreateSignatureStatus(int sesscode, boolean waitSignature) {
        Holder<String> status = new Holder<String>();
        Holder<String> signature = new Holder<String>();
        Holder<Integer> sesscodeHolder = new Holder<Integer>(sesscode);

        wsPort.getMobileCreateSignatureStatus(sesscodeHolder, waitSignature, status, signature);

        MobileCreateSignatureStatusResponse response = new MobileCreateSignatureStatusResponse();
        response.setSesscode(sesscodeHolder.value);
        response.setStatus(status.value);
        response.setSignature(signature.value);
        return response;
    }

    @Override
    public MobileCertificateResponse getMobileCertificate(
            String idCode,
            String country,
            String phoneNo,
            String returnCertData) {
        Holder<String> authCertStatus = new Holder<String>();
        Holder<String> signCertStatus = new Holder<String>();
        Holder<String> authCertData = new Holder<String>();
        Holder<String> signCertData = new Holder<String>();
        
        wsPort.getMobileCertificate(
                    idCode,
                    country,
                    phoneNo,
                    returnCertData,
                    authCertStatus,
                    signCertStatus,
                    authCertData,
                    signCertData);
  
        MobileCertificateResponse response = new MobileCertificateResponse();
        response.setAuthCertData(authCertData.value);
        response.setAuthCertStatus(authCertStatus.value);
        response.setSignCertData(signCertData.value);
        response.setSignCertStatus(signCertStatus.value);

        return response;
    }

}
