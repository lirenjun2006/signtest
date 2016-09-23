package com.li.javatest;

import org.apache.commons.lang.StringUtils;

import com.facishare.pay.common.pay.SignCounter;

import java.util.*;

/**
 * 加密解密方法
 *
 * Created by wangtao on 2016/6/17.
 */
public class SignUtils {
//    private static final Logger logger = LoggerFactory.getLogger(SignUtils.class);
    private static final String MD5_KEY = "qjdi3u4rcu2czbpyfj8clg0esoz1v5ri";

    /**
     * 验证加密算法
     * @param param
     * @param sign
     * @return
     */
    public static boolean checkSign(Map<String, String> param, String sign) {
        boolean result = false;
        try{
            String sourceSign = sign(param);

            if(sourceSign.equals(sign)){
                result = true;
            }
        }catch (Exception ex){
        	System.out.println(ex.toString());
        }
        return result;
    }


    /**
     * 加密
     * @param param
     * @return
     */
    public static String sign(Map<String, String> param) {
        String sign = "";
        try{
            String preStr = createLinkString(param);
            sign = MD5.sign(preStr, MD5_KEY, "UTF-8");
        }catch (Exception ex){
        	System.out.println(ex.toString());
        }
        return sign;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if(StringUtils.isNotBlank(value)){
                if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                    prestr = prestr + key + "=" + value;
                } else {
                    prestr = prestr + key + "=" + value + "&";
                }
            }
        }

        // 去掉最后一个&
        if (prestr.endsWith("&")) {
            prestr = prestr.substring(0, prestr.length() - 1);
        }

        return prestr;
    }

    public static void main(String[] args) {


        Map<String, String> map = new HashMap();
        map.put("amount", String.valueOf(1));
        map.put("merchantCode", "20160603100001");
        map.put("goodsId", "1001");
        map.put("merchantOrderNo", "1000");
        String sign = "3108de06fc5342eebac9cef1ccbe89ed";
        String companysign = sign(map);
        System.out.println(companysign);
//        System.out.println(checkSign(map, sign));
//        String personSign = SignCounter.countSign(amount, merchantOrder, limitPayer, fromEa, fromUid, toEa, toUid, merchantId, expireTime, token)
//        String personSign = SignCounter.countSign("0.24", "1029", "0", "149", "1278", "149", "1000", "2015122210001", "1461564216568", "gduuz5o86n");
//        String personSign = SignCounter.countSign(amount, merchantOrder, limitPayer, fromEa, fromUid, merchantId, expireTime, token)
        String personSign = SignCounter.countSign("0.50", "1073", "1", "149", "1000", "2015122210001", "123", "gduuz5o86n");
        System.out.println(personSign);
    }

}
