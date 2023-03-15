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
package vip.xiaonuo.biz.modular.topic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

import vip.xiaonuo.biz.modular.topic.entity.BusTopic;
import vip.xiaonuo.biz.modular.topic.param.BusTopicAddParam;
import vip.xiaonuo.biz.modular.topic.param.BusTopicEditParam;
import vip.xiaonuo.biz.modular.topic.param.BusTopicIdParam;
import vip.xiaonuo.biz.modular.topic.param.BusTopicPageParam;

/**
 * BUS_TOPICService接口
 *
 * @author twh
 * @date 2023/03/02 14:35
 **/
public interface BusTopicService extends IService<BusTopic> {

    /**
     * 获取BUS_TOPIC分页
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    Page<BusTopic> page(BusTopicPageParam busTopicPageParam);

    /**
     * 添加BUS_TOPIC
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    void add(BusTopicAddParam busTopicAddParam);

    /**
     * 编辑BUS_TOPIC
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    void edit(BusTopicEditParam busTopicEditParam);

    /**
     * 删除BUS_TOPIC
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    void delete(List<BusTopicIdParam> busTopicIdParamList);

    /**
     * 获取BUS_TOPIC详情
     *
     * @author twh
     * @date 2023/03/02 14:35
     */
    BusTopic detail(BusTopicIdParam busTopicIdParam);

    /**
     * 获取BUS_TOPIC详情
     *
     * @author twh
     * @date 2023/03/02 14:35
     **/
    BusTopic queryEntity(Long id);
}
