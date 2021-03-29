import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'ibizproprojectmonthlyname': commonLogic.appcommonhandle("项目月报名称",null),
		'createdate': commonLogic.appcommonhandle("建立时间",null),
		'yearmonth': commonLogic.appcommonhandle("年月",null),
		'totalestimates': commonLogic.appcommonhandle("总工时",null),
		'updateman': commonLogic.appcommonhandle("更新人",null),
		'tasks': commonLogic.appcommonhandle("任务",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'ibizproprojectmonthlyid': commonLogic.appcommonhandle("项目月报标识",null),
		'updatedate': commonLogic.appcommonhandle("更新时间",null),
		'createman': commonLogic.appcommonhandle("建立人",null),
		'pm': commonLogic.appcommonhandle("项目负责人",null),
		'projectname': commonLogic.appcommonhandle("项目名称",null),
		'project': commonLogic.appcommonhandle("项目编号",null),
	},
		views: {
			'gridview': {
				caption: commonLogic.appcommonhandle("项目月报",null),
				title: commonLogic.appcommonhandle("项目月报表格视图",null),
			},
			'editview': {
				caption: commonLogic.appcommonhandle("项目月报",null),
				title: commonLogic.appcommonhandle("项目月报编辑视图",null),
			},
		},
		main_form: {
			details: {
				'druipart1': commonLogic.appcommonhandle("",null), 
				'grouppanel1': commonLogic.appcommonhandle("设计",null), 
				'druipart3': commonLogic.appcommonhandle("",null), 
				'grouppanel3': commonLogic.appcommonhandle("开发",null), 
				'druipart2': commonLogic.appcommonhandle("",null), 
				'grouppanel2': commonLogic.appcommonhandle("测试",null), 
				'druipart4': commonLogic.appcommonhandle("",null), 
				'grouppanel4': commonLogic.appcommonhandle("研究",null), 
				'druipart5': commonLogic.appcommonhandle("",null), 
				'grouppanel5': commonLogic.appcommonhandle("讨论",null), 
				'druipart6': commonLogic.appcommonhandle("",null), 
				'grouppanel6': commonLogic.appcommonhandle("界面",null), 
				'druipart7': commonLogic.appcommonhandle("",null), 
				'grouppanel7': commonLogic.appcommonhandle("事务",null), 
				'druipart8': commonLogic.appcommonhandle("",null), 
				'grouppanel8': commonLogic.appcommonhandle("服务",null), 
				'druipart9': commonLogic.appcommonhandle("",null), 
				'grouppanel9': commonLogic.appcommonhandle("其他",null), 
				'druipart10': commonLogic.appcommonhandle("",null), 
				'grouppanel10': commonLogic.appcommonhandle("项目成员月报",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srfupdatedate': commonLogic.appcommonhandle("更新时间",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("项目月报标识",null), 
				'srfmajortext': commonLogic.appcommonhandle("项目月报名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'projectname': commonLogic.appcommonhandle("项目名称",null), 
				'pm': commonLogic.appcommonhandle("项目负责人",null), 
				'date': commonLogic.appcommonhandle("日期",null), 
				'totalestimates': commonLogic.appcommonhandle("总工时",null), 
				'tasks': commonLogic.appcommonhandle("任务",null), 
				'project': commonLogic.appcommonhandle("项目编号",null), 
				'year_month': commonLogic.appcommonhandle("年月",null), 
				'ibizpro_projectmonthlyid': commonLogic.appcommonhandle("项目月报标识",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				'ibizpro_projectmonthlyname': commonLogic.appcommonhandle("项目月报名称",null),
				'projectname': commonLogic.appcommonhandle("项目名称",null),
				'year_month': commonLogic.appcommonhandle("年月",null),
				'date': commonLogic.appcommonhandle("日期",null),
				'pm': commonLogic.appcommonhandle("项目负责人",null),
				'totalestimates': commonLogic.appcommonhandle("总工时",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		default_searchform: {
			details: {
				'formpage1': commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		gridviewtoolbar_toolbar: {
			'deuiaction3_manualcreatemonthly': {
				caption: commonLogic.appcommonhandle("生成月报",null),
				tip: commonLogic.appcommonhandle("生成月报",null),
			},
			'deuiaction2': {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			'deuiaction1': {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
			'deuiaction4': {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;