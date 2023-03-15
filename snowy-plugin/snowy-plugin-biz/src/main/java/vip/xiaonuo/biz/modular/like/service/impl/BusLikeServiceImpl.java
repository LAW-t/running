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
package vip.xiaonuo.biz.modular.like.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import vip.xiaonuo.biz.modular.like.entity.BusLike;
import vip.xiaonuo.biz.modular.like.mapper.BusLikeMapper;
import vip.xiaonuo.biz.modular.like.param.BusLikeAddParam;
import vip.xiaonuo.biz.modular.like.param.BusLikeEditParam;
import vip.xiaonuo.biz.modular.like.param.BusLikeIdParam;
import vip.xiaonuo.biz.modular.like.param.BusLikePageParam;
import vip.xiaonuo.biz.modular.like.service.BusLikeService;
import vip.xiaonuo.biz.modular.post.entity.BusPost;
import vip.xiaonuo.biz.modular.post.service.BusPostService;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;

/**
 * BUS_LIKEService接口实现类
 *
 * @author twh
 * @date 2023/03/02 14:48
 **/
@Service
public class BusLikeServiceImpl extends ServiceImpl<BusLikeMapper, BusLike> implements BusLikeService {

    @Resource
    private BusPostService postService;

    @Override
    public Page<BusLike> page(BusLikePageParam busLikePageParam) {
        QueryWrapper<BusLike> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isAllNotEmpty(busLikePageParam.getSortField(), busLikePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(busLikePageParam.getSortOrder());
            queryWrapper.orderBy(true, busLikePageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(busLikePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BusLike::getPostId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public void add(BusLikeAddParam busLikeAddParam) {
        BusLike busLike = BeanUtil.toBean(busLikeAddParam, BusLike.class);
        this.save(busLike);
    }

    @Override
    public void edit(BusLikeEditParam busLikeEditParam) {
        BusLike busLike = this.queryEntity(busLikeEditParam.getPostId(), busLikeEditParam.getUserId());
        BeanUtil.copyProperties(busLikeEditParam, busLike);
        this.updateById(busLike);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BusLikeIdParam> busLikeIdParamList) {
        if (ObjectUtils.isNotEmpty(busLikeIdParamList)) {
            busLikeIdParamList.forEach(v -> {
                // 执行删除
                LambdaQueryWrapper<BusLike> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(BusLike::getPostId, v.getPostId()).eq(BusLike::getUserId, v.getUserId());
                this.remove(queryWrapper);
            });
        }
    }

    @Override
    public BusLike detail(BusLikeIdParam busLikeIdParam) {
        return this.queryEntity(busLikeIdParam.getPostId(), busLikeIdParam.getUserId());
    }

    @Override
    public BusLike queryEntity(Long postId, Long userId) {
        LambdaQueryWrapper<BusLike> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusLike::getPostId, postId).eq(BusLike::getUserId, userId);
        BusLike busLike = this.getOne(queryWrapper);
        if (ObjectUtil.isEmpty(busLike)) {
            throw new CommonException("BUS_LIKE不存在");
        }
        return busLike;
    }

    @Override
    public void likePost(Long postId, Long userId) {
        // 查询帖子是否存在
        BusPost post = this.postService.getById(postId);
        if (BeanUtil.isEmpty(post)) {
            throw new CommonException("帖子不存在");
        }

        // 构造
        BusLike entity = new BusLike();
        entity.setPostId(postId);
        entity.setUserId(userId);

        LambdaQueryWrapper<BusLike> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusLike::getPostId, postId).eq(BusLike::getUserId, userId);
        BusLike busLike = this.getOne(queryWrapper);
        if (ObjectUtil.isEmpty(busLike)) {
            this.save(entity);
            post.setTotalLike(post.getTotalLike() + 1);
            this.postService.updateById(post);
        } else {
            this.remove(queryWrapper);
            post.setTotalLike(post.getTotalLike() - 1);
            this.postService.updateById(post);
        }
    }
}
