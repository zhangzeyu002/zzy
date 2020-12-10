package com.xxxx.crm.ExceptionResolver;
import com.alibaba.fastjson.JSON;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 全局异常统⼀处理
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    /**
     * ⽅法返回值类型
     * 视图
     * JSON
     * 如何判断⽅法的返回类型：
     * 如果⽅法级别配置了 @ResponseBody 注解，表示⽅法返回的是JSON；反之，返回的是视
     图⻚⾯
     * @param request
     * @param response
     * @param handler
     * @param e
     * @return
     */

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        //默认异常处理
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("");
        modelAndView.addObject("code",400);
        modelAndView.addObject("msg","系统异常，请稍后再试");
        if (handler instanceof HandlerMethod){
            //类型转换，否则我们取不到我们想要的@responsebody
            HandlerMethod handlerMethod=(HandlerMethod) handler;
            // 获取⽅法上的 ResponseBody 注解
            ResponseBody responseBody =
                    handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            if (responseBody==null){
                /**
                 * ⽅法返回视图
                 */
                if(e instanceof ParamsException){
                    ParamsException pe = (ParamsException) e;
                    modelAndView.addObject("code", pe.getCode());
                    modelAndView.addObject("msg", pe.getMsg());
                }
                return modelAndView;
            }
            else
            {
                /**
                 * ⽅法上返回JSON
                 */
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统异常，请重试！");
                // 如果捕获的是⾃定义异常
                if (e instanceof ParamsException) {
                    ParamsException pe = (ParamsException) e;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());
                }
                // 设置响应类型和编码格式 （响应JSON格式）
                response.setContentType("application/json;charset=utf-8");
                // 得到输出流
                PrintWriter out = null;
                try {
                    out = response.getWriter();
                    // 将对象转换成JSON格式，通过输出流输出
                    out.write(JSON.toJSONString(resultInfo));
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
                return null;
            }
        }
        return modelAndView;
    }
}
