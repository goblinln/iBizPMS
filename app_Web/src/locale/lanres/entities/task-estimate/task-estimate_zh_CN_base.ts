import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    account: commonLogic.appcommonhandle("用户",null),
    left: commonLogic.appcommonhandle("预计剩余",null),
    consumed: commonLogic.appcommonhandle("总计消耗",null),
    id: commonLogic.appcommonhandle("编号",null),
    date: commonLogic.appcommonhandle("日期",null),
    work: commonLogic.appcommonhandle("work",null),
    task: commonLogic.appcommonhandle("任务",null),
    dates: commonLogic.appcommonhandle("日期",null),
  },
	views: {
		optionview: {
			caption: commonLogic.appcommonhandle("任务预计",null),
      		title: commonLogic.appcommonhandle("任务预计选项操作视图",null),
		},
		editgridview9: {
			caption: commonLogic.appcommonhandle("工时",null),
      		title: commonLogic.appcommonhandle("任务预计表格视图",null),
		},
		lookgridview9: {
			caption: commonLogic.appcommonhandle("工时",null),
      		title: commonLogic.appcommonhandle("任务预计表格视图",null),
		},
	},
	main_form: {
		details: {
			group1: commonLogic.appcommonhandle("taskestimate基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("编号",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			date: commonLogic.appcommonhandle("日期",null), 
			consumed: commonLogic.appcommonhandle("工时",null), 
			left: commonLogic.appcommonhandle("剩余",null), 
			work: commonLogic.appcommonhandle("备注",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mainlook_grid: {
		columns: {
			id: commonLogic.appcommonhandle("ID",null),
			dates: commonLogic.appcommonhandle("日期",null),
			consumed: commonLogic.appcommonhandle("总计消耗",null),
			left: commonLogic.appcommonhandle("预计剩余",null),
			work: commonLogic.appcommonhandle("备注",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
		},
	},
	mainedit_grid: {
		columns: {
			id: commonLogic.appcommonhandle("ID",null),
			dates: commonLogic.appcommonhandle("日期",null),
			consumed: commonLogic.appcommonhandle("总计消耗",null),
			left: commonLogic.appcommonhandle("预计剩余",null),
			work: commonLogic.appcommonhandle("备注",null),
			uagridcolumn1: commonLogic.appcommonhandle("操作",null),
		},
		nodata:commonLogic.appcommonhandle("",null),
		uiactions: {
			taskestimate_edit: commonLogic.appcommonhandle("编辑",null),
			remove: commonLogic.appcommonhandle("删除",null),
		},
	},
};