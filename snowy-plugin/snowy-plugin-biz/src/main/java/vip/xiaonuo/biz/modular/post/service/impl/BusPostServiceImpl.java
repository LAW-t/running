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
package vip.xiaonuo.biz.modular.post.service.impl;

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
import vip.xiaonuo.biz.modular.comments.service.BusCommentsService;
import vip.xiaonuo.biz.modular.post.PostDetailResult;
import vip.xiaonuo.biz.modular.post.entity.BusPost;
import vip.xiaonuo.biz.modular.post.mapper.BusPostMapper;
import vip.xiaonuo.biz.modular.post.param.BusPostAddParam;
import vip.xiaonuo.biz.modular.post.param.BusPostEditParam;
import vip.xiaonuo.biz.modular.post.param.BusPostIdParam;
import vip.xiaonuo.biz.modular.post.param.BusPostPageParam;
import vip.xiaonuo.biz.modular.post.service.BusPostService;
import vip.xiaonuo.biz.modular.topic.entity.BusTopic;
import vip.xiaonuo.biz.modular.topic.service.BusTopicService;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;

/**
 * BUS_POSTService接口实现类
 *
 * @author twh
 * @date 2023/03/02 14:45
 **/
@Service
public class BusPostServiceImpl extends ServiceImpl<BusPostMapper, BusPost> implements BusPostService {

    @Resource
    private BusTopicService topicService;

    @Resource
    private BusCommentsService commentsService;

    @Override
    public Page<BusPost> page(BusPostPageParam busPostPageParam) {
        QueryWrapper<BusPost> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(busPostPageParam.getUserId())) {
            queryWrapper.lambda().eq(BusPost::getUserId, busPostPageParam.getUserId());
        }
        if (ObjectUtil.isAllNotEmpty(busPostPageParam.getSortField(), busPostPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(busPostPageParam.getSortOrder());
            queryWrapper.orderBy(true, busPostPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(busPostPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BusPost::getId);
        }
        Page<BusPost> res = this.page(CommonPageRequest.defaultPage(), queryWrapper);
        // 帖子浏览量
        res.getRecords().forEach(this::addView);
        // 话题浏览量
        res.getRecords().forEach(this::addTopicView);

        return res;
    }

    @Override
    public void add(BusPostAddParam busPostAddParam) {
        BusPost busPost = BeanUtil.toBean(busPostAddParam, BusPost.class);
        busPost.setTotalLike(0);
        busPost.setTotalComments(0);
        busPost.setTotalView(0);
        SaBaseLoginUser loginUser = StpLoginUserUtil.getLoginUser();
        busPost.setAvatar(loginUser.getAvatar());
        busPost.setNickName(loginUser.getName());
        busPost.setUserId(Long.valueOf(loginUser.getId()));
        this.save(busPost);

        // 增加话题评论量
        this.addTopicComments(busPost);
    }

    @Override
    public void edit(BusPostEditParam busPostEditParam) {
        BusPost busPost = this.queryEntity(busPostEditParam.getId());
        BeanUtil.copyProperties(busPostEditParam, busPost);
        this.updateById(busPost);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BusPostIdParam> busPostIdParamList) {
        // 执行删除
        this.removeBatchByIds(CollStreamUtil.toList(busPostIdParamList, BusPostIdParam::getId));
    }

    @Override
    public PostDetailResult detail(BusPostIdParam busPostIdParam) {
        BusPost post = this.queryEntity(busPostIdParam.getId());
        this.addView(post);
        this.addTopicView(post);

        PostDetailResult result = new PostDetailResult();
        BeanUtil.copyProperties(post, result);
        if (BeanUtil.isNotEmpty(post.getTopicId())) {
            BusTopic topic = this.topicService.getById(post.getTopicId());
            result.setTopic(topic);
        }
        result.setComments(this.commentsService.getListByPostId(post.getId()));

        return result;
    }

    @Override
    public BusPost queryEntity(Long id) {
        BusPost busPost = this.getById(id);
        if (ObjectUtil.isEmpty(busPost)) {
            throw new CommonException("BUS_POST不存在，id值为：{}", id);
        }
        return busPost;
    }

    @Override
    public void addView(BusPost post) {
        post.setTotalView(post.getTotalView() + 1);
        this.updateById(post);
    }

    @Override
    public void addTopicView(BusPost post) {
        Long topicId = post.getTopicId();
        if (BeanUtil.isEmpty(topicId)) {
            return;
        }
        BusTopic topic = this.topicService.getById(topicId);
        topic.setTotalView(topic.getTotalView() + 1);
        this.topicService.updateById(topic);
    }

    @Override
    public void addTopicComments(BusPost post) {
        Long topicId = post.getTopicId();
        if (BeanUtil.isEmpty(topicId)) {
            return;
        }
        BusTopic topic = this.topicService.getById(topicId);
        topic.setTotalComments(topic.getTotalComments() + 1);
        this.topicService.updateById(topic);
    }
}
