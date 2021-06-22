import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    mdeptname: "归属部门名",
    orgname: "归属组织名",
    ibizshort: "简称",
    createby: "由谁创建",
    isleaf: "叶子模块",
    type: "类型（task）",
    updateby: "由谁更新",
    orderpk: "数据选择排序",
    name: "名称",
    order: "排序值",
    collector: "collector",
    orgid: "组织机构标识",
    grade: "grade",
    branch: "branch",
    path: "path",
    id: "id",
    mdeptid: "部门标识",
    owner: "owner",
    deleted: "逻辑删除标志",
    rootname: "所属项目",
    parentname: "上级模块",
    root: "项目",
    parent: "id",
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