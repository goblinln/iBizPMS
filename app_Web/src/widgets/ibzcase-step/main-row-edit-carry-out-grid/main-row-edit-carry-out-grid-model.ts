/**
 * Main_RowEdit_CarryOut 部件模型
 *
 * @export
 * @class Main_RowEdit_CarryOutModel
 */
export default class Main_RowEdit_CarryOutModel {

	/**
	 * 是否是实体数据导出
	 *
	 * @returns {any[]}
	 * @memberof Main_RowEdit_CarryOutGridMode
	 */
	public isDEExport: boolean = false;

	/**
	 * 获取数据项集合
	 *
	 * @returns {any[]}
	 * @memberof Main_RowEdit_CarryOutGridMode
	 */
	public getDataItems(): any[] {
    if(this.isDEExport){
		  return [
      ]
    }else{
		  return [
        {
          name: 'expect',
          prop: 'expect',
          dataType: 'LONGTEXT',
        },
        {
          name: 'desc',
          prop: 'desc',
          dataType: 'LONGTEXT',
        },
        {
          name: 'parent',
          prop: 'parent',
          dataType: 'PICKUP',
        },
        {
          name: 'case',
          prop: 'ibizcase',
          dataType: 'PICKUP',
          isEditable:true
        },
        {
          name: 'steps',
          prop: 'steps',
          dataType: 'SSCODELIST',
          isEditable:true
        },
        {
          name: 'type',
          prop: 'type',
          dataType: 'SSCODELIST',
          isEditable:true
        },
        {
          name: 'srfmajortext',
          prop: 'expect',
          dataType: 'LONGTEXT',
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
          name: 'reals',
          prop: 'reals',
          dataType: 'TEXT',
          isEditable:true
        },
        {
          name: 'version',
          prop: 'version',
          dataType: 'PICKUPDATA',
        },
        {
          name: 'case',
          prop: 'id',
          dataType: 'FONTKEY',
        },
        {
          name: 'ibzcasestep',
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