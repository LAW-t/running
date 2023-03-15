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
package vip.xiaonuo.biz.modular.topic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import vip.xiaonuo.biz.modular.topic.entity.BusTopic;
import vip.xiaonuo.biz.modular.topic.param.BusTopicAddParam;
import vip.xiaonuo.biz.modular.topic.param.BusTopicEditParam;
import vip.xiaonuo.biz.modular.topic.param.BusTopicIdParam;
import vip.xiaonuo.biz.modular.topic.param.BusTopicPageParam;
import vip.xiaonuo.biz.modular.topic.service.BusTopicService;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.common.pojo.CommonValidList;

/**
 * BUS_TOPIC控制器
 *
 * @author twh
 * @date 2023/03/02 14:35
 */
@Api(tags = "BUS_TOPIC控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class BusTopicController {

    @Resource
    private BusTopicService busTopicService;

    /**
     * 获取BUS_TOPIC分页
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取BUS_TOPIC分页")
    @SaCheckPermission("/biz/topic/page")
    @GetMapping("/biz/topic/page")
    public CommonResult<Page<BusTopic>> page(BusTopicPageParam busTopicPageParam) {
        return CommonResult.data(this.busTopicService.page(busTopicPageParam));
    }

    /**
     * 获取BUS_TOPIC全部
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取BUS_TOPIC全部")
    @GetMapping("/biz/topic/list")
    public CommonResult<List<BusTopic>> list() {
        return CommonResult.data(this.busTopicService.list());
    }

    /**
     * 添加BUS_TOPIC
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加BUS_TOPIC")
    @CommonLog("添加BUS_TOPIC")
    @SaCheckPermission("/biz/topic/add")
    @PostMapping("/biz/topic/add")
    public CommonResult<String> add(@RequestBody @Valid BusTopicAddParam busTopicAddParam) {
        this.busTopicService.add(busTopicAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑BUS_TOPIC
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑BUS_TOPIC")
    @CommonLog("编辑BUS_TOPIC")
    @SaCheckPermission("/biz/topic/edit")
    @PostMapping("/biz/topic/edit")
    public CommonResult<String> edit(@RequestBody @Valid BusTopicEditParam busTopicEditParam) {
        this.busTopicService.edit(busTopicEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除BUS_TOPIC
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除BUS_TOPIC")
    @CommonLog("删除BUS_TOPIC")
    @SaCheckPermission("/biz/topic/delete")
    @PostMapping("/biz/topic/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       CommonValidList<BusTopicIdParam> busTopicIdParamList) {
        this.busTopicService.delete(busTopicIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取BUS_TOPIC详情
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取BUS_TOPIC详情")
    @SaCheckPermission("/biz/topic/detail")
    @GetMapping("/biz/topic/detail")
    public CommonResult<BusTopic> detail(@Valid BusTopicIdParam busTopicIdParam) {
        return CommonResult.data(this.busTopicService.detail(busTopicIdParam));
    }
}
