import { IPSAppDataEntity, IPSChartExpBar, IPSDEChart, IPSDEChartSeries, IPSDERBase } from '@ibiz/dynamic-model-api';
import { Util } from 'ibiz-core';
import { ExpBarControlBase } from './expbar-control-base';

export class ChartExpBarControlBase extends ExpBarControlBase {
    
    /**
     * 导航栏部件模型对象
     * 
     * @memberof ChartExpBarControlBase
     */
    public controlInstance!: IPSChartExpBar;

    /**
     * 数据部件
     *
     * @memberof ChartExpBarControlBase
     */
    public $xDataControl!: IPSDEChart;

    /**
     * 部件模型初始化
     * 
     * @memberof ChartExpBarControlBase
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        this.initNavView();
    }

    /**
     * 初始化导航视图
     * 
     * @memberof ChartExpBarControlBase
     */
    public initNavView() {
        if (!this.$xDataControl) {
            return;
        }
        let navViewName: any = {};
        let navParam: any = {};
        let navFilter: any = {};
        let navPSDer: any = {};
        const series: Array<IPSDEChartSeries> = this.$xDataControl.getPSDEChartSerieses() || [];
        series.forEach((item: IPSDEChartSeries) => {
            Object.assign(navViewName, {
                [item.name]: item.getNavPSAppView?.()?.modelPath || ""
            });
            Object.assign(navParam, {
                [item.name.toLowerCase()]: {
                    navigateContext: this.initNavParam(item.getPSNavigateContexts?.()),
                    navigateParams: this.initNavParam(item.getPSNavigateParams?.())
                }
            });
            if (item.navFilter) {
                Object.assign(navFilter, {
                    [item.name]: item.navFilter || ""
                });
            }
            Object.assign(navPSDer, {
                [item.name]: item.getNavPSDER() ? "n_" + (item.getNavPSDER() as IPSDERBase).minorCodeName?.toLowerCase() + "_eq" : ""
            });
        })
        this.navViewName = navViewName;
        this.navParam = navParam;
        this.navFilter = navFilter;
        this.navPSDer = navPSDer;
    }

   /**
    * 执行搜索
    *
    * @memberof ChartExpBarControlBase
    */
    public onSearch() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let chart: any = (this.$refs[`${this.xDataControlName}`] as any)?.ctrl;
        if(chart) {
            chart.load({ query: this.searchText });
        }
    }

    /**
     * 刷新
     *
     * @memberof ChartExpBarControlBase
     */
    public refresh(): void {
        let chart: any = (this.$refs[`${this.xDataControlName}`] as any)?.ctrl;
        if(chart) {
            chart.load({ query: this.searchText });
        }
    }

    /**
     * split值变化事件
     *
     * @memberof ChartExpBarControlBase
     */
    public onSplitChange() {
        super.onSplitChange();
        let chart: any = (this.$refs[`${this.xDataControlName}`] as any).ctrl;
        if(chart && chart.myChart && chart.myChart.resize && chart.myChart.resize instanceof Function) {
            chart.myChart.resize();
        }
    }

    /**
     * 图表部件选中数据变化
     * 
     * @memberof ChartExpBarControlBase
     */
    public onSelectionChange(args: any[]): void {
        let tempContext: any = {};
        let tempViewParam: any = {};
        if (args.length === 0) {
            this.calcToolbarItemState(true);
            return;
        }
        const arg: any = args[0];
        if (this.context) {
            Object.assign(tempContext, Util.deepCopy(this.context));
        }
        const seriesItem: IPSDEChartSeries | null | undefined = (this.$xDataControl.getPSDEChartSerieses() || []).find((item: IPSDEChartSeries) => {
            return item.name.toLowerCase() === arg._chartName.toLowerCase();
        });
        const appDataEntity: IPSAppDataEntity | null = this.$xDataControl?.getPSAppDataEntity();
        if (seriesItem && appDataEntity) {
            Object.assign(tempContext, { [appDataEntity.codeName?.toLowerCase()]: arg[appDataEntity.codeName?.toLowerCase()] });
            Object.assign(tempContext, { srfparentdename: appDataEntity.codeName,srfparentdemapname:(appDataEntity as any)?.getPSDEName(), srfparentkey: arg[appDataEntity.codeName?.toLowerCase()] });
            //  分类属性
            if (seriesItem.catalogField) {
                Object.assign(tempContext, { [seriesItem.catalogField]: arg[seriesItem.catalogField] });
                Object.assign(tempViewParam, { [seriesItem.catalogField]: arg[seriesItem.catalogField] });
            }
            //  数据属性
            if (seriesItem.valueField) {
                Object.assign(tempContext, { [seriesItem.valueField]: arg[seriesItem.valueField] });
                Object.assign(tempViewParam, { [seriesItem.valueField]: arg[seriesItem.valueField] });
            }
            if (this.navFilter && this.navFilter[arg._chartName] && !Object.is(this.navFilter[arg._chartName], "")) {
                Object.assign(tempViewParam, { [this.navFilter[arg._chartName]]: arg[appDataEntity.codeName?.toLowerCase()] });
            }
            if (this.navPSDer && this.navFilter[arg._chartName] && !Object.is(this.navPSDer[arg._chartName], "")) {
                Object.assign(tempViewParam, { [this.navPSDer[arg._chartName]]: arg[appDataEntity.codeName?.toLowerCase()] });
            }
            if (this.navParam && this.navParam[arg._chartName] && this.navParam[arg._chartName].navigateContext && Object.keys(this.navParam[arg._chartName].navigateContext).length > 0) {
                let _context: any = Util.computedNavData(arg, tempContext, tempViewParam, this.navParam[arg._chartName].navigateContext);
                Object.assign(tempContext, _context);
            }
            if (this.navParam && this.navParam[arg._chartName] && this.navParam[arg._chartName].navigateParams && Object.keys(this.navParam[arg._chartName].navigateParams).length > 0) {
                let _params: any = Util.computedNavData(arg, tempContext, tempViewParam, this.navParam[arg._chartName].navigateParams);
                Object.assign(tempViewParam, _params);
            }
            if (seriesItem.getNavPSAppView()) {
                Object.assign(tempContext, {
                    viewpath: seriesItem.getNavPSAppView()?.modelPath
                })
            }
        }
        this.selection = {};
        Object.assign(this.selection, { view: { viewname: 'app-view-shell' }, context: tempContext, viewparam: tempViewParam });
        this.calcToolbarItemState(false);
        this.$emit("ctrl-event", { controlname: this.controlInstance.name, action: "selectionchange", data: args });
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof ChartExpBarControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (action == 'selectionchange') {
            this.onSelectionChange(data.data);
        } else {
            super.onCtrlEvent(controlname, action, data);
        }
    }

}