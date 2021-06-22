import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    path:  commonLogic.appcommonhandle("path",null),
    orderpk:  commonLogic.appcommonhandle("数据选择排序",null),
    deleted:  commonLogic.appcommonhandle("逻辑删除标志",null),
    name:  commonLogic.appcommonhandle("名称",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    branch:  commonLogic.appcommonhandle("branch",null),
    iBizShort:  commonLogic.appcommonhandle("简称",null),
    order:  commonLogic.appcommonhandle("排序值",null),
    grade:  commonLogic.appcommonhandle("grade",null),
    type:  commonLogic.appcommonhandle("类型（story）",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    owner:  commonLogic.appcommonhandle("owner",null),
    mdeptName:  commonLogic.appcommonhandle("归属部门名",null),
    isLeaf:  commonLogic.appcommonhandle("叶子模块",null),
    id:  commonLogic.appcommonhandle("id",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
    collector:  commonLogic.appcommonhandle("collector",null),
    mdeptId:  commonLogic.appcommonhandle("部门标识",null),
    orgId:  commonLogic.appcommonhandle("组织机构标识",null),
    rootName:  commonLogic.appcommonhandle("所属产品",null),
    parentName:  commonLogic.appcommonhandle("上级模块",null),
    root:  commonLogic.appcommonhandle("产品",null),
    parent:  commonLogic.appcommonhandle("id",null),
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