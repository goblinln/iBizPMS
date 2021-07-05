import { MDControlBase } from "./md-control-base";
import { AppMapService } from '../ctrl-service';
import { IPSSysMap, IPSSysMapItem } from '@ibiz/dynamic-model-api';
import { init } from 'echarts';
import '../components/control/app-default-map/china.js'
import { MapControlInterface, Util } from "ibiz-core";
import { AMapManager } from 'vue-amap';

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
    public service!: any;

    /**
     * 获取地址需求AMap插件对象
     *
     * @type {*}
     * @memberof MapControlBase
     */
    public geocoder: any;


    /**
     * 当前 window
     *
     * @type {*}
     * @memberof MapControlBase
     */
    public win: any;

    /**
     * AMap SDK对象
     *
     * @type {*}
     * @memberof MapControlBase
     */
    public amapManager: any = new AMapManager();

    /**
     * 地图信息缓存
     * 
     * @memberof MapControlBase
     */
    public addressCache: Map<string, any> = new Map(); 

    /**
     * 获取选中数据
     *
     * @returns {any[]}
     * @memberof GridControlBase
     */
     public getSelection(): any[] {
        return this.selections;
    }

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
     * 显示图例
     * 
     * @type {*}
     * @memberof MapControlBase
     */
    public showLegends: any[] = [];

    /**
     * 显示的最大值
     * 
     * @type {number}
     * @memberof MapControlBase
     */
    public valueMax: number = 0;

    /**
     * 区域数据
     * 
     *  @memberof MapControlBase
     */
    public areaData: any = [];

    /**
     * 区域值集合
     * 
     * @memberof MapControlBase
     */
    public getAreaValueList() {
        return [
            {name:'南海诸岛',value: this.calculateAreaValue('南海')},
            {name: '北京', value: this.calculateAreaValue('北京')},
            {name: '天津', value: this.calculateAreaValue('天津')},
            {name: '上海', value: this.calculateAreaValue('上海')},
            {name: '重庆', value: this.calculateAreaValue('重庆')},
            {name: '河北', value: this.calculateAreaValue('河北')},
            {name: '河南', value: this.calculateAreaValue('河南')},
            {name: '云南', value: this.calculateAreaValue('云南')},
            {name: '辽宁', value: this.calculateAreaValue('辽宁')},
            {name: '黑龙江', value: this.calculateAreaValue('黑龙江')},
            {name: '湖南', value: this.calculateAreaValue('湖南')},
            {name: '安徽', value: this.calculateAreaValue('安徽')},
            {name: '山东', value: this.calculateAreaValue('山东')},
            {name: '新疆', value: this.calculateAreaValue('新疆')},
            {name: '江苏', value: this.calculateAreaValue('江苏')},
            {name: '浙江', value: this.calculateAreaValue('浙江')},
            {name: '江西', value: this.calculateAreaValue('江西')},
            {name: '湖北', value: this.calculateAreaValue('湖北')},
            {name: '广西', value: this.calculateAreaValue('广西')},
            {name: '甘肃', value: this.calculateAreaValue('甘肃')},
            {name: '山西', value: this.calculateAreaValue('山西')},
            {name: '内蒙古', value: this.calculateAreaValue('内蒙古')},
            {name: '陕西', value: this.calculateAreaValue('陕西')},
            {name: '吉林', value: this.calculateAreaValue('吉林')},
            {name: '福建', value: this.calculateAreaValue('福建')},
            {name: '贵州', value: this.calculateAreaValue('贵州')},
            {name: '广东', value: this.calculateAreaValue('广东')},
            {name: '青海', value: this.calculateAreaValue('青海')},
            {name: '西藏', value: this.calculateAreaValue('西藏')},
            {name: '四川', value: this.calculateAreaValue('四川')},
            {name: '宁夏', value: this.calculateAreaValue('宁夏')},
            {name: '海南', value: this.calculateAreaValue('海南')},
            {name: '台湾', value: this.calculateAreaValue('台湾')},
            {name: '香港', value: this.calculateAreaValue('香港')},
            {name: '澳门', value: this.calculateAreaValue('澳门')},
        ]
    };

    /**
     * 初始化配置
     *
     * @type {}
     * @memberof MapControlBase
     */
    public initOptions: any = {
        tooltip: {
            trigger: 'item',
        },
        legend: {
            orient: 'horizontal',
            x: 'center',
            data: [],
        },
        geo: {
            map: 'china',
            zoom: 1.2,
            label: {
                normal: {
                    show: true,
                },
                emphasis: {},
            },
            itemStyle: {
                normal: {
                    areaColor: '#e0ffff',
                },
                emphasis: {
                    areaColor: '#F3B329',
                },
            },
        },
        visualMap: [
            {
                min: 0,
                max: 1000,
                left: 'left',
                seriesIndex: 0,
                top: 'bottom',
                text: ['高','低'],
                inRange: {
                    color: ['#e0ffff', '#006edd'],
                },
                show: true,
            },
        ],
        series: [
            {
                name: '信息量',
                type: 'map',
                geoIndex: 0,
                data: [],
            }
        ]
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
        this.isSelectFirstDefault = newVal.isSelectFirstDefault ? true : false;
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
        this.win = window as any;
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
        let _this: any = this;
        let map: any = (this.$refs.map as any);
        if (map) {
            this.map = init(map);
        }
        if (this.map) {
            this.map.setOption(this.initOptions);
            // 如果是导航视图默认选中第一项
            this.map.on('selectchanged', 'series', function (params: any) {
                if (params.fromActionPayload?.custom) {
                    _this.onClick(params.fromActionPayload.custom);
                }
            });
            // 监听地图触发的点击事件
            this.map.on('click', 'series', function (params: any) {
                if (params.data?.value?.length > 3) {
                    _this.onClick(params.data.value[3]);
                }
            });
            // 图例改变过滤地图数据
            this.map.on('legendselectchanged', function (params: any) {
                if (params.name) {
                    _this.showLegends.forEach((showLegend: any) => {
                        if (Object.is(showLegend.name, params.name)) {
                            showLegend.show = !showLegend.show;
                        }
                    })
                    // 重新设置地图数据
                    _this.valueMax = 0;
                    const data = _this.getAreaValueList();
                    _this.map.setOption({
                        visualMap: {
                            max: _this.valueMax > 0 ? _this.valueMax : 1000,
                        },
                        series: {
                            data: data,
                        }
                    })
                }
            });
        }
        let amap: any = this.win.AMap;
        amap.plugin(["AMap.Geocoder"], () => {
            this.geocoder = new amap.Geocoder({
                extensions: "all",
            })
        })
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
     * 更新大小
     * 
     * @memberof MapControlBase
     */
     public updateSize(){
        if (this.map) {
            this.map.resize();
        }
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
            this.calculateAreaData().then(() => {
                this.setAreaData();
                this.handleOptions(this.items);
                this.setOptions();
            });
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
        // 如果是导航视图默认选中第一项
        if (this.isSelectFirstDefault) {
            const select: any = this.items.find((item: any) => Object.is(item.itemType, this.showLegends[0]?.itemType));
            this.map.dispatchAction({
                type: 'select',
                seriesId: this.showLegends[0]?.itemType,
                dataIndex: 0,
                custom: select,
            });
        }
        this.updateSize();
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
                    const data = this.handleSeriesOptions(tempItem, item, arg);
                    item.id = item.itemType;
                    item.data.push(data);
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
        const _tempItem = [...tempItem];
        const title = data['title'];
        const content = data['content'];
        _tempItem.push(content);
        _tempItem.push(Util.deepCopy(data));
        const _data = {
            name: title,
            value: _tempItem,
        }
        return _data;
    }

    /**
     * 初始化地图参数
     * 
     * @memberof MapControlBase
     */
    public initMapModel() {
        const mapItems: IPSSysMapItem[] | null = this.controlInstance.getPSSysMapItems();
        this.showLegends = [];
        if (mapItems) {
            mapItems.forEach((item: IPSSysMapItem, index: number) => {
                this.showLegends.push(
                    {
                        name: item.name,
                        itemType: item.itemType?.toLowerCase(),
                        show: true,
                    }
                );
                if (Object.is('REGION', item.itemStyle)) {
                    return
                }
                this.initOptions.legend.data.push(item.name);
                this.initOptions.visualMap.push(
                    {
                        type: 'piecewise',
                        left: 'left',
                        top: 'bottom',
                        splitNumber: 1,
                        seriesIndex: index+1,
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
                        selectedMode: 'single',
                        select: {
                            itemStyle: {
                                borderColor: item.color,
                                borderWidth: 6,
                            },
                        },
                        geoIndex: 0,
                        symbolSize: 16,
                        label: {
                            show: true,
                            position: 'right',
                            formatter: '{b}',
                        },
                        emphasis: {},
                        encode: {
                            value: 2
                        },
                        tooltip: {},
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
                });
            });
        }
    }

    /**
     * 调用服务，根据经纬度获取地址信息
     * 
     * @param {*} lng 经度
     * @param {*} lat 纬度
     * @memberof MapControlBase
     */
     public getAddress(lng: any, lat: any) {
        return new Promise((resolve, reject) => {
            if (this.addressCache.get(lng+'-'+lat)) {
                const address = this.addressCache.get(lng+'-'+lat);
                resolve(address);
            }
            this.geocoder.getAddress([lng,lat],(status:any,result: any) => {
                if (status === 'complete' && result.info === 'OK') {
                    if (result && result.regeocode) {
                        const address = result.regeocode.addressComponent;
                        this.addressCache.set(lng+'-'+lat, address);
                        resolve(address);
                    }
                }
            })
        });
    }

    /**
     * 计算区域数据
     * 
     * @memberof MapControlBase
     */
    public async calculateAreaData() {
        this.areaData = [];
        if (this.items.length > 0) {
            for (let item of this.items) {
                const address: any = await this.getAddress(item.longitude, item.latitude);
                const provinceName = address.province;
                this.areaData.push({
                    name: provinceName,
                    itemType: item.itemType,
                    data: item,
                })
            }
        }
    }

    /**
     * 计算区域数据值(项类容属性)
     * 
     * @param name 区域名
     * @returns 区域值
     * @memberof MapControlBase
     */
    public calculateAreaValue(name: string) {
        const areaData: any = [];
        this.areaData.forEach((item: any) => {
            const showLegend = this.showLegends.find((_showLegend: any) => Object.is(_showLegend.itemType, item.itemType));
            if (item.name.indexOf(name) > -1 && showLegend?.show) {
                areaData.push(item.data);
            }
        })
        let value: number = 0;
        areaData.forEach((item: any) => {
            if (Number.isFinite(Number(item.content))) {
                value += item.content;
            } else {
                value += 1;
            }
        })
        this.valueMax = value > this.valueMax ? value : this.valueMax;
        return value;
    }

    /**
     * 设置地图数据(初始化)
     * 
     * @memberof MapControlBase
     */
    public setAreaData(){
        let series: Array<any> = this.initOptions.series;
        if (!series || series.length == 0) {
            return;
        }
        this.valueMax = 0;
        this.initOptions.series[0].data = this.getAreaValueList();
        this.initOptions.visualMap[0].max = this.valueMax > 0 ? this.valueMax : 1000;
    }

    /**
     * 地图点击事件
     * 
     * @param $event 选中数据
     * @memberof MapControlBase
     */
    public onClick($event: any){
        this.selections = [$event];
        if($event && (Object.keys($event).length > 0)){
            this.$emit("ctrl-event", {controlname: this.controlInstance.name, action: "selectionchange", data: this.selections});
        }
    }
}
