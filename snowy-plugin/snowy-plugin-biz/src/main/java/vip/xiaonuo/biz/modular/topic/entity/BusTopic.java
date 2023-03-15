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
package vip.xiaonuo.biz.modular.topic.entity;

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
 * BUS_TOPIC实体
 *
 * @author twh
 * @date  2023/03/02 14:35
 **/
@Getter
@Setter
@TableName("BUS_TOPIC")
public class BusTopic {

    /** ID */
    @TableId
    @ApiModelProperty(value = "ID", position = 1)
    private Long id;

    /** 话题标题 */
    @ApiModelProperty(value = "话题标题", position = 2)
    private String name;

    /** 介绍 */
    @ApiModelProperty(value = "介绍", position = 3)
    private String introduce;

    /** 背景图片 */
    @ApiModelProperty(value = "背景图片", position = 4)
    private String image;

    /** 评论数 */
    @ApiModelProperty(value = "评论数", position = 5)
    private Integer totalComments;

    /** 浏览量 */
    @ApiModelProperty(value = "浏览量", position = 6)
    private Integer totalView;
}
