//
//package com.test;
//
//import cn.hutool.crypto.SecureUtil;
//import org.apache.commons.codec.DecoderException;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.binary.Hex;
//import org.apache.commons.text.StringEscapeUtils;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//
//import javax.crypto.*;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.security.*;
//
///**
// * 封装各种格式的编码解码工具类.
// * 1.Commons-Codec的 hex/base64 编码
// * 2.自制的base62 编码
// * 3.Commons-Lang的xml/html escape
// * 4.JDK提供的URLEncoder
// *
// * @author calvin
// * @version 2013-01-15
// */
//public class Encodes {
//
//    private static final String DEFAULT_URL_ENCODING = "UTF-8";
//    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
//    private static final String ENCRYPT_AES = "AES";
//    private static final String CHARSET = "UTF-8";
//    public static final String SECRETKEY = "suntech";
//    public static final String ENCRYPT_CIPHER = "AES/ECB/PKCS7Padding";
//    public static final String ENCRYPT_CIPHER_BC = "BC";
//
//
//    static {
//        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
//        Security.addProvider(new BouncyCastleProvider());
//    }
//    /**
//     * Hex编码.
//     */
//    public static String encodeHex(byte[] input) {
//        return new String(Hex.encodeHex(input));
//    }
//
//    /**
//     * Hex解码.
//     */
//    public static byte[] decodeHex(String input) {
//        try {
//            return Hex.decodeHex(input.toCharArray());
//        } catch (DecoderException e) {
//            throw Exceptions.unchecked(e);
//        }
//    }
//
//    /**
//     * Base64编码.
//     */
//    public static String encodeBase64(byte[] input) {
//        return new String(Base64.encodeBase64(input));
//    }
//
//    /**
//     * Base64编码.
//     */
//    public static String encodeBase64(String input) {
//        try {
//            return new String(Base64.encodeBase64(input.getBytes(DEFAULT_URL_ENCODING)));
//        } catch (UnsupportedEncodingException e) {
//            return "";
//        }
//    }
//
////	/**
////	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
////	 */
////	public static String encodeUrlSafeBase64(byte[] input) {
////		return Base64.encodeBase64URLSafe(input);
////	}
//
//    /**
//     * Base64解码.
//     */
//    public static byte[] decodeBase64(String input) {
//        return Base64.decodeBase64(input.getBytes());
//    }
//
//    /**
//     * Base64解码.
//     */
//    public static String decodeBase64String(String input) {
//        try {
//            return new String(Base64.decodeBase64(input.getBytes()), DEFAULT_URL_ENCODING);
//        } catch (UnsupportedEncodingException e) {
//            return "";
//        }
//    }
//
//    /**
//     * Base62编码。
//     */
//    public static String encodeBase62(byte[] input) {
//        char[] chars = new char[input.length];
//        for (int i = 0; i < input.length; i++) {
//            chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
//        }
//        return new String(chars);
//    }
//
//    /**
//     * Html 转码.
//     */
//    public static String escapeHtml(String html) {
//        return StringEscapeUtils.escapeHtml4(html);
//    }
//
//    /**
//     * Html 解码.
//     */
//    public static String unescapeHtml(String htmlEscaped) {
//        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
//    }
//
//    /**
//     * Xml 转码.
//     */
//    public static String escapeXml(String xml) {
//        return StringEscapeUtils.escapeXml10(xml);
//    }
//
//    /**
//     * Xml 解码.
//     */
//    public static String unescapeXml(String xmlEscaped) {
//        return StringEscapeUtils.unescapeXml(xmlEscaped);
//    }
//
//    /**
//     * URL 编码, Encode默认为UTF-8.
//     */
//    public static String urlEncode(String part) {
//        try {
//            return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
//        } catch (UnsupportedEncodingException e) {
//            throw Exceptions.unchecked(e);
//        }
//    }
//
//    /**
//     * URL 解码, Encode默认为UTF-8.
//     */
//    public static String urlDecode(String part) {
//
//        try {
//            return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
//        } catch (UnsupportedEncodingException e) {
//            throw Exceptions.unchecked(e);
//        }
//    }
//
//    public static String md5(String value) {
//        return SecureUtil.md5(value);
//    }
//
//    /**
//     * 生成固定的秘钥
//     * @param key
//     * @return
//     * @throws NoSuchAlgorithmException
//     */
//    public static SecretKeySpec generateKey(String key) throws NoSuchAlgorithmException {
//        KeyGenerator kgen = KeyGenerator.getInstance(ENCRYPT_AES);
//        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//        random.setSeed(key.getBytes());
//        kgen.init(128, random);
//        SecretKey secretKey = kgen.generateKey();
//        byte[] enCodeFormat = secretKey.getEncoded();
//        return new SecretKeySpec(enCodeFormat, ENCRYPT_AES);
//    }
//
//
//    /**
//     * 加密
//     */
//    public static String encrypt(String encryptStr, String secretKey) {
//        try {
//            SecretKeySpec key = generateKey(secretKey);
//            Cipher cipher = Cipher.getInstance(ENCRYPT_CIPHER, ENCRYPT_CIPHER_BC);// 创建密码器
//            byte[] byteContent = encryptStr.getBytes(CHARSET);
//            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
//            byte[] result = cipher.doFinal(byteContent);
//            return parseByte2HexStr(result); // 加密
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (NoSuchProviderException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 解密
//     * @param descryptStr  待解密内容
//     * @param secretKey 解密密钥
//     */
//    public static String decrypt(String descryptStr, String secretKey) {
//        try {
//            SecretKeySpec key = generateKey(secretKey);
//            Cipher cipher = Cipher.getInstance(ENCRYPT_CIPHER, ENCRYPT_CIPHER_BC);// 创建密码器
//            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
//            byte[] result = cipher.doFinal(parseHexStr2Byte(descryptStr));
//
//            return  new String(result); // 解密
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (NoSuchProviderException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    /**
//     * 将二进制转换成16进制
//     * @method parseByte2HexStr
//     */
//    public static String parseByte2HexStr(byte buf[]){
//        StringBuffer sb = new StringBuffer();
//        for(int i = 0; i < buf.length; i++){
//            String hex = Integer.toHexString(buf[i] & 0xFF);
//            if (hex.length() == 1) {
//                hex = '0' + hex;
//            }
//            sb.append(hex.toUpperCase());
//        }
//        return sb.toString();
//    }
//
//    /**
//     * 将16进制转换为二进制
//     * @method parseHexStr2Byte
//     */
//    public static byte[] parseHexStr2Byte(String hexStr){
//        if(hexStr.length() < 1)
//            return null;
//        byte[] result = new byte[hexStr.length()/2];
//        for (int i = 0;i< hexStr.length()/2; i++) {
//            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
//            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
//            result[i] = (byte) (high * 16 + low);
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        System.out.println(Encodes.md5(
//                "email=wufushun@sun-tech.cn&address=0x6186ad2d6d9e1fcb8e62eba2cb7c6f511d36aa0b&secret=50d334c412f1a1bd4bcb9359ccbe4d57"));
//    }
//}
