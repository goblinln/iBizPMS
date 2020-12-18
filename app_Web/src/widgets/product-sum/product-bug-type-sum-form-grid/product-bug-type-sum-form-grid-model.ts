/**
 * ProductBugTypeSumForm 部件模型
 *
 * @export
 * @class ProductBugTypeSumFormModel
 */
export default class ProductBugTypeSumFormModel {

	/**
	 * 是否是实体数据导出
	 *
	 * @returns {any[]}
	 * @memberof ProductBugTypeSumFormGridMode
	 */
	public isDEExport: boolean = false;

	/**
	 * 获取数据项集合
	 *
	 * @returns {any[]}
	 * @memberof ProductBugTypeSumFormGridMode
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
        },
        {
          name: 'codeerror',
          prop: 'codeerror',
          dataType: 'INT',
        },
        {
          name: 'config',
          prop: 'config',
          dataType: 'INT',
        },
        {
          name: 'install',
          prop: 'install',
          dataType: 'INT',
        },
        {
          name: 'security',
          prop: 'security',
          dataType: 'INT',
        },
        {
          name: 'performance',
          prop: 'performance',
          dataType: 'INT',
        },
        {
          name: 'standard',
          prop: 'standard',
          dataType: 'INT',
        },
        {
          name: 'automation',
          prop: 'automation',
          dataType: 'INT',
        },
        {
          name: 'designdefect',
          prop: 'designdefect',
          dataType: 'INT',
        },
        {
          name: 'others',
          prop: 'others',
          dataType: 'INT',
        },
        {
          name: 'bugsum',
          prop: 'bugsum',
          dataType: 'INT',
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
          name: 'id',
          prop: 'id',
          dataType: 'ACID',
        },
        {
          name: 'productsum',
          prop: 'id',
        },
      {
        name: 'closed',
        prop: 'closed',
      },
      {
        name: 'expired',
        prop: 'expired',
      },
      {
        name: 'n_id_eq',
        prop: 'n_id_eq',
        dataType: 'ACID',
      },
      {
        name: 'n_plan_eq',
        prop: 'n_plan_eq',
        dataType: 'BIGINT',
      },
      {
        name: 'productsum',
        prop: 'id',
        dataType: 'FONTKEY',
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