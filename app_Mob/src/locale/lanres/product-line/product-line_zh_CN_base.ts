import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    id: "id",
    mdeptid: "部门标识",
    orgid: "组织机构标识",
    deleted: "已删除",
    name: "产品线名称",
    mdeptname: "归属部门名",
    type: "类型",
    ibizshort: "简称",
    createby: "由谁创建",
    order: "排序",
    updateby: "由谁更新",
    orgname: "归属组织名",
  },
	views: {
		mobpickupview: {
			caption: commonLogic.appcommonhandle("产品线（废弃）",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("产品线（废弃）",null),
		},
	},
};