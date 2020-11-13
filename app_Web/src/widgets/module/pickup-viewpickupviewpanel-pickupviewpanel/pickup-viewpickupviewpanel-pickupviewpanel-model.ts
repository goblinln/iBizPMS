/**
 * PickupViewpickupviewpanel 部件模型
 *
 * @export
 * @class PickupViewpickupviewpanelModel
 */
export default class PickupViewpickupviewpanelModel {

  /**
    * 获取数据项集合
    *
    * @returns {any[]}
    * @memberof PickupViewpickupviewpanelModel
    */
  public getDataItems(): any[] {
    return [
      {
        name: 'root',
      },
      {
        name: 'grade',
      },
      {
        name: 'type',
      },
      {
        name: 'name',
      },
      {
        name: 'order',
      },
      {
        name: 'owner',
      },
      {
        name: 'module',
        prop: 'id',
      },
      {
        name: 'collector',
      },
      {
        name: 'ibizshort',
      },
      {
        name: 'path',
      },
      {
        name: 'deleted',
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
        name: 'mdeptid',
      },
      {
        name: 'orgid',
      },
    ]
  }


}