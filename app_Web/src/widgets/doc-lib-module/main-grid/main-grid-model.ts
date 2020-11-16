/**
 * Main 部件模型
 *
 * @export
 * @class MainModel
 */
export default class MainModel {

	/**
	 * 是否是实体数据导出
	 *
	 * @returns {any[]}
	 * @memberof MainGridMode
	 */
	public isDEExport: boolean = false;

	/**
	 * 获取数据项集合
	 *
	 * @returns {any[]}
	 * @memberof MainGridMode
	 */
	public getDataItems(): any[] {
    if(this.isDEExport){
		  return [
      ]
    }else{
		  return [
        {
          name: 'name',
          prop: 'name',
          dataType: 'TEXT',
          isEditable:true
        },
        {
          name: 'short',
          prop: 'ibizshort',
          dataType: 'TEXT',
          isEditable:true
        },
        {
          name: 'order',
          prop: 'order',
          dataType: 'INT',
          isEditable:true
        },
        {
          name: 'root',
          prop: 'root',
          dataType: 'PICKUP',
        },
        {
          name: 'owner',
          prop: 'owner',
          dataType: 'TEXT',
        },
        {
          name: 'parent',
          prop: 'parent',
          dataType: 'PICKUP',
          isEditable:true
        },
        {
          name: 'type',
          prop: 'type',
          dataType: 'TEXT',
          isEditable:true
        },
        {
          name: 'srfmajortext',
          prop: 'name',
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
          name: 'doclibmodule',
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