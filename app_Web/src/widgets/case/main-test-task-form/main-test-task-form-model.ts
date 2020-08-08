/**
 * MainTestTask 部件模型
 *
 * @export
 * @class MainTestTaskModel
 */
export default class MainTestTaskModel {

  /**
  * 获取数据项集合
  *
  * @returns {any[]}
  * @memberof MainTestTaskModel
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
        prop: 'title',
        dataType: 'TEXT',
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
        name: 'version',
        prop: 'version',
        dataType: 'INT',
      },
      {
        name: 'task',
        prop: 'task',
        dataType: 'TEXT',
      },
      {
        name: 'precondition',
        prop: 'precondition',
        dataType: 'LONGTEXT',
      },
      {
        name: 'resultcnt',
        prop: 'resultcnt',
        dataType: 'INT',
      },
      {
        name: 'resultfalicnt',
        prop: 'resultfalicnt',
        dataType: 'INT',
      },
      {
        name: 'id',
        prop: 'id',
        dataType: 'ACID',
      },
      {
        name: 'case',
        prop: 'id',
        dataType: 'FONTKEY',
      },
    ]
  }

}