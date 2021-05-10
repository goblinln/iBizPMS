import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    dept:  commonLogic.appcommonhandle("归属部门",null),
    org:  commonLogic.appcommonhandle("归属组织",null),
    name:  commonLogic.appcommonhandle("名称",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    addedDate:  commonLogic.appcommonhandle("创建时间",null),
    id:  commonLogic.appcommonhandle("编号",null),
    deleted:  commonLogic.appcommonhandle("已删除",null),
    lastEditedBy:  commonLogic.appcommonhandle("最后编辑人",null),
    type:  commonLogic.appcommonhandle("类型",null),
    lastEditedDate:  commonLogic.appcommonhandle("最后编辑时间",null),
    addedBy:  commonLogic.appcommonhandle("由谁创建",null),
    casecnt:  commonLogic.appcommonhandle("用例数",null),
    desc:  commonLogic.appcommonhandle("描述",null),
    product:  commonLogic.appcommonhandle("所属产品",null),
    testsuitesn:  commonLogic.appcommonhandle("测试套件编号",null),
  },
	views: {
		mobtabexpview: {
			caption: commonLogic.appcommonhandle("测试套件",null),
		},
		newmobeditview: {
			caption: commonLogic.appcommonhandle("测试套件",null),
		},
		mobeditview: {
			caption: commonLogic.appcommonhandle("测试套件",null),
		},
		mobmdview: {
			caption: commonLogic.appcommonhandle("测试套件",null),
		},
	},
	mobedittable_form: {
		details: {
			group1: commonLogic.appcommonhandle("测试套件基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后编辑时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("名称",null), 
			desc: commonLogic.appcommonhandle("描述",null), 
			type: commonLogic.appcommonhandle("类型",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobmain_form: {
		details: {
			druipart1: commonLogic.appcommonhandle("",null), 
			grouppanel1: commonLogic.appcommonhandle("历史记录",null), 
			group1: commonLogic.appcommonhandle("测试套件基本信息",null), 
			formpage1: commonLogic.appcommonhandle("基本信息",null), 
			srfupdatedate: commonLogic.appcommonhandle("最后编辑时间",null), 
			srforikey: commonLogic.appcommonhandle("",null), 
			srfkey: commonLogic.appcommonhandle("编号",null), 
			srfmajortext: commonLogic.appcommonhandle("名称",null), 
			srftempmode: commonLogic.appcommonhandle("",null), 
			srfuf: commonLogic.appcommonhandle("",null), 
			srfdeid: commonLogic.appcommonhandle("",null), 
			srfsourcekey: commonLogic.appcommonhandle("",null), 
			name: commonLogic.appcommonhandle("名称",null), 
			type: commonLogic.appcommonhandle("类型",null), 
			addedby: commonLogic.appcommonhandle("由谁创建",null), 
			addeddate: commonLogic.appcommonhandle("创建时间",null), 
			lasteditedby: commonLogic.appcommonhandle("最后编辑人",null), 
			lastediteddate: commonLogic.appcommonhandle("最后编辑时间",null), 
			id: commonLogic.appcommonhandle("编号",null), 
		},
		uiactions: {
		},
	},
	mobdef_searchform: {
		details: {
			formpage1: commonLogic.appcommonhandle("常规条件",null), 
			n_name_like: commonLogic.appcommonhandle("名称",null), 
			n_type_eq: commonLogic.appcommonhandle("类型",null), 
		},
		uiactions: {
		},
	},
	newmobeditviewrighttoolbar_toolbar: {
		tbitem1: {
			caption: commonLogic.appcommonhandle("Save And Close",null),
			tip: 'tbitem1',
		},
	},
	mobmdviewrighttoolbar_toolbar: {
		deuiaction1: {
			caption: commonLogic.appcommonhandle("新建测试套件",null),
			tip: 'deuiaction1',
		},
	},
	mobeditviewrighttoolbar_toolbar: {
	},
};