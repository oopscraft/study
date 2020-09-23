package net.oopscraft.study.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.CryptoAlgorithm;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;

public class KMSUtils {
	
	private static BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA6ES7M6AUMTPGKXZH", "hxXDYSl7/SujG7AvSZw5xt1fQhqA7QSaj7sq8XED");
	
	private static String awsKmsArn = "arn:aws:kms:ap-northeast-2:971936428072:key/591bf3a9-178c-4697-a146-796e6af043f7";
	
	
	// Instantiate the SDK
    private static AwsCrypto crypto = new AwsCrypto();

    // type
    enum Type {
    	 DIRECT	
    	,ROLE_BASE
    }
    
    private static KmsMasterKeyProvider getKeyProvider(Type type) throws Exception {
    	if(type == Type.DIRECT) { 
    		KmsMasterKeyProvider keyProvider = new KmsMasterKeyProvider(awsCreds, awsKmsArn);
    		return keyProvider;
    	}else if(type ==  Type.ROLE_BASE) {
            AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
                    .withCredentials(new StaticCredentialsProvider(awsCreds))
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .build();
            AssumeRoleRequest roleRequest = new AssumeRoleRequest()
                    .withRoleArn("arn:aws:iam::971936428072:role/kms_role")
                    .withRoleSessionName("test");
            AssumeRoleResult roleResponse = stsClient.assumeRole(roleRequest);
            Credentials sessionCredentials = roleResponse.getCredentials();
            BasicSessionCredentials awsCredentials = new BasicSessionCredentials(
                    sessionCredentials.getAccessKeyId(),
                    sessionCredentials.getSecretAccessKey(),
                    sessionCredentials.getSessionToken());
            KmsMasterKeyProvider keyProvider = new KmsMasterKeyProvider(awsCredentials, awsKmsArn);
    		
    		return keyProvider;
    	}else {
    		throw new IllegalArgumentException();
    	}
    }
	
    /**
     * encrypt
     * @param value
     * @return
     * @throws Exception
     */
	public static String encrypt(KmsMasterKeyProvider keyProvider, String value) throws Exception {
		System.out.println(String.format("encrypt[%s]", value));
        crypto.setEncryptionAlgorithm(CryptoAlgorithm.ALG_AES_256_GCM_IV12_TAG16_HKDF_SHA256);
        String ciphertext = crypto.encryptString(keyProvider, new String(value.getBytes("UTF-8"))).getResult();
        return ciphertext;
	}
	
	/**
	 * decrypt
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(KmsMasterKeyProvider keyProvider, String value) throws Exception {
		System.out.println(String.format("decrypt[%s]", value));
        crypto.setEncryptionAlgorithm(CryptoAlgorithm.ALG_AES_256_GCM_IV12_TAG16_HKDF_SHA256);
        String decryptText = crypto.decryptString(keyProvider, value).getResult();
        decryptText = new String(decryptText.getBytes("UTF-8"));
        return decryptText;
	}
	
	/**
	 * test
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//KmsMasterKeyProvider keyProvider = getKeyProvider(Type.DIRECT);
		KmsMasterKeyProvider keyProvider = getKeyProvider(Type.ROLE_BASE);
		String value = "test";
		System.out.println(String.format("value[%s]", value));
		String encValue = encrypt(keyProvider, value);
		System.out.println(String.format("encValue[%s]", encValue));
		String decValue = decrypt(keyProvider, encValue);
		System.out.println(String.format("decValue[%s]", decValue));
	}
}
