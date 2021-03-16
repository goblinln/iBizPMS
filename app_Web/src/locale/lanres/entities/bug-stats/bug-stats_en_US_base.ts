import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		bugactive: commonLogic.appcommonhandle("激活Bug",null),
		bugwillnotfix: commonLogic.appcommonhandle("不予解决",null),
		resolvedby: commonLogic.appcommonhandle("由谁解决",null),
		bugresolution: commonLogic.appcommonhandle("Bug解决方案",null),
		dept: commonLogic.appcommonhandle("部门",null),
		bugbydesign: commonLogic.appcommonhandle("设计如此",null),
		bugopenedby: commonLogic.appcommonhandle("bug创建人",null),
		bugstatus: commonLogic.appcommonhandle("Bug状态",null),
		bugclosed: commonLogic.appcommonhandle("已关闭Bug",null),
		assignedto: commonLogic.appcommonhandle("指派给",null),
		bugexternal: commonLogic.appcommonhandle("外部原因",null),
		begin: commonLogic.appcommonhandle("开始",null),
		bugresolveddate: commonLogic.appcommonhandle("bug解决日期",null),
		end: commonLogic.appcommonhandle("结束",null),
		bugid: commonLogic.appcommonhandle("Bug编号",null),
		projectname: commonLogic.appcommonhandle("项目名称",null),
		bugfixed: commonLogic.appcommonhandle("已解决",null),
		id: commonLogic.appcommonhandle("标识",null),
		bugpostponed: commonLogic.appcommonhandle("延期处理",null),
		bugtitle: commonLogic.appcommonhandle("Bug标题",null),
		bugcnt: commonLogic.appcommonhandle("Bug",null),
		bugopeneddate: commonLogic.appcommonhandle("Bug创建日期",null),
		bugwjj: commonLogic.appcommonhandle("未解决",null),
		title: commonLogic.appcommonhandle("名称",null),
		bugpri: commonLogic.appcommonhandle("Bug优先级",null),
		bugnotrepro: commonLogic.appcommonhandle("无法重现",null),
		projectname1: commonLogic.appcommonhandle("项目名称",null),
		bugresolved: commonLogic.appcommonhandle("已解决Bug",null),
		bugduplicate: commonLogic.appcommonhandle("重复Bug",null),
		openedby: commonLogic.appcommonhandle("由谁创建",null),
		bugefficient: commonLogic.appcommonhandle("有效率",null),
		bugtostory: commonLogic.appcommonhandle("转为需求",null),
		bugseverity: commonLogic.appcommonhandle("Bug严重程度",null),
		bugtotal: commonLogic.appcommonhandle("总计",null),
		productname: commonLogic.appcommonhandle("产品名称",null),
		product: commonLogic.appcommonhandle("编号",null),
		project: commonLogic.appcommonhandle("项目编号",null),
	},
		views: {
			bugresolvedbygridview: {
				caption: commonLogic.appcommonhandle("Bug完成表",null),
				title: commonLogic.appcommonhandle("Bug统计表格视图（Bug完成表）",null),
			},
			productbugresolutiongridview: {
				caption: commonLogic.appcommonhandle("产品Bug解决方案汇总",null),
				title: commonLogic.appcommonhandle("Bug统计表格视图",null),
			},
			bugresolvetion: {
				caption: commonLogic.appcommonhandle("Bug统计",null),
				title: commonLogic.appcommonhandle("每个解决方案的Bug数统计表格视图",null),
			},
			bugresolvedbygridviewzuzhi: {
				caption: commonLogic.appcommonhandle("Bug完成表",null),
				title: commonLogic.appcommonhandle("Bug统计表格视图（Bug完成表）",null),
			},
			usr2gridviewprojectbugstatuscount: {
				caption: commonLogic.appcommonhandle("bug状态统计",null),
				title: commonLogic.appcommonhandle("Bug统计表格视图(bug状态统计)",null),
			},
			productbugstatusgridview: {
				caption: commonLogic.appcommonhandle("产品Bug状态汇总",null),
				title: commonLogic.appcommonhandle("Bug统计表格视图",null),
			},
			gridview: {
				caption: commonLogic.appcommonhandle("Bug创建表",null),
				title: commonLogic.appcommonhandle("Bug统计表格视图",null),
			},
			assigntogridview: {
				caption: commonLogic.appcommonhandle("Bug指派表",null),
				title: commonLogic.appcommonhandle("Bug统计表格视图（Bug指派表）",null),
			},
			editview: {
				caption: commonLogic.appcommonhandle("Bug统计",null),
				title: commonLogic.appcommonhandle("Bug统计编辑视图",null),
			},
		},
		main_form: {
			details: {
				group1: commonLogic.appcommonhandle("Bug统计基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				group2: commonLogic.appcommonhandle("操作信息",null), 
				formpage2: commonLogic.appcommonhandle("其它",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("标识",null), 
				srfmajortext: commonLogic.appcommonhandle("名称",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				id: commonLogic.appcommonhandle("标识",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				openedby: commonLogic.appcommonhandle("由谁创建",null),
				bugwjj: commonLogic.appcommonhandle("未解决",null),
				bugbydesign: commonLogic.appcommonhandle("设计如此",null),
				bugduplicate: commonLogic.appcommonhandle("重复Bug",null),
				bugexternal: commonLogic.appcommonhandle("外部原因",null),
				bugfixed: commonLogic.appcommonhandle("已解决",null),
				bugnotrepro: commonLogic.appcommonhandle("无法重现",null),
				bugpostponed: commonLogic.appcommonhandle("延期处理",null),
				bugwillnotfix: commonLogic.appcommonhandle("不予解决",null),
				bugtostory: commonLogic.appcommonhandle("转为需求",null),
				bugefficient: commonLogic.appcommonhandle("有效率",null),
				bugtotal: commonLogic.appcommonhandle("总计",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		productbugresolutionstats_grid: {
			columns: {
				productname: commonLogic.appcommonhandle("产品名称",null),
				bugwjj: commonLogic.appcommonhandle("未解决",null),
				bugbydesign: commonLogic.appcommonhandle("设计如此",null),
				bugduplicate: commonLogic.appcommonhandle("重复Bug",null),
				bugexternal: commonLogic.appcommonhandle("外部原因",null),
				bugfixed: commonLogic.appcommonhandle("已解决",null),
				bugnotrepro: commonLogic.appcommonhandle("无法重现",null),
				bugpostponed: commonLogic.appcommonhandle("延期处理",null),
				bugwillnotfix: commonLogic.appcommonhandle("不予解决",null),
				bugtostory: commonLogic.appcommonhandle("转为需求",null),
				bugefficient: commonLogic.appcommonhandle("有效率",null),
				bugtotal: commonLogic.appcommonhandle("总计",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		bugresolvedby_grid: {
			columns: {
				resolvedby: commonLogic.appcommonhandle("姓名",null),
				productname: commonLogic.appcommonhandle("产品",null),
				bugcnt: commonLogic.appcommonhandle("Bug",null),
				bugtotal: commonLogic.appcommonhandle("总计",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		productbugstatuscount_grid: {
			columns: {
				productname: commonLogic.appcommonhandle("产品名称",null),
				bugresolved: commonLogic.appcommonhandle("已解决",null),
				bugclosed: commonLogic.appcommonhandle("已关闭",null),
				bugactive: commonLogic.appcommonhandle("已激活",null),
				bugefficient: commonLogic.appcommonhandle("有效率",null),
				bugtotal: commonLogic.appcommonhandle("总计",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		bugassignedto_grid: {
			columns: {
				assignedto: commonLogic.appcommonhandle("姓名",null),
				productname: commonLogic.appcommonhandle("产品",null),
				bugcnt: commonLogic.appcommonhandle("Bug",null),
				bugtotal: commonLogic.appcommonhandle("总计",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		projectbugstatuscount_grid: {
			columns: {
				projectname: commonLogic.appcommonhandle("项目名称",null),
				bugactive: commonLogic.appcommonhandle("激活",null),
				bugresolved: commonLogic.appcommonhandle("已解决",null),
				bugclosed: commonLogic.appcommonhandle("已关闭",null),
				bugcnt: commonLogic.appcommonhandle("总计",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		bugresolutioncnt_grid: {
			columns: {
				projectname: commonLogic.appcommonhandle("项目名称",null),
				bugduplicate: commonLogic.appcommonhandle("重复Bug",null),
				bugbydesign: commonLogic.appcommonhandle("设计如此",null),
				bugfixed: commonLogic.appcommonhandle("已解决",null),
				bugpostponed: commonLogic.appcommonhandle("延期处理",null),
				bugnotrepro: commonLogic.appcommonhandle("无法重现",null),
				bugtostory: commonLogic.appcommonhandle("转为需求",null),
				bugwillnotfix: commonLogic.appcommonhandle("不予解决",null),
				bugexternal: commonLogic.appcommonhandle("外部原因",null),
				bugcnt: commonLogic.appcommonhandle("总计",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		bugresolvedgird_grid: {
			columns: {
				resolvedby: commonLogic.appcommonhandle("由谁解决",null),
				bugid: commonLogic.appcommonhandle("Bug编号",null),
				bugtitle: commonLogic.appcommonhandle("Bug标题",null),
				bugpri: commonLogic.appcommonhandle("优先级",null),
				bugseverity: commonLogic.appcommonhandle("严重程度",null),
				bugopenedby: commonLogic.appcommonhandle("由谁创建",null),
				bugopeneddate: commonLogic.appcommonhandle("创建",null),
				bugresolution: commonLogic.appcommonhandle("解决方案",null),
				bugresolveddate: commonLogic.appcommonhandle("解决日期",null),
				bugstatus: commonLogic.appcommonhandle("Bug状态",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
		exportColumns: {
				resolvedby: commonLogic.appcommonhandle("由谁解决",null),
				bugid: commonLogic.appcommonhandle("Bug编号",null),
				bugtitle: commonLogic.appcommonhandle("Bug标题",null),
				bugpri: commonLogic.appcommonhandle("优先级",null),
				bugseverity: commonLogic.appcommonhandle("严重程度",null),
				bugopenedby: commonLogic.appcommonhandle("由谁创建",null),
				bugopeneddate: commonLogic.appcommonhandle("创建",null),
				bugresolution: commonLogic.appcommonhandle("解决方案",null),
				bugresolveddate: commonLogic.appcommonhandle("解决日期",null),
				bugstatus: commonLogic.appcommonhandle("Bug状态",null),
		},
			uiactions: {
			},
		},
		default_searchform: {
			details: {
				grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
				grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
				formpage1: commonLogic.appcommonhandle("常规条件",null), 
				openeddatelt: commonLogic.appcommonhandle("Bug创建时间",null), 
				openeddategt: commonLogic.appcommonhandle("至",null), 
				producteq: commonLogic.appcommonhandle("产品",null), 
				projecteq: commonLogic.appcommonhandle("项目",null), 
			},
			uiactions: {
			},
		},
		productbug_searchform: {
			details: {
				grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
				grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
				formpage1: commonLogic.appcommonhandle("常规条件",null), 
				openeddatelt: commonLogic.appcommonhandle("Bug创建时间",null), 
				openeddategt: commonLogic.appcommonhandle("至",null), 
				producteq: commonLogic.appcommonhandle("产品",null), 
				projecteq: commonLogic.appcommonhandle("项目",null), 
			},
			uiactions: {
			},
		},
		bugresolvedby_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("表单分页",null), 
				n_product_eq: commonLogic.appcommonhandle("产品",null), 
				n_resolvedby_eq: commonLogic.appcommonhandle("由谁完成",null), 
			},
			uiactions: {
			},
		},
		assignedtobug_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("表单分页",null), 
				n_product_eq: commonLogic.appcommonhandle("产品",null), 
				n_assignedto_eq: commonLogic.appcommonhandle("指派给",null), 
			},
			uiactions: {
			},
		},
		bugresolvedgird_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("表单分页",null), 
				n_dept_eq: commonLogic.appcommonhandle("部门",null), 
				n_begin_gtandeq: commonLogic.appcommonhandle("bug解决日期",null), 
				n_end_ltandeq: commonLogic.appcommonhandle("至",null), 
			},
			uiactions: {
			},
		},
		editviewtoolbar_toolbar: {
			tbitem3: {
				caption: commonLogic.appcommonhandle("Save",null),
				tip: commonLogic.appcommonhandle("Save",null),
			},
			tbitem4: {
				caption: commonLogic.appcommonhandle("Save And New",null),
				tip: commonLogic.appcommonhandle("Save And New",null),
			},
			tbitem5: {
				caption: commonLogic.appcommonhandle("Save And Close",null),
				tip: commonLogic.appcommonhandle("Save And Close Window",null),
			},
			tbitem7: {
				caption: commonLogic.appcommonhandle("Remove And Close",null),
				tip: commonLogic.appcommonhandle("Remove And Close Window",null),
			},
		},
		productbugresolutiongridviewtoolbar_toolbar: {
		},
		productbugstatusgridviewtoolbar_toolbar: {
		},
		bugresolvedbygridviewzuzhitoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;