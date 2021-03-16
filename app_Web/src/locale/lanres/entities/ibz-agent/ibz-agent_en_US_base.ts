import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		updateman: commonLogic.appcommonhandle("更新人",null),
		createmanname: commonLogic.appcommonhandle("创建人姓名",null),
		agentend: commonLogic.appcommonhandle("代理结束日期",null),
		ibzagentid: commonLogic.appcommonhandle("代理标识",null),
		createman: commonLogic.appcommonhandle("建立人",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		agentuser: commonLogic.appcommonhandle("代理用户",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		agentbegin: commonLogic.appcommonhandle("代理开始日期",null),
		ibzagentname: commonLogic.appcommonhandle("代理名称",null),
	},
		views: {
			createeditview: {
				caption: commonLogic.appcommonhandle("代理编辑视图",null),
				title: commonLogic.appcommonhandle("代理编辑视图",null),
			},
			maininfoeditview: {
				caption: commonLogic.appcommonhandle("代理",null),
				title: commonLogic.appcommonhandle("代理编辑视图",null),
			},
			maingridview: {
				caption: commonLogic.appcommonhandle("代理",null),
				title: commonLogic.appcommonhandle("代理表格视图",null),
			},
		},
		createform_form: {
			details: {
				group1: commonLogic.appcommonhandle("代理基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("代理标识",null), 
				srfmajortext: commonLogic.appcommonhandle("代理名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				createmanname: commonLogic.appcommonhandle("创建人姓名",null), 
				agentuser: commonLogic.appcommonhandle("代理用户",null), 
				agentbegin: commonLogic.appcommonhandle("代理开始日期",null), 
				agentend: commonLogic.appcommonhandle("代理结束日期",null), 
				ibz_agentid: commonLogic.appcommonhandle("代理标识",null), 
			},
			uiactions: {
			},
		},
		maininfo_form: {
			details: {
				group1: commonLogic.appcommonhandle("代理基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("代理标识",null), 
				srfmajortext: commonLogic.appcommonhandle("代理名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				agentuser: commonLogic.appcommonhandle("代理用户",null), 
				agentbegin: commonLogic.appcommonhandle("代理开始日期",null), 
				agentend: commonLogic.appcommonhandle("代理结束日期",null), 
				ibz_agentid: commonLogic.appcommonhandle("代理标识",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				ibz_agentid: commonLogic.appcommonhandle("代理标识",null),
				agentuser: commonLogic.appcommonhandle("代理用户",null),
				agentbegin: commonLogic.appcommonhandle("代理开始日期",null),
				agentend: commonLogic.appcommonhandle("代理结束日期",null),
				uagridcolumn1: commonLogic.appcommonhandle("操作",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			ibzagent_edit: commonLogic.appcommonhandle("编辑",null),
			},
		},
		default_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("常规条件",null), 
			},
			uiactions: {
			},
		},
		createeditviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
		},
		maingridviewtoolbar_toolbar: {
			deuiaction1_create: {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;