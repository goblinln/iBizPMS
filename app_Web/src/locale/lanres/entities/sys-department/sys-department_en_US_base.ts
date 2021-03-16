import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		deptid: commonLogic.appcommonhandle("decimal",null),
		deptcode: commonLogic.appcommonhandle("Cancel",null),
		deptname: commonLogic.appcommonhandle("{0}/P",null),
		orgid: commonLogic.appcommonhandle("单位",null),
		parentdeptid: commonLogic.appcommonhandle("上级部门",null),
		shortname: commonLogic.appcommonhandle("Others",null),
		deptlevel: commonLogic.appcommonhandle("部门级别",null),
		domains: commonLogic.appcommonhandle("区属",null),
		showorder: commonLogic.appcommonhandle("Audit",null),
		bcode: commonLogic.appcommonhandle("Condition",null),
		leaderid: commonLogic.appcommonhandle("分管领导标识",null),
		leadername: commonLogic.appcommonhandle("分管领导",null),
		orgname: commonLogic.appcommonhandle("单位",null),
		parentdeptname: commonLogic.appcommonhandle("上级部门",null),
		createdate: commonLogic.appcommonhandle("建立时间",null),
		enable: commonLogic.appcommonhandle("逻辑有效标志",null),
		updateman: commonLogic.appcommonhandle("更新人",null),
		updatedate: commonLogic.appcommonhandle("更新时间",null),
		createman: commonLogic.appcommonhandle("建立人",null),
	},
		views: {
			gridexpview: {
				caption: commonLogic.appcommonhandle("部门",null),
				title: commonLogic.appcommonhandle("部门表格导航视图",null),
			},
		},
		mainexp_grid: {
			columns: {
				deptid: commonLogic.appcommonhandle("decimal",null),
				deptname: commonLogic.appcommonhandle("{0}/P",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;