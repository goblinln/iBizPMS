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
        name: 'srfupdatedate',
        prop: 'lastediteddate',
        dataType: 'DATETIME',
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
        name: 'productname',
        prop: 'productname',
        dataType: 'PICKUPTEXT',
      },
      {
        name: 'product',
        prop: 'product',
        dataType: 'PICKUP',
      },
      {
        name: 'module',
        prop: 'module',
        dataType: 'PICKUP',
      },
      {
        name: 'project',
        prop: 'project',
        dataType: 'PICKUP',
      },
      {
        name: 'projectname',
        prop: 'projectname',
        dataType: 'PICKUPTEXT',
      },
      {
        name: 'openedbuild',
        prop: 'openedbuild',
        dataType: 'TEXT',
      },
      {
        name: 'assignedto',
        prop: 'assignedto',
        dataType: 'TEXT',
      },
      {
        name: 'deadline',
        prop: 'deadline',
        dataType: 'DATE',
      },
      {
        name: 'type',
        prop: 'type',
        dataType: 'SSCODELIST',
      },
      {
        name: 'os',
        prop: 'os',
        dataType: 'SSCODELIST',
      },
      {
        name: 'browser',
        prop: 'browser',
        dataType: 'SSCODELIST',
      },
      {
        name: 'title',
        prop: 'title',
        dataType: 'TEXT',
      },
      {
        name: 'severity',
        prop: 'severity',
        dataType: 'NSCODELIST',
      },
      {
        name: 'pri',
        prop: 'pri',
        dataType: 'NSCODELIST',
      },
      {
        name: 'steps',
        prop: 'steps',
        dataType: 'LONGTEXT',
      },
      {
        name: 'story',
        prop: 'story',
        dataType: 'PICKUP',
      },
      {
        name: 'task',
        prop: 'task',
        dataType: 'PICKUP',
      },
      {
        name: 'mailto',
        prop: 'mailto',
        dataType: 'SMCODELIST',
      },
      {
        name: 'keywords',
        prop: 'keywords',
        dataType: 'TEXT',
      },
      {
        name: 'id',
        prop: 'id',
        dataType: 'ACID',
      },
      {
        name: 'bug',
        prop: 'id',
        dataType: 'FONTKEY',
      },
    ]
  }

}