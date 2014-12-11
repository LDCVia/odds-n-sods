package com.ldcvia;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.*;
import javax.crypto.spec.SecretKeySpec;

import javax.xml.bind.DatatypeConverter;

/**
* Companion file for our node application.
*
* NOTE: you will need unrestricted Java security policy files to avoid 'Illegal Key Size' exceptions
*
* See this link for more:
* http://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters
*/
public class NodeCryptoTest {

  // IV and key: these should be mirrored in the node application
  protected static final byte[] ENC_IV = "0000000000000000".getBytes();
  protected static final String ENC_KEY = "hi";

  private static String decrypt(String encrypted, String seed) throws Exception {
    byte[] keyb = seed.getBytes("utf-8");
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] thedigest = md.digest(keyb);

    SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
    Cipher dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    dcipher.init(Cipher.DECRYPT_MODE, skey, new IvParameterSpec(ENC_IV));
    byte[] clearbyte = dcipher.doFinal(DatatypeConverter.parseHexBinary(encrypted));

    return new String(clearbyte);
  }

  /**
  * Currently unused
  * @param content String to encrypt
  * @param key String representing key to use
  * @return String representing encrypted content
  * @throws Exception
  */
  public static String encrypt(String content, String key) throws Exception {
    byte[] input = content.getBytes("utf-8");
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] thedigest = md.digest(key.getBytes("utf-8"));

    SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, skc, new IvParameterSpec(ENC_IV));
    byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
    int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
    ctLength += cipher.doFinal(cipherText, ctLength);

    return DatatypeConverter.printHexBinary(cipherText);
  }

  /**
  * Test method so we can run this code simply.
  * @param args
  * @throws Exception
  */
  public static void main(String[] args) throws Exception {
    // This is a sample encrypted JSON object generated from our node app using the same key, algorithm, and IV
    String enc = "9584481dcfc5824a6ba6487169d0bdb62c939ce459ec90fff2d685944dfb7fd3db6a2e46991ecc9c88be44bac594cdc3fff2505c846ae3640f58b5f050b02557fcc37a21fdfc833a69490d89750163b61fd8d4087bd211891f97f1d1d5e0e8bb";
    System.out.println(NodeCryptoTest.decrypt(enc, ENC_KEY));
  }

}
