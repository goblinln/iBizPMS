import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    root:  commonLogic.appcommonhandle("所属根",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    grade:  commonLogic.appcommonhandle("级别",null),
    type:  commonLogic.appcommonhandle("类型",null),
    name:  commonLogic.appcommonhandle("模块名称",null),
    order:  commonLogic.appcommonhandle("排序",null),
    owner:  commonLogic.appcommonhandle("负责人",null),
    id:  commonLogic.appcommonhandle("id",null),
    orderpk:  commonLogic.appcommonhandle("数据选择排序",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    mdeptName:  commonLogic.appcommonhandle("归属部门名",null),
    collector:  commonLogic.appcommonhandle("收藏者",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    iBizShort:  commonLogic.appcommonhandle("简称",null),
    path:  commonLogic.appcommonhandle("路径",null),
    mdeptId:  commonLogic.appcommonhandle("部门标识",null),
    orgId:  commonLogic.appcommonhandle("组织标识",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    parentName:  commonLogic.appcommonhandle("上级模块",null),
    branch:  commonLogic.appcommonhandle("平台/分支",null),
    parent:  commonLogic.appcommonhandle("上级模块",null),
    moduleSN:  commonLogic.appcommonhandle("模块编号",null),
  },
	views: {
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("模块",null),
		},
		mobpickupview: {
			caption: commonLogic.appcommonhandle("模块",null),
		},
	},
};