import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    ibizshort: "简称",
    isleaf: "叶子模块",
    type: "类型（task）",
    orderpk: "数据选择排序",
    name: "名称",
    order: "排序值",
    collector: "collector",
    grade: "grade",
    branch: "branch",
    path: "path",
    id: "id",
    owner: "owner",
    deleted: "逻辑删除标志",
    rootname: "所属项目",
    parentname: "上级模块",
    root: "项目",
    parent: "id",
    mdeptid: "部门标识",
    mdeptname: "归属部门名",
    orgid: "组织机构标识",
    createby: "由谁创建",
    orgname: "归属组织名",
    updateby: "由谁更新",
  },
	views: {
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("任务模块",null),
		},
		mobpickupview: {
			caption: commonLogic.appcommonhandle("任务模块",null),
		},
	},
};