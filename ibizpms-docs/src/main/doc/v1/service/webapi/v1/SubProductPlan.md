# 服务接口-IBZ_SUBPRODUCTPLAN
## 接口说明
产品计划

## 接口清单
### 根据建立产品计划
#### 访问路径
/productplans/{productplan_id}/subproductplans

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据批量建立产品计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplandtos | List<[SubProductPlanDTO](#SubProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取产品计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据删除产品计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除产品计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | ids | List<Long> | 产品计划主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新产品计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据批量更新产品计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplandtos | List<[SubProductPlanDTO](#SubProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量解除关联Bug
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 批量解除关联需求
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据检查产品计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### EE激活计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eeactiveplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### EE取消计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecancelplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### EE关闭计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecloseplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### EE完成计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eefinishplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### EE暂停计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eepauseplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 继续计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eerestartplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### EE开始计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eestartplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 获取上一个计划的名称
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/getoldplanname

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 导入计划模板
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/importplantemplet

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 关联Bug
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 关联需求
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 关联任务
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linktask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 移动端产品计划计数器
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/mobproductplancounter

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据保存产品计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存产品计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplandtos | List<[SubProductPlanDTO](#SubProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 解除关联Bug
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 解除关联需求
#### 访问路径
/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | subproductplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据获取子计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchchildplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询子计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchchildplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取CurProductPlan
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchcurproductplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询CurProductPlan
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchcurproductplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取默认查询
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchdefaultparent

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询默认查询
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchdefaultparent

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取计划（代码表）
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchplancodelist

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询计划（代码表）
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchplancodelist

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目计划任务（项目管理-项目计划）
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchplantasks

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询项目计划任务（项目管理-项目计划）
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchplantasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目立项
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchprojectapp

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询项目立项
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchprojectapp

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目计划列表
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchprojectplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询项目计划列表
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchprojectplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取跟计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchrootplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询跟计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchrootplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchtaskplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询任务计划
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchtaskplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务计划（根计划）
#### 访问路径
/productplans/{productplan_id}/subproductplans/fetchtaskplanroot

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询任务计划（根计划）
#### 访问路径
/productplans/{productplan_id}/subproductplans/searchtaskplanroot

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | productplan_id | Long | 产品计划主键ID |
| 2 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据建立产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据批量建立产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/batch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplandtos | List<[SubProductPlanDTO](#SubProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据获取产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据删除产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量删除产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/batch

#### 请求方法
DELETE

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | ids | List<Long> | 产品计划主键ID列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据更新产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据批量更新产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/batch

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplandtos | List<[SubProductPlanDTO](#SubProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 批量解除关联Bug
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 批量解除关联需求
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/batchunlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据检查产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/checkkey

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### EE激活计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eeactiveplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### EE取消计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecancelplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### EE关闭计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eecloseplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### EE完成计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eefinishplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### EE暂停计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eepauseplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 继续计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eerestartplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### EE开始计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/eestartplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 获取上一个计划的名称
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/getoldplanname

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 导入计划模板
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/importplantemplet

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 关联Bug
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 关联需求
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 关联任务
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/linktask

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 移动端产品计划计数器
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/mobproductplancounter

#### 请求方法
PUT

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据保存产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/save

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 根据批量保存产品计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/savebatch

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplandtos | List<[SubProductPlanDTO](#SubProductPlanDTO)> | 产品计划实体传输对象列表 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | true：处理成功。false：处理失败。 |

### 解除关联Bug
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkbug

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 解除关联需求
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/{subproductplan_id}/unlinkstory

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | subproductplan_id | Long | 产品计划主键ID |
| 4 | subproductplandto | [SubProductPlanDTO](#SubProductPlanDTO) | 产品计划实体传输对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | [SubProductPlanDTO](#SubProductPlanDTO)：产品计划实体传输对象 |

### 根据获取子计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchchildplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询子计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchchildplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取CurProductPlan
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchcurproductplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询CurProductPlan
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchcurproductplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取DEFAULT
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchdefault

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询DEFAULT
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchdefault

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取默认查询
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchdefaultparent

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询默认查询
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchdefaultparent

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取计划（代码表）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchplancodelist

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询计划（代码表）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchplancodelist

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目计划任务（项目管理-项目计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchplantasks

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询项目计划任务（项目管理-项目计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchplantasks

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目立项
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchprojectapp

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询项目立项
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchprojectapp

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取项目计划列表
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchprojectplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询项目计划列表
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchprojectplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取跟计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchrootplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询跟计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchrootplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchtaskplan

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询任务计划
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchtaskplan

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

### 根据获取任务计划（根计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/fetchtaskplanroot

#### 请求方法
GET

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | List<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象列表 |

### 根据查询任务计划（根计划）
#### 访问路径
/products/{product_id}/productplans/{productplan_id}/subproductplans/searchtaskplanroot

#### 请求方法
POST

#### 参数说明
| 序号 | 参数名 | 参数类型 | 说明 |
| ---- | ---- | ---- | ---- |
| 1 | product_id | Long | 产品主键ID |/r/n| 2 | productplan_id | Long | 产品计划主键ID |
| 3 | context | [ProductPlanSearchContext](#ProductPlanSearchContext) | 产品计划查询条件对象 |

#### 返回说明
| 项目 | 说明 |
| ---- | ---- |
| 返回状态 | 200：请求成功。<br>401：用户未认证。<br>500：服务异常。 |
| 返回类型 | Page<[SubProductPlanDTO](#SubProductPlanDTO)>：产品计划实体传输对象分页对象<br>分页对象为`org.springframework.data.domain.Page` |

## 附录
### 数据类型说明
#### SubProductPlanDTO
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | taskscnt | Integer | 允许 | 总任务数 |
| 2 | estimatecnt | Integer | 允许 | 工时数 |
| 3 | comment | String | 允许 | 备注 |
| 4 | title | String | 不可 | 名称 |
| 5 | delaytaskscnt | Integer | 允许 | 延迟任务数 |
| 6 | oldtitle | String | 允许 | 上一次计划名称 |
| 7 | id | Long | 不可 | 编号 |
| 8 | begin | Timestamp | 允许 | 预计开始日期<br>时间格式：yyyy-MM-dd |
| 9 | statuss | String | 允许 | 状态 |
| 10 | desc | String | 允许 | 描述 |
| 11 | end | Timestamp | 允许 | 预计结束日期<br>时间格式：yyyy-MM-dd |
| 12 | delay | String | 允许 | 延期 |
| 13 | duration | String | 允许 | 持续时间 |
| 14 | beginstr | String | 允许 | 开始日期 |
| 15 | leftestimate | Double | 允许 | 剩余工时 |
| 16 | plantemplet | String | 允许 | 计划模板 |
| 17 | unfinishedtaskscnt | Integer | 允许 | 未完成任务数 |
| 18 | endstr | String | 允许 | 结束日期 |
| 19 | status | String | 允许 | 计划状态 |
| 20 | isexpired | String | 允许 | 是否过期 |
| 21 | deleted | String | 不可 | 已删除 |
| 22 | consumedestimate | Double | 允许 | 消耗工时 |
| 23 | order | String | 允许 | 排序 |
| 24 | isleaf | Integer | 允许 | 叶子节点 |
| 25 | future | String | 允许 | 待定 |
| 26 | storycnt | Integer | 允许 | 需求数 |
| 27 | delta | String | 允许 | 周期 |
| 28 | finishedtaskscnt | Integer | 允许 | 完成任务数 |
| 29 | bugcnt | Integer | 允许 | bug数 |
| 30 | parentname | String | 允许 | 父计划名称 |
| 31 | branch | Long | 允许 | 平台/分支 |
| 32 | parent | Long | 允许 | 父计划 |
| 33 | product | Long | 允许 | 产品 |
| 34 | begintime | String | 允许 | 开始时间 |
| 35 | ordernum | String | 允许 | 排序 |
| 36 | beginact | Timestamp | 允许 | 实际开始日期<br>时间格式：yyyy-MM-dd |
| 37 | endact | Timestamp | 允许 | 实际结束日期<br>时间格式：yyyy-MM-dd |
| 38 | <动态属性> | Object | 允许 | 支持动态属性 |

#### ProductPlanSearchContext
| 序号 | 属性名 | 属性类型 | 是否可以为空 | 说明 |
| ---- | ---- | ---- | ---- | ---- |
| 1 | n_title_like | String | 允许 | 条件字段：title<br>条件组合方式：`%like%` |
| 2 | n_id_eq | Long | 允许 | 条件字段：id<br>条件组合方式：`=` |
| 3 | n_begin_gtandeq | Timestamp | 允许 | 条件字段：begin<br>条件组合方式：`>=`<br>时间格式：yyyy-MM-dd |
| 4 | n_statuss_eq | String | 允许 | 条件字段：statuss<br>条件组合方式：`=` |
| 5 | n_end_ltandeq | Timestamp | 允许 | 条件字段：end<br>条件组合方式：`<=`<br>时间格式：yyyy-MM-dd |
| 6 | n_plantemplet_eq | String | 允许 | 条件字段：plantemplet<br>条件组合方式：`=` |
| 7 | n_isexpired_eq | String | 允许 | 条件字段：isexpired<br>条件组合方式：`=` |
| 8 | n_future_eq | String | 允许 | 条件字段：future<br>条件组合方式：`=` |
| 9 | n_delta_eq | String | 允许 | 条件字段：delta<br>条件组合方式：`=` |
| 10 | n_parentname_eq | String | 允许 | 条件字段：parentname<br>条件组合方式：`=` |
| 11 | n_parentname_like | String | 允许 | 条件字段：parentname<br>条件组合方式：`%like%` |
| 12 | n_branch_eq | Long | 允许 | 条件字段：branch<br>条件组合方式：`=` |
| 13 | n_parent_eq | Long | 允许 | 条件字段：parent<br>条件组合方式：`=` |
| 14 | n_product_eq | Long | 允许 | 条件字段：product<br>条件组合方式：`=` |
| 15 | customcond | String | 允许 | 自定义查询条件 |
| 16 | customparams | String | 允许 | 自定义查询参数 |
| 17 | query | String | 允许 | 快速搜索 |
| 18 | filter | QueryFilter | 允许 | 条件表达式<br>参照`cn.ibizlab.pms.util.filter.QueryFilter` |
| 19 | page | int | 允许 | 当前页数<br>默认值0 |
| 20 | size | int | 允许 | 每页显示条数<br>默认值20 |
| 21 | sort | String | 允许 | 排序 |
