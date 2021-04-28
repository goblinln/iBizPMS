import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    thismonth:  commonLogic.appcommonhandle("本月",null),
    dept:  commonLogic.appcommonhandle("归属部门",null),
    yesterday:  commonLogic.appcommonhandle("昨天",null),
    extra:  commonLogic.appcommonhandle("附加值",null),
    files:  commonLogic.appcommonhandle("文件",null),
    lastmonth:  commonLogic.appcommonhandle("上月",null),
    isactorss:  commonLogic.appcommonhandle("当前用户",null),
    deptName:  commonLogic.appcommonhandle("归属部门名",null),
    thisweek:  commonLogic.appcommonhandle("本周",null),
    today:  commonLogic.appcommonhandle("今天",null),
    date1:  commonLogic.appcommonhandle("显示日期",null),
    objectType:  commonLogic.appcommonhandle("对象类型",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    id:  commonLogic.appcommonhandle("id",null),
    noticeusers:  commonLogic.appcommonhandle("消息通知用户",null),
    comment:  commonLogic.appcommonhandle("备注",null),
    read:  commonLogic.appcommonhandle("已读",null),
    action:  commonLogic.appcommonhandle("动作",null),
    date:  commonLogic.appcommonhandle("日期",null),
    product:  commonLogic.appcommonhandle("产品",null),
    lastcomment:  commonLogic.appcommonhandle("备注",null),
    srfkey:  commonLogic.appcommonhandle("前端键值",null),
    actionmanner:  commonLogic.appcommonhandle("操作方式",null),
    org:  commonLogic.appcommonhandle("归属组织",null),
    lastweek:  commonLogic.appcommonhandle("上周",null),
    objectID:  commonLogic.appcommonhandle("对象ID",null),
    actor:  commonLogic.appcommonhandle("操作者",null),
    project:  commonLogic.appcommonhandle("项目",null),
  },
	views: {
		mobmdview9: {
			caption: commonLogic.appcommonhandle("通用",null),
		},
		allmobmdview9: {
			caption: commonLogic.appcommonhandle("通用",null),
		},
		moremobmdview: {
			caption: commonLogic.appcommonhandle("更多",null),
		},
		mobmapview: {
			caption: commonLogic.appcommonhandle("系统日志",null),
		},
	},
};