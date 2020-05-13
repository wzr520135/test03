package com.test03.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_roles")
public class SysRoles {
  @TableId(type = IdType.AUTO)
  private long id;
  private String name;
  private String note;
  private java.sql.Date createdTime;
  private java.sql.Date updateTime;



}
