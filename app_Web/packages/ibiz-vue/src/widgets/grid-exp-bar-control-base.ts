import { IBizGridExpBarModel } from 'ibiz-core';
import { ExpBarControlBase } from './expbar-control-base';

/**
 * 表格导航部件基类
 *
 * @export
 * @class GridExpControlBase
 * @extends {MDControlBase}
 */
export class GridExpBarControlBase extends ExpBarControlBase {

    /**
     * 表格导航模型对象
     * 
     * @memberof GridExpBarControlBase
     */
    public controlInstance!: IBizGridExpBarModel;

    /**
     * 处理快速分组模型动态数据部分(%xxx%)
     *
     * @memberof GridExpBarControlBase
     */
    public handleDynamicData(inputArray:Array<any>){
        if(inputArray.length >0){
            inputArray.forEach((item:any) =>{
               if(item.data && Object.keys(item.data).length >0){
                   Object.keys(item.data).forEach((name:any) =>{
                        let value: any = item.data[name];
                        if (value && typeof(value)=='string' && value.startsWith('%') && value.endsWith('%')) {
                            const key = (value.substring(1, value.length - 1)).toLowerCase();
                            if (this.context[key]) {
                                value = this.context[key];
                            } else if(this.viewparams[key]){
                                value = this.viewparams[key];
                            }
                        }
                        item.data[name] = value;
                   })
               }
            })
        }
        return inputArray;
    }

    public onSearch() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let grid: any = (this.$refs[`${this.$xDataControl?.name?.toLowerCase()}`] as any).$refs.ctrl;
        if(grid) {
            grid.load({ query: this.searchText });
        }
    }

}