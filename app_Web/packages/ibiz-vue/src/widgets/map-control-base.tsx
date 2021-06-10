import { MDControlBase } from "./md-control-base";
import { AppMapService } from '../ctrl-service';
import { IPSSysMap, IPSSysMapItem } from '@ibiz/dynamic-model-api';
import { init } from 'echarts';
import '../components/control/app-default-map/china.js'
import { MapControlInterface } from "ibiz-core";
/**
 * 地图部件基类
 *
 * @export
 * @class MapControlBase
 * @extends {MDControlBase}
 */
export class MapControlBase extends MDControlBase implements MapControlInterface{

    /**
     * 地图的模型对象
     *
     * @type {*}
     * @memberof MapControlBase
     */
    public controlInstance!: IPSSysMap;

    /**
     * 地图对象
     *
     * @type {*}
     * @memberof MapControlBase
     */
    public map: any;

    /**
     * 地图服务对象
     *
     * @type {*}
     * @memberof MapControlBase
     */
    public service !: any;


    /**
      * 地图数据
      * 
      * @type {*}
      * @memberof MapControlBase
      */
    public items: Array<any> = [];

    /**
     * 地图数据项模型
     *
     * @type {}
     * @memberof MapControlBase
     */
    public mapItems: any = {};

    /**
     * 初始化配置
     *
     * @type {}
     * @memberof MapControlBase
     */
    public initOptions: any = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'horizontal',
            x: 'center',
            data: []
        },
        geo: {
            map: 'china',
            zoom: 1.2,
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    areaColor: '#4FADFD',
                    borderColor: '#111'
                },
                emphasis: {
                    areaColor: '#0CD3DB'
                }
            }
        },
        visualMap: [],
        series: []
    }

    /**
     * 默认排序方向
     *
     * @readonly
     * @memberof MapControlBase
     */
    public minorSortDir: any = '';

     /**
      * 默认排序应用实体属性
      *
      * @readonly
      * @memberof MapControlBase
      */
    public minorSortPSDEF: any = '';

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof MapControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSelectFirstDefault = newVal.isSelectFirstDefault;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
      * 部件模型数据初始化实例
      *
      * @memberof MapControlBase
      */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppMapService(this.controlInstance);
            await this.service.loaded(this.controlInstance);
            this.initMapModel();
        }
    }

    /**
     * 初始化
     *
     * @memberof MapControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit();
        // 绑定this
        this.transformData = this.transformData.bind(this);
        this.refresh = this.refresh.bind(this);
        // 初始化默认值
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: { tag: string, action: string, data: any }) => {
                if (!Object.is(this.name, tag)) {
                    return;
                }
                if (Object.is(action, 'load')) {
                    this.load(data);
                }
            });
        }
    }

    /** 
     * 部件挂载完毕
     *
     * @protected
     * @memberof MapControlBase
     */
    public ctrlMounted(): void {
        super.ctrlMounted();
        let map: any = (this.$refs.map as any);
        if (map) {
            this.map = init(map);
        }
        this.map.setOption(this.initOptions);
    }

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof MapControlBase
     */
    public refresh(args?: any) {
        this.load(args);
    }

    /**
     * 地图数据加载
     *
     * @param {*} [data={}] 额外参数
     * @returns {void}
     * @memberof MapControlBase
     */
    public load(data: any = {}): void {
        if (!this.fetchAction) {
            this.$throw(this.$t('app.map.notconfig.fetchaction'), 'load');
            return;
        }
        this.items = [];
        const parentData: any = {};
        this.$emit('beforeload', parentData);
        Object.assign(data, parentData);
        let tempViewParams: any = parentData.viewparams ? parentData.viewparams : {};
        if (this.viewparams) {
            Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        }
        Object.assign(data, { viewparams: tempViewParams });
        let tempContext: any = JSON.parse(JSON.stringify(this.context));
        this.onControlRequset('load', tempContext, data);
        const _this: any = this;
        this.service.search(this.fetchAction, this.context, data).then((response: any) => {
            _this.onControlResponse('load', response);
            if (!response || response.status !== 200) {
                this.$throw(response, 'load');
            }
            this.$emit('load', response.data ? response.data : []);
            this.items = response.data;
            console.log(this.items);
            
            this.handleOptions(response.data);
            this.setOptions();
        },
            (response: any) => {
                _this.onControlResponse('load', response);
                this.$throw(response, 'load');
            });

    }

    /**
     * 设置配置
     *
     * @memberof MapControlBase
     */
    public setOptions() {
        if (!this.map) {
            return;
        }
        const options = JSON.parse(JSON.stringify(this.initOptions));
        this.map.setOption(options);
    }

    /**
     * 处理数据集
     *
     * @param {any[]} items 数据集合
     * @return {*} 
     * @memberof MapControlBase
     */
    public handleOptions(items: any[]) {
        if (!items || items.length == 0) {
            return;
        }
        items.forEach((item: any) => {
            let longitudeArr: Array<any> = [];
            let latitudeArr: Array<any> = [];
            for (let key in this.mapItems) {
                if (Object.is(key, item.itemType)) {
                    item.longitude ? longitudeArr.push(item.longitude) : '';
                    item.latitude ? latitudeArr.push(item.latitude) : '';
                }
            }
            this.handleMapOptions(longitudeArr, latitudeArr, item);
        })
    }

    /**
     * 配置整合
     *
     * @param {Array<any>} longitude 经度
     * @param {Array<any>} latitude 纬度
     * @param {*} arg 数据
     * @return {*} 
     * @memberof MapControlBase
     */
    public handleMapOptions(longitude: Array<any>, latitude: Array<any>, arg: any) {
        let series: Array<any> = this.initOptions.series;
        if (!series || series.length == 0) {
            return;
        }
        series.forEach((item: any) => {
            if (Object.is(arg.itemType, item.itemType)) {
                longitude.forEach((jd: any, index: number) => {
                    let tempItem: any[] = [];
                    tempItem.push(parseFloat(jd));
                    tempItem.push(parseFloat(latitude[index]));
                    this.handleSeriesOptions(tempItem, item, arg);
                    item.data.push(tempItem);
                })
            }
        });
        Object.assign(this.initOptions.series, series);
    }

    /**
     * 数据整合
     *
     * @param {*} tempItem 临时序列
     * @param {*} item 序列
     * @param {*} data 数据
     * @memberof MapControlBase
     */
    public handleSeriesOptions(tempItem: any, item: any, data: any) {
        //  序列
        tempItem.push(item.seriesDataIndex);
    }

    /**
     * 初始化地图参数
     */
    public initMapModel() {
        const mapItems: IPSSysMapItem[] | null = this.controlInstance.getPSSysMapItems();
        if (mapItems) {
            mapItems.forEach((item: IPSSysMapItem, index: number) => {
                this.initOptions.legend.data.push(item.name);
                this.initOptions.visualMap.push(
                    {
                        type: 'piecewise',
                        left: 'left',
                        top: 'bottom',
                        splitNumber: 1,
                        seriesIndex: index,
                        pieces: [{
                            label: item.name,
                            min: index * 10,
                            max: (index + 1) * 10,
                            color: item.color,
                            backgroundColor: item.bKColor,
                            borderColor: item.borderColor,
                            borderWidth: item.borderWidth
                        }],
                        show: false
                    }
                );
                this.initOptions.series.push(
                    {
                        name: item.name,
                        //  目前支持 POINT
                        type: item.itemStyle == 'POINT' ? 'scatter' : 'scatter',
                        coordinateSystem: 'geo',
                        itemType: item.itemType?.toLowerCase(),
                        color: item.color,
                        geoIndex: 0,
                        symbolSize: 14,
                        label: {
                            show: false
                        },
                        emphasis: {
                            label: {
                                show: false
                            }
                        },
                        tooltip: {
                            formatter: '{a}'
                        },
                        seriesDataIndex: (index + 1) * 10 - 5,
                        data: []
                    }
                );
                Object.assign(this.mapItems, {
                    [item.itemType?.toLowerCase()]: {
                        bkcolor: item.bKColor,
                        color: item.color,
                        content: item.getContentPSAppDEField()?.codeName.toLowerCase(),
                        latitude: item.getLatitudePSAppDEField()?.codeName.toLowerCase(),
                        longitude: item.getLongitudePSAppDEField()?.codeName.toLowerCase(),
                        text: item.getTextPSAppDEField()?.codeName.toLowerCase(),
                        tips: item.getTipsPSAppDEField()?.codeName.toLowerCase(),
                        code: index
                    }
                })
            });
        }
    }

    /**
     * 获取选中数据
     *
     * @returns {any[]}
     * @memberof GridControlBase
     */
     public getSelection(): any[] {
        return this.selections;
    }
}
