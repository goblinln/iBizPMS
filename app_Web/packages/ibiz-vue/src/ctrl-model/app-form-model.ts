import { IBizFormItemModel, IBizFormModel } from "ibiz-core";

/**
 * AppFormModel 部件模型
 *
 * @export
 * @class AppFormModel
 */
export class AppFormModel {

    /**
    * 表单实例对象
    *
    * @memberof AppFormModel
    */
    public FormInstance !: IBizFormModel;

    /**
     * Creates an instance of AppFormModel.
     * 
     * @param {*} [opts={}]
     * @memberof AppFormModel
     */
    constructor(opts: any) {
        this.FormInstance = opts;
    }

    /**
    * 获取数据项集合
    *
    * @returns {any[]}
    * @memberof AppFormModel
    */
    public getDataItems(): any[] {
        let modelArray:any[] = [
            {
                name: 'srfwfmemo',
                prop: 'srfwfmemo',
                dataType: 'TEXT',
            },
            {
                name: 'srffrontuf',
                prop: 'srffrontuf',
                dataType: 'TEXT',
            }
        ]
        const { allFormDetails, formItems, appDataEntity } = this.FormInstance;
        // 表单部件
        allFormDetails.forEach(( detail: any ) => {
            if(detail.detailType && detail?.formPartType == 'DYNASYS'){
                modelArray.push({
                    name: detail.name,
                    dataType: 'FORMPART'
                })
            }
        });
        // 表单项
        formItems.forEach(( item: IBizFormItemModel)=>{
            let temp: any = { name: item.name};
            if(item.appDeField){
                temp.prop = item.appDeField.codeName?.toLowerCase();
                temp.dataType = item.appDeField.dataType?.toLowerCase();
            }else if(item.editor?.editorType !== 'HIDDEN'){
                //表单项无属性且界面显示类型（供开始流程、提交流程使用）
                if(!item.hidden){
                    temp.prop = item.name;
                }
                temp.dataType = 'FORMITEM';
            }
            modelArray.push(temp);
        });
        // todo 关联主实体的主键

        // 界面主键标识
        modelArray.push({
            name: appDataEntity.codeName.toLowerCase(),
            prop: appDataEntity.keyField.codeName.toLowerCase(),
            dataType: 'FRONTKEY',
        })
        
        return modelArray;
    }

}