package com.test03.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Auther wise wu
 * @Date 2020/5/11 11:47
 * @Description
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUITree {
    private Long id;		//节点ID
    private String text;	//文本信息
    private String state;	//open/closed
}
