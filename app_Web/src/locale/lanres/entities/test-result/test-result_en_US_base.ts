import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'createby': commonLogic.appcommonhandle("由谁创建",null),
		'org': commonLogic.appcommonhandle("归属组织",null),
		'lastrunner': commonLogic.appcommonhandle("最后执行人",null),
		'dept': commonLogic.appcommonhandle("归属部门",null),
		'stepresults': commonLogic.appcommonhandle("步骤结果",null),
		'caseresult': commonLogic.appcommonhandle("测试结果",null),
		'orgname': commonLogic.appcommonhandle("归属组织名",null),
		'updateby': commonLogic.appcommonhandle("由谁更新",null),
		'xml': commonLogic.appcommonhandle("结果文件",null),
		'task': commonLogic.appcommonhandle("属性",null),
		'duration': commonLogic.appcommonhandle("持续时间",null),
		'date': commonLogic.appcommonhandle("测试时间",null),
		'deptname': commonLogic.appcommonhandle("归属部门名",null),
		'id': commonLogic.appcommonhandle("编号",null),
		'version': commonLogic.appcommonhandle("用例版本",null),
		'story': commonLogic.appcommonhandle("相关需求",null),
		'title': commonLogic.appcommonhandle("用例名称",null),
		'modulename': commonLogic.appcommonhandle("所属模块",null),
		'module': commonLogic.appcommonhandle("所属模块",null),
		'precondition': commonLogic.appcommonhandle("前置条件",null),
		'product': commonLogic.appcommonhandle("所属产品",null),
		'job': commonLogic.appcommonhandle("构建任务",null),
		'ibizcase': commonLogic.appcommonhandle("用例",null),
		'run': commonLogic.appcommonhandle("测试执行",null),
		'compile': commonLogic.appcommonhandle("代码编译",null),
	},
		views: {
			'editview': {
				caption: commonLogic.appcommonhandle("测试结果",null),
				title: commonLogic.appcommonhandle("testresult编辑视图",null),
			},
			'gridview9': {
				caption: commonLogic.appcommonhandle("测试结果",null),
				title: commonLogic.appcommonhandle("测试结果表格视图",null),
			},
			'editview9': {
				caption: commonLogic.appcommonhandle("测试结果",null),
				title: commonLogic.appcommonhandle("测试结果编辑视图",null),
			},
			'testtaskmeditview9': {
				caption: commonLogic.appcommonhandle("测试结果",null),
				title: commonLogic.appcommonhandle("测试结果多表单编辑视图",null),
			},
			'meditview9': {
				caption: commonLogic.appcommonhandle("测试结果",null),
				title: commonLogic.appcommonhandle("测试结果多表单编辑视图",null),
			},
		},
		main_form: {
			details: {
				'rawitem1': commonLogic.appcommonhandle("",null), 
				'druipart1': commonLogic.appcommonhandle("",null), 
				'button1': commonLogic.appcommonhandle("转Bug",null), 
				'grouppanel2': commonLogic.appcommonhandle("分组面板",null), 
				'grouppanel1': commonLogic.appcommonhandle("用例步骤",null), 
				'formpage1': commonLogic.appcommonhandle("基本信息",null), 
				'srforikey': commonLogic.appcommonhandle("",null), 
				'srfkey': commonLogic.appcommonhandle("编号",null), 
				'srfmajortext': commonLogic.appcommonhandle("用例名称",null), 
				'srftempmode': commonLogic.appcommonhandle("",null), 
				'srfuf': commonLogic.appcommonhandle("",null), 
				'srfdeid': commonLogic.appcommonhandle("",null), 
				'srfsourcekey': commonLogic.appcommonhandle("",null), 
				'id': commonLogic.appcommonhandle("",null), 
				'date': commonLogic.appcommonhandle("",null), 
				'lastrunner': commonLogic.appcommonhandle("",null), 
				'caseresult': commonLogic.appcommonhandle("",null), 
				'formitem': commonLogic.appcommonhandle("",null), 
				'title': commonLogic.appcommonhandle("用例名称",null), 
				'module': commonLogic.appcommonhandle("所属模块",null), 
				'modulename': commonLogic.appcommonhandle("所属模块",null), 
				'job': commonLogic.appcommonhandle("构建任务",null), 
				'precondition': commonLogic.appcommonhandle("前置条件",null), 
				'story': commonLogic.appcommonhandle("相关需求",null), 
				'product': commonLogic.appcommonhandle("所属产品",null), 
				'case': commonLogic.appcommonhandle("用例",null), 
			},
			uiactions: {
			testresult_tobug: commonLogic.appcommonhandle("转Bug",null),
			},
		},
		main_grid: {
			columns: {
				'caseresult': commonLogic.appcommonhandle("测试结果",null),
				'date': commonLogic.appcommonhandle("测试时间",null),
				'lastrunner': commonLogic.appcommonhandle("最后执行人",null),
				'run': commonLogic.appcommonhandle("测试执行",null),
			},
			nodata:commonLogic.appcommonhandle("",null),
			uiactions: {
			},
		},
	};
	return data;
}

export default getLocaleResourceBase;