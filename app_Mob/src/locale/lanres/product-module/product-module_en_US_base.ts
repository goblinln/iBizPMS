import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    path:  commonLogic.appcommonhandle("path",null),
    deleted:  commonLogic.appcommonhandle("逻辑删除标志",null),
    name:  commonLogic.appcommonhandle("名称",null),
    branch:  commonLogic.appcommonhandle("branch",null),
    iBizShort:  commonLogic.appcommonhandle("简称",null),
    order:  commonLogic.appcommonhandle("排序值",null),
    grade:  commonLogic.appcommonhandle("grade",null),
    type:  commonLogic.appcommonhandle("类型（story）",null),
    owner:  commonLogic.appcommonhandle("owner",null),
    isLeaf:  commonLogic.appcommonhandle("叶子模块",null),
    id:  commonLogic.appcommonhandle("id",null),
    collector:  commonLogic.appcommonhandle("collector",null),
    root:  commonLogic.appcommonhandle("产品",null),
    parent:  commonLogic.appcommonhandle("id",null),
    rootName:  commonLogic.appcommonhandle("所属产品",null),
    parentName:  commonLogic.appcommonhandle("上级模块",null),
    orderpk:  commonLogic.appcommonhandle("数据选择排序",null),
  },
	views: {
		mobpickupview: {
			caption: commonLogic.appcommonhandle("需求模块",null),
		},
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("需求模块",null),
		},
	},
};