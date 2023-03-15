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
package vip.xiaonuo.biz.modular.comments.controller;

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
import vip.xiaonuo.biz.modular.comments.entity.BusComments;
import vip.xiaonuo.biz.modular.comments.param.BusCommentsAddParam;
import vip.xiaonuo.biz.modular.comments.param.BusCommentsEditParam;
import vip.xiaonuo.biz.modular.comments.param.BusCommentsIdParam;
import vip.xiaonuo.biz.modular.comments.param.BusCommentsPageParam;
import vip.xiaonuo.biz.modular.comments.service.BusCommentsService;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.common.pojo.CommonValidList;

/**
 * BUS_COMMENTS控制器
 *
 * @author twh
 * @date 2023/03/02 14:55
 */
@Api(tags = "BUS_COMMENTS控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class BusCommentsController {

    @Resource
    private BusCommentsService busCommentsService;

    /**
     * 获取BUS_COMMENTS分页
     *
     * @author twh
     * @date 2023/03/02 14:55
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取BUS_COMMENTS分页")
    @GetMapping("/biz/comments/page")
    public CommonResult<Page<BusComments>> page(BusCommentsPageParam busCommentsPageParam) {
        return CommonResult.data(this.busCommentsService.page(busCommentsPageParam));
    }

    /**
     * 添加BUS_COMMENTS
     *
     * @author twh
     * @date 2023/03/02 14:55
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加BUS_COMMENTS")
    @CommonLog("添加BUS_COMMENTS")
    @PostMapping("/biz/comments/add")
    public CommonResult<String> add(@RequestBody @Valid BusCommentsAddParam busCommentsAddParam) {
        this.busCommentsService.add(busCommentsAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑BUS_COMMENTS
     *
     * @author twh
     * @date 2023/03/02 14:55
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑BUS_COMMENTS")
    @CommonLog("编辑BUS_COMMENTS")
    @PostMapping("/biz/comments/edit")
    public CommonResult<String> edit(@RequestBody @Valid BusCommentsEditParam busCommentsEditParam) {
        this.busCommentsService.edit(busCommentsEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除BUS_COMMENTS
     *
     * @author twh
     * @date 2023/03/02 14:55
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除BUS_COMMENTS")
    @CommonLog("删除BUS_COMMENTS")
    @PostMapping("/biz/comments/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       CommonValidList<BusCommentsIdParam> busCommentsIdParamList) {
        this.busCommentsService.delete(busCommentsIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取BUS_COMMENTS详情
     *
     * @author twh
     * @date 2023/03/02 14:55
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取BUS_COMMENTS详情")
    @GetMapping("/biz/comments/detail")
    public CommonResult<BusComments> detail(@Valid BusCommentsIdParam busCommentsIdParam) {
        return CommonResult.data(this.busCommentsService.detail(busCommentsIdParam));
    }
}
