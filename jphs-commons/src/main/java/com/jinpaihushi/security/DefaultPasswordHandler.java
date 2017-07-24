package com.jinpaihushi.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;


@Service("i_PasswordHandler")
public class DefaultPasswordHandler implements I_PasswordHandler{
	 /**  The minimum length of a password. */
    public static final int PASSWORD_MIN_LENGTH = 4;

    /** The log object for this class. */
    //private static final Log LOG = Log.getLog(CmsDefaultPasswordHandler.class);

    /** The secure random number generator. */
    private static SecureRandom m_secureRandom;

    /** The configuration of the password handler. */
    private SortedMap<String, String> m_configuration;

    /** The digest type used. */
    private String m_digestType = DIGEST_TYPE_MD5;   
    
    /** The digest type used. */
    private String s_digestType = DIGEST_TYPE_SHA2;   
    
    public static final String ENCODING_UTF_8 = "UTF-8";
    
    /** The encoding the encoding used for translating the input string to bytes. */
    private String m_inputEncoding = ENCODING_UTF_8;
    /**
     * The constructor does not perform any operation.<p>
     */
    public DefaultPasswordHandler() {

        m_configuration = new TreeMap<String, String>();
    }
    
    public static void main(String[] args) throws Exception {
    	DefaultPasswordHandler handler = new DefaultPasswordHandler();
    	String password = "111111";
    	String result = handler.digest(password);
    	System.out.println(result);
    	
    
	}
    
    

    /**
     * @see org.opencms.configuration.I_CmsConfigurationParameterHandler#addConfigurationParameter(java.lang.String, java.lang.String)
     */
    public void addConfigurationParameter(String paramName, String paramValue) {

        m_configuration.put(paramName, paramValue);
    }

    /**
     * @see org.opencms.security.I_CmsPasswordHandler#digest(java.lang.String)
     */
    public String digest(String password) throws Exception {
    	
        return digest(password, m_digestType, m_inputEncoding);
    }
    
    public String sha2Digest(String password) throws Exception {
    	
        return digest(password, s_digestType, m_inputEncoding);
    }
    /**
     * @see org.opencms.security.I_CmsPasswordHandler#digest(java.lang.String, java.lang.String, java.lang.String)
     */
    public String digest(String password, String digestType, String inputEncoding)
    throws Exception {

        MessageDigest md;
        String result;

        try {
            if (DIGEST_TYPE_PLAIN.equals(digestType.toLowerCase())) {
            	System.out.println(1);
                result = password;

            } else if (DIGEST_TYPE_SSHA.equals(digestType.toLowerCase())) {
            	System.out.println(2);
                byte[] salt = new byte[4];
                byte[] digest;
                byte[] total;

                if (m_secureRandom == null) {
                    m_secureRandom = SecureRandom.getInstance("SHA1PRNG");
                }
                m_secureRandom.nextBytes(salt);

                md = MessageDigest.getInstance(DIGEST_TYPE_SHA);
                md.reset();
                md.update(password.getBytes(inputEncoding));
                md.update(salt);

                digest = md.digest();
                total = new byte[digest.length + salt.length];
                System.arraycopy(digest, 0, total, 0, digest.length);
                System.arraycopy(salt, 0, total, digest.length, salt.length);

                result = new String(Base64.encodeBase64(total));

            } else {
            	System.out.println(3);
                md = MessageDigest.getInstance(digestType);
                md.reset();
                md.update(password.getBytes(inputEncoding));
                
                result = new String(Base64.encodeBase64(md.digest()));

            }
        } catch (NoSuchAlgorithmException e) {
           
            throw e;
        } catch (UnsupportedEncodingException e) {
            
            throw e;
        }

        return result;
    }

    /**
     * @see org.opencms.configuration.I_CmsConfigurationParameterHandler#getConfiguration()
     */
    public Map<String, String> getConfiguration() {

        return m_configuration;
    }

    /**
     * Returns the digestType.<p>
     *
     * @return the digestType
     */
    public String getDigestType() {

        return m_digestType;
    }

    /**
     * Returns the input encoding.<p>
     *
     * @return the input encoding
     */
    public String getInputEncoding() {

        return m_inputEncoding;
    }

    /**
     * @see org.opencms.configuration.I_CmsConfigurationParameterHandler#initConfiguration()
     */
    public void initConfiguration() throws Exception {

        // simple default configuration does not need to be initialized
//        if (LOG.isDebugEnabled()) {
//            CmsMessageContainer message = Messages.get().container(Messages.LOG_INIT_CONFIG_CALLED_1, this);
//            LOG.debug(message.key());
//            LOG.debug(Messages.get().getBundle().key(Messages.LOG_INIT_CONFIG_CALLED_1, this));
//            // supress compiler warning, this is never true
//            if (this == null) {
//                throw new CmsConfigurationException(message);
//            }
//        }
        m_configuration = Collections.unmodifiableSortedMap(m_configuration);
    }

    /**
     * Sets the digestType.<p>
     *
     * @param digestType the digestType to set
     */
    public void setDigestType(String digestType) {

        m_digestType = digestType;
    }

    /**
     * Sets the input encoding.<p>
     *
     * @param inputEncoding the input encoding to set
     */
    public void setInputEncoding(String inputEncoding) {

        m_inputEncoding = inputEncoding;
    }

    /**
     * @see org.opencms.security.I_CmsPasswordHandler#validatePassword(java.lang.String)
     */
    public void validatePassword(String password)  throws Exception{

        if ((password == null) || (password.length() < PASSWORD_MIN_LENGTH)) {
            throw new Exception();
        }
    }
}
