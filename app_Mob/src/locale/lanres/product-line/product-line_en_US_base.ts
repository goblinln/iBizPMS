import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    id:  commonLogic.appcommonhandle("id",null),
    mdeptId:  commonLogic.appcommonhandle("部门标识",null),
    orgId:  commonLogic.appcommonhandle("组织机构标识",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    name:  commonLogic.appcommonhandle("产品线名称",null),
    mdeptName:  commonLogic.appcommonhandle("归属部门名",null),
    type:  commonLogic.appcommonhandle("类型",null),
    iBizShort:  commonLogic.appcommonhandle("简称",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    order:  commonLogic.appcommonhandle("排序",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
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