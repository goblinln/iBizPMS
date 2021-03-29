
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppDefaultGrid } from 'ibiz-vue/src/components/control/app-default-grid/app-default-grid';
import '../plugin-style.less';

/**
 * 产品汇总表格插件类
 *
 * @export
 * @class ProductSumGrid
 * @class ProductSumGrid
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class ProductSumGrid extends AppDefaultGrid {


    /**
     * 数据项合并行规则数组
     * @type {Array<any>}
     * @memberof MainGrid
     */
     public infoList:Array<any> = [];

     /**
      * 表格渲染所需数组
      * @type {Array<any>}
      * @memberof MainGrid
      */
     public itemsRenderList:Array<any> = [];
 
     /**
      * 监听items
      * @memberof MainGrid
      */
     @Watch("items")
     public itemsWatch(newVal:any, oldVal:any) {
         if (newVal) {
             // 重置渲染数组和规则数组
             this.itemsRenderList.length = 0;
             this.infoList.length = 0;
             this.itemsRenderList.push(...newVal);
             // 排序并合并设置合并行规则
             this.listSort();
             this.setRowSpanRule();
             //  产品负责人行合并
             this.setRowPoSpanRule();
         }
     }
 
     /**
      * 根据需要合并的列排序
      * @memberof MainGrid
      */
     public listSort() {
         this.itemsRenderList.sort((prev:any, next:any) => {
             if (prev['name'] !== next['name']) {
                 return prev['name'].localeCompare(next['name'])
             }
         })
     }
 
     /**
      * 设置合并行规则
      * @memberof MainGrid
      */
     public setRowSpanRule() {
         let tempArray:Array<any> = [];
         this.itemsRenderList.forEach((item:any) => {
             let firstIndex:number = this.itemsRenderList.findIndex((curr:any) => {return curr.name === item.name;});
             if (tempArray.findIndex((curr:any) => {return curr.firstIndex === firstIndex}) === -1) {
                 tempArray.push({length:this.itemsRenderList.filter((curr:any) => {return curr.name === item.name}).length,firstIndex:firstIndex,mergeIndex: 1});
             }
         })
         this.infoList = [...tempArray];
     }
 
     /**
      * 产品负责人行合并
      * @memberof MainGrid
      */
     public setRowPoSpanRule() {
         let tempArray:Array<any> = [];
         this.itemsRenderList.forEach((item:any) => {
             let firstIndex: number = this.itemsRenderList.findIndex((curr: any) => {return curr.name === item.name && curr.po === item.po;});
             if (tempArray.findIndex((curr:any) => {return curr.firstIndex === firstIndex}) === -1) {
                 tempArray.push({length:this.itemsRenderList.filter((curr:any) => {return curr.name === item.name && curr.po === item.po}).length,firstIndex:firstIndex,mergeIndex: 2});
             }
         });
         this.infoList = this.infoList.concat(tempArray);
     }
 
     /**
      * Element合并单元格所需方法
      * @memberof MainGrid
      */
     public rowSpanMethod({ row, column, rowIndex, columnIndex }:{row:any, column:any, rowIndex:any, columnIndex:any}) {
         if (columnIndex === 0) {
             let index = this.infoList.findIndex((item:any) => {
                 return item.firstIndex === rowIndex;
             })
             if (index > -1) {
                 return {
                     rowspan: this.infoList[index].length,
                     colspan: 1
                 }
             } else {
                 return {
                     rowspan: 0,
                     colspan: 0
                 }
             }
         }
         if(columnIndex === 1) {
             let index = this.infoList.findIndex((item: any) => {
                 return item.firstIndex === rowIndex && item.mergeIndex === 2;
             })
             if (index > -1) {
                 return {
                     rowspan: this.infoList[index].length,
                     colspan: 1
                 }
             } else {
                 return {
                     rowspan: 0,
                     colspan: 0
                 }
             }
         }
     }

    /**
     * 计算表格参数
     * 
     * @memberof AppGridBase
     */
     public computeGridParams() {
        let options: any = {
            "data": this.itemsRenderList,
            "show-header": !this.controlInstance.hideHeader,
            "max-height": this.items.length > 0 ? "calc(100% - 50px)" : "100%",
            "stripe" : true,
            "span-method": this.rowSpanMethod.bind(this),
            "cell-style": {background:'#fff !important',borderBottom:'solid 1px #e8e8e8 !important',borderRight:'solid 1px #e8e8e8 !important'}
        };
        const { aggMode } = this.controlInstance;
        //  支持排序
        if (!this.isNoSort) {
            Object.assign(options, {
                "default-sort": {
                    prop: this.minorSortPSDEF,
                    order: Object.is(this.minorSortDir, "ASC") ? "ascending" : Object.is(this.minorSortDir, "DESC") ? "descending" : ""
                }
            });
        }
        //  支持表格聚合
        if (aggMode && aggMode != "NONE") {
            Object.assign(options, {
                "show-summary": this.items.length > 0 ? true : false,
                "summary-method": (param: any) => this.getSummaries(param)
            });
        }
        return options;
    }

    /**
     * 计算表格事件
     * 
     * @memberof AppGridBase
     */
    public computeGridEvents() {
        let events: any = {
        }
        //  支持排序
        if (!this.controlInstance.noSort) {
            Object.assign(events, {
                "sort-change": ({ column, prop, order }: any) => this.onSortChange({ column, prop, order })
            })
        }
        return events;
    }

    /**
     * 绘制
     * 
     * @param {*} h CreateElement对象
     * @memberof AppGridBase
     */
    public render(h: any) {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        return (<div class={{ ...controlClassNames, 'grid': true,'product-sum': true }} style="height:100%">
            <i-form style="height:100%;display:flex;flex-direction: column;justify-content: space-between">
                {this.renderGridContent(h)}
                {this.controlInstance.enablePagingBar ? this.renderPagingBar(h) : ""}
            </i-form>
        </div>)
    }

}

