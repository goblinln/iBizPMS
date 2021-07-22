import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    estimate: "最初预计",
    org: "归属组织",
    updateby: "由谁更新",
    username: "用户",
    join: "加盟日",
    hours: "可用工时/天",
    account: "用户",
    order: "排序",
    left: "预计剩余",
    deptname: "归属部门名",
    createby: "由谁创建",
    consumed: "总计消耗",
    limited: "受限用户",
    role: "角色",
    dept: "归属部门",
    id: "编号",
    days: "可用工日",
    total: "总计可用",
    orgname: "归属组织名",
    type: "团队类型",
    root: "编号",
  },
	views: {
		mobmeditview9: {
			caption: commonLogic.appcommonhandle("任务预计",null),
		},
		mobeditview9: {
			caption: commonLogic.appcommonhandle("任务预计",null),
		},
	},
	newform_form: {
		details: {
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("编号",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			dates: commonLogic.appcommonhandle("日期",null), 
			consumed: commonLogic.appcommonhandle("工时",null), 
			left: commonLogic.appcommonhandle("剩余",null), 
			work: commonLogic.appcommonhandle("备注",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
};