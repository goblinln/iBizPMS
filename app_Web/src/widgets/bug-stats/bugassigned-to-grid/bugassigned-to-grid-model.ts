/**
 * BugassignedTo 部件模型
 *
 * @export
 * @class BugassignedToModel
 */
export default class BugassignedToModel {

	/**
	 * 是否是实体数据导出
	 *
	 * @returns {any[]}
	 * @memberof BugassignedToGridMode
	 */
	public isDEExport: boolean = false;

	/**
	 * 获取数据项集合
	 *
	 * @returns {any[]}
	 * @memberof BugassignedToGridMode
	 */
	public getDataItems(): any[] {
    if(this.isDEExport){
		  return [
      ]
    }else{
		  return [
        {
          name: 'assignedto',
          prop: 'assignedto',
          dataType: 'SSCODELIST',
        },
        {
          name: 'productname',
          prop: 'productname',
          dataType: 'PICKUPDATA',
        },
        {
          name: 'bugcnt',
          prop: 'bugcnt',
          dataType: 'INT',
        },
        {
          name: 'bugtotal',
          prop: 'bugtotal',
          dataType: 'INT',
        },
        {
          name: 'product',
          prop: 'product',
          dataType: 'PICKUP',
        },
        {
          name: 'srfmajortext',
          prop: 'title',
          dataType: 'TEXT',
        },
        {
          name: 'srfdataaccaction',
          prop: 'id',
          dataType: 'ACID',
        },
        {
          name: 'srfkey',
          prop: 'id',
          dataType: 'ACID',
          isEditable:true
        },
        {
          name: 'bugstats',
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
          name:'filter',
          prop:'filter'
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