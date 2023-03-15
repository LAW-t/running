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
package vip.xiaonuo.biz.modular.like.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

import vip.xiaonuo.biz.modular.like.entity.BusLike;
import vip.xiaonuo.biz.modular.like.param.BusLikeAddParam;
import vip.xiaonuo.biz.modular.like.param.BusLikeEditParam;
import vip.xiaonuo.biz.modular.like.param.BusLikeIdParam;
import vip.xiaonuo.biz.modular.like.param.BusLikePageParam;

/**
 * BUS_LIKEService接口
 *
 * @author twh
 * @date 2023/03/02 14:48
 **/
public interface BusLikeService extends IService<BusLike> {

    /**
     * 获取BUS_LIKE分页
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    Page<BusLike> page(BusLikePageParam busLikePageParam);

    /**
     * 添加BUS_LIKE
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    void add(BusLikeAddParam busLikeAddParam);

    /**
     * 编辑BUS_LIKE
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    void edit(BusLikeEditParam busLikeEditParam);

    /**
     * 删除BUS_LIKE
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    void delete(List<BusLikeIdParam> busLikeIdParamList);

    /**
     * 获取BUS_LIKE详情
     *
     * @author twh
     * @date 2023/03/02 14:48
     */
    BusLike detail(BusLikeIdParam busLikeIdParam);

    /**
     * 查询实体
     * 获取BUS_LIKE详情
     *
     * @param postId post id
     * @param userId 用户id
     * @return {@link BusLike}
     * @author twh
     * @date 2023/03/02 14:48
     */
    BusLike queryEntity(Long postId, Long userId);

    /**
     * 点赞帖子
     *
     * @param postId post id
     * @param id     id
     */
    void likePost(Long postId, Long userId);
}
