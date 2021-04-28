import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    type: "类型",
    createman: "建立人",
    ibzfavoritesid: "收藏标识",
    createdate: "建立时间",
    updateman: "更新人",
    objectid: "数据对象标识",
    account: "收藏用户",
    ibzfavoritesname: "收藏名称",
    updatedate: "更新时间",
  },
	views: {
		mobtabexpview: {
			caption: commonLogic.appcommonhandle("收藏",null),
		},
	},
};