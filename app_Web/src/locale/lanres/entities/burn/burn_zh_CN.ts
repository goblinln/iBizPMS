export default {
  fields: {
    date: "日期",
    id: "虚拟主键",
    consumed: "总计消耗",
    left: "预计剩余",
    estimate: "最初预计",
    project: "所属项目",
    task: "任务",
    isweekend: "周末",
  },
	views: {
		chartview: {
			caption: "燃尽图",
      		title: "燃尽图",
		},
	},
	default_searchform: {
		details: {
			formpage1: "常规条件", 
			formitem: "间隔", 
		},
		uiactions: {
		},
	},
	chartviewtoolbar_toolbar: {
		deuiaction1_computeburn: {
			caption: "更新燃尽图",
			tip: "更新燃尽图",
		},
	},
	burndown_chart: {
		nodata: "",
	},
	burndown2_chart: {
		nodata: "",
	},
};