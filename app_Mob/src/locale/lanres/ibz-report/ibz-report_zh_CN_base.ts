import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    updatemanname: "更新人名称",
    mailto: "抄送给",
    ibzdailyid: "汇报标识",
    reportlycnt: "未读汇报数",
    updatedate: "更新时间",
    worktoday: "工作",
    monthlycnt: "未读月报数",
    dailycnt: "未读日报数",
    createdate: "建立时间",
    todaytask: "完成任务",
    account: "用户",
    type: "类型",
    createman: "建立人",
    updateman: "更新人",
    ibzdailyname: "汇报名称",
    reportto: "汇报给",
    submittime: "提交时间",
    files: "附件",
    createmanname: "建立人名称",
    planstomorrow: "计划",
    reportstatus: "状态",
    date: "日期",
    issubmit: "是否提交",
    comment: "其他事项",
    tomorrowplanstask: "计划任务",
  },
	views: {
		mobmdview: {
			caption: commonLogic.appcommonhandle("我提交的",null),
		},
		myremobmdview: {
			caption: commonLogic.appcommonhandle("我收到的",null),
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			n_type_eq: commonLogic.appcommonhandle("模板",null), 
		},
		uiactions: {
		},
	},
};