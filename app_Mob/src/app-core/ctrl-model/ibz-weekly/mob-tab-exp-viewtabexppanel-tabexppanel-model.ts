/**
 * MobTabExpViewtabexppanel 部件模型
 *
 * @export
 * @class MobTabExpViewtabexppanelModel
 */
export class MobTabExpViewtabexppanelModel {

  /**
    * 获取数据项集合
    *
    * @returns {any[]}
    * @memberof MobTabExpViewtabexppanelModel
    */
  public getDataItems(): any[] {
    return [
      {
        name: 'ibzweeklyname',
      },
      {
        name: 'ibzweekly',
        prop: 'ibzweeklyid',
      },
      {
        name: 'createman',
      },
      {
        name: 'createdate',
      },
      {
        name: 'updateman',
      },
      {
        name: 'updatedate',
      },
      {
        name: 'account',
      },
      {
        name: 'mailto',
      },
      {
        name: 'files',
      },
      {
        name: 'issubmit',
      },
      {
        name: 'reportto',
      },
      {
        name: 'comment',
      },
      {
        name: 'date',
      },
      {
        name: 'workthisweek',
      },
      {
        name: 'plannextweek',
      },
      {
        name: 'thisweektask',
      },
      {
        name: 'nextweektask',
      },
      {
        name: 'updatemanname',
      },
      {
        name: 'createmanname',
      },
      {
        name: 'reportstatus',
      },
      {
        name: 'submittime',
      },
    ]
  }

}
// 默认导出
export default MobTabExpViewtabexppanelModel;