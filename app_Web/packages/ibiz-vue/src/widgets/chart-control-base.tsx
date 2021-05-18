import moment from 'moment';
import { init } from 'echarts';
import { MDControlBase } from './md-control-base';
import { AppChartService } from '../ctrl-service';
import {
    Util,
    ChartLineSeries,
    ChartFunnelSeries,
    ChartPieSeries,
    ChartBarSeries,
    ChartRadarSeries,
    LogUtil,
} from 'ibiz-core';
import {
    IPSChartCoordinateSystemCartesian2D,
    IPSChartGridXAxis,
    IPSChartSeriesCSCartesian2DEncode,
    IPSChartSeriesCSNoneEncode,
    IPSChartXAxis,
    IPSChartYAxis,
    IPSDEChart,
    IPSDEChartGrid,
    IPSDEChartSeries, IPSDEChartTitle
} from "@ibiz/dynamic-model-api";
/**
 * 图表部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {MDControlBase}
 */
export class ChartControlBase extends MDControlBase {
    /**
     * 图表的模型对象
     *
     * @type {*}
     * @memberof ChartControlBase
     */
    public controlInstance!: IPSDEChart;

    /**
     * 图表div绑定的id
     *
     * @type {}
     * @memberof ChartControlBase
     */
    public chartId: string = Util.createUUID();

    /**
     * 是否无数据
     *
     * @public
     * @type {boolean}
     * @memberof ChartControlBase
     */
    public isNoData: boolean = false;

    /**
     * echarts图表对象
     *
     * @type {}
     * @memberof ChartControlBase
     */
    public myChart: any;

    /**
     * 序列模型
     *
     * @type {}
     * @memberof ChartControlBase
     */
    public seriesModel: any = {};

    /**
     * 初始化series
     *
     * @memberof ChartControlBase
     */
    public async initSeriesModel() {
        if (!this.controlInstance.getPSDEChartSerieses()) {
            return;
        }
        for (let index = 0; index < (this.controlInstance.getPSDEChartSerieses() as any)?.length; index++) {
            const series: IPSDEChartSeries = (this.controlInstance.getPSDEChartSerieses() as any)[index];
            if (series) {
                this.initChartSeries(await this.getSeriesModelParam(series), series);
            }
        }
    }

    /**
     * 获取SeriesModel参数
     *
     * @memberof ChartControlBase
     */
    public getSeriesModelParam(series: any): Promise<any> {
        // 构造dataSetFields属性
        const opts: any = {
            name: series.name?.toLowerCase(),
            categorField: series.catalogField?.toLowerCase(),
            valueField: series.valueField?.toLowerCase(),
            seriesValues: [],
            seriesIndex: series.index | 0,
            data: [],
            seriesMap: {},
            categorCodeList: {
                type: series.getCatalogPSCodeList()?.codeListType,
                tag: series.getCatalogPSCodeList()?.codeName, emptycode: 'empty',
                emptytext: series.getCatalogPSCodeList()?.emptyText
            },
            dataSetFields: this.getDataSetFields(series),
            ecxObject: {
                label: {
                    show: true,
                    position: 'inside',
                },
                labelLine: {
                    length: 10,
                    lineStyle: {
                        width: 1,
                        type: 'solid',
                    },
                },
                itemStyle: {
                    borderColor: '#fff',
                    borderWidth: 1,
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 20,
                    },
                },
            },
            seriesCodeList: series.getSeriesPSCodeList() ? {
                type: series.getSeriesPSCodeList()?.codeListType,
                tag: series.getSeriesPSCodeList()?.codeName, emptycode: 'empty',
                emptytext: series.getSeriesPSCodeList()?.emptyText
            } : null,
            seriesNameField: series.seriesField?.toLowerCase(),
            ecObject: {},
            seriesTemp: {
                type: series.eChartsType,
            },
            type: series.eChartsType,
            seriesLayoutBy: series.seriesLayoutBy,
            baseOption: {},
        };
        // 饼图引导线默认配置
        if (Object.is(series.eChartsType, 'pie')) {
            Object.assign(opts.ecxObject.label, {
                position: 'outside',
            });
            Object.assign(opts.ecxObject.labelLine, {
                show: true,
            });
        }
        // 处理自定义ECX参数
        this.fillUserParam(series, opts.ecxObject, 'ECX');
        this.fillUserParam(series, opts.ecObject, 'EC');
        Object.assign(
            opts,
            series.baseOptionJOString ? new Function('return {' + series.baseOptionJOString + '}')() : {},
        );
        return opts;
    }

    /**
     * 临时获取seriesDataSetField 模型
     */
    public getDataSetFields(series: any) {
        const seriesData: any = [];
        const dataSet = (this.controlInstance as any).getPSChartDataSets()?.find((item: any) => {
            return item.id === series?.M?.getPSChartDataSet?.id || null
        }) || null;
        if (!dataSet && !dataSet.getPSChartDataSetFields()) {
            return null
        }
        for (let index = 0; index < dataSet.getPSChartDataSetFields()?.length; index++) {
            const dataFile: any = dataSet.getPSChartDataSetFields()[index];
            const data: any = {}
            if (dataFile.getPSCodeList()) {
                const codelist = dataFile.getPSCodeList();
                Object.assign(data, { codelist: codelist });
            }
            data['isGroupField'] = dataFile.groupField;
            data['name'] = dataFile.name?.toLowerCase();
            data['groupMode'] = dataFile.groupMode ? dataFile.groupMode : "";
            seriesData.push(data);
        }
        return seriesData

    }

    /**
     * 处理用户自定义参数
     *
     * @memberof ChartControlBase
     */
    public fillUserParam(param: any, opts: any, tag: string) {
        if (!param.userParams) {
            return;
        }
        const userParam = param.userParams;
        switch (tag) {
            case 'ECX':
                if (userParam['ECX.label']) {
                    opts['label'] = eval("("+userParam['ECX.label']+")");
                }
                if (userParam['ECX.labelLine']) {
                    opts['labelLine'] = eval("("+userParam['ECX.labelLine']+")");
                }
                if (userParam['ECX.itemStyle']) {
                    opts['itemStyle'] = eval("("+userParam['ECX.itemStyle']+")");
                }
                if (userParam['ECX.emphasis']) {
                    opts['emphasis'] = eval("("+userParam['ECX.emphasis']+")");
                }
                for (const key in userParam) {
                    if (Object.prototype.hasOwnProperty.call(userParam, key)) {
                        if (key.indexOf('EC.') != -1) {
                            const value = userParam[key].trim();
                            opts[key.replace('EC.', '')] = value;
                        }
                    }
                }
                break;
            case 'EC':
                for (const key in userParam) {
                    if (Object.prototype.hasOwnProperty.call(userParam, key)) {
                        if (key.indexOf('EC.') != -1) {
                            const value = userParam[key].trim();
                            opts[key.replace('EC.', '')] = this.isJson(value)
                                ? JSON.parse(value)
                                : this.isArray(value)
                                    ? eval(value)
                                    : value;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 是否为数组字符串
     */
    public isArray(str: string) {
        try {
            eval(str);
            return true;
        } catch (error) {
            return false;
        }
    }

    /**
     * 是否为json字符串
     */
    public isJson(str: any): boolean {
        try {
            JSON.parse(str);
            return true;
        } catch (error) {
            return false;
        }
    }

    /**
     * 初始化填充seriesModel
     *
     * @memberof ChartControlBase
     */
    public initChartSeries(opts: any, series: any): any {
        switch (series.eChartsType) {
            // 折线图
            case 'line':
                this.seriesModel[series.name?.toLowerCase()] = new ChartLineSeries(opts);
                break;
            // 漏斗图
            case 'funnel':
                this.seriesModel[series.name?.toLowerCase()] = new ChartFunnelSeries(opts);
                break;
            // 饼图
            case 'pie':
                this.seriesModel[series.name?.toLowerCase()] = new ChartPieSeries(opts);
                break;
            // 柱状图
            case 'bar':
                this.seriesModel[series.name?.toLowerCase()] = new ChartBarSeries(opts);
                break;
            // 雷达图
            case 'radar':
                this.seriesModel[series.name?.toLowerCase()] = new ChartRadarSeries(opts);
                break;
        }
    }

    /**
     * 图表自定义参数集合
     * 
     * @memberof ChartControlBase
     */
    public chartUserParams: any = {};

    /**
     * 初始化chartUserParams
     *
     * @memberof ChartControlBase
     */
    public initChartUserParams() {
        this.fillUserParam(this.controlInstance, this.chartUserParams, 'EC');
    }

    /**
     * 图表基础动态模型
     * 
     * @memberof ChartControlBase
     */
    public chartBaseOPtion: any = {};

    /**
     * 初始化图表基础动态模型
     */
    public initChartBaseOPtion() {
        this.chartBaseOPtion = new Function(
            'return {' + this.controlInstance?.baseOptionJOString + '}',
        )();
    }

    /**
     * 图表绘制最终参数
     *
     * @memberof ChartControlBase
     */
    public chartRenderOption: any = {};

    /**
     * 初始化图表所需参数
     *
     * @type {}
     * @memberof ChartControlBase
     */
    public chartOption: any = {};

    /**
     * 填充chartOption
     *
     * @memberof ChartControlBase
     */
    public initChartOption() {
        const series: any = [];
        // 填充series
        const indicator: any = [];
        this.controlInstance.getPSDEChartSerieses()?.forEach((_series: IPSDEChartSeries) => {
            series.push(this.fillSeries(_series, indicator));
        });
        // 填充xAxis
        const xAxis: any = [];
        //  todo  缺失getPSChartXAxises接口
        (this.controlInstance as any).getPSChartXAxises()?.forEach((_xAxis: IPSChartXAxis) => {
            xAxis.push(this.fillAxis(_xAxis));
        });
        // 填充yAxis
        const yAxis: any = [];
        //  todo  缺失getPSChartYAxises接口
        (this.controlInstance as any).getPSChartYAxises()?.forEach((_yAxis: IPSChartYAxis) => {
            yAxis.push(this.fillAxis(_yAxis));

        });
        // 填充grid
        const grid: any = [];
        //  todo  缺失getPSChartGrids接口
        (this.controlInstance as any).getPSChartGrids()?.forEach((_grid: IPSDEChartGrid) => {
            grid.push({
                ..._grid.baseOptionJOString ? (new Function("return {" + _grid.baseOptionJOString + '}'))() : {}
            })
        });
        // chartOption参数
        const opt = {
            tooltip: { show: true },
            dataset: [],
            legend: {
                show: this.controlInstance.getPSDEChartLegend()?.showLegend,
            },
            series: series,
            xAxis: xAxis,
            yAxis: yAxis,
            grid: grid,
        };
        if (this.controlInstance.getPSDEChartTitle()) {
            const _titleModel: IPSDEChartTitle | null = this.controlInstance.getPSDEChartTitle();
            const title = {
                show: _titleModel?.showTitle,
                text: _titleModel?.title,
                subtext: _titleModel?.subTitle,
            }
            Object.assign(opt, { title });
        }
        // 合并chartOption
        Object.assign(this.chartOption, opt);
        // 雷达图特殊参数
        if (indicator.length > 0) {
            Object.assign(this.chartOption, { radar: { indicator } });
        }
    }

    /**
     * 填充 series
     */
    public fillSeries(series: any, indicator: any = {}) {
        const encode: any = {};
        const assginCodeList = (item: any) => {
            item?.getPSCodeItems?.()?.forEach((_item: any) => {
                const item = Util.deepCopy(_item.M);
                item.name = _item.text;
                item.max = _item?.getUserParam?.MAXVALUE ? _item.getUserParam.MAXVALUE : 100;
                indicator.push(item);
                if (_item?.getPSCodeItems?.()?.length > 0) {
                    assginCodeList(_item);
                }
            });
        };
        switch (series.eChartsType) {
            case 'line':
            case 'bar':
                const cSCartesian2DEncode = series.getPSChartSeriesEncode() as IPSChartSeriesCSCartesian2DEncode;
                encode.x = this.arrayToLowerCase(cSCartesian2DEncode.getX())
                encode.y = this.arrayToLowerCase(cSCartesian2DEncode.getY())
                break;
            case 'pie':
            case 'funnel':
                const CSNoneEncode = series.getPSChartSeriesEncode() as IPSChartSeriesCSNoneEncode;
                encode.itemName = CSNoneEncode.category?.toLowerCase();
                encode.value = CSNoneEncode.value?.toLowerCase();
                break;
            case 'radar':
                encode.itemName = "type";
                if (series.getCatalogPSCodeList) {
                    assginCodeList(series.getCatalogPSCodeList());
                }
                break;
            default:
                break;
        }
        const axis: IPSChartCoordinateSystemCartesian2D = (series.getPSChartCoordinateSystem() as IPSChartCoordinateSystemCartesian2D);
        return {
            id: series?.name?.toLowerCase(),
            name: series.caption,
            type: series.eChartsType,
            xAxisIndex: (axis?.getPSChartGrid()?.getPSChartGridXAxis0() as any)?.getIndex() | 0,
            yAxisIndex: (axis?.getPSChartGrid()?.getPSChartGridYAxis0() as any)?.getIndex() | 0,
            datasetIndex: (series.getPSChartDataSet() as any)?.getIndex() | 0,
            encode: encode,
            ...series.baseOptionJOString ? (new Function("return {" + series.baseOptionJOString + '}'))() : {},
        };
    }

    /**
     * 数组元素小写
     *
     * @param {*} arr
     * @returns
     * @memberof ChartControlBase
     */
    public arrayToLowerCase(arr: any) {
        if (!arr || arr.length == 0) {
            return [];
        }
        for (let index = 0; index < arr.length; index++) {
            arr[index] = arr[index].toLowerCase();
        }
        return arr;
    }

    /**
     * 填充 axis
     */
    public fillAxis(axis: IPSChartGridXAxis): any {
        const _axis: any = {
            // todo  缺失index
            gridIndex: axis.index,
            position: axis.position,
            type: axis.eChartsType,
            name: axis.caption,
        }
        // 填充用户自定义参数
        this.fillUserParam(axis, _axis, 'EC.')
        if (axis.minValue) {
            _axis['min'] = axis.minValue;
        }
        if (axis.maxValue) {
            _axis['max'] = axis.maxValue;
        }
        return _axis;
    }

    /**
     * 刷新
     *
     * @param {*} [args={}]
     * @memberof ChartControlBase
     */
    public refresh(args?: any) {
        this.load(args);
    }

    /**
     * 获取图表数据
     *
     * @returns {*}
     * @memberof ChartControlBase
     */
    public load(opt?: any) {
        let _this = this;
        const arg: any = { ...opt };
        const parentdata: any = {};
        this.ctrlEvent({ controlname: this.controlInstance.name, action: 'beforeload', data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg, { viewparams: tempViewParams });
        Object.assign(arg, { page: 0, size: 1000 });
        if (this.controlInstance.minorSortDir && this.controlInstance.getMinorSortPSAppDEField()?.codeName) {
            Object.assign(arg, { sort: `${this.controlInstance.getMinorSortPSAppDEField()?.codeName?.toLowerCase()},${this.controlInstance.minorSortDir?.toLowerCase()}` });
        }
        this.ctrlBeginLoading();
        this.service
            .search(this.fetchAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator)
            .then(
                (res: any) => {
                    this.ctrlEndLoading();
                    if (res) {
                        this.transformToBasicChartSetData(res.data, (codelist: any) => {
                            _this.drawCharts();
                        });
                    }
                },
                (error: any) => {
                    this.ctrlEndLoading();
                    this.$throw(error);
                },
            );
    }

    /**
     * 绘制图表
     *
     * @returns {*}
     * @memberof ChartControlBase
     */
    public drawCharts() {
        if (!this.myChart) {
            let element: any = document.getElementById(this.chartId);
            if (element) {
                this.myChart = init(element);
            }
        }
        let _chartOption = this.handleChartOPtion();
        this.chartRenderOption = { ..._chartOption };
        if (this.myChart) {
            this.myChart.setOption(_chartOption);
            this.myChart.resize();
        }
    }

    /**
     * 处理图表参数
     *
     * @memberof ChartControlBase
     */
    public handleChartOPtion() {
        let _chartOption: any = JSON.parse(JSON.stringify(this.chartOption));
        if (Object.keys(this.seriesModel).length > 0) {
            let tempDataSourceMap: Map<string, any> = new Map();
            for (let i = 0; i < Object.keys(this.seriesModel).length; i++) {
                Object.values(this.seriesModel).forEach((seriesvalue: any) => {
                    if (seriesvalue.seriesIndex === i) {
                        tempDataSourceMap.set(seriesvalue.name, seriesvalue.data);
                    }
                });
            }
            if (tempDataSourceMap.size > 0) {
                tempDataSourceMap.forEach((item: any) => {
                    _chartOption.dataset.push({ source: item });
                });
            }
            Object.keys(this.seriesModel).forEach((seriesName: string) => {
                if (_chartOption && _chartOption.series.length > 0) {
                    _chartOption.series.forEach((item: any) => {
                        if (this.seriesModel[seriesName].ecxObject && Object.is(seriesName, item.id)) {
                            item = Util.deepObjectMerge(item, this.seriesModel[seriesName].ecxObject);
                        }
                        if (
                            this.seriesModel[seriesName].baseOption &&
                            Object.keys(this.seriesModel[seriesName].baseOption).length > 0 &&
                            Object.is(seriesName, item.id)
                        ) {
                            item = Util.deepObjectMerge(item, this.seriesModel[seriesName].baseOption);
                        }
                        if (this.seriesModel[seriesName].ecObject && Object.is(seriesName, item.id)) {
                            item = Util.deepObjectMerge(item, this.seriesModel[seriesName].ecObject);
                        }
                    });
                }
                //设置多序列
                let tempSeries: any = this.seriesModel[seriesName];
                // 非雷达图
                if (
                    tempSeries &&
                    tempSeries.seriesIdField &&
                    tempSeries.seriesValues.length > 0 &&
                    !Object.is(tempSeries.type, 'radar')
                ) {
                    const returnIndex: number = _chartOption.series.findIndex((item: any) => {
                        return Object.is(item.id, seriesName);
                    });
                    _chartOption.series.splice(returnIndex, 1);
                    let tempSeriesArray: Array<any> = [];
                    tempSeries.seriesValues.forEach((seriesvalueItem: any) => {
                        let tempSeriesTemp: any = JSON.parse(JSON.stringify(tempSeries.seriesTemp));
                        Object.assign(tempSeriesTemp, {
                            name: tempSeries.seriesMap[seriesvalueItem],
                            datasetIndex: tempSeries.seriesIndex,
                            encode: { x: tempSeries.categorField, y: `${seriesvalueItem}` },
                        });
                        _chartOption.series.push(tempSeriesTemp);
                    });
                }
            });
        }
        if (Object.keys(this.chartBaseOPtion).length > 0) {
            Object.assign(_chartOption, this.chartBaseOPtion);
        }
        if (Object.keys(this.chartUserParams).length > 0) {
            Object.assign(_chartOption, this.chartUserParams);
        }
        return _chartOption;
    }

    /**
     * 实体数据集转化为图表数据集
     *
     * 1.获取图表所有代码表值
     * 2.查询集合映射图表数据集
     * 3.补全图表数据集
     * 4.图表数据集分组求和
     * 5.排序图表数据集
     *
     * @param {*} data 实体数据集
     * @param {Function} callback 回调
     * @memberof ChartControlBase
     */
    public async transformToBasicChartSetData(data: any, callback: Function) {
        if (!data || !Array.isArray(data) || data.length === 0) {
            this.isNoData = true;
            if(this.myChart){
                this.myChart.dispose()
                this.myChart = undefined;
            }
            return;
        }
        this.isNoData = false;
        //获取代码表值
        let allCodeList: any = await this.getChartAllCodeList();
        if (Object.values(this.seriesModel).length > 0) {
            Object.values(this.seriesModel).forEach((singleSeries: any, index: number) => {
                // 值属性为srfcount设置{srfcount:1}到data
                let valueField = singleSeries.dataSetFields.find((datasetField: any) => {
                    return datasetField.name === singleSeries.valueField;
                });
                if (valueField && valueField.name && Object.is(valueField.name, 'srfcount')) {
                    data.forEach((singleData: any) => {
                        Object.assign(singleData, { srfcount: 1 });
                    });
                }
                // 分组属性
                let groupField = singleSeries.dataSetFields.find((datasetField: any) => {
                    return datasetField.name === singleSeries.categorField;
                });
                let tempChartSetData: Array<any> = [];
                let tempSeriesValues: Map<string, any> = new Map();
                data.forEach((item: any) => {
                    let tempChartSetDataItem: any = {};
                    // 序列属性不存在
                    if (!singleSeries.seriesIdField) {
                        Object.assign(tempChartSetDataItem, { name: singleSeries.name });
                        if (singleSeries.dataSetFields && singleSeries.dataSetFields.length > 0) {
                            singleSeries.dataSetFields.forEach((singleDataSetField: any) => {
                                this.handleSingleDataSetField(
                                    item,
                                    singleDataSetField,
                                    allCodeList,
                                    tempChartSetDataItem,
                                    groupField,
                                );
                            });
                        }
                    } else {
                        // 序列属性存在时
                        // 序列代码表存在时,翻译tempSeriesValues的键值对
                        if (singleSeries.seriesCodeList) {
                            const seriesCodeList: Map<string, any> = allCodeList.get(singleSeries.seriesCodeList.tag);
                            let tempSeriesValueItem = tempSeriesValues.get(
                                seriesCodeList.get(item[singleSeries.seriesIdField]),
                            );
                            if (!tempSeriesValueItem) {
                                tempSeriesValues.set(
                                    seriesCodeList.get(item[singleSeries.seriesIdField]),
                                    seriesCodeList.get(item[singleSeries.seriesIdField]),
                                );
                            }
                        } else {
                            let tempSeriesValueItem = tempSeriesValues.get(item[singleSeries.seriesIdField]);
                            if (!tempSeriesValueItem) {
                                tempSeriesValues.set(
                                    item[singleSeries.seriesIdField],
                                    item[singleSeries.seriesNameField],
                                );
                            }
                        }
                        Object.assign(tempChartSetDataItem, { name: item[singleSeries.seriesIdField] });
                        if (singleSeries.dataSetFields && singleSeries.dataSetFields.length > 0) {
                            singleSeries.dataSetFields.forEach((singleDataSetField: any) => {
                                this.handleSingleDataSetField(
                                    item,
                                    singleDataSetField,
                                    allCodeList,
                                    tempChartSetDataItem,
                                    groupField,
                                );
                            });
                        }
                    }
                    tempChartSetData.push(tempChartSetDataItem);
                });
                // 补全数据集合
                this.completeDataSet(tempChartSetData, singleSeries, allCodeList);
                // 序列代码表存在时,补全序列
                if (singleSeries.seriesCodeList) {
                    const seriesCodeList: Map<string, any> = allCodeList.get(singleSeries.seriesCodeList.tag);
                    tempSeriesValues = new Map();
                    seriesCodeList.forEach((item: any) => {
                        tempSeriesValues.set(item, item);
                    });
                }
                singleSeries.seriesValues = [...tempSeriesValues.keys()];
                let tempSeriesMapObj: any = {};
                tempSeriesValues.forEach((value: any, key: any) => {
                    tempSeriesMapObj[key] = value;
                });
                singleSeries.seriesMap = tempSeriesMapObj;
                let callbackFunction: any = index === Object.values(this.seriesModel).length - 1 ? callback : null;

                this.transformToChartSeriesDataSet(tempChartSetData, singleSeries, callbackFunction, allCodeList);
            });
        }
    }

    /**
     * 构建图表序列数据集合
     *
     * 1.分组求和
     * 2.排序求和数组
     *
     * @param {Array<any>} data 传入数据
     * @param {Array<any>} item 单个序列
     * @param {Array<any>} callback 回调
     * @param {*} allCodeList 所有代码表
     *
     * @memberof ChartControlBase
     */
    public transformToChartSeriesDataSet(data: any, item: any, callback: Function, allCodeList: any): any {
        if (item.seriesIdField) {
            // 多序列
            let groupField = item.dataSetFields.filter((datasetField: any) => {
                return datasetField.name === item.categorField;
            });
            let tempGroupField: Array<any> = groupField.map((item: any) => {
                return item.name;
            });
            let seriesField = item.dataSetFields.filter((datasetField: any) => {
                return datasetField.name === item.seriesIdField;
            });
            let tempSeriesField: Array<any> = seriesField.map((item: any) => {
                return item.name;
            });
            let valueField = item.dataSetFields.filter((datasetField: any) => {
                return datasetField.name === item.valueField;
            });
            let tempValueField: Array<any> = valueField.map((item: any) => {
                return item.name;
            });
            item.data = this.groupAndAdd(
                tempGroupField,
                tempSeriesField,
                tempValueField,
                data,
                item,
                groupField,
                allCodeList,
            );
        } else {
            //单序列
            let groupField = item.dataSetFields.filter((datasetField: any) => {
                return datasetField.name === item.categorField;
            });
            let tempGroupField: Array<any> = groupField.map((item: any) => {
                return item.name;
            });
            let valueField = item.dataSetFields.filter((datasetField: any) => {
                return datasetField.name === item.valueField;
            });
            let tempValueField: Array<any> = valueField.map((item: any) => {
                return item.name;
            });
            item.data = this.groupAndAdd(tempGroupField, [], tempValueField, data, item, groupField, allCodeList);
        }
        if (callback && callback instanceof Function) {
            callback(allCodeList);
        }
    }

    /**
     * 分组和求和
     * @param {Array<any>} groupField 分组属性
     * @param {Array<any>} groupField 值属性
     * @param {Array<any>} data 传入数据
     * @param {*} groupFieldModel 分组属性模型
     * @param {*} allCodeList 所有代码表
     *
     * @memberof ChartControlBase
     */
    public groupAndAdd(
        groupField: Array<any>,
        seriesField: Array<any>,
        valueField: Array<any>,
        data: any,
        item: any,
        groupFieldModel: any,
        allCodeList: any,
    ) {
        let tempMap: Map<string, any> = new Map();
        let groupMode: string = groupFieldModel[0].groupMode;
        let groupKeyStr: string = '';
        data.forEach((item: any) => {
            let tempGroupField: string = groupField[0];
            groupKeyStr = item[tempGroupField];
            let tempMapItem: any = tempMap.get(groupKeyStr);
            if (tempMapItem) {
                tempMapItem.push(item);
                tempMap.set(groupKeyStr, tempMapItem);
            } else {
                tempMap.set(groupKeyStr, [item]);
            }
        });
        // 处理多序列
        if (seriesField.length > 0 && tempMap.size > 0) {
            let tempSeriesField: string = seriesField[0];
            tempMap.forEach((item: any, key: string) => {
                let tempItemMap: Map<string, any> = new Map();
                item.forEach((singleItem: any) => {
                    let seriesValueArray: any = tempItemMap.get(singleItem[tempSeriesField]);
                    if (seriesValueArray) {
                        seriesValueArray.push(singleItem);
                        tempItemMap.set(singleItem[tempSeriesField], seriesValueArray);
                    } else {
                        tempItemMap.set(singleItem[tempSeriesField], [singleItem]);
                    }
                });
                tempMap.set(key, tempItemMap);
            });
        }
        let returnArray: Array<any> = [];
        if (seriesField.length == 0) {
            //单序列
            tempMap.forEach((tempItem: any) => {
                if (tempItem.length > 0) {
                    let curObject: any = {};
                    let valueResult: number = 0;
                    let categorResult: any;
                    tempItem.forEach((singleItem: any) => {
                        categorResult = singleItem[groupField[0]];
                        valueResult += singleItem[valueField[0]];
                    });
                    Object.defineProperty(curObject, groupField[0], {
                        value: categorResult,
                        writable: true,
                        enumerable: true,
                        configurable: true,
                    });
                    Object.defineProperty(curObject, valueField[0], {
                        value: valueResult,
                        writable: true,
                        enumerable: true,
                        configurable: true,
                    });
                    returnArray.push(curObject);
                }
            });
        } else {
            // 多序列
            let seriesValuesArray: Array<any> = item.seriesValues;
            tempMap.forEach((groupItem: any, groupKey: string) => {
                //求和
                let curObject: any = {};
                Object.defineProperty(curObject, groupField[0], {
                    value: groupKey,
                    writable: true,
                    enumerable: true,
                    configurable: true,
                });
                seriesValuesArray.forEach((seriesValueItem: any) => {
                    Object.defineProperty(curObject, seriesValueItem, {
                        value: 0,
                        writable: true,
                        enumerable: true,
                        configurable: true,
                    });
                });
                groupItem.forEach((seriesItem: any, seriesKey: string) => {
                    let seriesNum: number = 0;
                    seriesItem.forEach((dataItem: any) => {
                        seriesNum += dataItem[valueField[0]];
                    });
                    curObject[seriesKey] = seriesNum;
                });
                returnArray.push(curObject);
            });
        }
        // 补全空白分类
        if (returnArray.length > 0) {
            let emptyText =
                groupFieldModel[0] && groupFieldModel[0].codeList ? groupFieldModel[0].codeList.emptytext : '未定义';
            returnArray.forEach((item: any) => {
                if (!item[groupField[0]]) {
                    item[groupField[0]] = emptyText;
                }
            });
        }
        returnArray = this.sortReturnArray(returnArray, groupFieldModel, allCodeList);
        // 雷达图数据格式处理
        if (Object.is(item.type, 'radar') && returnArray.length > 0) {
            let tempReturnArray: Array<any> = [];
            let seriesValues: Array<any> = item.seriesValues;
            if (seriesValues && seriesValues.length > 0) {
                seriesValues.forEach((singleSeriesName: any) => {
                    let singleSeriesObj: any = {};
                    returnArray.forEach((item: any) => {
                        Object.assign(singleSeriesObj, { [item[groupField[0]]]: item[singleSeriesName] });
                    });
                    Object.assign(singleSeriesObj, { type: singleSeriesName });
                    tempReturnArray.push(singleSeriesObj);
                });
            }
            returnArray = tempReturnArray;
        }
        return returnArray;
    }

    /**
     * 排序数组
     *
     * @param {Array<any>} arr 传入数组
     * @param {*} groupField 分组属性
     * @param {*} allCodeList 所有代码表
     *
     * @memberof ChartControlBase
     */
    public sortReturnArray(arr: Array<any>, groupField: any, allCodeList: any) {
        let returnArray: Array<any> = [];
        // todo
        // 分组属性有代码表的情况(最后执行)
        if (groupField[0].codelist) {
            let curCodeList: Map<number, any> = allCodeList.get(groupField[0].codelist.tag);
            curCodeList.forEach((codelist: any) => {
                arr.forEach((item: any) => {
                    if (Object.is(item[groupField[0].name], codelist)) {
                        returnArray.push(item);
                        item.hasused = true;
                    }
                });
            });
            arr.forEach((item: any, index: number) => {
                if (!item.hasused) {
                    returnArray.push(item);
                }
            });
            returnArray.forEach((item: any) => {
                delete item.hasused;
            });
        } else {
            // 分组为年份
            if (Object.is(groupField[0].groupMode, 'YEAR')) {
                returnArray = arr.sort((a: any, b: any) => {
                    return Number(a[groupField[0].name]) - Number(b[groupField[0].name]);
                });
            } else if (Object.is(groupField[0].groupMode, 'QUARTER')) {
                returnArray = this.handleSortGroupData(arr, groupField, '季度' as string);
            } else if (Object.is(groupField[0].groupMode, 'MONTH')) {
                returnArray = this.handleSortGroupData(arr, groupField, '月' as string);
            } else if (Object.is(groupField[0].groupMode, 'YEARWEEK')) {
                returnArray = this.handleSortGroupData(arr, groupField, '周' as string);
            } else if (Object.is(groupField[0].groupMode, 'DAY')) {
                returnArray = arr.sort((a: any, b: any) => {
                    return moment(a[groupField[0].name]).unix() - moment(b[groupField[0].name]).unix();
                });
            } else {
                let groupFieldName: string = groupField[0].name;
                let isConvert: boolean = true;
                arr.forEach((item: any) => {
                    if (isNaN(item[groupFieldName])) {
                        isConvert = false;
                    }
                });
                if (isConvert) {
                    returnArray = arr.sort((a: any, b: any) => {
                        return a[groupFieldName] - b[groupFieldName];
                    });
                } else {
                    returnArray = arr;
                }
            }
        }
        return returnArray;
    }

    /**
     * 排序分组模式下的数据
     *
     * @param {Array<any>} arr 传入数据
     * @param {Array<any>} groupField 分组属性
     * @param {Array<any>} label label标签
     *
     * @memberof ChartControlBase
     */
    public handleSortGroupData(arr: Array<any>, groupField: any, label: string) {
        arr.forEach((item: any) => {
            let sortFieldValue: Array<any> = item[groupField[0].name].split('-');
            Object.assign(item, { sortField: Number(sortFieldValue[0]) * 10000 + Number(sortFieldValue[1]) });
            /**
             *  @judgment 分组为月份时，月份+1  start
             *  @author mos
             *  @date   2020.07.20
             */
            if (Object.is(label, '月')) {
                item[groupField[0].name] =
                    sortFieldValue[0] + ('年' as string) + (Number(sortFieldValue[1]) + 1) + label;
            } else {
                item[groupField[0].name] = sortFieldValue[0] + ('年' as string) + sortFieldValue[1] + label;
            }
            //  @judgment 分组为月份时，月份+1  end
        });
        arr.sort((a: any, b: any) => {
            return Number(a.sortField) - Number(b.sortField);
        });
        arr.forEach((item: any) => {
            delete item.sortField;
        });
        return arr;
    }

    /**
     * 补全数据集
     *
     * @param {Array<any>} data 传入数据
     * @param {Array<any>} item 单个序列
     * @param {Array<any>} allCodeList 所有的代码表
     *
     * @memberof ChartControlBase
     */
    public completeDataSet(data: any, item: any, allCodeList: any) {
        // 分组属性
        let groupField = item.dataSetFields.find((datasetField: any) => {
            return datasetField.name === item.categorField;
        });
        if (Object.is(groupField.groupMode, '')) {
            return;
        }
        //分组模式为代码表（补值）
        if (Object.is(groupField.groupMode, 'CODELIST')) {
            this.completeCodeList(data, item, allCodeList);
        }
        //分组模式为年/季度/月份（最大值，最小值，分组，补值）
        if (
            Object.is(groupField.groupMode, 'YEAR') ||
            Object.is(groupField.groupMode, 'QUARTER') ||
            Object.is(groupField.groupMode, 'MONTH') ||
            Object.is(groupField.groupMode, 'YEARWEEK') ||
            Object.is(groupField.groupMode, 'DAY')
        ) {
            this.handleTimeData(data, item, allCodeList, groupField);
        }
    }

    /**
     * 获取最大值最小值
     *
     * @param {Array<any>} tempTimeArray 传入数据
     *
     * @memberof ChartControlBase
     */
    public getRangeData(tempTimeArray: Array<any>) {
        tempTimeArray.forEach((item: any) => {
            let tempParams: Array<any> = item._i.split('-');
            item.sortField = Number(tempParams[0] + tempParams[1]);
        });
        tempTimeArray.sort((a: any, b: any) => {
            return Number(a.sortField) - Number(b.sortField);
        });
        tempTimeArray.forEach((item: any) => {
            delete item.sortField;
        });
        return tempTimeArray;
    }

    /**
     * 补全时间类型数据集
     *
     * @param {Array<any>} data 传入数据
     * @param {Array<any>} item 单个序列
     * @param {Array<any>} allCodeList 所有的代码表
     * @param {Array<any>} groupField 分组属性
     *
     * @memberof ChartControlBase
     */
    public handleTimeData(data: any, item: any, allCodeList: any, groupField: any) {
        let valueField = item.dataSetFields.find((datasetField: any) => {
            return datasetField.name === item.valueField;
        });
        let groupMode: string = groupField.groupMode;
        // 排序数据，找到最大值、最小值
        let tempTimeArray: Array<any> = [];
        if (data && data.length > 0) {
            data.forEach((dataItem: any) => {
                // 判断时间类型是否为空，为空不处理
                if (dataItem[groupField.name]) {
                    tempTimeArray.push(moment(dataItem[groupField.name]));
                }
            });
        }
        let maxTime: any;
        let minTime: any;
        if (Object.is(groupMode, 'YEAR') || Object.is(groupMode, 'DAY')) {
            maxTime = moment.max(tempTimeArray);
            minTime = moment.min(tempTimeArray);
        }
        if (Object.is(groupMode, 'QUARTER')) {
            tempTimeArray = this.getRangeData(tempTimeArray);
            minTime = moment()
                .year(tempTimeArray[0]._i.split('-')[0])
                .quarters(tempTimeArray[0]._i.split('-')[1]);
            maxTime = moment()
                .year(tempTimeArray[tempTimeArray.length - 1]._i.split('-')[0])
                .quarters(tempTimeArray[tempTimeArray.length - 1]._i.split('-')[1]);
        }
        if (Object.is(groupMode, 'MONTH')) {
            tempTimeArray = this.getRangeData(tempTimeArray);
            minTime = moment()
                .year(tempTimeArray[0]._i.split('-')[0])
                .month(tempTimeArray[0]._i.split('-')[1]);
            maxTime = moment()
                .year(tempTimeArray[tempTimeArray.length - 1]._i.split('-')[0])
                .month(tempTimeArray[tempTimeArray.length - 1]._i.split('-')[1]);
        }
        if (Object.is(groupMode, 'YEARWEEK')) {
            tempTimeArray = this.getRangeData(tempTimeArray);
            minTime = moment()
                .year(tempTimeArray[0]._i.split('-')[0])
                .week(tempTimeArray[0]._i.split('-')[1]);
            maxTime = moment()
                .year(tempTimeArray[tempTimeArray.length - 1]._i.split('-')[0])
                .week(tempTimeArray[tempTimeArray.length - 1]._i.split('-')[1]);
        }
        let timeFragmentArray: Array<any> = [];
        let tempGrounpData: Map<string, any> = new Map();
        // 时间分段
        //groupMode为"YEAR"
        if (Object.is(groupMode, 'YEAR')) {
            let curTime: any = minTime;
            while (curTime) {
                if (curTime.isSameOrBefore(maxTime)) {
                    let tempcurTime: any = curTime.clone();
                    timeFragmentArray.push(tempcurTime.year().toString());
                    curTime = tempcurTime.clone().add(1, 'years');
                } else {
                    curTime = null;
                }
            }
        }
        //groupMode为"QUARTER"
        if (Object.is(groupMode, 'QUARTER')) {
            let curTime: any = minTime;
            while (curTime) {
                if (curTime.isSameOrBefore(maxTime)) {
                    let tempcurTime: any = curTime.clone();
                    timeFragmentArray.push(tempcurTime.year().toString() + '-' + tempcurTime.quarter().toString());
                    curTime = tempcurTime.clone().add(1, 'quarters');
                } else {
                    curTime = null;
                }
            }
        }
        //groupMode为"MONTH"
        if (Object.is(groupMode, 'MONTH')) {
            let curTime: any = minTime;
            while (curTime) {
                if (curTime.isSameOrBefore(maxTime)) {
                    let tempcurTime: any = curTime.clone();
                    timeFragmentArray.push(tempcurTime.year().toString() + '-' + tempcurTime.month().toString());
                    curTime = tempcurTime.clone().add(1, 'months');
                } else {
                    curTime = null;
                }
            }
        }
        //groupMode为"YEARWEEK"
        if (Object.is(groupMode, 'YEARWEEK')) {
            let curTime: any = minTime;
            while (curTime) {
                if (curTime.isSameOrBefore(maxTime)) {
                    let tempcurTime: any = curTime.clone();
                    timeFragmentArray.push(tempcurTime.year().toString() + '-' + tempcurTime.week().toString());
                    curTime = tempcurTime.clone().add(1, 'weeks');
                } else {
                    curTime = null;
                }
            }
        }
        //groupMode为"DAY"
        if (Object.is(groupMode, 'DAY')) {
            let curTime: any = minTime;
            while (curTime) {
                if (curTime.isSameOrBefore(maxTime)) {
                    let tempcurTime: any = curTime.clone();
                    timeFragmentArray.push(tempcurTime.format('YYYY-MM-DD'));
                    curTime = tempcurTime.clone().add(1, 'days');
                } else {
                    curTime = null;
                }
            }
        }
        data.forEach((item: any) => {
            let tempKeyStr: string = item[groupField.name];
            let tempGrounpItem: any = tempGrounpData.get(tempKeyStr);
            if (!tempGrounpItem) {
                tempGrounpData.set(tempKeyStr, item);
            }
        });
        timeFragmentArray.forEach((timeFragment: any) => {
            if (!tempGrounpData.get(timeFragment)) {
                let copyTemp: any = JSON.parse(JSON.stringify(data[0]));
                let curObj: any = {};
                curObj[groupField.name] = timeFragment;
                curObj[valueField.name] = 0;
                Object.assign(copyTemp, curObj);
                data.push(copyTemp);
            }
        });
    }

    /**
     * 补全代码表
     *
     * @param {Array<any>} data 传入数据
     * @param {Array<any>} item 单个序列
     * @param {Array<any>} allCodeList 所有的代码表
     *
     * @memberof ChartControlBase
     */
    public completeCodeList(data: any, item: any, allCodeList: any) {
        let groupField = item.dataSetFields.find((datasetField: any) => {
            return datasetField.name === item.categorField;
        });
        if (!groupField.codelist) {
            return;
        }
        let valueField = item.dataSetFields.find((datasetField: any) => {
            return datasetField.name === item.valueField;
        });
        let curCodeList: Map<number, any> = allCodeList.get(groupField.codelist.tag);
        // 对分类实现分组
        let tempGrounpData: Map<string, any> = new Map();
        data.forEach((item: any) => {
            let tempGrounpItem: any = tempGrounpData.get(item[groupField.name + '_srfvalue']);
            if (!tempGrounpItem) {
                tempGrounpData.set(item[groupField.name + '_srfvalue'], item);
            }
        });
        if (curCodeList.size !== tempGrounpData.size) {
            curCodeList.forEach((text: any, value: any) => {
                if (!tempGrounpData.get(value)) {
                    let copyTemp: any = JSON.parse(JSON.stringify(data[0]));
                    let curObj: any = {};
                    curObj[groupField.name + '_srfvalue'] = value;
                    curObj[groupField.name] = text;
                    curObj[valueField.name] = 0;
                    Object.assign(copyTemp, curObj);
                    data.push(copyTemp);
                }
            });
        }
    }

    /**
     * 处理单个属性
     *
     * @param {*} input 输入值
     * @param {*} field 属性值
     * @param {*} allCodeList 所有代码表
     * @param {*} result 结果值
     *
     * @memberof ChartControlBase
     */
    public handleSingleDataSetField(input: any, field: any, allCodeList: any, result: any, groupField: any) {
        let tempFieldObj: any = {};
        //存在代码表的情况(自动转化值)
        if (field.codelist) {
            //获取代码表
            let curCodeList: Map<number, any> = allCodeList.get(field.codelist.tag);
            tempFieldObj[field.name] = curCodeList.get(input[field.name]);
            tempFieldObj[field.name + '_srfvalue'] = input[field.name];
        } else {
            // 不存在代码表的情况
            if (groupField && Object.is(groupField.name, field.name)) {
                if (Object.is(groupField.groupMode, 'YEAR')) {
                    tempFieldObj[field.name] = moment(input[field.name])
                        .year()
                        .toString();
                } else if (Object.is(groupField.groupMode, 'QUARTER')) {
                    tempFieldObj[field.name] =
                        moment(input[field.name])
                            .year()
                            .toString() +
                        '-' +
                        moment(input[field.name])
                            .quarters()
                            .toString();
                } else if (Object.is(groupField.groupMode, 'MONTH')) {
                    tempFieldObj[field.name] =
                        moment(input[field.name])
                            .year()
                            .toString() +
                        '-' +
                        moment(input[field.name])
                            .month()
                            .toString();
                } else if (Object.is(groupField.groupMode, 'YEARWEEK')) {
                    tempFieldObj[field.name] =
                        moment(input[field.name])
                            .year()
                            .toString() +
                        '-' +
                        moment(input[field.name])
                            .week()
                            .toString();
                } else if (Object.is(groupField.groupMode, 'DAY')) {
                    tempFieldObj[field.name] = moment(input[field.name]).format('YYYY-MM-DD');
                } else {
                    tempFieldObj[field.name] = input[field.name];
                }
            } else {
                tempFieldObj[field.name] = input[field.name];
            }
        }
        Object.assign(result, tempFieldObj);
    }

    /**
     * 获取图表所需代码表
     *
     * @memberof ChartControlBase
     */
    public getChartAllCodeList(): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            let codeListMap: Map<string, any> = new Map();
            if (Object.values(this.seriesModel).length > 0) {
                let tempFlag: boolean = true;
                Object.values(this.seriesModel).forEach((singleSeries: any) => {
                    if (singleSeries.dataSetFields && singleSeries.dataSetFields.length > 0) {
                        let promiseArray: Array<any> = [];
                        let promiseKeyArray: Array<any> = [];
                        singleSeries.dataSetFields.forEach((singleDataSetField: any, index: any) => {
                            if (singleDataSetField.codelist) {
                                tempFlag = false;
                                if (!codeListMap.get(singleDataSetField.codelist.tag)) {
                                    promiseArray.push(this.getCodeList(singleDataSetField.codelist));
                                    promiseKeyArray.push(singleDataSetField.codelist.tag);
                                    Promise.all(promiseArray).then((result: any) => {
                                        if (result && result.length > 0) {
                                            result.forEach((codeList: any) => {
                                                let tempCodeListMap: Map<number, any> = new Map();
                                                if (codeList.length > 0) {
                                                    codeList.forEach((codeListItem: any) => {
                                                        tempCodeListMap.set(codeListItem.value, codeListItem.text);
                                                    });
                                                }
                                                codeListMap.set(singleDataSetField.codelist.tag, tempCodeListMap);
                                            });
                                            resolve(codeListMap);
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
                if (tempFlag) {
                    resolve(codeListMap);
                }
            } else {
                resolve(codeListMap);
            }
        });
    }

    /**
     * 获取代码表
     *
     * @returns {Promise<any>}
     * @memberof ChartControlBase
     */
    public getCodeList(codeListObject: any): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            if (codeListObject.codeName && Object.is(codeListObject.codeListType, 'STATIC')) {
                this.codeListService
                    .getStaticItems(codeListObject.codeName)
                    .then((res: any) => {
                        resolve(res);
                    })
                    .catch((error: any) => {
                        LogUtil.log(`----${codeListObject.codeName}----代码表不存在`);
                    });
            } else if (codeListObject.codeName && Object.is(codeListObject.codeListType, 'DYNAMIC')) {
                this.codeListService
                    .getItems(codeListObject.codeName)
                    .then((res: any) => {
                        resolve(res);
                    })
                    .catch((error: any) => {
                        LogUtil.log(`----${codeListObject.codeName}----代码表不存在`);
                    });
            }
        });
    }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof AppDefaultChart
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        this.service = new AppChartService(this.controlInstance);
        await this.service.loaded(this.controlInstance);
        this.initChartParams();
    }

    /**
     * 执行created后的逻辑
     *
     * @memberof ChartControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(
                ({ tag, action, data }: { tag: string; action: string; data: any }) => {
                    if (!Object.is(tag, this.name)) {
                        return;
                    }
                    if (Object.is('load', action)) {
                        this.load(data);
                    }
                    if (Object.is('refresh', action)) {
                        this.refresh(data);
                    }
                },
            );
        }
    }

    /**
     * 初始化chart参数
     *
     * @memberof ChartControlBase
     */
    public initChartParams() {
        this.initSeriesModel();
        this.initChartOption();
        this.initChartUserParams();
        this.initChartBaseOPtion();
    }
}
