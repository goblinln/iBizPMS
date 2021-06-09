import { IPSAppView, IPSDEGrid, IPSGridExpBar } from '@ibiz/dynamic-model-api';
import { GridExpBarControlInterface } from 'ibiz-core';
import { ExpBarControlBase } from './expbar-control-base';

/**
 * 表格导航部件基类
 *
 * @export
 * @class GridExpControlBase
 * @extends {MDControlBase}
 */
export class GridExpBarControlBase extends ExpBarControlBase implements GridExpBarControlInterface {

    /**
     * 表格导航模型对象
     * 
     * @memberof GridExpBarControlBase
     */
    public controlInstance!: IPSGridExpBar;

    /**
     * 数据部件
     *
     * @memberof GridExpBarControlBase
     */
    protected $xDataControl!: IPSDEGrid;

    /**
     * 处理数据部件参数
     *
     * @memberof GridExpBarControlBase
     */
    public async handleXDataCtrlOptions() {
        //TODO 导航关系
        const navPSAppView: IPSAppView = await this.$xDataControl?.getNavPSAppView()?.fill() as IPSAppView;
        if (navPSAppView) {
            this.navViewName = navPSAppView.modelFilePath;
        }
        this.navFilter = this.$xDataControl?.navFilter ? this.$xDataControl.navFilter : "";
        // this.navPSDer = this.$xDataControl?.getNavPSDER ? this.$xDataControl.navPSDer : "";
    }

    /**
     * 处理快速分组模型动态数据部分(%xxx%)
     *
     *
     * @param {Array<any>} inputArray 代码表数组
     * @return {*} 
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

   /**
    * 执行搜索
    *
    * @memberof GridExpBarControlBase
    */
    public onSearch() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let grid: any = (this.$refs[`${this.xDataControlName?.toLowerCase()}`] as any).ctrl;
        if(grid) {
            grid.load({ query: this.searchText });
        }
    }

}