package com.attencecheckin.javabackend.handler;

import com.attencecheckin.javabackend.common.JsonResult;
import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import com.attencecheckin.javabackend.handler.exception.BusinessErrorException;
import com.attencecheckin.javabackend.common.enumer.ResultEnum;
import com.attencecheckin.javabackend.handler.exception.BusinessErrorException;
import com.attencecheckin.javabackend.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: javabackend
 * @description: 全局异常处理
 * @author: zxf
 * @create: 2019-05-21 18:31
 **/
//拦截项目中抛出的异常
@ControllerAdvice
//异常处理完之后给调用方输出一个 JSON 格式的封装数据
@ResponseBody
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 缺少请求参数异常
     * @param ex HttpMessageNotReadableException
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonResult handleHttpMessageNotReadableException(
            MissingServletRequestParameterException ex) {
        logger.error("缺少请求参数，{}", ex);
        return new JsonResult(ResultEnum.PARAMS_LACK.val(), ResultEnum.PARAMS_LACK.msg());
    }
    /**
     * 空指针异常
     * @param ex NullPointerException
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleTypeMismatchException(NullPointerException ex) {
        logger.error("空指针异常，{}", ex);
        return new JsonResult(ResultEnum.EXCEPTION_NullPointer.val(), ResultEnum.EXCEPTION_NullPointer.msg());
    }
    /**
     * 系统异常 预期以外异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleUnexpectedServer(Exception ex) {
        logger.error("系统异常：", ex);
        JsonResult<Exception> jsonResult = new JsonResult(ResultEnum.EXCEPTION.val(), ResultEnum.EXCEPTION.msg());
        jsonResult.setData(ex);
        return jsonResult;
    }
    /**
     * 拦截业务异常，返回业务异常信息
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleBusinessError(BusinessErrorException ex) {
        logger.error("自定义异常：", ex);
        String code = ex.getCode();
        String message = ex.getMessage();
        return new JsonResult(code, message);
    }
}
