import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		name: commonLogic.appcommonhandle("任务名",null),
		taskcnt: commonLogic.appcommonhandle("任务数",null),
		dept: commonLogic.appcommonhandle("部门",null),
		id: commonLogic.appcommonhandle("主键",null),
		workday: commonLogic.appcommonhandle("工作日天数",null),
		totaltaskcnt: commonLogic.appcommonhandle("总任务数",null),
		assignedto: commonLogic.appcommonhandle("指派给",null),
		left: commonLogic.appcommonhandle("剩余工时",null),
		assign: commonLogic.appcommonhandle("是否指派",null),
		begin: commonLogic.appcommonhandle("属性",null),
		totalleft: commonLogic.appcommonhandle("总工时",null),
		workload: commonLogic.appcommonhandle("工作负载",null),
		end: commonLogic.appcommonhandle("结束",null),
		projectname: commonLogic.appcommonhandle("项目",null),
		project: commonLogic.appcommonhandle("项目编号",null),
	},
		views: {
			editview: {
				caption: commonLogic.appcommonhandle("员工负载表",null),
				title: commonLogic.appcommonhandle("员工负载表编辑视图",null),
			},
			gridview: {
				caption: commonLogic.appcommonhandle("员工负载表",null),
				title: commonLogic.appcommonhandle("员工负载表表格视图",null),
			},
		},
		main_form: {
			details: {
				group1: commonLogic.appcommonhandle("员工负载表基本信息",null), 
				formpage1: commonLogic.appcommonhandle("基本信息",null), 
				group2: commonLogic.appcommonhandle("操作信息",null), 
				formpage2: commonLogic.appcommonhandle("其它",null), 
				srforikey: commonLogic.appcommonhandle("",null), 
				srfkey: commonLogic.appcommonhandle("主键",null), 
				srfmajortext: commonLogic.appcommonhandle("任务名",null), 
				srftempmode: commonLogic.appcommonhandle("",null), 
				srfuf: commonLogic.appcommonhandle("",null), 
				srfdeid: commonLogic.appcommonhandle("",null), 
				srfsourcekey: commonLogic.appcommonhandle("",null), 
				id: commonLogic.appcommonhandle("主键",null), 
			},
			uiactions: {
			},
		},
		main_grid: {
			columns: {
				assignedto: commonLogic.appcommonhandle("姓名",null),
				projectname: commonLogic.appcommonhandle("项目",null),
				taskcnt: commonLogic.appcommonhandle("任务数",null),
				left: commonLogic.appcommonhandle("剩余工时",null),
				totaltaskcnt: commonLogic.appcommonhandle("总任务数",null),
				totalleft: commonLogic.appcommonhandle("总工时",null),
				workload: commonLogic.appcommonhandle("工作负载",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
		workload_searchform: {
			details: {
				formpage1: commonLogic.appcommonhandle("表单分页",null), 
				dept: commonLogic.appcommonhandle("部门",null), 
				begin: commonLogic.appcommonhandle("起止时间",null), 
				end: commonLogic.appcommonhandle("至",null), 
				workday: commonLogic.appcommonhandle("工作天数",null), 
				everydaytime: commonLogic.appcommonhandle("每天工时",null), 
				assign: commonLogic.appcommonhandle("",null), 
			},
			uiactions: {
			},
		},
		editviewtoolbar_toolbar: {
			tbitem3: {
				caption: commonLogic.appcommonhandle("保存",null),
				tip: commonLogic.appcommonhandle("保存",null),
			},
			tbitem4: {
				caption: commonLogic.appcommonhandle("保存并新建",null),
				tip: commonLogic.appcommonhandle("保存并新建",null),
			},
			tbitem5: {
				caption: commonLogic.appcommonhandle("保存并关闭",null),
				tip: commonLogic.appcommonhandle("保存并关闭",null),
			},
			tbitem7: {
				caption: commonLogic.appcommonhandle("删除",null),
				tip: commonLogic.appcommonhandle("删除",null),
			},
		},
	};
	return data;
}
export default getLocaleResourceBase;