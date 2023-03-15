package vip.xiaonuo.biz.modular.post;

import java.util.Date;
import java.util.List;

import lombok.Data;
import vip.xiaonuo.biz.modular.comments.entity.BusComments;
import vip.xiaonuo.biz.modular.topic.entity.BusTopic;

/**
 * @author 汤卫豪
 **/
@Data
public class PostDetailResult {

    /**
     * ID
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片1
     */
    private String image1;

    /**
     * 图片2
     */
    private String image2;

    /**
     * 图片3
     */
    private String image3;

    /**
     * 图片4
     */
    private String image4;

    /**
     * 图片5
     */
    private String image5;

    /**
     * 图片6
     */
    private String image6;

    /**
     * 图片7
     */
    private String image7;

    /**
     * 图片8
     */
    private String image8;

    /**
     * 图片9
     */
    private String image9;

    /**
     * 点赞数
     */
    private Integer totalLike;

    /**
     * 评论数
     */
    private Integer totalComments;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 话题ID
     */
    private Long topicId;

    /**
     * 跑步ID
     */
    private Long runningId;

    /**
     * 标题
     */
    private String title;

    /**
     * 浏览数
     */
    private Integer totalView;

    private BusTopic topic;

    private List<BusComments> comments;
}
