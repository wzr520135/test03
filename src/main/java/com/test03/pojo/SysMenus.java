package com.test03.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
@TableName("sys_menus")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SysMenus {
 @TableId(type = IdType.AUTO)
  private long id;
  private String name;
  private String url;
  private long type;
  private long sort;
  private long parentId;
  private String permission;
  private Date createTime;
  private Date updateTime;
  private String createdUser;
  private String updateUser;

 public boolean getIsParent() {
      if(parentId!=0){
       return true;
      }else {
       return false;
      }
 }
}
