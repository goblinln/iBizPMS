import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    root: "所属根",
    grade: "级别",
    type: "类型",
    name: "模块名称",
    order: "排序",
    owner: "负责人",
    id: "id",
    orderpk: "数据选择排序",
    createby: "由谁创建",
    collector: "收藏者",
    updateby: "由谁更新",
    ibizshort: "简称",
    path: "路径",
    mdeptid: "部门标识",
    orgid: "组织标识",
    deleted: "已删除",
    parentname: "上级模块",
    branch: "平台/分支",
    parent: "上级模块",
    orgname: "归属组织名",
    mdeptname: "归属部门名",
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