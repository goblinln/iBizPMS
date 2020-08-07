
export default {
  fields: {
    stories: '完成的需求',
    marker: '里程碑',
    id: 'ID',
    leftbugs: '遗留的Bug',
    bugs: '解决的Bug',
    deleted: '已删除',
    name: '发布名称',
    date: '发布日期',
    status: '状态',
    substatus: '子状态',
    desc: '描述',
    buildname: '版本',
    product: '产品',
    build: '版本',
    branch: '平台/分支',
    productname: '产品名称',
  },
	views: {
		maininfoview: {
			caption: "发布",
      title: '发布',
		},
		gridview: {
			caption: "发布",
      title: '发布',
		},
		editview: {
			caption: "发布",
      title: '发布',
		},
		maintabexpview: {
			caption: "产品发布",
      title: '产品发布',
		},
		quickcreateview: {
			caption: "发布",
      title: '发布',
		},
	},
	main_form: {
		details: {
			group1: "基本信息", 
			grouppanel1: "附件", 
			druipart1: "", 
			grouppanel2: "历史记录", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "ID", 
			srfmajortext: "发布名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			productname: "产品名称", 
			name: "发布名称", 
			buildname: "版本", 
			status: "状态", 
			date: "发布日期", 
			desc: "描述", 
			id: "ID", 
			product: "产品", 
			build: "版本", 
		},
		uiactions: {
		},
	},
	quickcreate_form: {
		details: {
			grouppanel1: "分组面板", 
			grouppanel2: "分组面板", 
			group1: "release基本信息", 
			formpage1: "基本信息", 
			srforikey: "", 
			srfkey: "ID", 
			srfmajortext: "发布名称", 
			srftempmode: "", 
			srfuf: "", 
			srfdeid: "", 
			srfsourcekey: "", 
			product: "产品", 
			productname: "产品名称", 
			name: "发布名称", 
			buildname: "版本", 
			date: "发布日期", 
			marker: "里程碑", 
			desc: "描述", 
			id: "ID", 
			build: "版本", 
		},
		uiactions: {
		},
	},
	main_grid: {
		columns: {
			id: "ID",
			name: "发布名称",
			buildname: "版本",
			status: "状态",
			date: "发布日期",
		},
		uiactions: {
		},
	},
	gridviewtoolbar_toolbar: {
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
		seperator3: {
			caption: "",
			tip: "",
		},
		deuiaction5: {
			caption: "Export",
			tip: "Export {0} Data To Excel",
		},
	},
	quickcreateviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "Save And Close",
			tip: "Save And Close Window",
		},
	},
	editviewtoolbar_toolbar: {
		deuiaction1: {
			caption: "Save And Close",
			tip: "Save And Close Window",
		},
	},
};