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
package vip.xiaonuo.biz.modular.post.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

import vip.xiaonuo.biz.modular.post.PostDetailResult;
import vip.xiaonuo.biz.modular.post.entity.BusPost;
import vip.xiaonuo.biz.modular.post.param.BusPostAddParam;
import vip.xiaonuo.biz.modular.post.param.BusPostEditParam;
import vip.xiaonuo.biz.modular.post.param.BusPostIdParam;
import vip.xiaonuo.biz.modular.post.param.BusPostPageParam;

/**
 * BUS_POSTService接口
 *
 * @author twh
 * @date 2023/03/02 14:45
 **/
public interface BusPostService extends IService<BusPost> {

    /**
     * 获取BUS_POST分页
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    Page<BusPost> page(BusPostPageParam busPostPageParam);

    /**
     * 添加BUS_POST
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    void add(BusPostAddParam busPostAddParam);

    /**
     * 编辑BUS_POST
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    void edit(BusPostEditParam busPostEditParam);

    /**
     * 删除BUS_POST
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    void delete(List<BusPostIdParam> busPostIdParamList);

    /**
     * 获取BUS_POST详情
     *
     * @author twh
     * @date 2023/03/02 14:45
     */
    PostDetailResult detail(BusPostIdParam busPostIdParam);

    /**
     * 获取BUS_POST详情
     *
     * @author twh
     * @date 2023/03/02 14:45
     **/
    BusPost queryEntity(Long id);

    public void addView(BusPost post);

    public void addTopicView(BusPost post);

    public void addTopicComments(BusPost post);
}
