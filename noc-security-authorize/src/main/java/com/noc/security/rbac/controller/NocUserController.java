package com.noc.security.rbac.controller;

import com.noc.security.rbac.dataobject.ResultVo;
import com.noc.security.rbac.dataobject.User;
import com.noc.security.rbac.service.UserService;
import com.noc.security.rbac.utils.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noc/user")
@CrossOrigin
public class NocUserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResultVo list(@PageableDefault Pageable pageable) {
        return ResultVoUtil.success(userService.findAll(pageable));
    }

}
