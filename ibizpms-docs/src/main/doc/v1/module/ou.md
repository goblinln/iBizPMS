# ou组织用户管理模块(ou)

  

## 实体关系
{% plantuml %}

package ou组织用户管理模块 {

class 部门 {
}

class 人员 {
}

class 单位 {
}

class 岗位 {
}

class 组 {
}

class 组成员 {
}


部门 *-- 人员 


单位 *-- 人员 


部门 *-- 部门 


单位 *-- 部门 


单位 *-- 单位 


人员 *-- 组成员 


岗位 *-- 组成员 


组 *-- 组成员 



}

hide members

{% endplantuml %}


## 实体

| 实体编号    |    实体名称    |  实体类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|SYS_DEPT|[部门](ou/SysDepartment.md)|主实体|&nbsp;|
|IBZEMP|[人员](ou/SysEmployee.md)|主实体|&nbsp;|
|SYS_ORG|[单位](ou/SysOrganization.md)|主实体|&nbsp;|
|SYS_POST|[岗位](ou/SysPost.md)|主实体|&nbsp;|
|SYS_TEAM|[组](ou/SysTeam.md)|主实体|&nbsp;|
|SYS_TEAMMEMBER|[组成员](ou/SysTeamMember.md)|主实体|&nbsp;|
