/**
 * AppPanelModel 部件模型
 *
 * @export
 * @class AppPanelModel
 */
export class AppPanelModel {

    /**
    * 面板实例对象
    *
    * @memberof AppPanelModel
    */
   public PanelInstance:any;

   /**
    * Creates an instance of AppPanelModel.
    * 
    * @param {*} [opts={}]
    * @memberof AppPanelModel
    */
   constructor(opts: any) {
       this.PanelInstance = opts;
   }


  /**
    * 获取name找到对应field
    *
    * @returns {any[]}
    * @memberof AppPanelModel
    */
   public findField(arr:Array<any>, name:string):any {
    let _result = null;
    for (let i = 0; i < arr.length; i++) {
      if (arr[i].name == name) return arr[i];
      if (arr[i].getPSPanelItems) _result = this.findField(arr[i].getPSPanelItems, name)
      if (_result != null) return _result;
    }
    return _result
  }

  /**
    * 获取数据项集合
    *
    * @returns {any[]}
    * @memberof AppPanelModel
    */
  public getDataItems(): any[] {
    let arr:any = [];
    this.PanelInstance.getAllPSPanelFields.forEach((datafield:any) => {
      let field:any = this.findField(this.PanelInstance.getRootPSPanelItems,datafield.id);
      let obj:any = {};
      obj.name = field?.name.toLowerCase();
      obj.prop = field?.viewFieldName?.toLowerCase();
      arr.push(obj);
    });
    return arr
  }
}