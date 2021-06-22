import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    orgname: "归属组织名",
    type: "类型（story）",
    mdeptname: "归属部门名",
    mdeptid: "部门标识",
    path: "path",
    orgid: "组织机构标识",
    owner: "owner",
    updateby: "由谁更新",
    createby: "由谁创建",
    order: "排序值",
    deleted: "逻辑删除标志",
    branch: "branch",
    collector: "collector",
    id: "id",
    isleaf: "叶子模块",
    name: "名称",
    ibizshort: "简称",
    grade: "grade",
    parentname: "上级模块",
    rootname: "测试",
    root: "编号",
    parent: "id",
  },
	views: {
		mobpickupview: {
			caption: commonLogic.appcommonhandle("测试模块",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("测试模块",null),
		},
	},
};