# 数据字典-系统日志操作类型（Action__type）
## 字典说明
系统日志操作类型

## 基本说明
| 项目 | 说明 |
| ---- | ---- |
| 数值类型 | 字符串 |
| 启用多选 | 否 |
| 多选分隔符 | 无 |
| 启用缓存 | 是 |
| 缓存超时时长 | 无 |

## 取值范围
| 序号 | 数值 | 文本 | 父值 | 提示信息 | 是否显示空白 | 默认选择 | 说明 |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 1 | created | 创建 |  |  | 否 | 否 | 创建 |
| 2 | opened | 创建 |  |  | 否 | 否 | 创建 |
| 3 | changed | 变更了 |  |  | 否 | 否 | 变更了 |
| 4 | edited | 编辑了 |  |  | 否 | 否 | 编辑了 |
| 5 | assigned | 指派了 |  |  | 否 | 否 | 指派了 |
| 6 | closed | 关闭了 |  |  | 否 | 否 | 关闭了 |
| 7 | deleted | 删除了 |  |  | 否 | 否 | 删除了 |
| 8 | deletedfile | 删除附件 |  |  | 否 | 否 | 删除附件 |
| 9 | editfile | 编辑附件 |  |  | 否 | 否 | 编辑附件 |
| 10 | erased | 删除了 |  |  | 否 | 否 | 删除了 |
| 11 | undeleted | 还原了 |  |  | 否 | 否 | 还原了 |
| 12 | hidden | 隐藏了 |  |  | 否 | 否 | 隐藏了 |
| 13 | commented | 评论了 |  |  | 否 | 否 | 评论了 |
| 14 | activated | 激活了 |  |  | 否 | 否 | 激活了 |
| 15 | blocked | 阻塞了 |  |  | 否 | 否 | 阻塞了 |
| 16 | resolved | 解决了 |  |  | 否 | 否 | 解决了 |
| 17 | reviewed | 评审了 |  |  | 否 | 否 | 评审了 |
| 18 | moved | 移动了 |  |  | 否 | 否 | 移动了 |
| 19 | confirmed | 确认了需求 |  |  | 否 | 否 | 确认了需求 |
| 20 | bugconfirmed | 确认了 |  |  | 否 | 否 | 确认了 |
| 21 | tostory | 转需求 |  |  | 否 | 否 | 转需求 |
| 22 | frombug | 转需求 |  |  | 否 | 否 | 转需求 |
| 23 | fromlib | 从用例库导入 |  |  | 否 | 否 | 从用例库导入 |
| 24 | totask | 转任务 |  |  | 否 | 否 | 转任务 |
| 25 | svncommited | 提交代码 |  |  | 否 | 否 | 提交代码 |
| 26 | gitcommited | 提交代码 |  |  | 否 | 否 | 提交代码 |
| 27 | linked2plan | 关联计划 |  |  | 否 | 否 | 关联计划 |
| 28 | unlinkedfromplan | 移除计划 |  |  | 否 | 否 | 移除计划 |
| 29 | changestatus | 修改状态 |  |  | 否 | 否 | 修改状态 |
| 30 | marked | 编辑了 |  |  | 否 | 否 | 编辑了 |
| 31 | linked2project | 关联项目 |  |  | 否 | 否 | 关联项目 |
| 32 | unlinkedfromproject | 移除项目 |  |  | 否 | 否 | 移除项目 |
| 33 | unlinkedfrombuild | 移除版本 |  |  | 否 | 否 | 移除版本 |
| 34 | linked2release | 关联发布 |  |  | 否 | 否 | 关联发布 |
| 35 | unlinkedfromrelease | 移除发布 |  |  | 否 | 否 | 移除发布 |
| 36 | linkrelatedbug | 关联了相关Bug |  |  | 否 | 否 | 关联了相关Bug |
| 37 | unlinkrelatedbug | 移除了相关Bug |  |  | 否 | 否 | 移除了相关Bug |
| 38 | linkrelatedcase | 关联了相关用例 |  |  | 否 | 否 | 关联了相关用例 |
| 39 | unlinkrelatedcase | 移除了相关用例 |  |  | 否 | 否 | 移除了相关用例 |
| 40 | linkrelatedstory | 关联了相关需求 |  |  | 否 | 否 | 关联了相关需求 |
| 41 | unlinkrelatedstory | 移除了相关需求 |  |  | 否 | 否 | 移除了相关需求 |
| 42 | subdividestory | 细分了需求 |  |  | 否 | 否 | 细分了需求 |
| 43 | unlinkchildstory | 移除了细分需求 |  |  | 否 | 否 | 移除了细分需求 |
| 44 | started | 开始了 |  |  | 否 | 否 | 开始了 |
| 45 | restarted | 继续了 |  |  | 否 | 否 | 继续了 |
| 46 | recordestimate | 记录了工时 |  |  | 否 | 否 | 记录了工时 |
| 47 | editestimate | 编辑了工时 |  |  | 否 | 否 | 编辑了工时 |
| 48 | canceled | 取消了 |  |  | 否 | 否 | 取消了 |
| 49 | finished | 完成了 |  |  | 否 | 否 | 完成了 |
| 50 | paused | 暂停了 |  |  | 否 | 否 | 暂停了 |
| 51 | verified | 验收了 |  |  | 否 | 否 | 验收了 |
| 52 | delayed | 延期 |  |  | 否 | 否 | 延期 |
| 53 | suspended | 挂起 |  |  | 否 | 否 | 挂起 |
| 54 | login | 登录系统 |  |  | 否 | 否 | 登录系统 |
| 55 | logout | 退出登录 |  |  | 否 | 否 | 退出登录 |
| 56 | deleteestimate | 删除了工时 |  |  | 否 | 否 | 删除了工时 |
| 57 | linked2build | 关联了 |  |  | 否 | 否 | 关联了 |
| 58 | linked2bug | 关联了 |  |  | 否 | 否 | 关联了 |
| 59 | linkchildtask | 关联子任务 |  |  | 否 | 否 | 关联子任务 |
| 60 | unlinkchildrentask | 取消关联子任务 |  |  | 否 | 否 | 取消关联子任务 |
| 61 | linkparenttask | 关联到父任务 |  |  | 否 | 否 | 关联到父任务 |
| 62 | unlinkparenttask | 从父任务取消关联 |  |  | 否 | 否 | 从父任务取消关联 |
| 63 | batchcreate | 批量创建任务 |  |  | 否 | 否 | 批量创建任务 |
| 64 | createchildren | 创建子任务 |  |  | 否 | 否 | 创建子任务 |
| 65 | managed | 维护 |  |  | 否 | 否 | 维护 |
| 66 | deletechildrentask | 删除子任务 |  |  | 否 | 否 | 删除子任务 |
| 67 | createchildrenstory | 创建子需求 |  |  | 否 | 否 | 创建子需求 |
| 68 | linkchildstory | 关联子需求 |  |  | 否 | 否 | 关联子需求 |
| 69 | unlinkchildrenstory | 取消关联子需求 |  |  | 否 | 否 | 取消关联子需求 |
| 70 | linkparentstory | 关联到父需求 |  |  | 否 | 否 | 关联到父需求 |
| 71 | unlinkparentstory | 从父需求取消关联 |  |  | 否 | 否 | 从父需求取消关联 |
| 72 | deletechildrenstory | 删除子需求 |  |  | 否 | 否 | 删除子需求 |
| 73 | submit | 提交 |  |  | 否 | 否 | 提交 |
| 74 | read | 已读了 |  |  | 否 | 否 | 已读了 |
| 75 | remind | 提醒 |  |  | 否 | 否 | 提醒 |

