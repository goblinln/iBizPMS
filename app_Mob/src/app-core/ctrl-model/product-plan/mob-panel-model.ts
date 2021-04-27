/**
 * Mob 部件模型
 *
 * @export
 * @class MobModel
 */
export class MobModel {

  /**
    * 获取数据项集合
    *
    * @returns {any[]}
    * @memberof MobModel
    */
  public getDataItems(): any[] {
    return [
      {
        name: 'taskscnt',
      },
      {
        name: 'estimatecnt',
      },
      {
        name: 'comment',
      },
      {
        name: 'title',
      },
      {
        name: 'delaytaskscnt',
      },
      {
        name: 'oldtitle',
      },
      {
        name: 'productplan',
        prop: 'id',
      },
      {
        name: 'begin',
      },
      {
        name: 'statuss',
      },
      {
        name: 'desc',
      },
      {
        name: 'end',
      },
      {
        name: 'delay',
      },
      {
        name: 'duration',
      },
      {
        name: 'beginstr',
      },
      {
        name: 'leftestimate',
      },
      {
        name: 'plantemplet',
      },
      {
        name: 'unfinishedtaskscnt',
      },
      {
        name: 'endstr',
      },
      {
        name: 'status',
      },
      {
        name: 'isexpired',
      },
      {
        name: 'deleted',
      },
      {
        name: 'consumedestimate',
      },
      {
        name: 'order',
      },
      {
        name: 'isleaf',
      },
      {
        name: 'future',
      },
      {
        name: 'storycnt',
      },
      {
        name: 'delta',
      },
      {
        name: 'finishedtaskscnt',
      },
      {
        name: 'bugcnt',
      },
      {
        name: 'parentname',
      },
      {
        name: 'branch',
      },
      {
        name: 'parent',
      },
      {
        name: 'product',
      },
      {
        name: 'begintime',
      },
      {
        name: 'ordernum',
      },
    ]
  }

}
// 默认导出
export default MobModel;