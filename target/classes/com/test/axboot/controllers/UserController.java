package com.test.axboot.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.test.axboot.domain.user.User;
import com.test.axboot.domain.user.UserService;
import com.test.axboot.domain.user.UserVO;

@Controller
@RequestMapping(value = "/api/v1/users")
public class UserController extends BaseController {

    @Inject
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<User> requestParams) {
        List<User> users = userService.get(requestParams);
        return Responses.ListResponse.of(users);
    }
    
    /*@RequestMapping(value = "/parent", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse parentList(Pageable pageable) {
        Page<User> pages = userService.findAll(pageable);
        return Responses.PageResponse.of(UserVO.of(pages.getContent()), pages);
    }*/
    
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON, params = "userCd")
    public User get(RequestParams requestParams) {
        return userService.getUser(requestParams);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = APPLICATION_JSON)
    public ApiResponse save(@Valid @RequestBody List<User> users) throws Exception {
        userService.saveUser(users);
        return ok();
    }
}
