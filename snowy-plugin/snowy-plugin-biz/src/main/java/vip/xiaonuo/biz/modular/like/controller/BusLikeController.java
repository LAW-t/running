/*
 * Copyright [2022] [https://www.xiaonuo.vip]
 *
 * Snowy采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：
 *
 * 1.请不要删除和修改根目录下的LICENSE文件。
 * 2.请不要删除和修改Snowy源码头部的版权声明。
 * 3.本项目代码可免费商业使用，商业使用请保留源码和相关描述文件的项目出处，作者声明等。
 * 4.分发源码时候，请注明软件出处 https://www.xiaonuo.vip
 * 5.不可二次分发开源参与同类竞品，如有想法可联系团队xiaonuobase@qq.com商议合作。
 * 6.若您的项目无法满足以上几点，需要更多功能代码，获取Snowy商业授权许可，请在官网购买授权，地址为 https://www.xiaonuo.vip
 */
package vip.xiaonuo.biz.modular.like.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import vip.xiaonuo.auth.core.util.StpLoginUserUtil;
import vip.xiaonuo.biz.modular.like.entity.BusLike;
import vip.xiaonuo.biz.modular.like.param.BusLikeAddParam;
import vip.xiaonuo.biz.modular.like.param.BusLikeEditParam;
import vip.xiaonuo.biz.modular.like.param.BusLikeIdParam;
import vip.xiaonuo.biz.modular.like.param.BusLikePageParam;
import vip.xiaonuo.biz.modular.like.service.BusLikeService;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.common.pojo.CommonValidList;

/**
 * BUS_LIKE控制器
 *
 * @author twh
 * @date 2023/03/02 14:48
 */
@Api(tags = "BUS_LIKE控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class BusLikeController {

    @Resource
    private BusLikeService busLikeService;

    /**
     * 获取BUS_LIKE分页
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取BUS_LIKE分页")
    @SaCheckPermission("/biz/like/page")
    @GetMapping("/biz/like/page")
    public CommonResult<Page<BusLike>> page(BusLikePageParam busLikePageParam) {
        return CommonResult.data(this.busLikeService.page(busLikePageParam));
    }

    /**
     * 添加BUS_LIKE
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加BUS_LIKE")
    @CommonLog("添加BUS_LIKE")
    @SaCheckPermission("/biz/like/add")
    @PostMapping("/biz/like/add")
    public CommonResult<String> add(@RequestBody @Valid BusLikeAddParam busLikeAddParam) {
        this.busLikeService.add(busLikeAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑BUS_LIKE
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑BUS_LIKE")
    @CommonLog("编辑BUS_LIKE")
    @SaCheckPermission("/biz/like/edit")
    @PostMapping("/biz/like/edit")
    public CommonResult<String> edit(@RequestBody @Valid BusLikeEditParam busLikeEditParam) {
        this.busLikeService.edit(busLikeEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除BUS_LIKE
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除BUS_LIKE")
    @CommonLog("删除BUS_LIKE")
    @SaCheckPermission("/biz/like/delete")
    @PostMapping("/biz/like/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       CommonValidList<BusLikeIdParam> busLikeIdParamList) {
        this.busLikeService.delete(busLikeIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取BUS_LIKE详情
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取BUS_LIKE详情")
    @SaCheckPermission("/biz/like/detail")
    @GetMapping("/biz/like/detail")
    public CommonResult<BusLike> detail(@Valid BusLikeIdParam busLikeIdParam) {
        return CommonResult.data(this.busLikeService.detail(busLikeIdParam));
    }

    /**
     * 编辑BUS_LIKE
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑BUS_LIKE")
    @CommonLog("编辑BUS_LIKE")
    @PutMapping("/biz/like/post")
    public CommonResult<String> likePost(@Valid BusLikeEditParam busLikeEditParam) {
        this.busLikeService.likePost(busLikeEditParam.getPostId(), Long.valueOf(StpLoginUserUtil.getLoginUser().getId()));
        return CommonResult.ok();
    }
}
