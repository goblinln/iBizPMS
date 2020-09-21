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
          name: 'endstr',
          prop: 'endstr',
          dataType: 'TEXT',
        },
        {
          name: 'beginstr',
          prop: 'beginstr',
          dataType: 'TEXT',
        },
        {
          name: 'parent',
          prop: 'parent',
          dataType: 'PICKUP',
        },
        {
          name: 'srfmstag',
        },
        {
          name: 'storycnt',
          prop: 'storycnt',
          dataType: 'INT',
        },
        {
          name: 'srfmajortext',
          prop: 'title',
          dataType: 'TEXT',
        },
        {
          name: 'srfkey',
          prop: 'id',
          dataType: 'ACID',
          isEditable:true
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
          name: 'product',
          prop: 'product',
          dataType: 'PICKUP',
        },
        {
          name: 'id',
          prop: 'id',
          dataType: 'ACID',
        },
        {
          name: 'title',
          prop: 'title',
          dataType: 'TEXT',
        },
        {
          name: 'statuss',
          prop: 'statuss',
          dataType: 'TEXT',
        },
        {
          name: 'bugcnt',
          prop: 'bugcnt',
          dataType: 'INT',
        },
        {
          name: 'branch',
          prop: 'branch',
          dataType: 'PICKUP',
        },
        {
          name: 'end',
          prop: 'end',
          dataType: 'DATE',
        },
        {
          name: 'productplan',
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
          name:'items',
          prop:'items'
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