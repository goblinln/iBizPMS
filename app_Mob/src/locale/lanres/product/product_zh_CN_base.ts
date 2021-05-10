import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    orgid: "组织标识",
    updatedate: "更新时间",
    istop: "是否置顶",
    comment: "备注",
    qd: "测试负责人",
    productclass: "产品分类",
    unconfirmbugcnt: "未确认Bug数",
    acl: "访问控制",
    name: "产品名称",
    mobimage: "移动端图片",
    testtaskcnt: "测试单数",
    testsuitecnt: "套件数",
    productplancnt: "计划总数",
    id: "编号",
    deleted: "已删除",
    closedstorycnt: "已关闭需求",
    relatedbugcnt: "相关Bug数",
    whitelist: "分组白名单",
    mdeptid: "部门标识",
    releasecnt: "发布总数",
    rd: "发布负责人",
    popk: "产品负责人（选择）",
    notclosedbugcnt: "未关闭Bug数",
    updateman: "更新人",
    supproreport: "支持产品汇报",
    order: "排序",
    type: "产品类型",
    mdeptname: "归属部门名",
    po: "产品负责人",
    qdpk: "测试负责人（选择）",
    desc: "产品描述	",
    status: "状态",
    changedstorycnt: "已变更需求",
    activebugcnt: "未解决Bug数",
    createdby: "由谁创建",
    rdpk: "发布负责人（选择）",
    createman: "建立人",
    createdversion: "当前系统版本",
    draftstorycnt: "草稿需求",
    doccnt: "文档数",
    casecnt: "用例数",
    relatedprojects: "关联项目数",
    ibiz_id: "IBIZ标识",
    substatus: "子状态",
    code: "产品代号",
    srfcount: "属性",
    order1: "排序",
    updateby: "由谁更新",
    buildcnt: "BUILD数",
    orgname: "归属组织名",
    createddate: "创建日期",
    noticeusers: "消息通知用户",
    activestorycnt: "激活需求数",
    linename: "产品线",
    line: "产品线",
    productsn: "产品编号",
  },
	views: {
		testmobmdview: {
			caption: commonLogic.appcommonhandle("测试",null),
		},
		mobtabexpview: {
			caption: commonLogic.appcommonhandle("测试",null),
		},
		mobpickupview: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
		closemobeditview: {
			caption: commonLogic.appcommonhandle("关闭产品",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
		prodmobtabexpview: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
		newmobeditview: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
		mobchartview: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
		mobchartview9: {
			caption: commonLogic.appcommonhandle("产品",null),
		},
	},
	mobnewfrom_form: {
		details: {
			grouppanel3: commonLogic.appcommonhandle("分组面板",null), 
			grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
			group1: commonLogic.appcommonhandle("product基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("产品名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("产品名称",null), 
			code: commonLogic.appcommonhandle("产品代号",null), 
			line: commonLogic.appcommonhandle("产品线",null), 
			linename: commonLogic.appcommonhandle("产品线",null), 
			popk: commonLogic.appcommonhandle("产品负责人",null), 
			qdpk: commonLogic.appcommonhandle("测试负责人",null), 
			rdpk: commonLogic.appcommonhandle("发布负责人",null), 
			type: commonLogic.appcommonhandle("产品类型",null), 
			desc: commonLogic.appcommonhandle("产品描述	",null), 
			acl: commonLogic.appcommonhandle("访问控制",null), 
			id: commonLogic.appcommonhandle("编号",null), 
			po: commonLogic.appcommonhandle("产品负责人",null), 
			rd: commonLogic.appcommonhandle("发布负责人",null), 
			qd: commonLogic.appcommonhandle("测试负责人",null), 
		},
		uiactions: {
		},
	},
	mobmain_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("产品基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("产品名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("产品名称",null), 
			status: commonLogic.appcommonhandle("状态",null), 
			type: commonLogic.appcommonhandle("产品类型",null), 
			po: commonLogic.appcommonhandle("产品负责人",null), 
			qd: commonLogic.appcommonhandle("测试负责人",null), 
			rd: commonLogic.appcommonhandle("发布负责人",null), 
			productplancnt: commonLogic.appcommonhandle("计划总数",null), 
			activestorycnt: commonLogic.appcommonhandle("激活需求数",null), 
			buildcnt: commonLogic.appcommonhandle("BUILD数",null), 
			relatedbugcnt: commonLogic.appcommonhandle("相关Bug数",null), 
			unconfirmbugcnt: commonLogic.appcommonhandle("未确认Bug数",null), 
			activebugcnt: commonLogic.appcommonhandle("未解决Bug数",null), 
			notclosedbugcnt: commonLogic.appcommonhandle("未关闭Bug数",null), 
			casecnt: commonLogic.appcommonhandle("用例数",null), 
			desc: commonLogic.appcommonhandle("产品描述	",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobclose_form: {
		details: {
			group1: commonLogic.appcommonhandle("产品基本信息",null), 
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("产品名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			comment: commonLogic.appcommonhandle("备注",null), 
			mobimage: commonLogic.appcommonhandle("",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			n_name_like: commonLogic.appcommonhandle("产品名称",null), 
			n_status_eq: commonLogic.appcommonhandle("状态",null), 
			n_type_eq: commonLogic.appcommonhandle("产品类型",null), 
			n_linename_like: commonLogic.appcommonhandle("产品线",null), 
		},
		uiactions: {
		},
	},
	mobpickupmdviewmdctrl_batchtoolbar_toolbar: {
	},
	newmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	closemobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("保存",null),
			tip: '保存',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
		items1: {
			caption: commonLogic.appcommonhandle("更多",null),
			tip: '更多',
		},
		deuiaction1: {
			caption: commonLogic.appcommonhandle("关闭",null),
			tip: '关闭',
		},
		deuiaction2: {
			caption: commonLogic.appcommonhandle("删除",null),
			tip: '删除',
		},
	},
	testmobmdviewmdctrl_batchtoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("删除",null),
			tip: '删除',
		},
	},
	mobmdviewmdctrl_batchtoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("删除",null),
			tip: '删除',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建",null),
			tip: '新建',
		},
	},
};