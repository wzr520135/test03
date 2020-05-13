package com.test03.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_users")
public class User {
  @TableId(type = IdType.AUTO)
  private long id;
  private String username;
  private String password;
  private long phone;
  private String email;
  private Date createtime;
  private Date updatetime;
  private long deptid;




}
