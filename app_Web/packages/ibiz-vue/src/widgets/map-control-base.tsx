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
     * 省份区域数据
     * 
     * @memberof MapControlBase
     */
    public areaData: any = [];

    /**
     * 区域样式图数据
     * 
     * @memberof MapControlBase
     */
    public regionData: any = [];

    /**
     * 省份区域值集合
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
        title: {
            text: '',
            left: 'center',
            top: 20,
        },
        tooltip: {
            trigger: 'item',
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            y: 'center',
            data: [],
        },
        geo: {
            map: 'china',
            zoom: 1.2,
            label: {
                show: true,
            },
            itemStyle: {
                areaColor: '#e0ffff',
            },
            emphasis: {
                itemStyle: {
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
                    const select: any[] = [params.fromActionPayload.custom];
                    _this.onClick(select);
                }
            });
            // 监听地图触发的点击事件
            this.map.on('click', 'series', function (params: any) {
                if (Object.is(params.seriesType, 'scatter') && params.data?.value) {
                    const select: any[] = [params.data.value[3]];
                    _this.onClick(select);
                } else if ((Object.is(params.seriesType, 'custom') || Object.is(params.seriesType, 'lines')) && params.data?.coords) {
                    const selects: any [] = [];
                    params.data.coords.forEach((coord: any) => {
                        selects.push(coord[3])
                    })
                    _this.onClick(selects);
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
        this.ctrlEvent({ controlname: this.name, action: "beforeload", data: parentData });
        Object.assign(data, parentData);
        let tempViewParams: any = parentData.viewparams ? parentData.viewparams : {};
        if (this.viewparams) {
            Object.assign(tempViewParams, Util.deepCopy(this.viewparams));
        }
        for (let item in this.mapItems) {
            let sort = '';
            if (this.mapItems[item].sort) {
                sort += this.mapItems[item].sort + ',';
            }
            if (sort) {
                sort = sort + 'desc';
                Object.assign(tempViewParams, { sort: sort });
            }
        }
        Object.assign(data, { viewparams: tempViewParams });
        let tempContext: any =  Util.deepCopy(this.context);
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
                this.handleMapOptions();
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
        const options = Util.deepCopy(this.initOptions);
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
        };
        this.updateSize();
    }

    /**
     * 配置整合
     *
     * @return {*} 
     * @memberof MapControlBase
     */
    public handleMapOptions() {
        let series: Array<any> = this.initOptions.series;
        if (!series || series.length == 0) {
            return;
        }
        series.forEach((serie: any) => {
            const seriesData: Array<any> = []
            this.items.forEach((item: any) => {
                if (Object.is(item.itemType, serie.itemType)) {
                    seriesData.push(item);
                }
            })
            this.handleSeriesOptions(seriesData, serie);
        });
        Object.assign(this.initOptions.series, series);
    }

    /**
     * 整合序列数据
     *
     * @param {*} seriesData 序列数据
     * @param {*} serie 序列
     * @memberof MapControlBase
     */
    public handleSeriesOptions(seriesData: Array<any>, serie: any) {
        serie.id = serie.itemType;
        if (Object.is(serie.type, 'scatter')) {
            // 点样式数据处理
            seriesData.forEach((data: any) => {
                const tempItem = [
                    parseFloat(data.longitude), parseFloat(data.latitude), data.content, data
                ];
                const _data = {
                    name: data.title,
                    value: tempItem,
                };
                serie.data.push(_data);
            });
        } else if (Object.is(serie.type, 'lines') || Object.is(serie.type, 'custom')) {
            const groupDatas: Array<any> = [];
            // 获取对应分组的数据集合
            const getGroupItems = (groupName: any) => {
                const items: Array<any> = [];
                if (groupName) {
                    seriesData.forEach((data: any) => {
                        if (Object.is(data.group, groupName)) {
                            items.push(data);
                        }
                    });
                };
                return items;
            }
            // 分组
            if (this.mapItems[serie.itemType].group) {
                seriesData.forEach((data: any) => {
                    if (data.group) {
                        const group = groupDatas.find((groupData: any) => Object.is(groupData.group, data.group));
                        if (!group) {
                            groupDatas.push({
                                group: data.group,
                                items: getGroupItems(data.group),
                            });
                        };
                    };
                });
            } else {
                groupDatas.push({
                    group: serie.name,
                    items: seriesData,
                });
            }
            if (Object.is(serie.type, 'custom')) {
                // 区域图数据处理
                this.regionData = [];
            }
            groupDatas.forEach((groupData: any) => {
                if (this.mapItems[serie.itemType].sort) {
                    groupData.items.sort((a: any, b: any) => {
                        const x: any = a.sort;
                        const y: any = b.sort;
                        return x > y ? -1 : x < y? 1 : 0;
                    })
                };
                const coords: Array<any> = [];
                groupData.items.forEach((item: any) => {
                    const coord: any[] = [
                        parseFloat(item.longitude), parseFloat(item.latitude), item.content, item
                    ];
                    coords.push(coord);
                });
                serie.data.push({
                    name: groupData.group,
                    coords: coords,
                });
            });
            if (Object.is(serie.type, 'custom')) {
                // 区域图数据处理
                this.regionData = [...serie.data];
            }
        }
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
                Object.assign(this.mapItems, {
                    [item.itemType?.toLowerCase()]: {
                        bkcolor: item.bKColor,
                        color: item.color,
                        content: item.getContentPSAppDEField()?.codeName.toLowerCase(),
                        latitude: item.getLatitudePSAppDEField()?.codeName.toLowerCase(),
                        longitude: item.getLongitudePSAppDEField()?.codeName.toLowerCase(),
                        text: item.getTextPSAppDEField()?.codeName.toLowerCase(),
                        tips: item.getTipsPSAppDEField()?.codeName.toLowerCase(),
                        group: item.getGroupPSAppDEField()?.codeName.toLowerCase(),
                        sort: item.getOrderValuePSAppDEField()?.codeName.toLowerCase(),
                        code: index
                    }
                });
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
                if (Object.is('POINT', item.itemStyle)) {
                    this.initOptions.series.push(
                        {
                            name: item.name,
                            type: 'scatter',
                            coordinateSystem: 'geo',
                            itemType: item.itemType?.toLowerCase(),
                            color: item.color,
                            selectedMode: 'single',
                            select: {
                                itemStyle: {
                                    borderColor: item.color,
                                    opacity: 1,
                                    borderWidth: 6,
                                },
                            },
                            itemStyle: {
                                opacity: 0.7,
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
                                value: 2,
                            },
                            tooltip: {},
                            data: [],
                        }
                    );
                } else if (Object.is('LINE', item.itemStyle)) {
                    this.initOptions.series.push(
                        {
                            name: item.name,
                            type: 'lines',
                            geoIndex: 0,
                            itemType: item.itemType?.toLowerCase(),
                            coordinateSystem: 'geo',
                            polyline: true,
                            tooltip:{
                                show: true,
                                formatter: (arg: any) => this.renderTooltip(arg),
                            },
                            lineStyle: {
                                color: item.color,
                                opacity: 1,
                                width: 3,
                            },
                            data: [],
                        }
                    )
                } else if (Object.is('REGION', item.itemStyle)) {
                    this.initOptions.series.push(
                        {
                            name: item.name,
                            type: 'custom',
                            geoIndex: 0,
                            itemType: item.itemType?.toLowerCase(),
                            coordinateSystem: 'geo',
                            renderItem: (params: any, api: any) => this.renderRegion(params, api),
                            selectedMode: 'single',
                            itemStyle: {
                                color: item.color,
                                opacity: 0.5,
                            },
                            tooltip:{
                                formatter: (arg: any) => this.renderTooltip(arg),
                            },
                            data: [],
                        }
                    )
                }
            });
        }
    }

    /**
     * 绘制悬浮提示
     * 
     * @param arg 
     */
    public renderTooltip(arg: any) {
        const curData: any = arg.data;
        const items: any[] = curData.coords;
        let content: any = '';
        if (Object.is("lines" ,arg.seriesType)) {
            content = items[0][2] + '-' + items[items.length - 1][2];
        } else {
            let total: number = 0;
            items.forEach((item: any) => {
                total += item[2];
            });
            content = total;
        }
        const interval: any = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
        const tooltip = arg.name + '<br />' + arg.marker + curData.name + interval + content;
        return tooltip;
    }

    /**
     * 绘制区域图
     * 
     * @param params 参数
     * @param api 方法集合
     * @returns 
     */
    public renderRegion(params: any, api: any) {
        const children: any[] = [];
        const color = api.visual('color');
        this.regionData.forEach((data: any) => {
            let points: any[] = [];
            data.coords.forEach((value: any) => {
                points.push(api.coord(value));
            });
            const child: any ={
                type: 'polygon',
                shape: {
                    points: points,
                },
                style: api.style({
                    fill: color,
                }),
                select: {
                    style: {
                        opacity: 1,
                        fill: color,
                    },
                },
            }
            children.push(child);
        });
        return {
            type: 'group',
            children: children,
        };
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
     * 计算省份区域数据
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
     * 计算省份区域数据值(项类容属性)
     * 
     * @param name 省份区域名
     * @returns 省份区域值
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
        this.initOptions.title.text = this.controlInstance?.logicName;
        this.initOptions.series[0].data = this.getAreaValueList();
        this.initOptions.visualMap[0].max = this.valueMax > 0 ? this.valueMax : 1000;
    }

    /**
     * 地图点击事件
     * 
     * @param $event 选中数据
     * @memberof MapControlBase
     */
    public onClick($event: any[]){
        this.selections = $event;
        if($event && (Object.keys($event).length > 0)){
            this.$emit("ctrl-event", {controlname: this.controlInstance.name, action: "selectionchange", data: this.selections});
        }
    }
}
