import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
		createman: commonLogic.appcommonhandle("建立人",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		sysupdatelogid: commonLogic.appcommonhandle("系统更新日志标识",null),
		updatebranch: commonLogic.appcommonhandle("更新平台",null),
		updatefeatures: commonLogic.appcommonhandle("更新功能列表详情",null),
		updesc: commonLogic.appcommonhandle("更新说明",null),
		sysupdatelogname: commonLogic.appcommonhandle("更新名称",null),
		latestupdate: commonLogic.appcommonhandle("最新更新",null),
		update: commonLogic.appcommonhandle("更新日期",null),
	},
		views: {
			editview: {
				caption: commonLogic.appcommonhandle("更新日志",null),
				title: commonLogic.appcommonhandle("系统更新日志编辑视图",null),
			},
			gridview: {
				caption: commonLogic.appcommonhandle("更新日志",null),
				title: commonLogic.appcommonhandle("系统更新日志表格视图",null),
			},
			lasttabexpview: {
				caption: commonLogic.appcommonhandle("更新日志",null),
				title: commonLogic.appcommonhandle("系统更新日志分页导航视图",null),
			},
			lastgridview: {
				caption: commonLogic.appcommonhandle("更新日志",null),
				title: commonLogic.appcommonhandle("系统更新日志表格视图",null),
			},
			tabexpview: {
				caption: commonLogic.appcommonhandle("更新日志",null),
				title: commonLogic.appcommonhandle("系统更新日志分页导航视图",null),
			},
			infoeditview: {
				caption: commonLogic.appcommonhandle("更新日志",null),
				title: commonLogic.appcommonhandle("系统更新日志编辑视图",null),
			},
		},
		mainl_form: {
			details: {
				group1: commonLogic.appcommonhandle("系统更新日志基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				group2: commonLogic.appcommonhandle("操作信息",null), 
				formpage2: commonLogic.appcommonhandle("其它",null), 
				srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("系统更新日志标识",null), 
				srfmajortext: commonLogic.appcommonhandle("更新名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				sys_update_logname: commonLogic.appcommonhandle("更新名称",null), 
				update: commonLogic.appcommonhandle("更新日期",null), 
				updatebranch: commonLogic.appcommonhandle("更新平台",null), 
				latestupdate: commonLogic.appcommonhandle("最新更新",null), 
				updesc: commonLogic.appcommonhandle("更新说明",null), 
				createman: commonLogic.appcommonhandle("建立人",null), 
				createdate: commonLogic.appcommonhandle("建立时间",null), 
				updateman: commonLogic.appcommonhandle("更新人",null), 
				updatedate: commonLogic.appcommonhandle("更新时间",null), 
				sys_update_logid: commonLogic.appcommonhandle("系统更新日志标识",null), 
			},
			uiactions: {
			},
		},
		main_form: {
			details: {
				group1: commonLogic.appcommonhandle("系统更新日志基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				group2: commonLogic.appcommonhandle("操作信息",null), 
				formpage2: commonLogic.appcommonhandle("其它",null), 
				srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("系统更新日志标识",null), 
				srfmajortext: commonLogic.appcommonhandle("更新名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				sys_update_logname: commonLogic.appcommonhandle("更新名称",null), 
				update: commonLogic.appcommonhandle("更新日期",null), 
				updatebranch: commonLogic.appcommonhandle("更新平台",null), 
				latestupdate: commonLogic.appcommonhandle("最新更新",null), 
				updesc: commonLogic.appcommonhandle("更新说明",null), 
				createman: commonLogic.appcommonhandle("建立人",null), 
				createdate: commonLogic.appcommonhandle("建立时间",null), 
				updateman: commonLogic.appcommonhandle("更新人",null), 
				updatedate: commonLogic.appcommonhandle("更新时间",null), 
				sys_update_logid: commonLogic.appcommonhandle("系统更新日志标识",null), 
			},
			uiactions: {
			},
		},
		lookmain_grid: {
			columns: {
				sys_update_logname: commonLogic.appcommonhandle("更新名称",null),
				update: commonLogic.appcommonhandle("更新日期",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
				sysupdatelog_lookdetail: commonLogic.appcommonhandle("查看",null),
			},
		},
		main_grid: {
			columns: {
				sys_update_logname: commonLogic.appcommonhandle("更新名称",null),
				update: commonLogic.appcommonhandle("更新日期",null),
				updatebranch: commonLogic.appcommonhandle("更新平台",null),
				latestupdate: commonLogic.appcommonhandle("最新更新",null),
				updateman: commonLogic.appcommonhandle("更新人",null),
				updatedate: commonLogic.appcommonhandle("更新时间",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		editviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
		},
		gridviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("新建",null),
				tip: commonLogic.appcommonhandle("新建",null),
			},
			deuiaction4: {
				caption: commonLogic.appcommonhandle("删除",null),
				tip: commonLogic.appcommonhandle("删除",null),
			},
			seperator1: {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			deuiaction2: {
				caption: commonLogic.appcommonhandle("刷新",null),
				tip: commonLogic.appcommonhandle("刷新",null),
			},
			seperator3: {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			deuiaction5: {
				caption: commonLogic.appcommonhandle("导出",null),
				tip: commonLogic.appcommonhandle("导出",null),
			},
			seperator4: {
				caption: commonLogic.appcommonhandle("",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			deuiaction7: {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
			deuiaction6_togglefilter: {
				caption: commonLogic.appcommonhandle("过滤",null),
				tip: commonLogic.appcommonhandle("过滤",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;