package com.attencecheckin.javabackend.common.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLUtil {

	private static final String GLOBAL_SUBFIELDSIGN_NEW = "▼";// ▼▲□△▽

	/**
	 * 过虑xml的无效字符。
	 * <p/>
	 * <ol>
	 * <li>0x00 - 0x08</li>
	 * <li>0x0b - 0x0c</li>
	 * <li>0x0e - 0x1f</li>
	 * </ol>
	 */
	public static String filter(String xmlStr) {
		StringBuilder sb = new StringBuilder();
		char[] chs = xmlStr.toCharArray();
		// System.out.println("filter before=" +chs.length);
		for (char ch : chs) {
			if (ch == 0x1f) { // MARC中的子字段分割符，如果不处理解析的时候会报错。
				sb.append(GLOBAL_SUBFIELDSIGN_NEW);
			} else if ((ch >= 0x00 && ch <= 0x08) || (ch >= 0x0b && ch <= 0x0c)
					|| (ch >= 0x0e && ch <= 0x1f)) {
				// eat...
			} else {
				sb.append(ch);
			}
		}
		// System.out.println("filter after=" +sb.length());
		return sb.toString();
	}

	/**
	 * 将xml格式的字符串转换成Map对象
	 * 
	 * @param xmlStr
	 *            xml格式的字符串
	 * @return Map对象
	 * @throws Exception
	 *             异常
	 */
	public static Map<String, Object> xmlStrToMap(String xmlStr)
			throws Exception {
		if (xmlStr == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Document doc = DocumentHelper.parseText(xmlStr);
		Element root = doc.getRootElement();
		List children = root.elements();
		if (children != null && children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				Element child = (Element) children.get(i);
				List list = child.elements();
				if(list!=null&&list.size()>0) {
					List tmpList = new ArrayList();
					Map<String, Object> map0 = new HashMap<String, Object>();
					for (int j = 0; j < list.size(); j++) {
						Element c = (Element) list.get(j);
						List l1 = c.elements();
						if(l1!=null&&l1.size()>0) {
							Map<String, Object> map1 = new HashMap<String, Object>();
							for (int k = 0; k < l1.size(); k++) {
								Element c1 = (Element) l1.get(k);
								map1.put(c1.getName(), c1.getTextTrim());
							}
							tmpList.add(map1);
						} else {
							map0.put(c.getName(), c.getTextTrim());
						}
					}
					if(tmpList.size()>0) {
						map.put(child.getName(), tmpList);
					} else {
						map.put(child.getName(), map0);
					}
				} else {
					map.put(child.getName(), child.getTextTrim());
				}
			}
		}
		return map;
	}
}
