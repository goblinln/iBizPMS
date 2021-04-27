# 服务接口-ZT_STORY
## 接口说明
需求

## 接口清单
### 新建需求
#### 访问路径
/stories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量新建需求
#### 访问路径
/stories/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | storydtos | List<[StoryDTO](#StoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新需求
#### 访问路径
/stories/{story_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量更新需求
#### 访问路径
/stories/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | storydtos | List<[StoryDTO](#StoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 删除需求
#### 访问路径
/stories/{story_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除需求
#### 访问路径
/stories/batch

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
/stories/{story_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 激活
#### 访问路径
/stories/{story_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 全部推送
#### 访问路径
/stories/{story_id}/allpush

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 指派
#### 访问路径
/stories/{story_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量指派
#### 访问路径
/stories/{story_id}/batchassignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量变更平台/分支
#### 访问路径
/stories/{story_id}/batchchangebranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量变更模块
#### 访问路径
/stories/{story_id}/batchchangemodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量关联计划
#### 访问路径
/stories/{story_id}/batchchangeplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量变更阶段
#### 访问路径
/stories/{story_id}/batchchangestage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量关闭
#### 访问路径
/stories/{story_id}/batchclose

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量评审
#### 访问路径
/stories/{story_id}/batchreview

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 计划批量解除关联需求
#### 访问路径
/stories/{story_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### bug转需求
#### 访问路径
/stories/{story_id}/bugtostory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 版本批量解除关联需求
#### 访问路径
/stories/{story_id}/buildbatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目关联需求
#### 访问路径
/stories/{story_id}/buildlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 版本解除关联需求
#### 访问路径
/stories/{story_id}/buildunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 版本解除关联需求
#### 访问路径
/stories/{story_id}/buildunlinkstorys

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 变更
#### 访问路径
/stories/{story_id}/change

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 检查需求
#### 访问路径
/stories/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/stories/{story_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 生成任务
#### 访问路径
/stories/{story_id}/createtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 获取需求描述
#### 访问路径
/stories/{story_id}/getstoryspec

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 获取需求描述
#### 访问路径
/stories/{story_id}/getstoryspecs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目关联需求-按计划关联
#### 访问路径
/stories/{story_id}/importplanstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 计划关联需求
#### 访问路径
/stories/{story_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目批量解除关联需求
#### 访问路径
/stories/{story_id}/projectbatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目关联需求
#### 访问路径
/stories/{story_id}/projectlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目解除关联需求
#### 访问路径
/stories/{story_id}/projectunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目解除关联需求
#### 访问路径
/stories/{story_id}/projectunlinkstorys

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 推送
#### 访问路径
/stories/{story_id}/push

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 发布批量解除关联需求
#### 访问路径
/stories/{story_id}/releasebatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 发布关联需求
#### 访问路径
/stories/{story_id}/releaselinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 发布解除关联需求
#### 访问路径
/stories/{story_id}/releaseunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 重置由谁评审
#### 访问路径
/stories/{story_id}/resetreviewedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 评审
#### 访问路径
/stories/{story_id}/review

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 保存需求
#### 访问路径
/stories/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存需求
#### 访问路径
/stories/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | storydtos | List<[StoryDTO](#StoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/stories/{story_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 发送消息前置处理
#### 访问路径
/stories/{story_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 需求收藏
#### 访问路径
/stories/{story_id}/storyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 取消收藏
#### 访问路径
/stories/{story_id}/storynfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 同步Ibz平台实体
#### 访问路径
/stories/{story_id}/syncfromibiz

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 计划解除关联需求
#### 访问路径
/stories/{story_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | story_id | Long | 需求主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 获取指派给我的需求
#### 访问路径
/stories/fetchassignedtomystory

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询指派给我的需求
#### 访问路径
/stories/searchassignedtomystory

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取指派给我的需求（日历）
#### 访问路径
/stories/fetchassignedtomystorycalendar

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询指派给我的需求（日历）
#### 访问路径
/stories/searchassignedtomystorycalendar

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取Bug相关需求
#### 访问路径
/stories/fetchbugstory

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询Bug相关需求
#### 访问路径
/stories/searchbugstory

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本关联已完成的需求（选择数据源）
#### 访问路径
/stories/fetchbuildlinkcompletedstories

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询版本关联已完成的需求（选择数据源）
#### 访问路径
/stories/searchbuildlinkcompletedstories

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本可关联的需求（产品内）
#### 访问路径
/stories/fetchbuildlinkablestories

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询版本可关联的需求（产品内）
#### 访问路径
/stories/searchbuildlinkablestories

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取版本相关需求
#### 访问路径
/stories/fetchbuildstories

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询获取版本相关需求
#### 访问路径
/stories/searchbuildstories

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取通过模块查询
#### 访问路径
/stories/fetchbymodule

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询通过模块查询
#### 访问路径
/stories/searchbymodule

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取相关用例需求
#### 访问路径
/stories/fetchcasestory

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询相关用例需求
#### 访问路径
/stories/searchcasestory

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取DEFAULT
#### 访问路径
/stories/fetchdefault

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/stories/searchdefault

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取ES批量的导入
#### 访问路径
/stories/fetchesbulk

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询ES批量的导入
#### 访问路径
/stories/searchesbulk

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取产品需求
#### 访问路径
/stories/fetchgetproductstories

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询获取产品需求
#### 访问路径
/stories/searchgetproductstories

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我代理的需求
#### 访问路径
/stories/fetchmyagentstory

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询我代理的需求
#### 访问路径
/stories/searchmyagentstory

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取所创建需求数和对应的优先级及状态
#### 访问路径
/stories/fetchmycuropenedstory

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询所创建需求数和对应的优先级及状态
#### 访问路径
/stories/searchmycuropenedstory

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取我的收藏
#### 访问路径
/stories/fetchmyfavorites

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询我的收藏
#### 访问路径
/stories/searchmyfavorites

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取计划关联需求(去除已关联)
#### 访问路径
/stories/fetchnotcurplanlinkstory

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询计划关联需求(去除已关联)
#### 访问路径
/stories/searchnotcurplanlinkstory

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据查询
#### 访问路径
/stories/fetchparentdefault

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询数据查询
#### 访问路径
/stories/searchparentdefault

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取数据查询
#### 访问路径
/stories/fetchparentdefaultq

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询数据查询
#### 访问路径
/stories/searchparentdefaultq

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目关联需求
#### 访问路径
/stories/fetchprojectlinkstory

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询项目关联需求
#### 访问路径
/stories/searchprojectlinkstory

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取项目相关需求
#### 访问路径
/stories/fetchprojectstories

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询项目相关需求
#### 访问路径
/stories/searchprojectstories

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取版本可关联的完成的需求
#### 访问路径
/stories/fetchreleaselinkablestories

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询版本可关联的完成的需求
#### 访问路径
/stories/searchreleaselinkablestories

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取产品发布相关需求
#### 访问路径
/stories/fetchreleasestories

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询获取产品发布相关需求
#### 访问路径
/stories/searchreleasestories

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取通过模块查询
#### 访问路径
/stories/fetchreportstories

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询通过模块查询
#### 访问路径
/stories/searchreportstories

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取产品发布相关需求
#### 访问路径
/stories/fetchstorychild

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询获取产品发布相关需求
#### 访问路径
/stories/searchstorychild

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取获取产品发布相关需求
#### 访问路径
/stories/fetchstoryrelated

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询获取产品发布相关需求
#### 访问路径
/stories/searchstoryrelated

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取需求细分
#### 访问路径
/stories/fetchsubstory

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询需求细分
#### 访问路径
/stories/searchsubstory

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取任务相关需求
#### 访问路径
/stories/fetchtaskrelatedstory

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询任务相关需求
#### 访问路径
/stories/searchtaskrelatedstory

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取默认（全部数据）
#### 访问路径
/stories/fetchview

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
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 查询默认（全部数据）
#### 访问路径
/stories/searchview

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
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立需求
#### 访问路径
/products/{product_id}/stories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 根据批量建立需求
#### 访问路径
/products/{product_id}/stories/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | storydtos | List<[StoryDTO](#StoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新需求
#### 访问路径
/products/{product_id}/stories/{story_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 根据批量更新需求
#### 访问路径
/products/{product_id}/stories/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | storydtos | List<[StoryDTO](#StoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据删除需求
#### 访问路径
/products/{product_id}/stories/{story_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除需求
#### 访问路径
/products/{product_id}/stories/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 需求主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取需求
#### 访问路径
/products/{product_id}/stories/{story_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 激活
#### 访问路径
/products/{product_id}/stories/{story_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 全部推送
#### 访问路径
/products/{product_id}/stories/{story_id}/allpush

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 指派
#### 访问路径
/products/{product_id}/stories/{story_id}/assignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量指派
#### 访问路径
/products/{product_id}/stories/{story_id}/batchassignto

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量变更平台/分支
#### 访问路径
/products/{product_id}/stories/{story_id}/batchchangebranch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量变更模块
#### 访问路径
/products/{product_id}/stories/{story_id}/batchchangemodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量关联计划
#### 访问路径
/products/{product_id}/stories/{story_id}/batchchangeplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量变更阶段
#### 访问路径
/products/{product_id}/stories/{story_id}/batchchangestage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量关闭
#### 访问路径
/products/{product_id}/stories/{story_id}/batchclose

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 批量评审
#### 访问路径
/products/{product_id}/stories/{story_id}/batchreview

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 计划批量解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### bug转需求
#### 访问路径
/products/{product_id}/stories/{story_id}/bugtostory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 版本批量解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/buildbatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/buildlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 版本解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/buildunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 版本解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/buildunlinkstorys

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 变更
#### 访问路径
/products/{product_id}/stories/{story_id}/change

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 根据检查需求
#### 访问路径
/products/{product_id}/stories/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关闭
#### 访问路径
/products/{product_id}/stories/{story_id}/close

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 生成任务
#### 访问路径
/products/{product_id}/stories/{story_id}/createtasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 获取需求描述
#### 访问路径
/products/{product_id}/stories/{story_id}/getstoryspec

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 获取需求描述
#### 访问路径
/products/{product_id}/stories/{story_id}/getstoryspecs

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目关联需求-按计划关联
#### 访问路径
/products/{product_id}/stories/{story_id}/importplanstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 计划关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目批量解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/projectbatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/projectlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/projectunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 项目解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/projectunlinkstorys

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 推送
#### 访问路径
/products/{product_id}/stories/{story_id}/push

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 发布批量解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/releasebatchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 发布关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/releaselinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 发布解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/releaseunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 重置由谁评审
#### 访问路径
/products/{product_id}/stories/{story_id}/resetreviewedby

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 评审
#### 访问路径
/products/{product_id}/stories/{story_id}/review

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 根据保存需求
#### 访问路径
/products/{product_id}/stories/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存需求
#### 访问路径
/products/{product_id}/stories/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | storydtos | List<[StoryDTO](#StoryDTO)> | 需求实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 行为
#### 访问路径
/products/{product_id}/stories/{story_id}/sendmessage

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 发送消息前置处理
#### 访问路径
/products/{product_id}/stories/{story_id}/sendmsgpreprocess

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 需求收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/storyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 取消收藏
#### 访问路径
/products/{product_id}/stories/{story_id}/storynfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 同步Ibz平台实体
#### 访问路径
/products/{product_id}/stories/{story_id}/syncfromibiz

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 计划解除关联需求
#### 访问路径
/products/{product_id}/stories/{story_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | story_id | Long | 需求主键ID |
| 3 | storydto | [StoryDTO](#StoryDTO) | 需求实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [StoryDTO](#StoryDTO)：需求实体传输对象 |

### 根据获取指派给我的需求
#### 访问路径
/products/{product_id}/stories/fetchassignedtomystory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询指派给我的需求
#### 访问路径
/products/{product_id}/stories/searchassignedtomystory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取指派给我的需求（日历）
#### 访问路径
/products/{product_id}/stories/fetchassignedtomystorycalendar

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询指派给我的需求（日历）
#### 访问路径
/products/{product_id}/stories/searchassignedtomystorycalendar

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取Bug相关需求
#### 访问路径
/products/{product_id}/stories/fetchbugstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询Bug相关需求
#### 访问路径
/products/{product_id}/stories/searchbugstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本关联已完成的需求（选择数据源）
#### 访问路径
/products/{product_id}/stories/fetchbuildlinkcompletedstories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询版本关联已完成的需求（选择数据源）
#### 访问路径
/products/{product_id}/stories/searchbuildlinkcompletedstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本可关联的需求（产品内）
#### 访问路径
/products/{product_id}/stories/fetchbuildlinkablestories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询版本可关联的需求（产品内）
#### 访问路径
/products/{product_id}/stories/searchbuildlinkablestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取版本相关需求
#### 访问路径
/products/{product_id}/stories/fetchbuildstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询获取版本相关需求
#### 访问路径
/products/{product_id}/stories/searchbuildstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/products/{product_id}/stories/fetchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/products/{product_id}/stories/searchbymodule

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取相关用例需求
#### 访问路径
/products/{product_id}/stories/fetchcasestory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询相关用例需求
#### 访问路径
/products/{product_id}/stories/searchcasestory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/stories/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/stories/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取ES批量的导入
#### 访问路径
/products/{product_id}/stories/fetchesbulk

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询ES批量的导入
#### 访问路径
/products/{product_id}/stories/searchesbulk

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品需求
#### 访问路径
/products/{product_id}/stories/fetchgetproductstories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品需求
#### 访问路径
/products/{product_id}/stories/searchgetproductstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我代理的需求
#### 访问路径
/products/{product_id}/stories/fetchmyagentstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询我代理的需求
#### 访问路径
/products/{product_id}/stories/searchmyagentstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取所创建需求数和对应的优先级及状态
#### 访问路径
/products/{product_id}/stories/fetchmycuropenedstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询所创建需求数和对应的优先级及状态
#### 访问路径
/products/{product_id}/stories/searchmycuropenedstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取我的收藏
#### 访问路径
/products/{product_id}/stories/fetchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询我的收藏
#### 访问路径
/products/{product_id}/stories/searchmyfavorites

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取计划关联需求(去除已关联)
#### 访问路径
/products/{product_id}/stories/fetchnotcurplanlinkstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询计划关联需求(去除已关联)
#### 访问路径
/products/{product_id}/stories/searchnotcurplanlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询
#### 访问路径
/products/{product_id}/stories/fetchparentdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询数据查询
#### 访问路径
/products/{product_id}/stories/searchparentdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取数据查询
#### 访问路径
/products/{product_id}/stories/fetchparentdefaultq

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询数据查询
#### 访问路径
/products/{product_id}/stories/searchparentdefaultq

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目关联需求
#### 访问路径
/products/{product_id}/stories/fetchprojectlinkstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询项目关联需求
#### 访问路径
/products/{product_id}/stories/searchprojectlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目相关需求
#### 访问路径
/products/{product_id}/stories/fetchprojectstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询项目相关需求
#### 访问路径
/products/{product_id}/stories/searchprojectstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取版本可关联的完成的需求
#### 访问路径
/products/{product_id}/stories/fetchreleaselinkablestories

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询版本可关联的完成的需求
#### 访问路径
/products/{product_id}/stories/searchreleaselinkablestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/fetchreleasestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/searchreleasestories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取通过模块查询
#### 访问路径
/products/{product_id}/stories/fetchreportstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询通过模块查询
#### 访问路径
/products/{product_id}/stories/searchreportstories

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/fetchstorychild

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/searchstorychild

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/fetchstoryrelated

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询获取产品发布相关需求
#### 访问路径
/products/{product_id}/stories/searchstoryrelated

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取需求细分
#### 访问路径
/products/{product_id}/stories/fetchsubstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询需求细分
#### 访问路径
/products/{product_id}/stories/searchsubstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务相关需求
#### 访问路径
/products/{product_id}/stories/fetchtaskrelatedstory

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询任务相关需求
#### 访问路径
/products/{product_id}/stories/searchtaskrelatedstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取默认（全部数据）
#### 访问路径
/products/{product_id}/stories/fetchview

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[StoryDTO](#StoryDTO)>：需求实体传输对象列表 |

### 根据查询默认（全部数据）
#### 访问路径
/products/{product_id}/stories/searchview

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [StorySearchContext](#StorySearchContext) | 需求查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[StoryDTO](#StoryDTO)>：需求实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### StoryDTO
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
| 18 | n_ischild_eq | String | 允许 | 条件字段：ischild<br>条件组合方式：`=` |
| 19 | n_closedreason_eq | String | 允许 | 条件字段：closedreason<br>条件组合方式：`=` |
| 20 | n_color_eq | String | 允许 | 条件字段：color<br>条件组合方式：`=` |
| 21 | n_isfavorites_eq | String | 允许 | 条件字段：isfavorites<br>条件组合方式：`=` |
| 22 | n_keywords_like | String | 允许 | 条件字段：keywords<br>条件组合方式：`%like%` |
| 23 | n_lasteditedby_eq | String | 允许 | 条件字段：lasteditedby<br>条件组合方式：`=` |
| 24 | n_stage_eq | String | 允许 | 条件字段：stage<br>条件组合方式：`=` |
| 25 | n_stage_noteq | String | 允许 | 条件字段：stage<br>条件组合方式：`!=`或者`<>` |
| 26 | n_closedby_eq | String | 允许 | 条件字段：closedby<br>条件组合方式：`=` |
| 27 | n_result_eq | String | 允许 | 条件字段：result<br>条件组合方式：`=` |
| 28 | n_type_eq | String | 允许 | 条件字段：type<br>条件组合方式：`=` |
| 29 | n_preversion_eq | Integer | 允许 | 条件字段：preversion<br>条件组合方式：`=` |
| 30 | n_path_like | String | 允许 | 条件字段：path<br>条件组合方式：`%like%` |
| 31 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 32 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 33 | n_modulename_eq | String | 允许 | 条件字段：modulename<br>条件组合方式：`=` |
| 34 | n_modulename_like | String | 允许 | 条件字段：modulename<br>条件组合方式：`%like%` |
| 35 | n_prodoctname_eq | String | 允许 | 条件字段：prodoctname<br>条件组合方式：`=` |
| 36 | n_prodoctname_like | String | 允许 | 条件字段：prodoctname<br>条件组合方式：`%like%` |
| 37 | n_branchname_eq | String | 允许 | 条件字段：branchname<br>条件组合方式：`=` |
| 38 | n_branchname_like | String | 允许 | 条件字段：branchname<br>条件组合方式：`%like%` |
| 39 | n_frombug_eq | Long | 允许 | 条件字段：frombug<br>条件组合方式：`=` |
| 40 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 41 | n_parent_gtandeq | Long | 允许 | 条件字段：parent<br>条件组合方式：`>=` |
| 42 | n_module_eq | Long | 允许 | 条件字段：module<br>条件组合方式：`=` |
| 43 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 44 | n_duplicatestory_eq | Long | 允许 | 条件字段：duplicatestory<br>条件组合方式：`=` |
| 45 | n_branch_eq | Long | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 46 | n_tobug_eq | Long | 允许 | 条件字段：tobug<br>条件组合方式：`=` |
| 47 | customcond | String | 允许 | 自定义查询条件 |
| 48 | customparams | String | 允许 | 自定义查询参数 |
| 49 | query | String | 允许 | 快速搜索 |
| 50 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 51 | page | int | 允许 | 当前页数<br>默认值0 |
| 52 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 53 | sort | String | 允许 | 排序 |
