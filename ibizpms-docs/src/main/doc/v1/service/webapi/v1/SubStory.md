# 服务接口-IBZ_SUBSTORY
## 接口说明
需求

## 接口清单
### 新建需求
#### 访问路径
/substories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量新建需求
#### 访问路径
/substories/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substorydtos | List<[SubStoryDTO](#SubStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新需求
#### 访问路径
/substories/{substory_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量更新需求
#### 访问路径
/substories/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substorydtos | List<[SubStoryDTO](#SubStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除需求
#### 访问路径
/substories/{substory_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除需求
#### 访问路径
/substories/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 需求主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取需求
#### 访问路径
/substories/{substory_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 激活
#### 访问路径
/substories/{substory_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 全部推送
#### 访问路径
/substories/{substory_id}/allpush

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 指派
#### 访问路径
/substories/{substory_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量指派
#### 访问路径
/substories/{substory_id}/batchassignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量变更平台/分支
#### 访问路径
/substories/{substory_id}/batchchangebranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量变更模块
#### 访问路径
/substories/{substory_id}/batchchangemodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量关联计划
#### 访问路径
/substories/{substory_id}/batchchangeplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量变更阶段
#### 访问路径
/substories/{substory_id}/batchchangestage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量关闭
#### 访问路径
/substories/{substory_id}/batchclose

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量评审
#### 访问路径
/substories/{substory_id}/batchreview

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 计划批量解除关联需求
#### 访问路径
/substories/{substory_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### bug转需求
#### 访问路径
/substories/{substory_id}/bugtostory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 版本批量解除关联需求
#### 访问路径
/substories/{substory_id}/buildbatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目关联需求
#### 访问路径
/substories/{substory_id}/buildlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 版本解除关联需求
#### 访问路径
/substories/{substory_id}/buildunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 版本解除关联需求
#### 访问路径
/substories/{substory_id}/buildunlinkstorys

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 变更
#### 访问路径
/substories/{substory_id}/change

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 检查需求
#### 访问路径
/substories/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/substories/{substory_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 生成任务
#### 访问路径
/substories/{substory_id}/createtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 获取需求描述
#### 访问路径
/substories/{substory_id}/getstoryspec

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 获取需求描述
#### 访问路径
/substories/{substory_id}/getstoryspecs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目关联需求-按计划关联
#### 访问路径
/substories/{substory_id}/importplanstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 计划关联需求
#### 访问路径
/substories/{substory_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目批量解除关联需求
#### 访问路径
/substories/{substory_id}/projectbatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目关联需求
#### 访问路径
/substories/{substory_id}/projectlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目解除关联需求
#### 访问路径
/substories/{substory_id}/projectunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目解除关联需求
#### 访问路径
/substories/{substory_id}/projectunlinkstorys

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 推送
#### 访问路径
/substories/{substory_id}/push

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发布批量解除关联需求
#### 访问路径
/substories/{substory_id}/releasebatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发布关联需求
#### 访问路径
/substories/{substory_id}/releaselinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发布解除关联需求
#### 访问路径
/substories/{substory_id}/releaseunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 重置由谁评审
#### 访问路径
/substories/{substory_id}/resetreviewedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 评审
#### 访问路径
/substories/{substory_id}/review

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 保存需求
#### 访问路径
/substories/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存需求
#### 访问路径
/substories/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substorydtos | List<[SubStoryDTO](#SubStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/substories/{substory_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发送消息前置处理
#### 访问路径
/substories/{substory_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 需求收藏
#### 访问路径
/substories/{substory_id}/storyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 取消收藏
#### 访问路径
/substories/{substory_id}/storynfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 同步Ibz平台实体
#### 访问路径
/substories/{substory_id}/syncfromibiz

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 计划解除关联需求
#### 访问路径
/substories/{substory_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | substory_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 获取指派给我的需求
#### 访问路径
/substories/fetchassignedtomystory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询指派给我的需求
#### 访问路径
/substories/searchassignedtomystory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取指派给我的需求（日历）
#### 访问路径
/substories/fetchassignedtomystorycalendar

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询指派给我的需求（日历）
#### 访问路径
/substories/searchassignedtomystorycalendar

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Bug相关需求
#### 访问路径
/substories/fetchbugstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询Bug相关需求
#### 访问路径
/substories/searchbugstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本关联已完成的需求（选择数据源）
#### 访问路径
/substories/fetchbuildlinkcompletedstories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询版本关联已完成的需求（选择数据源）
#### 访问路径
/substories/searchbuildlinkcompletedstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本可关联的需求（产品内）
#### 访问路径
/substories/fetchbuildlinkablestories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询版本可关联的需求（产品内）
#### 访问路径
/substories/searchbuildlinkablestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取版本相关需求
#### 访问路径
/substories/fetchbuildstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询获取版本相关需求
#### 访问路径
/substories/searchbuildstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取通过模块查询
#### 访问路径
/substories/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询通过模块查询
#### 访问路径
/substories/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取相关用例需求
#### 访问路径
/substories/fetchcasestory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询相关用例需求
#### 访问路径
/substories/searchcasestory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/substories/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/substories/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取ES批量的导入
#### 访问路径
/substories/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询ES批量的导入
#### 访问路径
/substories/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取产品需求
#### 访问路径
/substories/fetchgetproductstories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询获取产品需求
#### 访问路径
/substories/searchgetproductstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我代理的需求
#### 访问路径
/substories/fetchmyagentstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询我代理的需求
#### 访问路径
/substories/searchmyagentstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取所创建需求数和对应的优先级及状态
#### 访问路径
/substories/fetchmycuropenedstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询所创建需求数和对应的优先级及状态
#### 访问路径
/substories/searchmycuropenedstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的收藏
#### 访问路径
/substories/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询我的收藏
#### 访问路径
/substories/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取计划关联需求(去除已关联)
#### 访问路径
/substories/fetchnotcurplanlinkstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询计划关联需求(去除已关联)
#### 访问路径
/substories/searchnotcurplanlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据查询
#### 访问路径
/substories/fetchparentdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询数据查询
#### 访问路径
/substories/searchparentdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据查询
#### 访问路径
/substories/fetchparentdefaultq

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询数据查询
#### 访问路径
/substories/searchparentdefaultq

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目关联需求
#### 访问路径
/substories/fetchprojectlinkstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询项目关联需求
#### 访问路径
/substories/searchprojectlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目相关需求
#### 访问路径
/substories/fetchprojectstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询项目相关需求
#### 访问路径
/substories/searchprojectstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本可关联的完成的需求
#### 访问路径
/substories/fetchreleaselinkablestories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询版本可关联的完成的需求
#### 访问路径
/substories/searchreleaselinkablestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取产品发布相关需求
#### 访问路径
/substories/fetchreleasestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询获取产品发布相关需求
#### 访问路径
/substories/searchreleasestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取通过模块查询
#### 访问路径
/substories/fetchreportstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询通过模块查询
#### 访问路径
/substories/searchreportstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取产品发布相关需求
#### 访问路径
/substories/fetchstorychild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询获取产品发布相关需求
#### 访问路径
/substories/searchstorychild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取产品发布相关需求
#### 访问路径
/substories/fetchstoryrelated

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询获取产品发布相关需求
#### 访问路径
/substories/searchstoryrelated

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取需求细分
#### 访问路径
/substories/fetchsubstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询需求细分
#### 访问路径
/substories/searchsubstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务相关需求
#### 访问路径
/substories/fetchtaskrelatedstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询任务相关需求
#### 访问路径
/substories/searchtaskrelatedstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取默认（全部数据）
#### 访问路径
/substories/fetchview

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 查询默认（全部数据）
#### 访问路径
/substories/searchview

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立需求
#### 访问路径
/stories/{story_id}/substories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 根据批量建立需求
#### 访问路径
/stories/{story_id}/substories/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substorydtos | List<[SubStoryDTO](#SubStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 根据批量更新需求
#### 访问路径
/stories/{story_id}/substories/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substorydtos | List<[SubStoryDTO](#SubStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除需求
#### 访问路径
/stories/{story_id}/substories/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | ids | List<Long> | 需求主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 激活
#### 访问路径
/stories/{story_id}/substories/{substory_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 全部推送
#### 访问路径
/stories/{story_id}/substories/{substory_id}/allpush

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 指派
#### 访问路径
/stories/{story_id}/substories/{substory_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量指派
#### 访问路径
/stories/{story_id}/substories/{substory_id}/batchassignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量变更平台/分支
#### 访问路径
/stories/{story_id}/substories/{substory_id}/batchchangebranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量变更模块
#### 访问路径
/stories/{story_id}/substories/{substory_id}/batchchangemodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量关联计划
#### 访问路径
/stories/{story_id}/substories/{substory_id}/batchchangeplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量变更阶段
#### 访问路径
/stories/{story_id}/substories/{substory_id}/batchchangestage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量关闭
#### 访问路径
/stories/{story_id}/substories/{substory_id}/batchclose

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量评审
#### 访问路径
/stories/{story_id}/substories/{substory_id}/batchreview

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 计划批量解除关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### bug转需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/bugtostory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 版本批量解除关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/buildbatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/buildlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 版本解除关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/buildunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 版本解除关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/buildunlinkstorys

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 变更
#### 访问路径
/stories/{story_id}/substories/{substory_id}/change

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 根据检查需求
#### 访问路径
/stories/{story_id}/substories/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/stories/{story_id}/substories/{substory_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 生成任务
#### 访问路径
/stories/{story_id}/substories/{substory_id}/createtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 获取需求描述
#### 访问路径
/stories/{story_id}/substories/{substory_id}/getstoryspec

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 获取需求描述
#### 访问路径
/stories/{story_id}/substories/{substory_id}/getstoryspecs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目关联需求-按计划关联
#### 访问路径
/stories/{story_id}/substories/{substory_id}/importplanstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 计划关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目批量解除关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/projectbatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/projectlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目解除关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/projectunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目解除关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/projectunlinkstorys

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 推送
#### 访问路径
/stories/{story_id}/substories/{substory_id}/push

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发布批量解除关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/releasebatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发布关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/releaselinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发布解除关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/releaseunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 重置由谁评审
#### 访问路径
/stories/{story_id}/substories/{substory_id}/resetreviewedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 评审
#### 访问路径
/stories/{story_id}/substories/{substory_id}/review

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 根据保存需求
#### 访问路径
/stories/{story_id}/substories/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存需求
#### 访问路径
/stories/{story_id}/substories/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substorydtos | List<[SubStoryDTO](#SubStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/stories/{story_id}/substories/{substory_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发送消息前置处理
#### 访问路径
/stories/{story_id}/substories/{substory_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 需求收藏
#### 访问路径
/stories/{story_id}/substories/{substory_id}/storyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 取消收藏
#### 访问路径
/stories/{story_id}/substories/{substory_id}/storynfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 同步Ibz平台实体
#### 访问路径
/stories/{story_id}/substories/{substory_id}/syncfromibiz

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 计划解除关联需求
#### 访问路径
/stories/{story_id}/substories/{substory_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | substory_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 根据获取指派给我的需求
#### 访问路径
/stories/{story_id}/substories/fetchassignedtomystory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询指派给我的需求
#### 访问路径
/stories/{story_id}/substories/searchassignedtomystory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我的需求（日历）
#### 访问路径
/stories/{story_id}/substories/fetchassignedtomystorycalendar

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询指派给我的需求（日历）
#### 访问路径
/stories/{story_id}/substories/searchassignedtomystorycalendar

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关需求
#### 访问路径
/stories/{story_id}/substories/fetchbugstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询Bug相关需求
#### 访问路径
/stories/{story_id}/substories/searchbugstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联已完成的需求（选择数据源）
#### 访问路径
/stories/{story_id}/substories/fetchbuildlinkcompletedstories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询版本关联已完成的需求（选择数据源）
#### 访问路径
/stories/{story_id}/substories/searchbuildlinkcompletedstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本可关联的需求（产品内）
#### 访问路径
/stories/{story_id}/substories/fetchbuildlinkablestories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询版本可关联的需求（产品内）
#### 访问路径
/stories/{story_id}/substories/searchbuildlinkablestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取版本相关需求
#### 访问路径
/stories/{story_id}/substories/fetchbuildstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询获取版本相关需求
#### 访问路径
/stories/{story_id}/substories/searchbuildstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/stories/{story_id}/substories/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/stories/{story_id}/substories/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关用例需求
#### 访问路径
/stories/{story_id}/substories/fetchcasestory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询相关用例需求
#### 访问路径
/stories/{story_id}/substories/searchcasestory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/stories/{story_id}/substories/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/stories/{story_id}/substories/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/stories/{story_id}/substories/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/stories/{story_id}/substories/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品需求
#### 访问路径
/stories/{story_id}/substories/fetchgetproductstories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品需求
#### 访问路径
/stories/{story_id}/substories/searchgetproductstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的需求
#### 访问路径
/stories/{story_id}/substories/fetchmyagentstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询我代理的需求
#### 访问路径
/stories/{story_id}/substories/searchmyagentstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所创建需求数和对应的优先级及状态
#### 访问路径
/stories/{story_id}/substories/fetchmycuropenedstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询所创建需求数和对应的优先级及状态
#### 访问路径
/stories/{story_id}/substories/searchmycuropenedstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/stories/{story_id}/substories/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/stories/{story_id}/substories/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取计划关联需求(去除已关联)
#### 访问路径
/stories/{story_id}/substories/fetchnotcurplanlinkstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询计划关联需求(去除已关联)
#### 访问路径
/stories/{story_id}/substories/searchnotcurplanlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询
#### 访问路径
/stories/{story_id}/substories/fetchparentdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询数据查询
#### 访问路径
/stories/{story_id}/substories/searchparentdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询
#### 访问路径
/stories/{story_id}/substories/fetchparentdefaultq

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询数据查询
#### 访问路径
/stories/{story_id}/substories/searchparentdefaultq

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目关联需求
#### 访问路径
/stories/{story_id}/substories/fetchprojectlinkstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询项目关联需求
#### 访问路径
/stories/{story_id}/substories/searchprojectlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目相关需求
#### 访问路径
/stories/{story_id}/substories/fetchprojectstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询项目相关需求
#### 访问路径
/stories/{story_id}/substories/searchprojectstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本可关联的完成的需求
#### 访问路径
/stories/{story_id}/substories/fetchreleaselinkablestories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询版本可关联的完成的需求
#### 访问路径
/stories/{story_id}/substories/searchreleaselinkablestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品发布相关需求
#### 访问路径
/stories/{story_id}/substories/fetchreleasestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品发布相关需求
#### 访问路径
/stories/{story_id}/substories/searchreleasestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/stories/{story_id}/substories/fetchreportstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/stories/{story_id}/substories/searchreportstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品发布相关需求
#### 访问路径
/stories/{story_id}/substories/fetchstorychild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品发布相关需求
#### 访问路径
/stories/{story_id}/substories/searchstorychild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品发布相关需求
#### 访问路径
/stories/{story_id}/substories/fetchstoryrelated

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品发布相关需求
#### 访问路径
/stories/{story_id}/substories/searchstoryrelated

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取需求细分
#### 访问路径
/stories/{story_id}/substories/fetchsubstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询需求细分
#### 访问路径
/stories/{story_id}/substories/searchsubstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务相关需求
#### 访问路径
/stories/{story_id}/substories/fetchtaskrelatedstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询任务相关需求
#### 访问路径
/stories/{story_id}/substories/searchtaskrelatedstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取默认（全部数据）
#### 访问路径
/stories/{story_id}/substories/fetchview

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询默认（全部数据）
#### 访问路径
/stories/{story_id}/substories/searchview

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 根据批量建立需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substorydtos | List<[SubStoryDTO](#SubStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 根据批量更新需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substorydtos | List<[SubStoryDTO](#SubStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | ids | List<Long> | 需求主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 激活
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 全部推送
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/allpush

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 指派
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量指派
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchassignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量变更平台/分支
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchchangebranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量变更模块
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchchangemodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量关联计划
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchchangeplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量变更阶段
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchchangestage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量关闭
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchclose

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 批量评审
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchreview

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 计划批量解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### bug转需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/bugtostory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 版本批量解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/buildbatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/buildlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 版本解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/buildunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 版本解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/buildunlinkstorys

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 变更
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/change

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 根据检查需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 生成任务
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/createtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 获取需求描述
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/getstoryspec

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 获取需求描述
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/getstoryspecs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目关联需求-按计划关联
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/importplanstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 计划关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目批量解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/projectbatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/projectlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/projectunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 项目解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/projectunlinkstorys

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 推送
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/push

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发布批量解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/releasebatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发布关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/releaselinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发布解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/releaseunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 重置由谁评审
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/resetreviewedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 评审
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/review

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 根据保存需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substorydtos | List<[SubStoryDTO](#SubStoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 发送消息前置处理
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 需求收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/storyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 取消收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/storynfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 同步Ibz平台实体
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/syncfromibiz

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 计划解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/{substory_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | substory_id | Long | 需求主键ID |
| 4 | substorydto | [SubStoryDTO](#SubStoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubStoryDTO](#SubStoryDTO)：需求实体传输对象 |

### 根据获取指派给我的需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchassignedtomystory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询指派给我的需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchassignedtomystory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我的需求（日历）
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchassignedtomystorycalendar

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询指派给我的需求（日历）
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchassignedtomystorycalendar

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchbugstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询Bug相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchbugstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联已完成的需求（选择数据源）
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchbuildlinkcompletedstories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询版本关联已完成的需求（选择数据源）
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchbuildlinkcompletedstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本可关联的需求（产品内）
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchbuildlinkablestories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询版本可关联的需求（产品内）
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchbuildlinkablestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取版本相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchbuildstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询获取版本相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchbuildstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关用例需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchcasestory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询相关用例需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchcasestory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchgetproductstories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchgetproductstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchmyagentstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询我代理的需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchmyagentstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所创建需求数和对应的优先级及状态
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchmycuropenedstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询所创建需求数和对应的优先级及状态
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchmycuropenedstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取计划关联需求(去除已关联)
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchnotcurplanlinkstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询计划关联需求(去除已关联)
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchnotcurplanlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchparentdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询数据查询
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchparentdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchparentdefaultq

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询数据查询
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchparentdefaultq

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchprojectlinkstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询项目关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchprojectlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchprojectstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询项目相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchprojectstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本可关联的完成的需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchreleaselinkablestories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询版本可关联的完成的需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchreleaselinkablestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchreleasestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchreleasestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchreportstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchreportstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchstorychild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchstorychild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchstoryrelated

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchstoryrelated

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取需求细分
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchsubstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询需求细分
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchsubstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchtaskrelatedstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询任务相关需求
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchtaskrelatedstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取默认（全部数据）
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/fetchview

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象列表 |

### 根据查询默认（全部数据）
#### 访问路径
/products/{product_id}/stories/{story_id}/substories/searchview

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | story_id | Long | 需求主键ID |
| 3 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubStoryDTO](#SubStoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### SubStoryDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | assignedto | String | 允许 | 指派给 |
| 2 | modulename1 | String | 允许 | 所属模块名称 |
| 3 | files | String | 允许 | 附件 |
| 4 | childstories | String | 允许 | 细分需求 |
| 5 | ibiz_id | String | 允许 | IBIZ标识 |
| 6 | plan | String | 允许 | 所属计划 |
| 7 | version | Integer | 允许 | 版本号 |
| 8 | assigneddate | Timestamp | 允许 | 指派日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 9 | storypoints | String | 允许 | 故事点 |
| 10 | sourcename | String | 允许 | 来源对象名称 |
| 11 | storyprovidedate | Timestamp | 允许 | 需求提供时间<br>时间格式：yyyy-MM-dd |
| 12 | isleaf | String | 允许 | 是否子需求 |
| 13 | pri | Integer | 允许 | 优先级 |
| 14 | sourceid | String | 允许 | 来源对象标识 |
| 15 | linkstories | String | 允许 | 相关需求 |
| 16 | assessresult | String | 允许 | 评审结果 |
| 17 | status | String | 允许 | 当前状态 |
| 18 | mailtopk | String | 允许 | 抄送给 |
| 19 | estimate | Double | 允许 | 预计工时 |
| 20 | noticeusers | String | 允许 | 消息通知用户 |
| 21 | revieweddate | Timestamp | 允许 | 评审时间<br>时间格式：yyyy-MM-dd |
| 22 | title | String | 不可 | 需求名称 |
| 23 | mailtoconact | String | 允许 | 联系人 |
| 24 | sourcenote | String | 允许 | 来源备注 |
| 25 | versionc | String | 允许 | 版本号 |
| 26 | reviewedby | String | 允许 | 由谁评审 |
| 27 | substatus | String | 允许 | 子状态 |
| 28 | stagedby | String | 允许 | 设置阶段者 |
| 29 | openedby | String | 允许 | 由谁创建 |
| 30 | openeddate | Timestamp | 允许 | 创建日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 31 | id | Long | 不可 | 编号 |
| 32 | ibiz_sourceobject | String | 允许 | 来源对象 |
| 33 | source | String | 允许 | 需求来源 |
| 34 | storylatestfinisheddate | Timestamp | 允许 | 需求最晚完成时间<br>时间格式：yyyy-MM-dd |
| 35 | neednotreview | String | 允许 | 不需要评审 |
| 36 | ischild | String | 允许 | 是否可以细分 |
| 37 | closedreason | String | 允许 | 关闭原因 |
| 38 | color | String | 允许 | 标题颜色 |
| 39 | orgid | String | 允许 | orgid |
| 40 | mailto | String | 允许 | 抄送给 |
| 41 | isfavorites | String | 允许 | 是否收藏 |
| 42 | sourceobject | String | 允许 | 来源对象 |
| 43 | deleted | String | 允许 | 已删除 |
| 44 | keywords | String | 允许 | 关键词 |
| 45 | lasteditedby | String | 允许 | 最后修改 |
| 46 | stage | String | 允许 | 所处阶段 |
| 47 | project | Long | 允许 | 项目 |
| 48 | closeddate | Timestamp | 允许 | 关闭日期	<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 49 | spec | String | 允许 | 需求描述 |
| 50 | ibiz_sourcename | String | 允许 | 来源对象名称 |
| 51 | assignedtopk | String | 允许 | 指派给（选择） |
| 52 | comment | String | 允许 | 备注 |
| 53 | acllist | String | 允许 | acllist |
| 54 | verify | String | 允许 | 验收标准 |
| 55 | closedby | String | 允许 | 由谁关闭 |
| 56 | acl | String | 允许 | acl |
| 57 | result | String | 允许 | 评审结果 |
| 58 | type | String | 允许 | 需求类型 |
| 59 | lastediteddate | Timestamp | 允许 | 最后修改日期<br>时间格式：yyyy-MM-dd HH:mm:ss |
| 60 | ibiz_sourceid | String | 允许 | 来源对象标识 |
| 61 | preversion | Integer | 允许 | 之前的版本 |
| 62 | storyprovider | String | 允许 | 需求提供人 |
| 63 | mdeptid | String | 允许 | MDEPTID |
| 64 | path | String | 允许 | 模块路径 |
| 65 | parentname | String | 允许 | 父需求名称 |
| 66 | modulename | String | 允许 | 所属模块名称 |
| 67 | productname | String | 允许 | 产品名称 |
| 68 | branchname | String | 允许 | 平台/分支 |
| 69 | frombug | Long | 允许 | 来源Bug |
| 70 | parent | Long | 允许 | 父需求 |
| 71 | module | Long | 允许 | 所属模块 |
| 72 | product | Long | 允许 | 所属产品 |
| 73 | duplicatestory | Long | 允许 | 重复需求ID |
| 74 | branch | Long | 允许 | 平台/分支 |
| 75 | tobug | Long | 允许 | 转Bug |
| 76 | <动态属性> | Object | 允许 | 支持动态属性 |

#### StorySearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_assignedto_eq | String | 允许 | 条件字段：assignedto<br>条件组合方式：`=` |
| 2 | n_plan_eq | String | 允许 | 条件字段：plan<br>条件组合方式：`=` |
| 3 | n_plan_noteq | String | 允许 | 条件字段：plan<br>条件组合方式：`!=`或者`<>` |
| 4 | n_storypoints_eq | String | 允许 | 条件字段：storypoints<br>条件组合方式：`=` |
| 5 | n_pri_eq | Integer | 允许 | 条件字段：pri<br>条件组合方式：`=` |
| 6 | n_pri_gtandeq | Integer | 允许 | 条件字段：pri<br>条件组合方式：`>=` |
| 7 | n_pri_ltandeq | Integer | 允许 | 条件字段：pri<br>条件组合方式：`<=` |
| 8 | n_assessresult_eq | String | 允许 | 条件字段：assessresult<br>条件组合方式：`=` |
| 9 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 10 | n_status_noteq | String | 允许 | 条件字段：status<br>条件组合方式：`!=`或者`<>` |
| 11 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 12 | n_reviewedby_eq | String | 允许 | 条件字段：reviewedby<br>条件组合方式：`=` |
| 13 | n_stagedby_eq | String | 允许 | 条件字段：stagedby<br>条件组合方式：`=` |
| 14 | n_openedby_eq | String | 允许 | 条件字段：openedby<br>条件组合方式：`=` |
| 15 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 16 | n_id_noteq | Long | 允许 | 条件字段：id<br>条件组合方式：`!=`或者`<>` |
| 17 | n_source_eq | String | 允许 | 条件字段：source<br>条件组合方式：`=` |
| 18 | n_closedreason_eq | String | 允许 | 条件字段：closedreason<br>条件组合方式：`=` |
| 19 | n_color_eq | String | 允许 | 条件字段：color<br>条件组合方式：`=` |
| 20 | n_keywords_like | String | 允许 | 条件字段：keywords<br>条件组合方式：`%like%` |
| 21 | n_lasteditedby_eq | String | 允许 | 条件字段：lasteditedby<br>条件组合方式：`=` |
| 22 | n_stage_eq | String | 允许 | 条件字段：stage<br>条件组合方式：`=` |
| 23 | n_stage_noteq | String | 允许 | 条件字段：stage<br>条件组合方式：`!=`或者`<>` |
| 24 | n_closedby_eq | String | 允许 | 条件字段：closedby<br>条件组合方式：`=` |
| 25 | n_result_eq | String | 允许 | 条件字段：result<br>条件组合方式：`=` |
| 26 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 27 | n_preversion_eq | Integer | 允许 | 条件字段：preversion<br>条件组合方式：`=` |
| 28 | n_path_like | String | 允许 | 条件字段：path<br>条件组合方式：`%like%` |
| 29 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 30 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 31 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 32 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 33 | n_prodoctname_eq | String | 允许 | 条件字段：prodoctname<br>条件组合方式：`=` |
| 34 | n_prodoctname_like | String | 允许 | 条件字段：prodoctname<br>条件组合方式：`%like%` |
| 35 | n_branchname_eq | String | 允许 | 条件字段：branchname<br>条件组合方式：`=` |
| 36 | n_branchname_like | String | 允许 | 条件字段：branchname<br>条件组合方式：`%like%` |
| 37 | n_frombug_eq | Long | 允许 | 条件字段：frombug<br>条件组合方式：`=` |
| 38 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 39 | n_parent_gtandeq | Long | 允许 | 条件字段：parent<br>条件组合方式：`>=` |
| 40 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 41 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 42 | n_duplicatestory_eq | Long | 允许 | 条件字段：duplicatestory<br>条件组合方式：`=` |
| 43 | n_branch_eq | Long | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 44 | n_tobug_eq | Long | 允许 | 条件字段：tobug<br>条件组合方式：`=` |
| 45 | customcond | String | 允许 | 自定义查询条件 |
| 46 | customparams | String | 允许 | 自定义查询参数 |
| 47 | query | String | 允许 | 快速搜索 |
| 48 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 49 | page | int | 允许 | 当前页数<br>默认值0 |
| 50 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 51 | sort | String | 允许 | 排序 |
