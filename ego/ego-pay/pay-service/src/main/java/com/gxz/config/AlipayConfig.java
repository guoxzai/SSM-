package com.gxz.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 支付宝网关      测试环境
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092600600347";

    // 商户私钥，您的PKCS8格式RSA2私钥 使用工具生成的
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCxrS+LJM96kJW6Vm4+so5rWBD5yRzUTQ44toDfxalzR/u1R7XmvmEOB29XrAXeC3L6hq84HoyUBiXIM9fCwqjkEWsqiQX2eZHWDhf9FWFKqrqzlrDXMBDcTfbnNawAOy0KvnViQLNit4XntTJmzQvazs/Gu2bRTxWyyUskeODqBN2z4VMdeBuG0newDo8Exmcqp2A3CW0Z5WTKXSi3jfczNqdPgpqHfKA0dtamTPI+ab9detTKLTa3EEZ+wQxP7yCcReHh7uPQvu0RhriLLlbDxl9zK5BGNKkeqMpyuj1GzSDq0Xl1RtmsZV2d+uUmuE62uKk/36eKsGY8V5Xn6gRrAgMBAAECggEAX5cYXBj60ysUkwLLH0o7Wz8vKuPrccDfJN3zafDTIKWao0YodDL6zmxrl9SB95tbSbPKczvkUAMasqJJs4u9B67jZCgCID/5mRtOBqhJBr3h4prxG2TNvWjorC3xEtLDgeNEDg9XM4YfMbEHo80vUpIvsPDD+Vo+B0lK9x1djCkYmVCiTtvQAqlX/YvdmlQlBnNHj8axh4CARSyC9AdO6Xmv9qSAHDUc8d2ofNV+DAfZfpDBGI1N7Dza1LGsnflU2t8M/xB/I2bSmbalG46CywN6c3jOfwBLXeCG/xLRKXRLZRk/oknKi9jrgymc1LLPwOdntCqhD34AtyIBziEdiQKBgQDy/TNo6f4lbAIr+bWWkba8sNSKouh+/747zch5/mLAXOQK2wAM3RcPeddgODr8C3KxEZ3bph33FIYj7xg8GsyUaWTSabG3CLrBbhx2LLMGkZOH1S+9T8IptcyDAse3zuota6DmK2t1VzUukRfI/Tc07KCXnFG491rgQ9YytrlZHwKBgQC7MLSy7h2Mx5cvzbLTei+FzkTW6Crwr3zm1K+EQJzT7Z4S/u+3uIvDFMP+w+Al3GBb8ermwx0bBBgUEob9yrl+8l6pahldSFb0kCiGAcgbC4UHRL4HmmZA8/sUSiDitNfFX/qsxsO5RPb5qbFH5cBZ97JJNtyIeCv/qfhQ27ZPNQKBgFiIFGEXUDfGQIxRMO4pLRFWxDm23UrYVzFm6rI4EzbPIW8rF7uYGjzTQbuXATav3RvStVdAEQ7i9XTGOmg7w2oXdFqNk+iCT7kGK2yLILc19zC4Zept0upriJX7P1UjGINwAXGbRE2+dmv/BVF/oMuxo1lZ5RSlWwD29mYH2UQ1AoGBALfqv+/54pC6XcI7IZOEvoKCSsK4M9BtK5QErjqL3UqgcaqKTu885B0VHwkGXQn1/7r28uwuEj/EAwBdv8JF14FyEy3zL96mP8Fkjlw0De78n4SntunzerogvbWOq8bzFfk4Nyxg0lcK+noMj0Fr7FS4+LPUslNndxtoXbDcXsBtAoGBAK9LsGDeIf43iIZVv/kGzLLEDbud1VEqGqIHIZPjmVYgHHhWeSKRbs/ocIZMfUrz/vlglnqIGZeULp7a+PVVnwEbr0xwlTveTRSFlrwMmbyIW5x1EcreKeBsIY6PgO8SBEQMHx5DeUXLFskhaCt1625mNm9I0516sYCTCHwnNxIM";

    // 载网页上看，不是工具生成的 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4YszXqlqfySCq/hkUaP9kdX9+TKTrDDhucHBw0PEZM7Usvf1gU7uIPHSheJxLLV5s/rd7OmsavfZLyFmb831ia5fo3ENCQdU87JO0ABQtxxRaq2w8PheA82C8P1bTs/19xYMPB9XBbzi3LqEYCpJ/+MnUeHGChzAUH/axq3/tfFyy4gPpgj2KMChVFeRAV2+FcsQR6B+9jHhII65hMNOyv1iCvwJk+TsmJcb7Gz6IJ8rfvWFEUqjgAkWfIcfBbgn0kdSSxQImoTjifhg5RGt6+PsvUBXjT5EiAFGUbdTaL8OsKerMfztYbUAr8Fw3KKqXDihdwBIpnyamW85JnkkowIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8083/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";


    // 支付宝网关
    public static String log_path = "C:\\";


    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

