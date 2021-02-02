/**
 * MyWork 部件模型
 *
 * @export
 * @class MyWorkModel
 */
export default class MyWorkModel {

	/**
	 * 获取数据项集合
	 *
	 * @returns {any[]}
	 * @memberof MyWorkListMode
	 */
	public getDataItems(): any[] {
		return [
			{
				name: 'realname',
			},
			{
				name: 'account',
			},
			{
				name: 'myebugs',
			},
			{
				name: 'products',
			},
			{
				name: 'mystorys',
			},
			{
				name: 'mybugs',
			},
			{
				name: 'projects',
			},
			{
				name: 'mytasks',
			},
			{
				name: 'myetasks',
			},
			{
				name: 'eprojects',
			},
			{
				name: 'srfkey',
				prop: 'id',
				dataType: 'ACID',
			},
			{
				name: 'srfmajortext',
				prop: 'realname',
				dataType: 'TEXT',
			},
			{
				name: 'ibzmyterritory',
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
      // 前端新增修改标识，新增为"0",修改为"1"或未设值
      {
        name: 'srffrontuf',
        prop: 'srffrontuf',
        dataType: 'TEXT',
      },
		]
	}

}