import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    path: "path",
    orderpk: "数据选择排序",
    deleted: "逻辑删除标志",
    name: "名称",
    createby: "由谁创建",
    branch: "branch",
    ibizshort: "简称",
    order: "排序值",
    grade: "grade",
    type: "类型（story）",
    orgname: "归属组织名",
    owner: "owner",
    mdeptname: "归属部门名",
    isleaf: "叶子模块",
    id: "id",
    updateby: "由谁更新",
    collector: "collector",
    mdeptid: "部门标识",
    orgid: "组织机构标识",
    rootname: "所属产品",
    parentname: "上级模块",
    root: "产品",
    parent: "id",
  },
	views: {
		mobpickupview: {
			caption: commonLogic.appcommonhandle("需求模块",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("需求模块",null),
		},
	},
};