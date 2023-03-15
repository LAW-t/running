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
package vip.xiaonuo.biz.modular.topic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import vip.xiaonuo.biz.modular.topic.entity.BusTopic;
import vip.xiaonuo.biz.modular.topic.mapper.BusTopicMapper;
import vip.xiaonuo.biz.modular.topic.param.BusTopicAddParam;
import vip.xiaonuo.biz.modular.topic.param.BusTopicEditParam;
import vip.xiaonuo.biz.modular.topic.param.BusTopicIdParam;
import vip.xiaonuo.biz.modular.topic.param.BusTopicPageParam;
import vip.xiaonuo.biz.modular.topic.service.BusTopicService;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;

/**
 * BUS_TOPICService接口实现类
 *
 * @author twh
 * @date 2023/03/02 14:35
 **/
@Service
public class BusTopicServiceImpl extends ServiceImpl<BusTopicMapper, BusTopic> implements BusTopicService {

    @Override
    public Page<BusTopic> page(BusTopicPageParam busTopicPageParam) {
        QueryWrapper<BusTopic> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(busTopicPageParam.getName())) {
            queryWrapper.lambda().like(BusTopic::getName, busTopicPageParam.getName());
        }
        if (ObjectUtil.isAllNotEmpty(busTopicPageParam.getSortField(), busTopicPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(busTopicPageParam.getSortOrder());
            queryWrapper.orderBy(true, busTopicPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(busTopicPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BusTopic::getId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public void add(BusTopicAddParam busTopicAddParam) {
        BusTopic busTopic = BeanUtil.toBean(busTopicAddParam, BusTopic.class);
        this.save(busTopic);
    }

    @Override
    public void edit(BusTopicEditParam busTopicEditParam) {
        BusTopic busTopic = this.queryEntity(busTopicEditParam.getId());
        BeanUtil.copyProperties(busTopicEditParam, busTopic);
        this.updateById(busTopic);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BusTopicIdParam> busTopicIdParamList) {
        // 执行删除
        this.removeBatchByIds(CollStreamUtil.toList(busTopicIdParamList, BusTopicIdParam::getId));
    }

    @Override
    public BusTopic detail(BusTopicIdParam busTopicIdParam) {
        return this.queryEntity(busTopicIdParam.getId());
    }

    @Override
    public BusTopic queryEntity(Long id) {
        BusTopic busTopic = this.getById(id);
        if (ObjectUtil.isEmpty(busTopic)) {
            throw new CommonException("BUS_TOPIC不存在，id值为：{}", id);
        }
        return busTopic;
    }
}
