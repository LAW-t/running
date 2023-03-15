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
package vip.xiaonuo.biz.modular.post.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import vip.xiaonuo.auth.core.util.StpLoginUserUtil;
import vip.xiaonuo.biz.modular.post.PostDetailResult;
import vip.xiaonuo.biz.modular.post.entity.BusPost;
import vip.xiaonuo.biz.modular.post.param.BusPostAddParam;
import vip.xiaonuo.biz.modular.post.param.BusPostEditParam;
import vip.xiaonuo.biz.modular.post.param.BusPostIdParam;
import vip.xiaonuo.biz.modular.post.param.BusPostPageParam;
import vip.xiaonuo.biz.modular.post.service.BusPostService;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.common.pojo.CommonValidList;

/**
 * BUS_POST控制器
 *
 * @author twh
 * @date 2023/03/02 14:45
 */
@Api(tags = "BUS_POST控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class BusPostController {

    @Resource
    private BusPostService busPostService;

    /**
     * 获取BUS_POST分页
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取BUS_POST分页")
    @GetMapping("/biz/post/page")
    public CommonResult<Page<BusPost>> page(BusPostPageParam busPostPageParam) {
        return CommonResult.data(this.busPostService.page(busPostPageParam));
    }

    /**
     * 获取BUS_POST分页
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取BUS_POST分页")
    @GetMapping("/biz/post/page/my")
    public CommonResult<Page<BusPost>> myPage(BusPostPageParam busPostPageParam) {
        busPostPageParam.setUserId(StpLoginUserUtil.getLoginUser().getId());
        return CommonResult.data(this.busPostService.page(busPostPageParam));
    }

    /**
     * 添加BUS_POST
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加BUS_POST")
    @CommonLog("添加BUS_POST")
    @PostMapping("/biz/post/add")
    public CommonResult<String> add(@RequestBody @Valid BusPostAddParam busPostAddParam) {
        this.busPostService.add(busPostAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑BUS_POST
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑BUS_POST")
    @CommonLog("编辑BUS_POST")
    @PostMapping("/biz/post/edit")
    public CommonResult<String> edit(@RequestBody @Valid BusPostEditParam busPostEditParam) {
        this.busPostService.edit(busPostEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除BUS_POST
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除BUS_POST")
    @CommonLog("删除BUS_POST")
    @PostMapping("/biz/post/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       CommonValidList<BusPostIdParam> busPostIdParamList) {
        this.busPostService.delete(busPostIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取BUS_POST详情
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取BUS_POST详情")
    @GetMapping("/biz/post/detail")
    public CommonResult<PostDetailResult> detail(@Valid BusPostIdParam busPostIdParam) {
        return CommonResult.data(this.busPostService.detail(busPostIdParam));
    }
}
