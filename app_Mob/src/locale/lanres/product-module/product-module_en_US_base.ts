import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    path: "path",
    deleted: "逻辑删除标志",
    name: "名称",
    branch: "branch",
    iBizShort: "简称",
    order: "排序值",
    grade: "grade",
    type: "类型（story）",
    owner: "owner",
    isLeaf: "叶子模块",
    id: "id",
    collector: "collector",
    root: "产品",
    parent: "id",
    rootName: "所属产品",
    parentName: "上级模块",
    orderpk: "数据选择排序",
  },
	views: {
		mobpickupview: {
			caption: 'commonLogic.appcommonhandle("需求模块",null)',
		},
		mobpickupmdview: {
			caption: 'commonLogic.appcommonhandle("需求模块",null)',
		},
	},
};