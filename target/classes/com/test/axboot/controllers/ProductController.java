package com.test.axboot.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chequer.axboot.core.api.ApiException;
import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.test.axboot.domain.prdt.Product;
import com.test.axboot.domain.prdt.ProductService;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;

@Controller
@RequestMapping(value = "/api/v1/product")
public class ProductController extends BaseController {

    @Inject
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "prdtCd", value = "제품코드", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "prdtNm", value = "제품명", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "filter", value = "검색어", dataType = "String", paramType = "query")
})    
    public Responses.ListResponse list(RequestParams<Product> requestParams) {
        List<Product> list = productService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<Product> request) {
        //productService.save(request);
    	//중복체크 후 중복발생 시 Exception핸들러로 받는다.
        //ApiException 및 handleApiException는 Axboot에서 제공하는 class이다.
     	try
     	{
            productService.savePrdt(request);
     	}
     	catch(ApiException e)
     	{
     		return handleApiException(e);
     	}
        return ok();
    }
}