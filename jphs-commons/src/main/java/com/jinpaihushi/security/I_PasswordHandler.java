package com.jinpaihushi.security;


public interface I_PasswordHandler extends I_ConfigurationParameterHandler{

    /** 
     * Flag for conversion of the password encoding.<p> 
     */
    String CONVERT_DIGEST_ENCODING = "compatibility.convert.digestencoding";

    /**
     * String to identify the key value for md5 password hashes.<p>
     */
    String DIGEST_TYPE_MD5 = "md5";

    /**
     * String to identify the key value for unhashed passwords.<p>
     */
    String DIGEST_TYPE_PLAIN = "plain";

    /**
     * String to identify the key value for sha password hashes.<p>
     */
    String DIGEST_TYPE_SHA = "sha";
    
    /**
     * String to identify the key value for sha2 password hashes.<p>
     */
    String DIGEST_TYPE_SHA2 = "SHA-256";

    /**
     * String to identify the key value for sha password hashes with 4 byte salt.<p>
     */
    String DIGEST_TYPE_SSHA = "ssha";

    /**
     * Creates an OpenCms password digest according to the default setting for method/encodings.<p>
     * 
     * @param password the password to encrypt
     * @return the password digest
     * @throws CmsPasswordEncryptionException if something goes wrong
     */
    String digest(String password) throws Exception;
    
    String sha2Digest(String password) throws Exception;

    /**
     * Creates an OpenCms password digest.<p>
     *
     * @param password the password to encrypt
     * @param digestType the algorithm used for encryption (i.e. MD5, SHA ...)
     * @param inputEncoding the encoding used when converting the password to bytes (i.e. UTF-8)
     * @return the password digest
     * @throws CmsPasswordEncryptionException if something goes wrong
     */
    String digest(String password, String digestType, String inputEncoding) throws Exception;

    /**
     * Returns the default digest type.<p>
     * 
     * @return the default digest type
     */
    String getDigestType();

    /**
     * Returns the default password encoding.<p>
     * 
     * @return the default password encoding
     */
    String getInputEncoding();

    /**
     * Sets the default digest type.<p>
     * 
     * @param digestType the digest type used
     */
    void setDigestType(String digestType);

    /**
     * Sets the default input encoding.<p>
     * 
     * @param inputEncoding the encoding used for translation the password string to bytes
     */
    void setInputEncoding(String inputEncoding);

    /**
     * This method checks if a new password follows the rules for
     * new passwords, which are defined by a Class configured in 
     * the opencms.properties file.<p>
     * 
     * If this method throws no exception the password is valid.<p>
     *
     * @param password the password to check
     * 
     * @throws CmsSecurityException if validation of the password failed
     */
    void validatePassword(String password) throws Exception;
}
