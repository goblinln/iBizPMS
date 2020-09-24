export default {
  fields: {
    name: "名称编号",
    builder: "构建者",
    desc: "描述",
    id: "id",
    deleted: "已删除",
    scmPath: "源代码地址",
    filePath: "下载地址",
    stories: "完成的需求",
    bugs: "解决的Bug",
    date: "打包日期",
    product: "产品",
    branch: "平台/分支",
    project: "所属项目",
    productName: "产品名称",
    ids: "Bug版本健值",
    files: "附件",
    rebuild: "重新构建",
    releasetype: "运行模式",
    frontapplication: "系统应用",
    backgroundid: "后台体系",
    sqlid: "运行数据库",
  },
	views: {
		mobpickupmdview: {
			caption: '版本',
		},
		mobmdview: {
			caption: '版本',
		},
		mobeditview: {
			caption: '版本',
		},
		mobpickupview: {
			caption: '版本',
		},
		mobmpickupview: {
			caption: '版本',
		},
	},
	mobmain_form: {
		details: {
			group1: '版本基本信息', 
			formpage1: '基本信息', 
			srforikey: '', 
			srfkey: 'id', 
			srfmajortext: '名称编号', 
			srftempmode: '', 
			srfuf: '', 
			srfdeid: '', 
			srfsourcekey: '', 
			name: '名称编号', 
			date: '打包日期', 
			builder: '构建者', 
			id: 'id', 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: '常规条件', 
			n_name_like: '名称编号(文本包含(%))', 
			n_releasetype_eq: '运行模式(等于(=))', 
			n_sqlid_eq: '运行数据库(等于(=))', 
			n_project_eq: '所属项目(等于(=))', 
		},
		uiactions: {
		},
	},
	mobmdviewrighttoolbar_toolbar: {
	},
	mobeditviewrighttoolbar_toolbar: {
	},
};