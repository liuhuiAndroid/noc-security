package com.noc.security.rbac.utils;

import com.noc.security.rbac.dataobject.ResultVo;

public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return resultVo;
    }
}
