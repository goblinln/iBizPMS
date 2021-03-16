import { IBizMdViewModel } from './ibiz-md-view-model';

/**
 * 图表视图模型类
 * 
 * @class IBizChartViewModel
 */
export class IBizChartViewModel extends IBizMdViewModel {

    /**
     * 实体视图图表
     * 
     * @memberof IBizChartViewModel
     */
    private $chart: any = {};

    /**
     * 初始化IBizChartViewModel对象
     * 
     * @memberof IBizChartViewModel
     */
    public constructor(opts: any, context: any) {
        super(opts, context);
    }

    /**
     * 加载模型数据
     * 
     * @memberof IBizChartViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$chart = this.getControlByName("chart");
    }

    /**
     * 获取视图图表
     * 
     * @memberof IBizChartViewModel
     */
    get viewChart() {
        return this.$chart;
    }

    /**
     * 显示处理提示
     * 
     * @memberof IBizChartViewModel
     */
    get showBusyIndicator() {
        return this.viewChart.getPSControlParam.showBusyIndicator;
    }

}