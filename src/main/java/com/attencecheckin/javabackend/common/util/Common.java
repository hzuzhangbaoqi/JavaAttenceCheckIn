/*
 * 方法来源Interlib com.libwan.utils.Common
 */

package com.attencecheckin.javabackend.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Common {

	public static boolean isDigitS(String s) {
		for (int i = 0; i < s.length(); i++)
			if (!Character.isDigit(s.charAt(i)))
				return (false);
		return (true);
	}

	public static Vector str2vector(String str, String del) {
		Vector retV = new Vector();
		// StringTokenizer st = new StringTokenizer(str,del);
		String strA[] = str.split(del);
		String tmpS = "";
		boolean ins = false;
		// 这里因为在marcUtil中判断头标区的时候000必须在第一位，下面这个排序当字段名的首字母包含诸如减号等ascII码小于000的就会出错
		// 而导致整条MARC丢失，这里应该不管什么情况，始终把第一个000放在第一位。因为有些字段名为000和头标区的000表示有些冲突。
		// 处理方式:凡是非数字的字段名全部往后面靠.凡是数字的才进行比较排序.还是不处理,直接保持原样呢,这个比较罗嗦.
		//
		String fieldName = "";

		for (int len = 0; len < strA.length; len++) {
			tmpS = strA[len];// st.nextToken();
			fieldName = tmpS.substring(0, 3);
			ins = false;
			if (isDigitS(fieldName)) {// 这里仅对属于数字的进行这样处理，对于非数字的不这样处理目的是始终使000字段在最前面
				for (int i = 0; i < retV.size(); i++) {
					if (((String) retV.get(i)).substring(0, 3).compareTo(
							fieldName) > 0) {
						retV.insertElementAt(tmpS, i);
						ins = true;
						break;
					}
				}
			}
			if (!ins)
				retV.add(tmpS);
		}
		return retV;
	}
	public static String Filter2Bib(String paramString1, String paramString2){
	    paramString2 = "\"" + paramString2 + "\"";
	    if (paramString1.indexOf("title") != -1)
	      return "@attrset bib-1 @attr 1=4 " + paramString2;
	    if (paramString1.indexOf("isbn") != -1)
	      return "@attrset bib-1 @attr 1=7 " + paramString2;
	    if (paramString1.indexOf("issn") != -1)
	      return "@attrset bib-1 @attr 1=8 " + paramString2;
	    if (paramString1.indexOf("author") != -1)
	      return "@attrset bib-1 @attr 1=1003 " + paramString2;
	    if (paramString1.indexOf("classno") != -1)
	      return "@attrset bib-1 @attr 1=20 " + paramString2;
	    if (paramString1.indexOf("ctrlno") != -1)
	      return "@attrset bib-1 @attr 1=12 " + paramString2;
	    if (paramString1.indexOf("orderno") != -1)
	      return "@attrset bib-1 @attr 1=4 " + paramString2;
	    if (paramString1.indexOf("publisher") != -1)
	      return "@attrset bib-1 @attr 1=1018 " + paramString2;
	    if (paramString1.indexOf("pubdate") != -1)
	      return "@attrset bib-1 @attr 1=31 " + paramString2;
	    if (paramString1.indexOf("unino") != -1)
	      return "@attrset bib-1 @attr 1=4 " + paramString2;
	    if (paramString1.indexOf("subject") != -1)
	      return "@attrset bib-1 @attr 1=21 " + paramString2;
	    return "-1";
	  }
	public static String Filter2Bib(String paramString1, String paramString2, String paramString3, String paramString4){
		String bib1 = "@attrset bib-1 @and ";
		if(TextUtil.isNotNull(paramString4)){
	    	bib1 += getExpression(paramString1,paramString2)+" "+getExpression(paramString3, paramString4);
	    }else{
	    	bib1 = Filter2Bib(paramString1, paramString2);
	    }
		return bib1;
	 }
	public static String getExpression(String paramString1, String paramString2){
	    paramString2 = "\"" + paramString2 + "\"";
	    if (paramString1.indexOf("title") != -1)
	      return "@attr 1=4 " + paramString2;
	    if (paramString1.indexOf("isbn") != -1)
	      return "@attr 1=7 " + paramString2;
	    if (paramString1.indexOf("issn") != -1)
	      return "@attr 1=8 " + paramString2;
	    if (paramString1.indexOf("author") != -1)
	      return "@attr 1=1003 " + paramString2;
	    if (paramString1.indexOf("classno") != -1)
	      return "@attr 1=20 " + paramString2;
	    if (paramString1.indexOf("ctrlno") != -1)
	      return "@attr 1=12 " + paramString2;
	    if (paramString1.indexOf("orderno") != -1)
	      return "@attr 1=4 " + paramString2;
	    if (paramString1.indexOf("publisher") != -1)
	      return "@attr 1=1018 " + paramString2;
	    if (paramString1.indexOf("pubdate") != -1)
	      return "@attr 1=31 " + paramString2;
	    if (paramString1.indexOf("unino") != -1)
	      return "@attr 1=4 " + paramString2;
	    if (paramString1.indexOf("subject") != -1)
	      return "@attr 1=21 " + paramString2;
	    return "-1";
	  }
	/**
	 * 生成的tranid首尾带单引号
	 * @return
	 */
	public static String getCirfinTranID(){
		return "'" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())  + "'";
		//or return CIR_FIN_TRANID_SQ.nextval()
	}
}
