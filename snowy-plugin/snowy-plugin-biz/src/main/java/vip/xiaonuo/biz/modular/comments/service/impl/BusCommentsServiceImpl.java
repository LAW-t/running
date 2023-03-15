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
package vip.xiaonuo.biz.modular.comments.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import vip.xiaonuo.auth.core.pojo.SaBaseLoginUser;
import vip.xiaonuo.auth.core.util.StpLoginUserUtil;
import vip.xiaonuo.biz.modular.comments.entity.BusComments;
import vip.xiaonuo.biz.modular.comments.mapper.BusCommentsMapper;
import vip.xiaonuo.biz.modular.comments.param.BusCommentsAddParam;
import vip.xiaonuo.biz.modular.comments.param.BusCommentsEditParam;
import vip.xiaonuo.biz.modular.comments.param.BusCommentsIdParam;
import vip.xiaonuo.biz.modular.comments.param.BusCommentsPageParam;
import vip.xiaonuo.biz.modular.comments.service.BusCommentsService;
import vip.xiaonuo.biz.modular.post.entity.BusPost;
import vip.xiaonuo.biz.modular.post.service.BusPostService;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;

/**
 * BUS_COMMENTSService接口实现类
 *
 * @author twh
 * @date 2023/03/02 14:55
 **/
@Service
public class BusCommentsServiceImpl extends ServiceImpl<BusCommentsMapper, BusComments> implements BusCommentsService {

    @Resource
    private BusPostService postService;

    @Override
    public Page<BusComments> page(BusCommentsPageParam busCommentsPageParam) {
        QueryWrapper<BusComments> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isAllNotEmpty(busCommentsPageParam.getSortField(), busCommentsPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(busCommentsPageParam.getSortOrder());
            queryWrapper.orderBy(true, busCommentsPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(busCommentsPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BusComments::getId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public void add(BusCommentsAddParam busCommentsAddParam) {
        // 查询帖子是否存在
        BusPost post = this.postService.getById(busCommentsAddParam.getPostId());
        if (BeanUtil.isEmpty(post)) {
            throw new CommonException("帖子不存在");
        }

        BusComments busComments = BeanUtil.toBean(busCommentsAddParam, BusComments.class);
        SaBaseLoginUser loginUser = StpLoginUserUtil.getLoginUser();
        busComments.setAvatar(loginUser.getAvatar());
        busComments.setNickName(loginUser.getName());
        busComments.setUserId(Long.valueOf(loginUser.getId()));
        this.save(busComments);

        post.setTotalComments(post.getTotalComments() + 1);
        this.postService.updateById(post);
        this.postService.addTopicComments(post);
    }

    @Override
    public void edit(BusCommentsEditParam busCommentsEditParam) {
        BusComments busComments = this.queryEntity(busCommentsEditParam.getId());
        BeanUtil.copyProperties(busCommentsEditParam, busComments);
        this.updateById(busComments);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BusCommentsIdParam> busCommentsIdParamList) {
        List<Long> commentsIds = CollStreamUtil.toList(busCommentsIdParamList, BusCommentsIdParam::getId);
        commentsIds.forEach(id -> {
            BusComments comments = this.getById(id);
            BusPost post = this.postService.getById(comments.getPostId());
            if (BeanUtil.isNotEmpty(post)) {
                post.setTotalComments(post.getTotalComments() - 1);
                this.postService.updateById(post);
            }
        });
        // 执行删除
        this.removeBatchByIds(commentsIds);
    }

    @Override
    public BusComments detail(BusCommentsIdParam busCommentsIdParam) {
        return this.queryEntity(busCommentsIdParam.getId());
    }

    @Override
    public BusComments queryEntity(Long id) {
        BusComments busComments = this.getById(id);
        if (ObjectUtil.isEmpty(busComments)) {
            throw new CommonException("BUS_COMMENTS不存在，id值为：{}", id);
        }
        return busComments;
    }

    @Override
    public List<BusComments> getListByPostId(Long postId) {
        LambdaQueryWrapper<BusComments> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BusComments::getPostId, postId);
        return this.list(wrapper);
    }
}
