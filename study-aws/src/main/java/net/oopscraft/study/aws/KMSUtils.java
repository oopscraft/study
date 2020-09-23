package net.oopscraft.study.aws;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.CryptoAlgorithm;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;

public class KMSUtils {
	
	private static BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA6ES7M6AUMTPGKXZH", "hxXDYSl7/SujG7AvSZw5xt1fQhqA7QSaj7sq8XED");
	
	private static String awsKmsArn = "arn:aws:kms:ap-northeast-2:971936428072:key/591bf3a9-178c-4697-a146-796e6af043f7";
	
	// Instantiate the SDK
    private static AwsCrypto crypto = new AwsCrypto();

    // Set up the KmsMasterKeyProvider backed by the default credentials
    private static KmsMasterKeyProvider keyProvider = new KmsMasterKeyProvider(awsCreds, awsKmsArn);
	
    /**
     * encrypt
     * @param value
     * @return
     * @throws Exception
     */
	public static String encrypt(String value) throws Exception {
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
	public static String decrypt(String value) throws Exception {
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
		String value = "test";
		System.out.println(String.format("value[%s]", value));
		String encValue = encrypt(value);
		System.out.println(String.format("encValue[%s]", encValue));
		String decValue = decrypt(encValue);
		System.out.println(String.format("decValue[%s]", decValue));
	}
}
