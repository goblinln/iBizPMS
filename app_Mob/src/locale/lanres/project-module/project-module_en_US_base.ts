import commonLogic from '@/locale/logic/common/common-logic';
export default {
  fields: {
    iBizShort:  commonLogic.appcommonhandle("简称",null),
    isLeaf:  commonLogic.appcommonhandle("叶子模块",null),
    type:  commonLogic.appcommonhandle("类型（task）",null),
    orderpk:  commonLogic.appcommonhandle("数据选择排序",null),
    name:  commonLogic.appcommonhandle("名称",null),
    order:  commonLogic.appcommonhandle("排序值",null),
    collector:  commonLogic.appcommonhandle("collector",null),
    grade:  commonLogic.appcommonhandle("grade",null),
    branch:  commonLogic.appcommonhandle("branch",null),
    path:  commonLogic.appcommonhandle("path",null),
    id:  commonLogic.appcommonhandle("id",null),
    owner:  commonLogic.appcommonhandle("owner",null),
    deleted:  commonLogic.appcommonhandle("逻辑删除标志",null),
    rootName:  commonLogic.appcommonhandle("所属项目",null),
    parentName:  commonLogic.appcommonhandle("上级模块",null),
    root:  commonLogic.appcommonhandle("项目",null),
    parent:  commonLogic.appcommonhandle("id",null),
    mdeptId:  commonLogic.appcommonhandle("部门标识",null),
    mdeptName:  commonLogic.appcommonhandle("归属部门名",null),
    orgId:  commonLogic.appcommonhandle("组织机构标识",null),
    createBy:  commonLogic.appcommonhandle("由谁创建",null),
    orgName:  commonLogic.appcommonhandle("归属组织名",null),
    updateBy:  commonLogic.appcommonhandle("由谁更新",null),
  },
	views: {
		mobpickupmdview: {
			caption: commonLogic.appcommonhandle("任务模块",null),
		},
		mobpickupview: {
			caption: commonLogic.appcommonhandle("任务模块",null),
		},
	},
};