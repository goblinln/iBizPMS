import commonLogic from '@/locale/logic/common/common-logic';

function getLocaleResourceBase(){
	const data:any = {
	fields: {
		'projectname': commonLogic.appcommonhandle("项目名称",null),
		'sevenconsumed': commonLogic.appcommonhandle("七号工时",null),
		'sevenevaluationtime': commonLogic.appcommonhandle("七号评估工时",null),
		'fourteenevaluationtime': commonLogic.appcommonhandle("十四号评估工时",null),
		'tenconsumed': commonLogic.appcommonhandle("十号工时",null),
		'nineconsumed': commonLogic.appcommonhandle("九号工时",null),
		'twentyconsumed': commonLogic.appcommonhandle("二十号工时",null),
		'sixconsumed': commonLogic.appcommonhandle("六号工时",null),
		'thirtyevaluationtime': commonLogic.appcommonhandle("三十号评估工时",null),
		'thirteenconsumed': commonLogic.appcommonhandle("十三号工时",null),
		'eightevaluationcost': commonLogic.appcommonhandle("八号评估成本",null),
		'nineteenevaluationtime': commonLogic.appcommonhandle("十九号评估工时",null),
		'twentynineevaluationtime': commonLogic.appcommonhandle("二十九号评估工时",null),
		'seventeenevaluationtime': commonLogic.appcommonhandle("十七号评估工时",null),
		'twelveevaluationcost': commonLogic.appcommonhandle("十二号评估成本",null),
		'twentynineevaluationcost': commonLogic.appcommonhandle("二十九号评估成本",null),
		'threeevaluationcost': commonLogic.appcommonhandle("三号评估成本",null),
		'twentysixevaluationtime': commonLogic.appcommonhandle("二十六号评估工时",null),
		'fourteenevaluationcost': commonLogic.appcommonhandle("十四号评估成本",null),
		'eighteenevaluationcost': commonLogic.appcommonhandle("十八号评估成本",null),
		'oneevaluationtime': commonLogic.appcommonhandle("一号评估工时",null),
		'month': commonLogic.appcommonhandle("月",null),
		'date': commonLogic.appcommonhandle("日期",null),
		'twentynineconsumed': commonLogic.appcommonhandle("二十九号工时",null),
		'evaluationtime': commonLogic.appcommonhandle("评估工时",null),
		'evaluationcost': commonLogic.appcommonhandle("评估成本",null),
		'elevenevaluationtime': commonLogic.appcommonhandle("十一号评估工时",null),
		'tenevaluationtime': commonLogic.appcommonhandle("十号评估工时",null),
		'thirtyoneevaluationcost': commonLogic.appcommonhandle("三十一号评估成本",null),
		'fourevaluationtime': commonLogic.appcommonhandle("四号评估工时",null),
		'twentytwoconsumed': commonLogic.appcommonhandle("二十二号工时",null),
		'twentyoneconsumed': commonLogic.appcommonhandle("二十一号工时",null),
		'thirteenevaluationtime': commonLogic.appcommonhandle("十三号评估工时",null),
		'seventeenconsumed': commonLogic.appcommonhandle("十七号工时",null),
		'nineevaluationtime': commonLogic.appcommonhandle("九号评估工时",null),
		'twoconsumed': commonLogic.appcommonhandle("二号工时",null),
		'twentysevenconsumed': commonLogic.appcommonhandle("二十七号工时",null),
		'inputcost': commonLogic.appcommonhandle("投入成本",null),
		'fourteenconsumed': commonLogic.appcommonhandle("十四号工时",null),
		'twentyeightconsumed': commonLogic.appcommonhandle("二十八号工时",null),
		'consumed': commonLogic.appcommonhandle("工时",null),
		'thirteenevaluationcost': commonLogic.appcommonhandle("十三号评估成本",null),
		'elevenconsumed': commonLogic.appcommonhandle("十一号工时",null),
		'twentyoneevaluationtime': commonLogic.appcommonhandle("二十一号评估工时",null),
		'twelveconsumed': commonLogic.appcommonhandle("十二号工时",null),
		'thirtyoneconsumed': commonLogic.appcommonhandle("三十一号工时",null),
		'fiveconsumed': commonLogic.appcommonhandle("五号工时",null),
		'twentyfourconsumed': commonLogic.appcommonhandle("二十四号工时",null),
		'twentyeightevaluationcost': commonLogic.appcommonhandle("二十八号评估成本",null),
		'twentythreeevaluationtime': commonLogic.appcommonhandle("二十三号评估工时",null),
		'nineteenevaluationcost': commonLogic.appcommonhandle("十九号评估成本",null),
		'thirtyconsumed': commonLogic.appcommonhandle("三十号工时",null),
		'twentyoneevaluationcost': commonLogic.appcommonhandle("二十一号评估成本",null),
		'twentyevaluationtime': commonLogic.appcommonhandle("二十号评估工时",null),
		'sixevaluationcost': commonLogic.appcommonhandle("六号评估成本",null),
		'nineteenconsumed': commonLogic.appcommonhandle("十九号工时",null),
		'eightconsumed': commonLogic.appcommonhandle("八号工时",null),
		'seventeenevaluationcost': commonLogic.appcommonhandle("十七号评估成本",null),
		'twelveevaluationtime': commonLogic.appcommonhandle("十二号评估工时",null),
		'twentytwoevaluationcost': commonLogic.appcommonhandle("二十二号评估成本",null),
		'twentyeightevaluationtime': commonLogic.appcommonhandle("二十八号评估工时",null),
		'fifteenconsumed': commonLogic.appcommonhandle("十五号工时",null),
		'threeevaluationtime': commonLogic.appcommonhandle("三号评估工时",null),
		'eighteenevaluationtime': commonLogic.appcommonhandle("十八号评估工时",null),
		'thirtyevaluationcost': commonLogic.appcommonhandle("三十号评估成本",null),
		'sixevaluationtime': commonLogic.appcommonhandle("六号评估工时",null),
		'id': commonLogic.appcommonhandle("主键",null),
		'twentythreeevaluationcost': commonLogic.appcommonhandle("二十三号评估成本",null),
		'twentysixevaluationcost': commonLogic.appcommonhandle("二十六号评估成本",null),
		'twentysevenevaluationtime': commonLogic.appcommonhandle("二十七号评估工时",null),
		'oneconsumed': commonLogic.appcommonhandle("一号工时",null),
		'twentytwoevaluationtime': commonLogic.appcommonhandle("二十二号评估工时",null),
		'eightevaluationtime': commonLogic.appcommonhandle("八号评估工时",null),
		'twentyfiveevaluationcost': commonLogic.appcommonhandle("二十五号评估成本",null),
		'sevenevaluationcost': commonLogic.appcommonhandle("七号评估成本",null),
		'sixteenevaluationtime': commonLogic.appcommonhandle("十六号评估工时",null),
		'eighteenconsumed': commonLogic.appcommonhandle("十八号工时",null),
		'fiveevaluationcost': commonLogic.appcommonhandle("五号评估成本",null),
		'twoevaluationcost': commonLogic.appcommonhandle("二号评估成本",null),
		'elevenevaluationcost': commonLogic.appcommonhandle("十一号评估成本",null),
		'twoevaluationtime': commonLogic.appcommonhandle("二号评估工时",null),
		'fifteenevaluationcost': commonLogic.appcommonhandle("十五号评估成本",null),
		'twentyevaluationcost': commonLogic.appcommonhandle("二十号评估成本",null),
		'twentythreeconsumed': commonLogic.appcommonhandle("二十三号工时",null),
		'thirtyoneevaluationtime': commonLogic.appcommonhandle("三十一号评估工时",null),
		'nineevaluationcost': commonLogic.appcommonhandle("九号评估成本",null),
		'twentysevenevaluationcost': commonLogic.appcommonhandle("二十七号评估成本",null),
		'twentyfiveevaluationtime': commonLogic.appcommonhandle("二十五号评估工时",null),
		'twentyfiveconsumed': commonLogic.appcommonhandle("二十五号工时",null),
		'year': commonLogic.appcommonhandle("年",null),
		'twentyfourevaluationtime': commonLogic.appcommonhandle("二十四号评估工时",null),
		'twentyfourevaluationcost': commonLogic.appcommonhandle("二十四号评估成本",null),
		'project': commonLogic.appcommonhandle("项目标识",null),
		'fifteenevaluationtime': commonLogic.appcommonhandle("十五号评估工时",null),
		'tenevaluationcost': commonLogic.appcommonhandle("十号评估成本",null),
		'threeconsumed': commonLogic.appcommonhandle("三号工时",null),
		'account': commonLogic.appcommonhandle("用户",null),
		'twentysixconsumed': commonLogic.appcommonhandle("二十六号工时",null),
		'sixteenevaluationcost': commonLogic.appcommonhandle("十六号评估成本",null),
		'oneevaluationcost': commonLogic.appcommonhandle("一号评估成本",null),
		'fourevaluationcost': commonLogic.appcommonhandle("四号评估成本",null),
		'fiveevaluationtime': commonLogic.appcommonhandle("五号评估工时",null),
		'fourconsumed': commonLogic.appcommonhandle("四号工时",null),
		'sixteenconsumed': commonLogic.appcommonhandle("十六号工时",null),
	},
	};
	return data;
}
export default getLocaleResourceBase;