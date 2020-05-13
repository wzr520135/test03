package com.test03.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@TableName("sys_user_role")
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRole {
 @TableId(type = IdType.AUTO)
  private long id;
  private long userId;
  private long roleId;

}
