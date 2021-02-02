/**
 * ProductStoryKanBan 部件模型
 *
 * @export
 * @class ProductStoryKanBanModel
 */
export default class ProductStoryKanBanModel {

	/**
	 * 获取数据项集合
	 *
	 * @returns {any[]}
	 * @memberof ProductStoryKanBanKanbanMode
	 */
	public getDataItems(): any[] {
		return [
			{
				name: 'module',
				prop: 'module',
				dataType: 'PICKUP',
			},
			{
				name: 'frombug',
				prop: 'frombug',
				dataType: 'PICKUP',
			},
			{
				name: 'status',
				prop: 'status',
				dataType: 'SSCODELIST',
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
				name: 'stage',
				prop: 'stage',
				dataType: 'SSCODELIST',
			},
			{
				name: 'estimate',
			},
			{
				name: 'tobug',
				prop: 'tobug',
				dataType: 'PICKUP',
			},
			{
				name: 'srfkey',
				prop: 'id',
				dataType: 'ACID',
			},
			{
				name: 'srfmajortext',
				prop: 'title',
				dataType: 'TEXT',
			},
			{
				name: 'product',
				prop: 'product',
				dataType: 'PICKUP',
			},
			{
				name: 'id',
			},
			{
				name: 'title',
				prop: 'title',
				dataType: 'TEXT',
			},
			{
				name: 'pri',
			},
			{
				name: 'branch',
				prop: 'branch',
				dataType: 'PICKUP',
			},
			{
				name: 'duplicatestory',
				prop: 'duplicatestory',
				dataType: 'PICKUP',
			},
			{
				name: 'isfavorites',
				prop: 'isfavorites',
				dataType: 'TEXT',
			},
			{
				name: 'ischild',
				prop: 'ischild',
				dataType: 'TEXT',
			},

			{
				name: 'story',
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
        name:'sort',
        prop:'sort'
      },
      {
        name:'page',
        prop:'page'
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