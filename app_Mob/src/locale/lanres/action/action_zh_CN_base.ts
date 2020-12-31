import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    extra: "附加值",
    objecttype: "对象类型",
    id: "id",
    comment: "备注",
    read: "已读",
    action: "动作",
    date: "日期",
    product: "产品",
    objectid: "对象ID",
    actor: "操作者",
    project: "项目",
    lastcomment: "备注",
    actionmanner: "操作方式",
    isactorss: "当前用户",
    date1: "显示日期",
    today: "今天",
    yesterday: "昨天",
    thisweek: "本周",
    lastweek: "上周",
    thismonth: "本月",
    lastmonth: "上月",
    srfkey: "前端键值",
    noticeusers: "消息通知用户",
    files: "文件",
  },
	views: {
		mobmapview: {
			caption: commonLogic.appcommonhandle("系统日志",null),
		},
		moremobmdview: {
			caption: commonLogic.appcommonhandle("更多",null),
		},
		mobmdview9: {
			caption: commonLogic.appcommonhandle("通用",null),
		},
		allmobmdview9: {
			caption: commonLogic.appcommonhandle("通用",null),
		},
	},
};