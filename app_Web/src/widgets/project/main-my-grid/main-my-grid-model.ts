/**
 * MainMy 部件模型
 *
 * @export
 * @class MainMyModel
 */
export default class MainMyModel {

	/**
	 * 是否是实体数据导出
	 *
	 * @returns {any[]}
	 * @memberof MainMyGridMode
	 */
	public isDEExport: boolean = false;

	/**
	 * 获取数据项集合
	 *
	 * @returns {any[]}
	 * @memberof MainMyGridMode
	 */
	public getDataItems(): any[] {
    if(this.isDEExport){
		  return [
      ]
    }else{
		  return [
        {
          name: 'status',
          prop: 'status',
          dataType: 'SSCODELIST',
        },
        {
          name: 'hours',
          prop: 'hours',
          dataType: 'DECIMAL',
        },
        {
          name: 'parent',
          prop: 'parent',
          dataType: 'PICKUP',
        },
        {
          name: 'code',
          prop: 'code',
          dataType: 'TEXT',
        },
        {
          name: 'join',
          prop: 'join',
          dataType: 'DATE',
        },
        {
          name: 'srfmstag',
        },
        {
          name: 'srfmajortext',
          prop: 'name',
          dataType: 'TEXT',
        },
        {
          name: 'srfkey',
          prop: 'id',
          dataType: 'ACID',
        },
        {
          name: 'srfdataaccaction',
          prop: 'id',
          dataType: 'ACID',
        },
        {
          name: 'begin',
          prop: 'begin',
          dataType: 'DATE',
        },
        {
          name: 'id',
          prop: 'id',
          dataType: 'ACID',
        },
        {
          name: 'name',
          prop: 'name',
          dataType: 'TEXT',
        },
        {
          name: 'role',
          prop: 'role',
          dataType: 'TEXT',
        },
        {
          name: 'end',
          prop: 'end',
          dataType: 'DATE',
        },
        {
          name: 'project',
          prop: 'id',
        },
        {
          name:'size',
          prop:'size'
        },
        {
          name:'query',
          prop:'query'
        },
        {
          name:'page',
          prop:'page'
        },
        {
          name:'sort',
          prop:'sort'
        },
        {
          name:'srfparentdata',
          prop:'srfparentdata'
        },
        // 前端新增修改标识，新增为"0",修改为"1"或未设值
        {
          name: 'srffrontuf',
          prop: 'srffrontuf',
          dataType: 'TEXT',
        },
      ]
    }
  }

}