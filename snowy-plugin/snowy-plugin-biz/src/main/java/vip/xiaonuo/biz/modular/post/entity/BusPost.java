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
package vip.xiaonuo.biz.modular.post.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * BUS_POST实体
 *
 * @author twh
 * @date  2023/03/02 14:45
 **/
@Getter
@Setter
@TableName("BUS_POST")
public class BusPost {

    /** ID */
    @TableId
    @ApiModelProperty(value = "ID", position = 1)
    private Long id;

    /** 内容 */
    @ApiModelProperty(value = "内容", position = 2)
    private String content;

    /** 图片1 */
    @ApiModelProperty(value = "图片1", position = 3)
    private String image1;

    /** 图片2 */
    @ApiModelProperty(value = "图片2", position = 4)
    private String image2;

    /** 图片3 */
    @ApiModelProperty(value = "图片3", position = 5)
    private String image3;

    /** 图片4 */
    @ApiModelProperty(value = "图片4", position = 6)
    private String image4;

    /** 图片5 */
    @ApiModelProperty(value = "图片5", position = 7)
    private String image5;

    /** 图片6 */
    @ApiModelProperty(value = "图片6", position = 8)
    private String image6;

    /** 图片7 */
    @ApiModelProperty(value = "图片7", position = 9)
    private String image7;

    /** 图片8 */
    @ApiModelProperty(value = "图片8", position = 10)
    private String image8;

    /** 图片9 */
    @ApiModelProperty(value = "图片9", position = 11)
    private String image9;

    /** 点赞数 */
    @ApiModelProperty(value = "点赞数", position = 12)
    private Integer totalLike;

    /** 评论数 */
    @ApiModelProperty(value = "评论数", position = 13)
    private Integer totalComments;

    /** 用户ID */
    @ApiModelProperty(value = "用户ID", position = 14)
    private Long userId;

    /** 发布时间 */
    @ApiModelProperty(value = "发布时间", position = 15)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 昵称 */
    @ApiModelProperty(value = "昵称", position = 16)
    private String nickName;

    /** 头像 */
    @ApiModelProperty(value = "头像", position = 17)
    private String avatar;

    /** 话题ID */
    @ApiModelProperty(value = "话题ID", position = 18)
    private Long topicId;

    /** 跑步ID */
    @ApiModelProperty(value = "跑步ID", position = 19)
    private Long runningId;

    /** 标题 */
    @ApiModelProperty(value = "标题", position = 20)
    private String title;

    /** 浏览数 */
    @ApiModelProperty(value = "浏览数", position = 21)
    private Integer totalView;
}
