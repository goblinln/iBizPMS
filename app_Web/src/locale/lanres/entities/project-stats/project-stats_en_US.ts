
export default {
  fields: {
    id: "项目编号",
    storycnt: "需求总数",
    taskcnt: "任务总数",
    totalestimate: "任务最初预计总工时",
    totalconsumed: "任务消耗总工时",
    totalleft: "任务预计剩余总工时",
    undonetaskcnt: "未完成任务总数",
    closedstorycnt: "关闭需求总数",
    bugcnt: "Bug总数",
    activebugcnt: "未解决Bug总数",
    unclosedstorycnt: "未关闭需求总数",
    finishtaskcnt: "已结束任务总数",
    finishbugcnt: "已解决Bug总数",
    deleted: "已删除",
    time: "工时",
    type: "工时类型",
    name: "项目名称",
    unconfirmedbugcnt: "未确认Bug总数",
    unclosedbugcnt: "未关闭Bug总数",
    totalwh: "总工时",
    releasedstorycnt: "已发布需求数",
    yesterdayctaskcnt: "昨日完成任务数",
    yesterdayrbugcnt: "昨天解决Bug数",
    end: "截止日期",
    status: "状态",
  },
	views: {
		allgridview: {
			caption: "所有项目",
      		title: "所有项目",
		},
		editview9: {
			caption: "项目统计",
      		title: "项目统计编辑视图",
		},
		gridview9: {
			caption: "项目统计",
      		title: "项目统计表格视图",
		},
	},
	main_form: {
		details: {
			grouppanel6: "分组面板", 
			grouppanel5: "分组面板", 
			grouppanel4: "分组面板", 
			rawitem1: "", 
			rawitem6: "", 
			rawitem7: "", 
			grouppanel1: "任务统计", 
			rawitem2: "", 
			rawitem5: "", 
			rawitem8: "", 
			grouppanel2: "需求统计", 
			rawitem3: "", 
			rawitem4: "", 
			rawitem9: "", 
			grouppanel3: "bug统计", 
			group1: "项目统计基本信息", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "项目编号", 
			srfmajortext: "项目名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			custom7: "总工时", 
			custom8: "任务消耗总工时", 
			formitemex4: "已完成", 
			totalestimate: "预计", 
			totalconsumed: "消耗", 
			totalleft: "剩余", 
			yesterdayctaskcnt: "", 
			taskcnt: "总任务", 
			custom1: "任务总数", 
			custom2: "已结束任务总数", 
			formitemex1: "", 
			undonetaskcnt: "未完成", 
			releasedstorycnt: "", 
			storycnt: "总需求", 
			custom3: "需求总数", 
			custom4: "关闭需求总数", 
			formitemex2: "", 
			unclosedstorycnt: "未关闭", 
			yesterdayrbugcnt: "", 
			bugcnt: "所有", 
			custom5: "Bug总数", 
			custom6: "已解决Bug总数", 
			formitemex3: "", 
			activebugcnt: "未解决", 
			id: "项目编号", 
		},
		uiactions: {
		},
	},
	allproject_grid: {
		columns: {
			name: "项目名称",
			end: "截至日期",
			status: "状态",
			totalestimate: "预计",
			totalconsumed: "消耗",
			totalleft: "剩余",
			totalwh: "总工时",
			progress: "进度",
		},
		uiactions: {
		},
	},
	notcloseproject_grid: {
		columns: {
			name: "项目名称",
			end: "截至日期",
			status: "状态",
			totalestimate: "预计",
			totalconsumed: "消耗",
			totalleft: "剩余",
			totalwh: "总工时",
			progress: "进度",
		},
		uiactions: {
		},
	},
	default_searchform: {
		details: {
			formpage1: "常规条件", 
		},
		uiactions: {
		},
	},
	allgridviewtoolbar_toolbar: {
		deuiaction3_addproject: {
			caption: "添加项目",
			tip: "添加项目",
		},
		deuiaction2: {
			caption: "刷新",
			tip: "刷新",
		},
		seperator2: {
			caption: "",
			tip: "",
		},
		deuiaction1: {
			caption: "Export",
			tip: "Export {0} Data To Excel",
		},
	},
};