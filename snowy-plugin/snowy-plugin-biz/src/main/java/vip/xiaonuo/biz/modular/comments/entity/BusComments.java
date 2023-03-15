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
package vip.xiaonuo.biz.modular.comments.entity;

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
 * BUS_COMMENTS实体
 *
 * @author twh
 * @date  2023/03/02 14:55
 **/
@Getter
@Setter
@TableName("BUS_COMMENTS")
public class BusComments {

    /** ID */
    @TableId
    @ApiModelProperty(value = "ID", position = 1)
    private Long id;

    /** 帖子ID */
    @ApiModelProperty(value = "帖子ID", position = 2)
    private Long postId;

    /** 用户ID */
    @ApiModelProperty(value = "用户ID", position = 3)
    private Long userId;

    /** 内容 */
    @ApiModelProperty(value = "内容", position = 4)
    private String content;

    /** 昵称 */
    @ApiModelProperty(value = "昵称", position = 5)
    private String nickName;

    /** 头像 */
    @ApiModelProperty(value = "头像", position = 6)
    private String avatar;

    /** 发布时间 */
    @ApiModelProperty(value = "发布时间", position = 7)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
