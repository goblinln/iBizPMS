import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    estimate: "最初预计",
    username: "用户",
    join: "加盟日",
    hours: "可用工时/天",
    account: "用户",
    order: "排序",
    left: "预计剩余",
    consumed: "总计消耗",
    limited: "受限用户",
    role: "角色",
    id: "编号",
    days: "可用工日",
    total: "总计可用",
    type: "团队类型",
    root: "编号",
  },
	views: {
		mobeditview9: {
			caption: commonLogic.appcommonhandle("任务团队",null),
		},
		mobmeditview9: {
			caption: commonLogic.appcommonhandle("任务团队",null),
		},
	},
	mobmain_form: {
		details: {
			group1: commonLogic.appcommonhandle("任务团队基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("用户",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			root: commonLogic.appcommonhandle("编号",null), 
			type: commonLogic.appcommonhandle("团队类型",null), 
			account: commonLogic.appcommonhandle("用户",null), 
			estimate: commonLogic.appcommonhandle("最初预计",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobeditview9righttoolbar_toolbar: {
	},
};