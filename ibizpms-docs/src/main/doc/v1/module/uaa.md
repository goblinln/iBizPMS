# uaa权限模块(uaa)

  

## 实体关系
{% plantuml %}

package uaa权限模块 {

class 系统角色 {
}

class 系统用户 {
}

class 用户角色关系 {
}


系统角色 *-- 系统角色 


系统角色 *-- 用户角色关系 


系统用户 *-- 用户角色关系 



}

hide members

{% endplantuml %}


## 实体

| 实体编号    |    实体名称    |  实体类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|SYS_ROLE|[系统角色](uaa/SysRole.md)|主实体|&nbsp;|
|SYS_USER|[系统用户](uaa/SysUser.md)|主实体|&nbsp;|
|SYS_USER_ROLE|[用户角色关系](uaa/SysUserRole.md)|主实体|&nbsp;|
