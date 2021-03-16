import { IBizMainControlModel } from '../ibiz-main-control-model';
import { DynamicService } from '../../../service';
/**
 * 图表
 */
export class IBizChartModel extends IBizMainControlModel {

    /**
     * 获取标题对象
     *
     * @private
     * @memberof IBizChartModel
     */
    get title() {
        return this.controlModelData.getPSDEChartTitle;
    }

    /**
     * 获取图例
     *
     * @private
     * @memberof IBizChartModel
     */
    get chartLegend() {
        return this.controlModelData.getPSDEChartLegend;
    }

    /**
     * 获取图例
     *
     * @private
     * @memberof IBizChartModel
     */
    get chartGrids() {
        return this.controlModelData.getPSChartGrids;
    }

    /**
     * 加载模型参数
     *
     * @private
     * @memberof IBizChartModel
     */
    public async loaded() {
        await super.loaded();
        // 构造catalogPSCodeList属性
        if(this.controlModelData?.getPSDEChartSerieses?.length>0){
            for (let index = 0; index < this.controlModelData?.getPSDEChartSerieses?.length; index++) {
                const series: any = this.controlModelData?.getPSDEChartSerieses[index];
                if (series.getCatalogPSCodeList) {
                    const res = await DynamicService.getInstance(this.context).getAppCodeListJsonData(series.getCatalogPSCodeList.path);
                    Object.assign(series.getCatalogPSCodeList, res)
                }
                // 构造dataSetFields属性
                if (this.controlModelData?.getPSChartDataSets?.length > 0 && series.getPSChartDataSet) {
                    const dataSet = this.controlModelData.getPSChartDataSets.find((item: any) => {
                        return item.id === series.getPSChartDataSet.id;
                    });
                    for (let index = 0; index < dataSet.getPSChartDataSetFields?.length; index++) {
                        const dataFile: any = dataSet.getPSChartDataSetFields[index];
                        if (dataFile.getPSCodeList) {
                            const res = await DynamicService.getInstance(this.context).getAppCodeListJsonData(dataFile.getPSCodeList.path);
                            if (res) {
                                res.tag = res.codeName
                                Object.assign(dataFile, { codelist: res });
                            }
                        }
                        dataFile['isGroupField'] = dataFile.groupField;
                        dataFile['name'] = dataFile.name.toLowerCase();
                        dataFile['groupMode'] = dataFile.groupMode ? dataFile.groupMode : "";
                    }
                    series.id = series.name;
                    Object.assign(series, { dataSetFields: dataSet.getPSChartDataSetFields })
                }
            }
        }
        
        this.controlModelData?.getPSDEChartSerieses.forEach( async (item:any) => {
            if (item.getSeriesPSCodeList && item.getSeriesPSCodeList.modelref && item.getSeriesPSCodeList.path) {
                const res = await DynamicService.getInstance(this.context).getAppCodeListJsonData(item.getSeriesPSCodeList.path);
                if (res) {
                    res.tag = res.codeName
                    Object.assign(item, { seriesCodeList: res });
                }
            }
        });


    }

}