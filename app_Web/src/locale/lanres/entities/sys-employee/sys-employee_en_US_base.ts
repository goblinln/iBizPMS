import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		userid: commonLogic.appcommonhandle("用户标识",null),
		username: commonLogic.appcommonhandle("用户全局名",null),
		personname: commonLogic.appcommonhandle("姓名",null),
		usercode: commonLogic.appcommonhandle("用户工号",null),
		loginname: commonLogic.appcommonhandle("登录名",null),
		password: commonLogic.appcommonhandle("密码",null),
		domains: commonLogic.appcommonhandle("区属",null),
		mdeptid: commonLogic.appcommonhandle("主部门",null),
		mdeptcode: commonLogic.appcommonhandle("主部门代码",null),
		mdeptname: commonLogic.appcommonhandle("主部门名称",null),
		bcode: commonLogic.appcommonhandle("业务编码",null),
		orgid: commonLogic.appcommonhandle("单位",null),
		orgcode: commonLogic.appcommonhandle("单位代码",null),
		orgname: commonLogic.appcommonhandle("单位名称",null),
		nickname: commonLogic.appcommonhandle("昵称别名",null),
		sex: commonLogic.appcommonhandle("性别",null),
		phone: commonLogic.appcommonhandle("联系方式",null),
		email: commonLogic.appcommonhandle("邮件",null),
		avatar: commonLogic.appcommonhandle("社交账号",null),
		addr: commonLogic.appcommonhandle("地址",null),
		usericon: commonLogic.appcommonhandle("照片",null),
		ipaddr: commonLogic.appcommonhandle("ip地址",null),
		lang: commonLogic.appcommonhandle("语言",null),
		memo: commonLogic.appcommonhandle("备注",null),
		reserver: commonLogic.appcommonhandle("保留",null),
		showorder: commonLogic.appcommonhandle("排序",null),
		enable: commonLogic.appcommonhandle("the workflow instance current processing step is invalid",null),
		createdate: commonLogic.appcommonhandle("Print",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
		createman: commonLogic.appcommonhandle("建立人",null),
	},
		views: {
			editview: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员编辑视图",null),
			},
			gridview: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员表格视图",null),
			},
			editviewbasicinfo: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员编辑视图",null),
			},
			usr3gridview: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员表格视图",null),
			},
			gridexpviewprojectmember: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员表格导航视图",null),
			},
			gridexpview: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员表格导航视图",null),
			},
			usr2gridview: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员表格视图人员实体表格视图(组织下所有人员)",null),
			},
			pickupview: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员数据选择视图",null),
			},
			treeexpview: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员树导航视图",null),
			},
			pickupgridview: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员选择表格视图",null),
			},
			mpickupview: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员数据多项选择视图",null),
			},
			dashboardview: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员数据看板视图",null),
			},
			usr3gridviewuserconcat: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员表格视图",null),
			},
			gridexpviewdept: {
				caption: commonLogic.appcommonhandle("人员",null),
				title: commonLogic.appcommonhandle("人员表格导航视图",null),
			},
		},
		main_form: {
			details: {
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("用户标识",null), 
				srfmajortext: commonLogic.appcommonhandle("姓名",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				orgname: commonLogic.appcommonhandle("单位",null), 
				mdeptname: commonLogic.appcommonhandle("部门",null), 
				personname: commonLogic.appcommonhandle("姓名",null), 
				nickname: commonLogic.appcommonhandle("别名",null), 
				sex: commonLogic.appcommonhandle("性别",null), 
				phone: commonLogic.appcommonhandle("电话",null), 
				orgid: commonLogic.appcommonhandle("单位",null), 
				userid: commonLogic.appcommonhandle("用户标识",null), 
			},
			uiactions: {
			},
		},
		personalinfomain_form: {
			details: {
				grouppanel2: commonLogic.appcommonhandle("分组面板",null), 
				grouppanel3: commonLogic.appcommonhandle("分组面板",null), 
				grouppanel1: commonLogic.appcommonhandle("分组面板",null), 
				group1: commonLogic.appcommonhandle("人员基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				srfupdatedate: commonLogic.appcommonhandle("更新时间",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("用户标识",null), 
				srfmajortext: commonLogic.appcommonhandle("姓名",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				loginname: commonLogic.appcommonhandle("登录名",null), 
				personname: commonLogic.appcommonhandle("姓名",null), 
				sex: commonLogic.appcommonhandle("性别",null), 
				mdeptname: commonLogic.appcommonhandle("主部门名称",null), 
				userid: commonLogic.appcommonhandle("用户标识",null), 
				phone: commonLogic.appcommonhandle("联系方式",null), 
				email: commonLogic.appcommonhandle("邮件",null), 
				addr: commonLogic.appcommonhandle("地址",null), 
				avatar: commonLogic.appcommonhandle("社交账号",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				userid: commonLogic.appcommonhandle("用户标识",null),
				sex: commonLogic.appcommonhandle("性别",null),
				mdeptname: commonLogic.appcommonhandle("主部门名称",null),
				personname: commonLogic.appcommonhandle("姓名",null),
				nickname: commonLogic.appcommonhandle("昵称别名",null),
				phone: commonLogic.appcommonhandle("联系方式",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		expmainprojectmember_grid: {
			columns: {
				userid: commonLogic.appcommonhandle("用户标识",null),
				personname: commonLogic.appcommonhandle("姓名",null),
				nickname: commonLogic.appcommonhandle("昵称别名",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		expmain_grid: {
			columns: {
				userid: commonLogic.appcommonhandle("用户标识",null),
				personname: commonLogic.appcommonhandle("姓名",null),
				nickname: commonLogic.appcommonhandle("昵称别名",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		expmaindept_grid: {
			columns: {
				userid: commonLogic.appcommonhandle("用户标识",null),
				personname: commonLogic.appcommonhandle("姓名",null),
				nickname: commonLogic.appcommonhandle("昵称别名",null),
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
		usr2gridviewtoolbar_toolbar: {
		},
		usr3gridviewuserconcattoolbar_toolbar: {
		},
		gridviewtoolbar_toolbar: {
			tbitem3: {
				caption: commonLogic.appcommonhandle("New",null),
				tip: commonLogic.appcommonhandle("New",null),
			},
			tbitem4: {
				caption: commonLogic.appcommonhandle("Edit",null),
				tip: commonLogic.appcommonhandle("Edit {0}",null),
			},
			tbitem6: {
				caption: commonLogic.appcommonhandle("Copy",null),
				tip: commonLogic.appcommonhandle("Copy {0}",null),
			},
			tbitem7: {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			tbitem8: {
				caption: commonLogic.appcommonhandle("Remove",null),
				tip: commonLogic.appcommonhandle("Remove {0}",null),
			},
			tbitem9: {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			tbitem13: {
				caption: commonLogic.appcommonhandle("Export",null),
				tip: commonLogic.appcommonhandle("Export {0} Data To Excel",null),
			},
			tbitem10: {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			tbitem16: {
				caption: commonLogic.appcommonhandle("其它",null),
				tip: commonLogic.appcommonhandle("其它",null),
			},
			tbitem21: {
				caption: commonLogic.appcommonhandle("Export Data Model",null),
				tip: commonLogic.appcommonhandle("导出数据模型",null),
			},
			tbitem23: {
				caption: commonLogic.appcommonhandle("数据导入",null),
				tip: commonLogic.appcommonhandle("数据导入",null),
			},
			tbitem17: {
				caption: commonLogic.appcommonhandle("-",null),
				tip: commonLogic.appcommonhandle("",null),
			},
			tbitem19: {
				caption: commonLogic.appcommonhandle("Filter",null),
				tip: commonLogic.appcommonhandle("Filter",null),
			},
			tbitem18: {
				caption: commonLogic.appcommonhandle("Help",null),
				tip: commonLogic.appcommonhandle("Help",null),
			},
		},
		usr3gridviewtoolbar_toolbar: {
		},
		emptreemenuzs_treeview: {
			nodata:commonLogic.appcommonhandle("",null),
			nodes: {
				project: commonLogic.appcommonhandle("项目团队",null),
				post: commonLogic.appcommonhandle("岗位",null),
				root: commonLogic.appcommonhandle("默认根节点",null),
				orgemp: commonLogic.appcommonhandle("组织人员",null),
				concatlist: commonLogic.appcommonhandle("联系人",null),
				team: commonLogic.appcommonhandle("团队",null),
			},
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;