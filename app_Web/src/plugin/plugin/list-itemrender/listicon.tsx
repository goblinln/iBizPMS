



/**
 * 列表项图标呈现插件插件类
 *
 * @export
 * @class Listicon
 * @extends {Vue}
 */
export class Listicon {

  /**
   * 绘制部件项内容
   * @param h createElement函数
   * @param ctrlItemModel 部件项模型数据
   * @param parentContainer  父容器对象
   * @param data  当前项数据
   * 
   * @memberof Listicon
   */  
  public renderCtrlItem(h:any,ctrlItemModel:any,parentContainer:any,data:any){
    const { name, codelist } = ctrlItemModel;
    let renderParams: any = {
      "value": data[name],
    };
    if (codelist) {
      renderParams.tag = codelist.codeName;
      renderParams.codelistType = codelist.codeListType;
      renderParams.renderMode = codelist.orMode;
      renderParams.valueSeparator = codelist.valueSeparator;
      renderParams.textSeparator = codelist.textSeparator;
    }
    return parentContainer.$createElement('icon-codelist', {
      props: renderParams,
    });

  }

}
