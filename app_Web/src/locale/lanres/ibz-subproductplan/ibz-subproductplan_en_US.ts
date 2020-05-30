
export default {
  fields: {
    title: '名称',
    id: '编号',
    begin: '开始日期',
    desc: '描述',
    end: '结束日期',
    deleted: '已删除',
    order: '排序',
    parentname: '父计划名称',
    branch: '平台/分支',
    parent: '父计划',
    product: '产品',
  },
	views: {
		subplancreateview: {
			caption: "产品计划",
      title: '产品计划',
		},
		subplangridview: {
			caption: "产品计划",
      title: '产品计划',
		},
		subplaneditview: {
			caption: "子计划",
      title: '子计划',
		},
	},
	mainsubplan_form: {
		details: {
			grouppanel1: "分组面板", 
			group1: "productplan基本信息", 
			button1: "Save And Close", 
			button2: "关闭", 
			grouppanel3: "分组面板", 
			grouppanel2: "分组面板", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "编号", 
			srfmajortext: "名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			product: "产品", 
			parent: "父计划", 
			parentname: "父计划名称", 
			title: "名称", 
			begin: "开始日期", 
			end: "结束日期", 
			desc: "描述", 
			id: "编号", 
		},
		uiactions: {
			saveandexit: "Save And Close",
			exit: "关闭",
		},
	},
	mainsubplan_grid: {
		columns: {
			id: "编号",
			title: "名称",
			begin: "开始日期",
			end: "结束日期",
		},
		uiactions: {
		},
	},
	subplangridviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "New",
			tip: "New",
		},
		deuiaction4: {
			caption: "Remove",
			tip: "Remove {0}",
		},
		seperator1: {
			caption: "",
			tip: "",
		},
		deuiaction2: {
			caption: "刷新",
			tip: "刷新",
		},
	},
	subplancreateviewtoolbar_toolbar: {
		deuiaction2: {
			caption: "Save",
			tip: "Save",
		},
		deuiaction1: {
			caption: "Save And Close",
			tip: "Save And Close Window",
		},
	},
};