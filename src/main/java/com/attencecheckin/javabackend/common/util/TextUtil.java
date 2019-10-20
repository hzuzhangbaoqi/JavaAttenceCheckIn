package com.attencecheckin.javabackend.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextUtil {

	public static int getNumber(String param) {
		int value = 0;
		if (isNotNull(param)) {
			try {
				value = Integer.parseInt(param);
			} catch (Exception e) {
			}
		}
		return value;
	}

	public static String getText(String value) {
		if (!isNotNull(value)) {
			return "";
		}
		return value;
	}

	public static String filterSignForParam(String param) {
		if(param == null) {
			return null;
		}
		try {
			String marcSignFilter = "";
//			String marcSignFilter = LibraryConfig.getMarcSignFilter();
	//		if(!TextUtil.isNotNull(marcSignFilter)) {
	//			marcSignFilter = Constants.DATA_FILTER_SIGN;
	//		}
			if(TextUtil.isNotNull(marcSignFilter)) {
				marcSignFilter = "["+marcSignFilter.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]")+"]";
				//System.out.println(marcSignFilter);
				param = param.replaceAll(marcSignFilter, "");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return param;
	}
	
	public static boolean isNotNull(String param) {
		if (param != null && !"".equals(param.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTrue(String value) {
		if (value != null && value.trim().equals("1")) { // ||value.equalsIgnoreCase("true")
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTrue(Integer value) {
		if (value != null && value == 1) { // ||value.equalsIgnoreCase("true")
			return true;
		} else {
			return false;
		}
	}

	public static String wrap(String param, String value) {
		if (isNotNull(param)) {
			return param.trim();
		} else {
			return value;
		}
	}

	public static String wrap(Object obj, String value) {
		if (obj != null) {
			return obj.toString();
		} else {
			return value;
		}
	}

	public static Integer wrap(Integer val, Integer defaultVal) {
		return val == 0 ? defaultVal : val;
	}

	public static String wrap(String param) {
		String val = wrap(param, "");
		val = val.replace("\'", "chr(39)");
		return val;
	}

	public static String wrapJsQuot(String val) {
		val = wrap(val, "");
		val = val.replace("'", "\\'");
		// val = val.replace("\"", "\\\"");
		return val;
	}

	public static String wrapSqlQuot(String val) {
		val = wrap(val, "");
		val = val.replace("'", "''");
		val = val.replace("%", "/%");
		return val;
	}

	public static String warpEmpty(String val, int length, String fillVal) {
		val = (val == null) ? "" : val.replaceAll(" ", fillVal);
		if (val.length() > length) {
			val = val.substring(0, 2);
		} else {
			while (val.length() < length) {
				val = val + fillVal;
			}
		}
		return val;
	}

	public static String warpEmpty(String val, int length) {
		return TextUtil.warpEmpty(val, length, " ");
	}

	public static String warpEmpty(String val) {
		return warpEmpty(val, 2);
	}

	public static String WarpEmpty(String oldVal, String newVal, String val) {
		if (TextUtil.isNotNull(newVal)) {
			val = newVal;
		} else if (TextUtil.isNotNull(oldVal)) {
			val = oldVal;
		}
		return warpEmpty(val);
	}

	public static String WarpEmpty(String oldVal, String newVal) {
		return WarpEmpty(oldVal, newVal, "  ");
	}

	public static String concatSqlInValue(String values) {
		if (TextUtil.isNotNull(values)) {
			int length = values.length();
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < length; i++) {
				String tmpStr = (i == length - 1) ? values.substring(i)
						: values.substring(i, i + 1);
				if (!tmpStr.trim().equals("") && !tmpStr.equals("'")
						&& !tmpStr.equals("\"")) {
					if (result.length() > 0) {
						result.append(",");
					}
					result.append("'" + tmpStr + "'");
				}
			}
			values = result.toString();
		} else {
			values = null;
		}
		return values;
	}

	public static String trim(String values) {
		return trim(values, ",");
	}

	public static String trim(String values, String sperator) {
		values = TextUtil.isNotNull(values) ? values : "";
		String[] arrValues = values.split(" ");
		int length = arrValues.length;
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < length; i++) {
			String value = arrValues[i];
			System.out.println("|" + value + "|");
			if (value.trim().equals("")) {
				continue;
			} else {
				if (result.length() > 0) {
					result.append(sperator);
				}
				result.append(value);
			}
		}
		values = result.toString();
		return values;
	}

	public static double scannerNumber(String price) {
		// String price = "CNY04.23";
		double price_number = 0;
		Scanner sc = null;
		try {
			sc = new Scanner(price).useDelimiter("[^0-9.]+");
			price_number = sc.nextDouble();
			System.out.println(price_number);
		} catch (Exception e) {
			try {
				sc = new Scanner(price).useDelimiter("[^0-9]+");
				price_number = sc.nextDouble();
				System.out.println(price_number);
			} catch (Exception e1) {
			}
		}
		return price_number;
	}

	public static String getContainNumber(String temp_price) {
		temp_price = temp_price.replace('(', ' ');
		temp_price = temp_price.replace(')', ' ');
		String temp = "0.00";
		if (temp_price == null || temp_price.equals("")) {
			return temp;
		} else {
			List<String> digitList = new ArrayList<String>();
			String[] arr = temp_price.split("[^\\+?\\d+(\\.\\d{1,2})?]+");
			for (String str : arr) {
				if (str.length() > 0)
					digitList.add(str);
			}
			if (digitList.size() > 0) {
				temp = digitList.get(0);
			}
			return temp;
		}
	}

	public static String replaceStr(String in) {
		in = in.replaceAll("&", "&amp;");
		in = in.replaceAll("<", "&lt;");
		in = in.replaceAll(">", "&gt;");
		return in;
	}

	public static String htmlString(String orgstr) {
		String retStr = "";
		if (TextUtil.isNotNull(orgstr)) {
			retStr = orgstr;
			retStr = retStr.replaceAll("&", "&amp;");
			retStr = retStr.replaceAll("<", "&lt;");
			retStr = retStr.replaceAll(">", "&gt;");
			retStr = retStr.replaceAll("\"", "&quot;");
		}
		return retStr;
	}

	public static void main(String[] args) {
		// String tmpValue = " 中  dd个  fds 	fds.... dfgd";
		// System.out.println(tmpValue);
		// System.out.println(concatSqlInValue(tmpValue));
		// System.out.println(trim(tmpValue));

		/*
		 * String st = "1986-1-1"; try { DateFormat df = new
		 * SimpleDateFormat("yyyy-MM-dd");
		 * System.out.println(df.format(df.parse(st))); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		//LibraryConfig.setMarcSignFilter("．·，：。,.:;[]");
		System.out.println(TextUtil.filterSignForParam("．梧·，志：[府【州】]。,.什么:;；{}[[崇祯]]]测试"));
		System.out.println(wrapJsQuot("'"));

		System.out.println("小数为:" + scannerNumber("CNYf"));

	}
}
