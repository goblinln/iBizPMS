import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    orgid: "组织机构标识",
    order: "排序",
    id: "id",
    ibizshort: "简称",
    mdeptid: "部门标识",
    createby: "由谁创建",
    type: "类型",
    updateby: "由谁更新",
    mdeptname: "归属部门名",
    orgname: "归属组织名",
    name: "产品线名称",
    deleted: "已删除",
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