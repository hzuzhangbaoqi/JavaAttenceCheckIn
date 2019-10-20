package com.attencecheckin.javabackend.common.util;

public class OperateManager {
	// 操作类型
	public static final int OPERATOR_NO = -1;
	public static final int OPERATOR_ADD = 0;
	public static final int OPERATOR_DELETE = 1;
	public static final int OPERATOR_MODIFY = 2;
	public static final int OPERATOR_QUERY = 3;
	// 结果类型
	public static final int RESULT_FAIL = 0;
	public static final int RESULT_SUCCESS = 1;
	// 失败类型
	public static final int FAIL_PRIVILEGE = 0;
	public static final int FAIL_EXCEPTION = 1;
	public static final int FAIL_OLDPWD = 2;
	public static final int FAIL_LIBPRIV = 3;

	private int operatorType;
	private int resultType;
	private int failType;
	private String message;

	public OperateManager() {

	}
	
	public OperateManager(int operatorType) {
		this.operatorType = operatorType;
	}

	public OperateManager(int operatorType, int resultType, int failType) {
		this.operatorType = operatorType;
		this.resultType = resultType;
		this.failType = failType;
	}
	
	public int getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(int operatorType) {
		this.operatorType = operatorType;
	}

	public int getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

	public int getFailType() {
		return failType;
	}

	public void setFailType(int failType) {
		this.failType = failType;
	}

	public String getMessage() {
		return TextUtil.wrap(message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
