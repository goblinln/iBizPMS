import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    updateDate:  commonLogic.appcommonhandle("更新时间",null),
    worktoday:  commonLogic.appcommonhandle("工作",null),
    todaytask:  commonLogic.appcommonhandle("完成任务",null),
    tomorrowplanstask:  commonLogic.appcommonhandle("计划任务",null),
    createMan:  commonLogic.appcommonhandle("建立人",null),
    reportto:  commonLogic.appcommonhandle("汇报给",null),
    createDate:  commonLogic.appcommonhandle("建立时间",null),
    date:  commonLogic.appcommonhandle("日期",null),
    issubmit:  commonLogic.appcommonhandle("是否提交",null),
    updateManName:  commonLogic.appcommonhandle("更新人名称",null),
    files:  commonLogic.appcommonhandle("附件",null),
    updateMan:  commonLogic.appcommonhandle("更新人",null),
    reportstatus:  commonLogic.appcommonhandle("状态",null),
    comment:  commonLogic.appcommonhandle("其他事项",null),
    mailto:  commonLogic.appcommonhandle("抄送给",null),
    ibzDailyId:  commonLogic.appcommonhandle("汇报标识",null),
    planstomorrow:  commonLogic.appcommonhandle("计划",null),
    account:  commonLogic.appcommonhandle("用户",null),
    createManName:  commonLogic.appcommonhandle("建立人名称",null),
    ibzDailyName:  commonLogic.appcommonhandle("汇报名称",null),
    type:  commonLogic.appcommonhandle("类型",null),
    dailycnt:  commonLogic.appcommonhandle("未读日报数",null),
    monthlyCnt:  commonLogic.appcommonhandle("未读月报数",null),
    submittime:  commonLogic.appcommonhandle("提交时间",null),
    reportlycnt:  commonLogic.appcommonhandle("未读汇报数",null),
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