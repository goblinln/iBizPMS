/**
 * Main 部件模型
 *
 * @export
 * @class MainModel
 */
export default class MainModel {

  /**
  * 获取数据项集合
  *
  * @returns {any[]}
  * @memberof MainModel
  */
  public getDataItems(): any[] {
    return [
      {
        name: 'srfwfmemo',
        prop: 'srfwfmemo',
        dataType: 'TEXT',
      },
      // 前端新增修改标识，新增为"0",修改为"1"或未设值
      {
        name: 'srffrontuf',
        prop: 'srffrontuf',
        dataType: 'TEXT',
      },
      {
        name: 'srforikey',
      },
      {
        name: 'srfkey',
        prop: 'id',
        dataType: 'ACID',
      },
      {
        name: 'srfmajortext',
        prop: 'id',
        dataType: 'ACID',
      },
      {
        name: 'srftempmode',
      },
      {
        name: 'srfuf',
      },
      {
        name: 'srfdeid',
      },
      {
        name: 'srfsourcekey',
      },
      {
        name: 'date',
        prop: 'date',
        dataType: 'DATE',
      },
      {
        name: 'consumed',
        prop: 'consumed',
        dataType: 'FLOAT',
      },
      {
        name: 'left',
        prop: 'left',
        dataType: 'FLOAT',
      },
      {
        name: 'work',
        prop: 'work',
        dataType: 'LONGTEXT',
      },
      {
        name: 'id',
        prop: 'id',
        dataType: 'ACID',
      },
      {
        name: 'task',
        prop: 'id',
        dataType: 'FONTKEY',
      },
      {
        name: 'taskestimate',
        prop: 'id',
        dataType: 'FONTKEY',
      },
    ]
  }

}