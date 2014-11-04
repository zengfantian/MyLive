package cn.com.hoonsoft.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * 字符串工具类
 * @author 曾繁添
 *
 */
public class ToolString {

	/**
	 * 获取UUID
	 * @return 32UUID小写字符串
	 */
	public static String gainUUID(){
		String strUUID = UUID.randomUUID().toString();
		strUUID = strUUID.replaceAll("-", "").toLowerCase();
		return strUUID;
	}
	
	/**
	 * 判断字符串是否非空非null
	 * @param strParm 需要判断的字符串
	 * @return 真假
	 */
    public static boolean isNoBlankAndNoNull(String strParm)
    {
      return (strParm == null) || (strParm.equals(""));
    }
    
    /**
     * 将流转成字符串
     * @param is 输入流
     * @return
     * @throws Exception
     */
    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    /**
     * 将文件转成字符串
     * @param file 文件
     * @return
     * @throws Exception
     */
    public static String getStringFromFile(File file) throws Exception {
        FileInputStream fin = new FileInputStream(file);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }
	
}
