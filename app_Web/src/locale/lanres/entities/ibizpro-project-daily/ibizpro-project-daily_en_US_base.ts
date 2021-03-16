import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		ibizproprojectdailyname: commonLogic.appcommonhandle("项目日报名称",null),
		tasks: commonLogic.appcommonhandle("任务",null),
		begin: commonLogic.appcommonhandle("开始日期",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		end: commonLogic.appcommonhandle("结束日期",null),
		createman: commonLogic.appcommonhandle("建立人",null),
		date: commonLogic.appcommonhandle("日期",null),
		totalestimates: commonLogic.appcommonhandle("总工时",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
		pm: commonLogic.appcommonhandle("项目负责人",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		ibizproprojectdailyid: commonLogic.appcommonhandle("项目日报标识",null),
		projectname: commonLogic.appcommonhandle("项目名称",null),
		project: commonLogic.appcommonhandle("项目编号",null),
	},
		views: {
			editview: {
				caption: commonLogic.appcommonhandle("项目日报",null),
				title: commonLogic.appcommonhandle("项目日报编辑视图",null),
			},
			gridview: {
				caption: commonLogic.appcommonhandle("项目日报",null),
				title: commonLogic.appcommonhandle("项目日报表格视图",null),
			},
		},
		main_form: {
			details: {
				grouppanel11: commonLogic.appcommonhandle("分组面板",null), 
				druipart1: commonLogic.appcommonhandle("",null), 
				grouppanel1: commonLogic.appcommonhandle("设计",null), 
				druipart2: commonLogic.appcommonhandle("",null), 
				grouppanel2: commonLogic.appcommonhandle("开发",null), 
				druipart3: commonLogic.appcommonhandle("",null), 
				grouppanel3: commonLogic.appcommonhandle("测试",null), 
				druipart4: commonLogic.appcommonhandle("",null), 
				grouppanel4: commonLogic.appcommonhandle("研究",null), 
				druipart5: commonLogic.appcommonhandle("",null), 
				grouppanel5: commonLogic.appcommonhandle("讨论",null), 
				druipart6: commonLogic.appcommonhandle("",null), 
				grouppanel6: commonLogic.appcommonhandle("界面",null), 
				druipart7: commonLogic.appcommonhandle("",null), 
				grouppanel7: commonLogic.appcommonhandle("事务",null), 
				druipart8: commonLogic.appcommonhandle("",null), 
				grouppanel8: commonLogic.appcommonhandle("服务",null), 
				druipart9: commonLogic.appcommonhandle("",null), 
				grouppanel9: commonLogic.appcommonhandle("其他",null), 
				druipart10: commonLogic.appcommonhandle("",null), 
				grouppanel10: commonLogic.appcommonhandle("项目成员日报",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("项目日报标识",null), 
				srfmajortext: commonLogic.appcommonhandle("项目日报名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				projectname: commonLogic.appcommonhandle("项目名称",null), 
				pm: commonLogic.appcommonhandle("项目负责人",null), 
				date: commonLogic.appcommonhandle("日期",null), 
				totalestimates: commonLogic.appcommonhandle("总工时",null), 
				begin: commonLogic.appcommonhandle("开始日期",null), 
				end: commonLogic.appcommonhandle("结束日期",null), 
				project: commonLogic.appcommonhandle("项目编号",null), 
				tasks: commonLogic.appcommonhandle("任务",null), 
				ibizpro_projectdailyid: commonLogic.appcommonhandle("项目日报标识",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				ibizpro_projectdailyname: commonLogic.appcommonhandle("项目日报名称",null),
				projectname: commonLogic.appcommonhandle("项目名称",null),
				pm: commonLogic.appcommonhandle("项目负责人",null),
				date: commonLogic.appcommonhandle("日期",null),
				begin: commonLogic.appcommonhandle("开始日期",null),
				end: commonLogic.appcommonhandle("结束日期",null),
				totalestimates: commonLogic.appcommonhandle("总工时",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		default_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		gridviewtoolbar_toolbar: {
			deuiaction3_manualcreatedaily: {
				caption: commonLogic.appcommonhandle("生成日报",null),
				tip: commonLogic.appcommonhandle("生成日报",null),
			},
			deuiaction2: {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			deuiaction4: {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;