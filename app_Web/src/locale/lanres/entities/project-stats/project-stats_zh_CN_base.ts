import commonLogic from '@/locale/logic/common/common-logic';
function getLocaleResourceBase(){
	const data:any = {
		appdename: commonLogic.appcommonhandle("项目统计", null),
		fields: {
			id: commonLogic.appcommonhandle("项目编号",null),
			storycnt: commonLogic.appcommonhandle("需求总数",null),
			taskcnt: commonLogic.appcommonhandle("任务总数",null),
			totalestimate: commonLogic.appcommonhandle("任务最初预计总工时",null),
			totalconsumed: commonLogic.appcommonhandle("任务消耗总工时",null),
			totalleft: commonLogic.appcommonhandle("任务预计剩余总工时",null),
			undonetaskcnt: commonLogic.appcommonhandle("未完成任务总数",null),
			closedstorycnt: commonLogic.appcommonhandle("关闭需求总数",null),
			bugcnt: commonLogic.appcommonhandle("Bug总数",null),
			activebugcnt: commonLogic.appcommonhandle("未解决Bug总数",null),
			unclosedstorycnt: commonLogic.appcommonhandle("未关闭需求总数",null),
			finishtaskcnt: commonLogic.appcommonhandle("已结束任务总数",null),
			finishbugcnt: commonLogic.appcommonhandle("已解决Bug总数",null),
			deleted: commonLogic.appcommonhandle("已删除",null),
			time: commonLogic.appcommonhandle("工时",null),
			type: commonLogic.appcommonhandle("工时类型",null),
			name: commonLogic.appcommonhandle("项目名称",null),
			unconfirmedbugcnt: commonLogic.appcommonhandle("未确认Bug总数",null),
			unclosedbugcnt: commonLogic.appcommonhandle("未关闭Bug总数",null),
			totalwh: commonLogic.appcommonhandle("总工时",null),
			releasedstorycnt: commonLogic.appcommonhandle("已发布需求数",null),
			yesterdayctaskcnt: commonLogic.appcommonhandle("昨日完成任务数",null),
			yesterdayrbugcnt: commonLogic.appcommonhandle("昨天解决Bug数",null),
			end: commonLogic.appcommonhandle("截止日期",null),
			status: commonLogic.appcommonhandle("状态",null),
			order1: commonLogic.appcommonhandle("项目排序",null),
			istop: commonLogic.appcommonhandle("是否置顶",null),
			closedtaskcnt: commonLogic.appcommonhandle("已关闭任务数",null),
			canceltaskcnt: commonLogic.appcommonhandle("已取消任务数",null),
			pausetaskcnt: commonLogic.appcommonhandle("已暂停任务数",null),
			waittaskcnt: commonLogic.appcommonhandle("未开始任务数",null),
			doingtaskcnt: commonLogic.appcommonhandle("进行中任务数",null),
			donetaskcnt: commonLogic.appcommonhandle("已完成任务数",null),
			designtakcnt: commonLogic.appcommonhandle("设计类型任务",null),
		},
			views: {
				allgridview: {
					caption: commonLogic.appcommonhandle("所有项目",null),
					title: commonLogic.appcommonhandle("所有项目",null),
				},
				usr2gridviewtaskstatuscount: {
					caption: commonLogic.appcommonhandle("任务状态统计",null),
					title: commonLogic.appcommonhandle("项目统计表格视图（任务状态统计）",null),
				},
				editview: {
					caption: commonLogic.appcommonhandle("项目统计",null),
					title: commonLogic.appcommonhandle("项目统计编辑视图",null),
				},
				editview9: {
					caption: commonLogic.appcommonhandle("项目统计",null),
					title: commonLogic.appcommonhandle("项目统计编辑视图",null),
				},
				gridview9: {
					caption: commonLogic.appcommonhandle("项目统计",null),
					title: commonLogic.appcommonhandle("项目统计表格视图",null),
				},
			},
			main_form: {
				details: {
					grouppanel6: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel5: commonLogic.appcommonhandle("分组面板",null), 
					grouppanel4: commonLogic.appcommonhandle("分组面板",null), 
					rawitem1: commonLogic.appcommonhandle("",null), 
					rawitem6: commonLogic.appcommonhandle("",null), 
					rawitem7: commonLogic.appcommonhandle("",null), 
					grouppanel1: commonLogic.appcommonhandle("任务统计",null), 
					rawitem2: commonLogic.appcommonhandle("",null), 
					rawitem5: commonLogic.appcommonhandle("",null), 
					rawitem8: commonLogic.appcommonhandle("",null), 
					grouppanel2: commonLogic.appcommonhandle("需求统计",null), 
					rawitem3: commonLogic.appcommonhandle("",null), 
					rawitem4: commonLogic.appcommonhandle("",null), 
					rawitem9: commonLogic.appcommonhandle("",null), 
					grouppanel3: commonLogic.appcommonhandle("bug统计",null), 
					group1: commonLogic.appcommonhandle("项目统计基本信息",null), 
					formpage1: commonLogic.appcommonhandle("基本信息",null), 
					srforikey: commonLogic.appcommonhandle("",null), 
					srfkey: commonLogic.appcommonhandle("项目编号",null), 
					srfmajortext: commonLogic.appcommonhandle("项目名称",null), 
					srftempmode: commonLogic.appcommonhandle("",null), 
					srfuf: commonLogic.appcommonhandle("",null), 
					srfdeid: commonLogic.appcommonhandle("",null), 
					srfsourcekey: commonLogic.appcommonhandle("",null), 
					custom7: commonLogic.appcommonhandle("总工时",null), 
					custom8: commonLogic.appcommonhandle("任务消耗总工时",null), 
					formitemex4: commonLogic.appcommonhandle("已完成",null), 
					totalestimate: commonLogic.appcommonhandle("预计",null), 
					totalconsumed: commonLogic.appcommonhandle("消耗",null), 
					totalleft: commonLogic.appcommonhandle("剩余",null), 
					yesterdayctaskcnt: commonLogic.appcommonhandle("",null), 
					taskcnt: commonLogic.appcommonhandle("总任务",null), 
					custom1: commonLogic.appcommonhandle("任务总数",null), 
					custom2: commonLogic.appcommonhandle("已结束任务总数",null), 
					formitemex1: commonLogic.appcommonhandle("",null), 
					undonetaskcnt: commonLogic.appcommonhandle("未完成",null), 
					releasedstorycnt: commonLogic.appcommonhandle("",null), 
					storycnt: commonLogic.appcommonhandle("总需求",null), 
					custom3: commonLogic.appcommonhandle("需求总数",null), 
					custom4: commonLogic.appcommonhandle("关闭需求总数",null), 
					formitemex2: commonLogic.appcommonhandle("",null), 
					unclosedstorycnt: commonLogic.appcommonhandle("未关闭",null), 
					yesterdayrbugcnt: commonLogic.appcommonhandle("",null), 
					bugcnt: commonLogic.appcommonhandle("所有",null), 
					custom5: commonLogic.appcommonhandle("Bug总数",null), 
					custom6: commonLogic.appcommonhandle("已解决Bug总数",null), 
					formitemex3: commonLogic.appcommonhandle("",null), 
					activebugcnt: commonLogic.appcommonhandle("未解决",null), 
					id: commonLogic.appcommonhandle("项目编号",null), 
				},
				uiactions: {
				},
			},
			allproject_grid: {
				columns: {
					name: commonLogic.appcommonhandle("项目名称",null),
					end: commonLogic.appcommonhandle("截至日期",null),
					status: commonLogic.appcommonhandle("状态",null),
					totalestimate: commonLogic.appcommonhandle("预计",null),
					totalconsumed: commonLogic.appcommonhandle("消耗",null),
					totalleft: commonLogic.appcommonhandle("剩余",null),
					totalwh: commonLogic.appcommonhandle("总工时",null),
					progress: commonLogic.appcommonhandle("进度",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			projecttaskstatuscount_grid: {
				columns: {
					name: commonLogic.appcommonhandle("项目名称",null),
					doingtaskcnt: commonLogic.appcommonhandle("进行中",null),
					waittaskcnt: commonLogic.appcommonhandle("未开始",null),
					pausetaskcnt: commonLogic.appcommonhandle("已暂停",null),
					closedtaskcnt: commonLogic.appcommonhandle("已关闭",null),
					canceltaskcnt: commonLogic.appcommonhandle("已取消",null),
					donetaskcnt: commonLogic.appcommonhandle("已完成",null),
					taskcnt: commonLogic.appcommonhandle("总计",null),
				},
				nodata:commonLogic.appcommonhandle("",null),
				uiactions: {
				},
			},
			notcloseproject_grid: {
				columns: {
					name: commonLogic.appcommonhandle("项目名称",null),
					end: commonLogic.appcommonhandle("截至日期",null),
					status: commonLogic.appcommonhandle("状态",null),
					totalestimate: commonLogic.appcommonhandle("预计",null),
					totalconsumed: commonLogic.appcommonhandle("消耗",null),
					totalleft: commonLogic.appcommonhandle("剩余",null),
					totalwh: commonLogic.appcommonhandle("总工时",null),
					progress: commonLogic.appcommonhandle("进度",null),
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
			allgridviewtoolbar_toolbar: {
				deuiaction3_addproject: {
					caption: commonLogic.appcommonhandle("添加项目",null),
					tip: commonLogic.appcommonhandle("添加项目",null),
				},
				deuiaction2: {
					caption: commonLogic.appcommonhandle("刷新",null),
					tip: commonLogic.appcommonhandle("刷新",null),
				},
				deuiaction1: {
					caption: commonLogic.appcommonhandle("导出",null),
					tip: commonLogic.appcommonhandle("导出",null),
				},
				deuiaction4: {
					caption: commonLogic.appcommonhandle("过滤",null),
					tip: commonLogic.appcommonhandle("过滤",null),
				},
			},
			editviewtoolbar_toolbar: {
				tbitem3: {
					caption: commonLogic.appcommonhandle("保存",null),
					tip: commonLogic.appcommonhandle("保存",null),
				},
				tbitem4: {
					caption: commonLogic.appcommonhandle("保存并新建",null),
					tip: commonLogic.appcommonhandle("保存并新建",null),
				},
				tbitem5: {
					caption: commonLogic.appcommonhandle("保存并关闭",null),
					tip: commonLogic.appcommonhandle("保存并关闭",null),
				},
				tbitem7: {
					caption: commonLogic.appcommonhandle("删除",null),
					tip: commonLogic.appcommonhandle("删除",null),
				},
			},
		};
		return data;
}
export default getLocaleResourceBase;