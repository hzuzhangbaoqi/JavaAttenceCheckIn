package com.attencecheckin.javabackend.common.util;

public class NumberUtil {

	public static int fillNull(Integer param) {
		if (param == null) {
			return 0;
		}
		return param.intValue();
	}

	public static void main(String[] args) {

	}
}
