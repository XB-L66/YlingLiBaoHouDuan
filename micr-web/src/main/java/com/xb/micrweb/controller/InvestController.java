package com.xb.micrweb.controller;

import com.xb.common.Util.CommonUtil;
import com.xb.common.constants.RedisKey;
import com.xb.micrweb.view.Result;
import com.xb.micrweb.view.invest.RankView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Api(tags = "投资理财产品")
public class InvestController extends BaseController{
    @GetMapping("/v1/invest/rank")
    @ApiOperation(value = "投资排行榜",notes = "显示投资金额最高的3位用户信息")
    public Result showInvestRank(){
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringRedisTemplate.boundZSetOps(RedisKey.KEY_INVEST_RANK).
                reverseRangeWithScores(0, 2);

        List<RankView> list=
        typedTuples.stream().map((item)->{
            RankView rankView=new RankView();
            rankView.setPhone(CommonUtil.tuoMinPhone(item.getValue()));
            rankView.setMoney(item.getScore());
            return rankView;
        }).collect(Collectors.toList());
        Result result=new Result();
        result.setList(list);
        return result;
    }
}
