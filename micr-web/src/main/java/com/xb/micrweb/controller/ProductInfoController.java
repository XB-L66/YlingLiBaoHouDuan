package com.xb.micrweb.controller;

import com.xb.api.model.BidInfoProduct;
import com.xb.api.model.ProductInfo;
import com.xb.api.vo.MultiProductVo;
import com.xb.common.Util.CommonUtil;
import com.xb.common.enums.RCode;
import com.xb.micrweb.view.PageInfo;
import com.xb.micrweb.view.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Api(tags = "理财产品功能")
public class ProductInfoController extends BaseController{
    @GetMapping("/product/index")
    @ApiOperation(value = "首页三页产品列表",notes ="一个新手宝，三个优选，三个散标")
    public Result queryProductIndex(){
        Result result= Result.ok();
        MultiProductVo multiProductVo = productInfoService.queryIndexPageProducts();
        result.setData(multiProductVo);
        return result;
    }
    @ApiOperation(value = "产品分页查询",notes="按照产品类型进行分类")
    @GetMapping("/product/list")
    public Result queryProductByType(@RequestParam("ptype") Integer pType,
                                     @RequestParam(value = "pageNo",required = false,defaultValue ="1")Integer pageNo,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "9")Integer pageSize){
        Result result=Result.fail();
        if(pType!=null&&(pType==0||pType==1|| pType==2)){
            pageNo= CommonUtil.defaultPageNo(pageNo);
            pageSize=CommonUtil.defaultPageSize(pageSize);
            Integer count = productInfoService.queryRecordNumsByType(pType);
            if(count>0){
                List<ProductInfo> productInfos = productInfoService.queryByTypeLimit(pType, pageNo, pageSize);
                result=Result.ok();
                result.setList(productInfos);
                PageInfo pageInfo=new PageInfo(pageNo,pageSize,count);
                result.setPage(pageInfo);

            }
        }else{
            result.setRCode(RCode.REQUEST_PRODUCT_TYPE_ERR);
        }
        return result;
    }
    @ApiOperation(value = "产品详情",notes = "查询某个产品的详细信息和投资5条记录")
    @GetMapping("/product/info")
    public Result queryProductDetail(@RequestParam("productId")Integer id){
        Result result=Result.fail();
           if(id!=null&&id>0){
            ProductInfo productInfo = productInfoService.queryById(id);
           if(productInfo!=null){
               List<BidInfoProduct> bidInfoList = investService.queryBidListByProductId(id,1,5);
               result=Result.ok();
               result.setData(productInfo);
               result.setList(bidInfoList);
           }else{
               result.setRCode(RCode.PRODUCT_OFFLINE);
           }
        }
        return result;
    }
}
