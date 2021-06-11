# 服务接口-ZT_RELEASE
## 接口说明
发布

## 接口清单
### 新建发布
#### 访问路径
/releases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 批量新建发布
#### 访问路径
/releases/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | releasedtos | List<[ReleaseDTO](#ReleaseDTO)> | 发布实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 获取发布
#### 访问路径
/releases/{release_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 删除发布
#### 访问路径
/releases/{release_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量删除发布
#### 访问路径
/releases/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | ids | List<Long> | 发布主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 更新发布
#### 访问路径
/releases/{release_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 批量更新发布
#### 访问路径
/releases/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | releasedtos | List<[ReleaseDTO](#ReleaseDTO)> | 发布实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 状态变更（激活）
#### 访问路径
/releases/{release_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/releases/{release_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 状态变更
#### 访问路径
/releases/{release_id}/changestatus

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 检查发布
#### 访问路径
/releases/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关联Bug
#### 访问路径
/releases/{release_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 关联Bug（解决Bug）
#### 访问路径
/releases/{release_id}/linkbugbybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 关联Bug（遗留Bug）
#### 访问路径
/releases/{release_id}/linkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 关联需求
#### 访问路径
/releases/{release_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 移动端发布计数器
#### 访问路径
/releases/{release_id}/mobreleasecounter

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 一键发布
#### 访问路径
/releases/{release_id}/oneclickrelease

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 保存发布
#### 访问路径
/releases/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量保存发布
#### 访问路径
/releases/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | releasedtos | List<[ReleaseDTO](#ReleaseDTO)> | 发布实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 状态变更（停止维护）
#### 访问路径
/releases/{release_id}/terminate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 解除关联Bug
#### 访问路径
/releases/{release_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | release_id | Long | 发布主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 获取DEFAULT
#### 访问路径
/releases/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ReleaseSearchContext](#ReleaseSearchContext) | 发布查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ReleaseDTO](#ReleaseDTO)>：发布实体传输对象列表 |

### 查询DEFAULT
#### 访问路径
/releases/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ReleaseSearchContext](#ReleaseSearchContext) | 发布查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ReleaseDTO](#ReleaseDTO)>：发布实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 获取测试报告关联发布
#### 访问路径
/releases/fetchreportrelease

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ReleaseSearchContext](#ReleaseSearchContext) | 发布查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ReleaseDTO](#ReleaseDTO)>：发布实体传输对象列表 |

### 查询测试报告关联发布
#### 访问路径
/releases/searchreportrelease

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | context | [ReleaseSearchContext](#ReleaseSearchContext) | 发布查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ReleaseDTO](#ReleaseDTO)>：发布实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立发布
#### 访问路径
/products/{product_id}/releases

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 根据批量建立发布
#### 访问路径
/products/{product_id}/releases/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | releasedtos | List<[ReleaseDTO](#ReleaseDTO)> | 发布实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取发布
#### 访问路径
/products/{product_id}/releases/{release_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 根据删除发布
#### 访问路径
/products/{product_id}/releases/{release_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除发布
#### 访问路径
/products/{product_id}/releases/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | ids | List<Long> | 发布主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新发布
#### 访问路径
/products/{product_id}/releases/{release_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 根据批量更新发布
#### 访问路径
/products/{product_id}/releases/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | releasedtos | List<[ReleaseDTO](#ReleaseDTO)> | 发布实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 状态变更（激活）
#### 访问路径
/products/{product_id}/releases/{release_id}/activate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 批量解除关联Bug
#### 访问路径
/products/{product_id}/releases/{release_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 状态变更
#### 访问路径
/products/{product_id}/releases/{release_id}/changestatus

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 根据检查发布
#### 访问路径
/products/{product_id}/releases/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 关联Bug
#### 访问路径
/products/{product_id}/releases/{release_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 关联Bug（解决Bug）
#### 访问路径
/products/{product_id}/releases/{release_id}/linkbugbybug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 关联Bug（遗留Bug）
#### 访问路径
/products/{product_id}/releases/{release_id}/linkbugbyleftbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 关联需求
#### 访问路径
/products/{product_id}/releases/{release_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 移动端发布计数器
#### 访问路径
/products/{product_id}/releases/{release_id}/mobreleasecounter

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 一键发布
#### 访问路径
/products/{product_id}/releases/{release_id}/oneclickrelease

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 根据保存发布
#### 访问路径
/products/{product_id}/releases/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存发布
#### 访问路径
/products/{product_id}/releases/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | releasedtos | List<[ReleaseDTO](#ReleaseDTO)> | 发布实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 状态变更（停止维护）
#### 访问路径
/products/{product_id}/releases/{release_id}/terminate

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 解除关联Bug
#### 访问路径
/products/{product_id}/releases/{release_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | release_id | Long | 发布主键ID |
| 3 | releasedto | [ReleaseDTO](#ReleaseDTO) | 发布实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [ReleaseDTO](#ReleaseDTO)：发布实体传输对象 |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/releases/fetchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ReleaseSearchContext](#ReleaseSearchContext) | 发布查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ReleaseDTO](#ReleaseDTO)>：发布实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/releases/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ReleaseSearchContext](#ReleaseSearchContext) | 发布查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ReleaseDTO](#ReleaseDTO)>：发布实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取测试报告关联发布
#### 访问路径
/products/{product_id}/releases/fetchreportrelease

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ReleaseSearchContext](#ReleaseSearchContext) | 发布查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[ReleaseDTO](#ReleaseDTO)>：发布实体传输对象列表 |

### 根据查询测试报告关联发布
#### 访问路径
/products/{product_id}/releases/searchreportrelease

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |
| 2 | context | [ReleaseSearchContext](#ReleaseSearchContext) | 发布查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[ReleaseDTO](#ReleaseDTO)>：发布实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### ReleaseDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | stories | String | 允许 | 完成的需求 |
| 2 | marker | String | 允许 | 里程碑 |
| 3 | id | Long | 不可 | ID |
| 4 | sqlid | String | 允许 | 运行数据库 |
| 5 | leftbugs | String | 允许 | 遗留的Bug |
| 6 | bugs | String | 允许 | 解决的Bug |
| 7 | deleted | String | 允许 | 已删除 |
| 8 | noticeusers | String | 允许 | 消息通知用户 |
| 9 | rebuild | Integer | 允许 | 重新构建 |
| 10 | name | String | 不可 | 发布名称 |
| 11 | date | Timestamp | 允许 | 发布日期<br>时间格式：yyyy-MM-dd |
| 12 | status | String | 允许 | 状态 |
| 13 | releasetype | String | 允许 | 运行模式 |
| 14 | substatus | String | 允许 | 子状态 |
| 15 | backgroundid | String | 允许 | 后台体系 |
| 16 | desc | String | 允许 | 描述 |
| 17 | files | String | 允许 | 附件 |
| 18 | frontapplication | String | 允许 | 系统应用 |
| 19 | productname | String | 允许 | 产品名称 |
| 20 | builder | String | 允许 | 构建者 |
| 21 | buildname | String | 允许 | 版本 |
| 22 | builddate | Timestamp | 允许 | 打包日期<br>时间格式：yyyy-MM-dd |
| 23 | product | Long | 允许 | 产品 |
| 24 | build | Long | 允许 | 版本 |
| 25 | branch | Long | 允许 | 平台/分支 |
| 26 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ReleaseSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_marker_eq | String | 允许 | 条件字段：marker<br>条件组合方式：`=` |
| 2 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 3 | n_rebuild_eq | Integer | 允许 | 条件字段：rebuild<br>条件组合方式：`=` |
| 4 | n_name_like | String | 允许 | 条件字段：name<br>条件组合方式：`%like%` |
| 5 | n_date_ltandeq | Timestamp | 允许 | 条件字段：date<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 6 | n_status_eq | String | 允许 | 条件字段：status<br>条件组合方式：`=` |
| 7 | n_releasetype_eq | String | 允许 | 条件字段：releasetype<br>条件组合方式：`=` |
| 8 | n_productname_eq | String | 允许 | 条件字段：productname<br>条件组合方式：`=` |
| 9 | n_productname_like | String | 允许 | 条件字段：productname<br>条件组合方式：`%like%` |
| 10 | n_buildname_eq | String | 允许 | 条件字段：buildname<br>条件组合方式：`=` |
| 11 | n_buildname_like | String | 允许 | 条件字段：buildname<br>条件组合方式：`%like%` |
| 12 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 13 | n_build_eq | Long | 允许 | 条件字段：build<br>条件组合方式：`=` |
| 14 | n_branch_eq | Long | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 15 | customcond | String | 允许 | 自定义查询条件 |
| 16 | customparams | String | 允许 | 自定义查询参数 |
| 17 | query | String | 允许 | 快速搜索 |
| 18 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 19 | page | int | 允许 | 当前页数<br>默认值0 |
| 20 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 21 | sort | String | 允许 | 排序 |
