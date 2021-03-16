import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		standard: commonLogic.appcommonhandle("标准规范",null),
		type: commonLogic.appcommonhandle("工时类型",null),
		emptystory: commonLogic.appcommonhandle("空需求",null),
		draftstory: commonLogic.appcommonhandle("草稿需求",null),
		end: commonLogic.appcommonhandle("截止日期",null),
		releasedstagestorycnt: commonLogic.appcommonhandle("已发布阶段需求数",null),
		importantbugcnt: commonLogic.appcommonhandle("重要Bug数",null),
		canceltaskcnt: commonLogic.appcommonhandle("已取消任务数",null),
		begin: commonLogic.appcommonhandle("开始时间",null),
		status: commonLogic.appcommonhandle("状态",null),
		seriousbugproportion: commonLogic.appcommonhandle("严重Bug比率",null),
		doingtaskcnt: commonLogic.appcommonhandle("进行中任务数",null),
		undonetaskcnt: commonLogic.appcommonhandle("未完成任务总数",null),
		servetaskcnt: commonLogic.appcommonhandle("服务类型任务",null),
		donetaskcnt: commonLogic.appcommonhandle("已完成任务数",null),
		totalwh: commonLogic.appcommonhandle("总工时",null),
		unclosedstorycnt: commonLogic.appcommonhandle("未关闭需求总数",null),
		misctaskcnt: commonLogic.appcommonhandle("其他类型任务",null),
		leftstorycnt: commonLogic.appcommonhandle("剩余需求数",null),
		performance: commonLogic.appcommonhandle("性能问题",null),
		emptystagestorycnt: commonLogic.appcommonhandle("空阶段需求数",null),
		waitstagestorycnt: commonLogic.appcommonhandle("未开始阶段需求数",null),
		developingstagestorycnt: commonLogic.appcommonhandle("研发中阶段需求数",null),
		istop: commonLogic.appcommonhandle("是否置顶",null),
		completestorycnt: commonLogic.appcommonhandle("完成需求数",null),
		testtaskcnt: commonLogic.appcommonhandle("测试类型任务",null),
		deleted: commonLogic.appcommonhandle("已删除",null),
		storycnt: commonLogic.appcommonhandle("需求总数",null),
		projectedstagestorycnt: commonLogic.appcommonhandle("已立项阶段需求数",null),
		discusstaskcnt: commonLogic.appcommonhandle("讨论类型任务",null),
		completetaskcnt: commonLogic.appcommonhandle("完成任务数",null),
		plannedstagestorycnt: commonLogic.appcommonhandle("已计划阶段需求数",null),
		testedstagestorycnt: commonLogic.appcommonhandle("测试完毕阶段需求数",null),
		name: commonLogic.appcommonhandle("项目名称",null),
		taskcnt: commonLogic.appcommonhandle("任务总数",null),
		totalleft: commonLogic.appcommonhandle("任务预计剩余总工时",null),
		timescale: commonLogic.appcommonhandle("工期",null),
		activestory: commonLogic.appcommonhandle("激活需求",null),
		order1: commonLogic.appcommonhandle("项目排序",null),
		activebugcnt: commonLogic.appcommonhandle("未解决Bug总数",null),
		designdefect: commonLogic.appcommonhandle("设计缺陷",null),
		waittaskcnt: commonLogic.appcommonhandle("未开始任务数",null),
		pausetaskcnt: commonLogic.appcommonhandle("已暂停任务数",null),
		closedstagestorycnt: commonLogic.appcommonhandle("已关闭阶段需求数",null),
		totalestimate: commonLogic.appcommonhandle("任务最初预计总工时",null),
		verifiedstagestorycnt: commonLogic.appcommonhandle("已验收阶段需求数",null),
		install: commonLogic.appcommonhandle("安装部署",null),
		id: commonLogic.appcommonhandle("项目编号",null),
		designtaskcnt: commonLogic.appcommonhandle("设计类型任务",null),
		security: commonLogic.appcommonhandle("安全相关",null),
		others: commonLogic.appcommonhandle("其他",null),
		bugcnt: commonLogic.appcommonhandle("Bug总数",null),
		unclosedbugcnt: commonLogic.appcommonhandle("未关闭Bug总数",null),
		yesterdayctaskcnt: commonLogic.appcommonhandle("昨日完成任务数",null),
		time: commonLogic.appcommonhandle("工时",null),
		automation: commonLogic.appcommonhandle("测试脚本",null),
		unconfirmedbugcnt: commonLogic.appcommonhandle("未确认Bug总数",null),
		developedstagestorycnt: commonLogic.appcommonhandle("研发完毕阶段需求数",null),
		testingstagestorycnt: commonLogic.appcommonhandle("测试中阶段需求数",null),
		finishtaskcnt: commonLogic.appcommonhandle("已结束任务总数",null),
		config: commonLogic.appcommonhandle("配置相关",null),
		totalconsumed: commonLogic.appcommonhandle("任务消耗总工时",null),
		affairtaskcnt: commonLogic.appcommonhandle("事务类型任务",null),
		projecttotalconsumed: commonLogic.appcommonhandle("项目消耗总工时",null),
		uitaskcnt: commonLogic.appcommonhandle("界面类型任务",null),
		bugstory: commonLogic.appcommonhandle("Bug/完成需求",null),
		closedstory: commonLogic.appcommonhandle("已关闭需求",null),
		bugtask: commonLogic.appcommonhandle("Bug/完成任务",null),
		releasedstorycnt: commonLogic.appcommonhandle("已发布需求数",null),
		studytaskcnt: commonLogic.appcommonhandle("研究类型任务",null),
		closedtaskcnt: commonLogic.appcommonhandle("已关闭任务数",null),
		codeerror: commonLogic.appcommonhandle("代码错误",null),
		closedstorycnt: commonLogic.appcommonhandle("关闭需求总数",null),
		finishbugcnt: commonLogic.appcommonhandle("已解决Bug总数",null),
		changedstory: commonLogic.appcommonhandle("已变更需求",null),
		develtaskcnt: commonLogic.appcommonhandle("开发类型任务",null),
		membercnt: commonLogic.appcommonhandle("人数",null),
		progress: commonLogic.appcommonhandle("进度",null),
		yesterdayrbugcnt: commonLogic.appcommonhandle("昨天解决Bug数",null),
	},
		views: {
			usr2gridviewtasktypecount: {
				caption: commonLogic.appcommonhandle("任务类型统计",null),
				title: commonLogic.appcommonhandle("项目统计表格视图（任务状态统计）",null),
			},
			projectstorystagestatusgridview: {
				caption: commonLogic.appcommonhandle("项目需求阶段统计",null),
				title: commonLogic.appcommonhandle("项目统计表格视图",null),
			},
			projectprogressusr2gridview: {
				caption: commonLogic.appcommonhandle("项目统计",null),
				title: commonLogic.appcommonhandle("项目进度表格视图",null),
			},
			projectqualityusr2gridview: {
				caption: commonLogic.appcommonhandle("项目质量表",null),
				title: commonLogic.appcommonhandle("项目质量表",null),
			},
			usr2gridviewtaskstatuscount: {
				caption: commonLogic.appcommonhandle("任务状态统计",null),
				title: commonLogic.appcommonhandle("项目统计表格视图（任务状态统计）",null),
			},
			projectinputstatsgridview: {
				caption: commonLogic.appcommonhandle("项目投入统计",null),
				title: commonLogic.appcommonhandle("项目统计表格视图",null),
			},
			editview: {
				caption: commonLogic.appcommonhandle("项目统计",null),
				title: commonLogic.appcommonhandle("项目统计编辑视图",null),
			},
			gridview9: {
				caption: commonLogic.appcommonhandle("项目统计",null),
				title: commonLogic.appcommonhandle("项目统计表格视图",null),
			},
			allgridview: {
				caption: commonLogic.appcommonhandle("所有项目",null),
				title: commonLogic.appcommonhandle("所有项目",null),
			},
			projectstorystatusstatsgridview: {
				caption: commonLogic.appcommonhandle("项目需求状态统计",null),
				title: commonLogic.appcommonhandle("项目统计表格视图",null),
			},
			projectbugtypesumusr2gridview: {
				caption: commonLogic.appcommonhandle("项目统计",null),
				title: commonLogic.appcommonhandle("项目bug类型统计表格视图",null),
			},
			editview9: {
				caption: commonLogic.appcommonhandle("项目统计",null),
				title: commonLogic.appcommonhandle("项目统计编辑视图",null),
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
		projecttasktypecount_grid: {
			columns: {
				name: commonLogic.appcommonhandle("项目名称",null),
				designtaskcnt: commonLogic.appcommonhandle("设计",null),
				develtaskcnt: commonLogic.appcommonhandle("开发",null),
				testtaskcnt: commonLogic.appcommonhandle("测试",null),
				studytaskcnt: commonLogic.appcommonhandle("研究",null),
				discusstaskcnt: commonLogic.appcommonhandle("讨论",null),
				uitaskcnt: commonLogic.appcommonhandle("界面",null),
				affairtaskcnt: commonLogic.appcommonhandle("事务",null),
				servetaskcnt: commonLogic.appcommonhandle("服务",null),
				misctaskcnt: commonLogic.appcommonhandle("其他",null),
				taskcnt: commonLogic.appcommonhandle("总计",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		projectbugtypesum_grid: {
			columns: {
				name: commonLogic.appcommonhandle("项目名称",null),
				codeerror: commonLogic.appcommonhandle("代码错误",null),
				config: commonLogic.appcommonhandle("配置相关",null),
				designdefect: commonLogic.appcommonhandle("设计缺陷",null),
				install: commonLogic.appcommonhandle("安装部署",null),
				performance: commonLogic.appcommonhandle("性能问题",null),
				security: commonLogic.appcommonhandle("安全相关",null),
				standard: commonLogic.appcommonhandle("标准规范",null),
				automation: commonLogic.appcommonhandle("测试脚本",null),
				others: commonLogic.appcommonhandle("其他",null),
				bugcnt: commonLogic.appcommonhandle("Bug总数",null),
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
		projectquality_grid: {
			columns: {
				name: commonLogic.appcommonhandle("项目名称",null),
				storycnt: commonLogic.appcommonhandle("需求总数",null),
				completestorycnt: commonLogic.appcommonhandle("完成需求数",null),
				taskcnt: commonLogic.appcommonhandle("任务总数",null),
				completetaskcnt: commonLogic.appcommonhandle("完成任务数",null),
				bugcnt: commonLogic.appcommonhandle("Bug数",null),
				finishbugcnt: commonLogic.appcommonhandle("解决Bug数",null),
				bugstory: commonLogic.appcommonhandle("Bug/完成需求",null),
				bugtask: commonLogic.appcommonhandle("Bug/完成任务",null),
				importantbugcnt: commonLogic.appcommonhandle("重要Bug数",null),
				seriousbugproportion: commonLogic.appcommonhandle("严重Bug比率",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		projectstorystagestats_grid: {
			columns: {
				name: commonLogic.appcommonhandle("项目名称",null),
				emptystagestorycnt: commonLogic.appcommonhandle("空",null),
				waitstagestorycnt: commonLogic.appcommonhandle("未开始",null),
				plannedstagestorycnt: commonLogic.appcommonhandle("已计划",null),
				projectedstagestorycnt: commonLogic.appcommonhandle("已立项",null),
				developingstagestorycnt: commonLogic.appcommonhandle("研发中",null),
				developedstagestorycnt: commonLogic.appcommonhandle("研发完毕",null),
				testingstagestorycnt: commonLogic.appcommonhandle("测试中",null),
				testedstagestorycnt: commonLogic.appcommonhandle("测试完毕",null),
				verifiedstagestorycnt: commonLogic.appcommonhandle("已验收",null),
				releasedstagestorycnt: commonLogic.appcommonhandle("已发布",null),
				closedstagestorycnt: commonLogic.appcommonhandle("已关闭",null),
				storycnt: commonLogic.appcommonhandle("总计",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		projectinputstats_grid: {
			columns: {
				name: commonLogic.appcommonhandle("项目名称",null),
				timescale: commonLogic.appcommonhandle("工期",null),
				taskcnt: commonLogic.appcommonhandle("任务数",null),
				storycnt: commonLogic.appcommonhandle("需求数",null),
				membercnt: commonLogic.appcommonhandle("人数",null),
				projecttotalconsumed: commonLogic.appcommonhandle("总消耗",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		projectprogress_grid: {
			columns: {
				name: commonLogic.appcommonhandle("项目名称",null),
				storycnt: commonLogic.appcommonhandle("需求数",null),
				leftstorycnt: commonLogic.appcommonhandle("剩余需求数",null),
				taskcnt: commonLogic.appcommonhandle("任务数",null),
				undonetaskcnt: commonLogic.appcommonhandle("剩余任务数",null),
				totalleft: commonLogic.appcommonhandle("剩余工时",null),
				totalconsumed: commonLogic.appcommonhandle("已消耗工时",null),
				progress: commonLogic.appcommonhandle("进度",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		projectstorystatusstats_grid: {
			columns: {
				name: commonLogic.appcommonhandle("项目名称",null),
				emptystory: commonLogic.appcommonhandle("空",null),
				draftstory: commonLogic.appcommonhandle("草稿",null),
				activestory: commonLogic.appcommonhandle("激活",null),
				closedstory: commonLogic.appcommonhandle("已关闭",null),
				changedstory: commonLogic.appcommonhandle("已变更",null),
				storycnt: commonLogic.appcommonhandle("总计",null),
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
		projecttasktypecount_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("表单分页",null), 
				n_status_eq: commonLogic.appcommonhandle("项目状态",null), 
				n_begin_gtandeq: commonLogic.appcommonhandle("项目起始时间",null), 
				n_end_ltandeq: commonLogic.appcommonhandle("至",null), 
			},
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
		projectstorystats_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("表单分页",null), 
				n_status_eq: commonLogic.appcommonhandle("项目状态",null), 
			},
			uiactions: {
			},
		},
		projectinputstats_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("表单分页",null), 
				n_status_eq: commonLogic.appcommonhandle("项目状态",null), 
				n_begin_gtandeq: commonLogic.appcommonhandle("项目起始时间",null), 
				n_end_ltandeq: commonLogic.appcommonhandle("项目结束时间",null), 
			},
			uiactions: {
			},
		},
		projectstatusdef_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("表单分页",null), 
				n_status_eq: commonLogic.appcommonhandle("项目状态",null), 
			},
			uiactions: {
			},
		},
		usr2gridviewtasktypecounttoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
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
		projectbugtypesumusr2gridviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
		projectqualityusr2gridviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
		projectstorystagestatusgridviewtoolbar_toolbar: {
			deuiaction7: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
		projectinputstatsgridviewtoolbar_toolbar: {
			deuiaction7: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
		projectprogressusr2gridviewtoolbar_toolbar: {
			deuiaction1: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
		},
		projectstorystatusstatsgridviewtoolbar_toolbar: {
			deuiaction7: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
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