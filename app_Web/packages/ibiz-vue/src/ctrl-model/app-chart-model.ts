import { IPSAppDEChartView, IPSDEChart, IPSDEFormItem, IPSDESearchForm } from "@ibiz/dynamic-model-api";
import { ModelTool } from "ibiz-core";

export class AppChartModel {

    /**
    * 图表实例对象
    *
    * @memberof AppChartModel
    */
    public chartInstance !: IPSDEChart;

    /**
     * Creates an instance of AppChartModel.
     * 
     * @param {*} [opts={}]
     * @memberof AppChartModel
     */
    constructor(opts: any) {
        this.chartInstance = opts;
    }

    /**
     * 获取数据项集合
     *
     * @returns {any[]}
     * @memberof AppChartModel
     */
    public getDataItems(): any[] {
        const modelArray = [
            {
                name: 'size',
                prop: 'size'
            },
            {
                name: 'query',
                prop: 'query'
            },
            {
                name: 'page',
                prop: 'page'
            },
            {
                name: 'sort',
                prop: 'sort'
            }
        ]
        const searchFormInstance: IPSDESearchForm = ModelTool.findPSControlByType("SEARCHFORM", (this.chartInstance.getParentPSModelObject() as IPSAppDEChartView).getPSControls() || []);
        if (searchFormInstance) {
            (searchFormInstance.getPSDEFormItems?.() || []).forEach((formItem: IPSDEFormItem) => {
                let temp: any = { name: formItem.id, prop: formItem.id };
                if (formItem.getPSAppDEField?.()) {
                    temp.dataType = 'QUERYPARAM';
                }
                modelArray.push(temp);
            });
        }
        return modelArray
    }
}