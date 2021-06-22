import { Vue, Component, Watch, Prop } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Util, ViewTool, ModelTool } from 'ibiz-core';
import { MDControlBase } from './md-control-base';
import { GlobalService } from 'ibiz-service';
import { AppMobMapService } from '../ctrl-service';
import { AppCenterService, AppViewLogicService } from '../app-service';
import { ViewOpenService } from '../app-service/common-service/view-open-service';
import { IPSMap, IPSSysMap, IPSSysMapItem } from '@ibiz/dynamic-model-api';
import echarts from 'echarts';
import 'echarts/map/js/china.js';

/**
 * 地图部件基类
 *
 * @export
 * @class MobMapControlBase
 * @extends {MDControlBase}
 */
export class MobMapControlBase extends MDControlBase{

    /**
     * 地图部件实例
     *
     * @returns {any[]}
     * @memberof MobMapControlBase
     */
    public controlInstance!: IPSMap

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof MobMapControlBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 应用状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof MobMapControlBase
     */
    public appStateEvent: Subscription | undefined;

    /**
    * 地图数据数组
    *
    * @param {Array<any>}
    * @memberof MobMapControlBase
    */
    public items:Array<any> =[];

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof MobMapControlBase
     */
    public getData(): any {
        return null;
    }

    /**
    * vue生命周期created
    *
    * @memberof MobMapControlBase
    */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof MobMapControlBase
     */    
    protected afterCreated(){
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }:any) => {
                if (!Object.is(this.name, tag)) {
                    return;
                }
                if (Object.is(action, "load")) {
                    this.load(Object.assign(data));
                }
                if (Object.is(action, "search")) {
                    this.load(Object.assign(data));
                }
                if (Object.is(action, "quicksearch")) {
                    this.load(Object.assign({ query: data }));
                }
                if (Object.is(action, "refresh")) {
                    this.refresh();
                }
            });
        }
        if(AppCenterService && AppCenterService.getMessageCenter()){
            this.appStateEvent = AppCenterService.getMessageCenter().subscribe(({ name, action, data }) =>{
                if(!Object.is(name,"MOBMAP")){
                    return;
                }
                if(Object.is(action,'appRefresh')){
                    this.refresh();
                }
            })
        }
    }

    /**
     * 部件模型数据初始化
     *
     * @memberof MobMapControlBase
     */
    public async ctrlModelInit(args?:any) {
        await super.ctrlModelInit(args);
        if (!(this.Environment?.isPreviewMode)) {
            this.service = new AppMobMapService(this.controlInstance);
            this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
            this.initMapModel();
        }
        this.mapStyle = this.controlInstance.mapStyle;
    }

    /**
     * 数据加载
     *
     * @private
     * @param {*} [data={}]
     * @param {string} [type=""]
     * @returns {Promise<any>}
     * @memberof MobMapControlBase
     */
    private async load(data: any = {}, type: string = "",isloadding = this.showBusyIndicator): Promise<any> {
        const parentdata: any = {};
        this.ctrlEvent({ controlname: this.controlInstance.name, action: 'beforeload', data: parentdata });
        Object.assign(data, parentdata);
        let tempViewParams:any = parentdata.viewparams?parentdata.viewparams:{};
        Object.assign(tempViewParams,JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(data,{viewparams:tempViewParams});
        const response: any = await this.service.search(this.fetchAction, this.context, data, isloadding);
        if (!response || response.status !== 200) {
            this.$notify({ type: 'danger', message: response.error.message });
            return response;
        }
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'load',
            data: (response.data && response.data) ? response.data : [],
        });
        this.items = [];
        this.items = response.data;
        this.handleOptions(response.data);
        this.setOptions();
        return response;
    }

    /**
     * 刷新数据
     *
     * @returns {Promise<any>}
     * @memberof MobMapControlBase
     */
    public refresh(): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            this.load().then((res) => {
                resolve(res);
            }).catch((error: any) => {
                reject(error);
            })
        })
    }

    /**
     * map对象
     *
     * @type {}
     * @memberof MobMapControlBase
     */   
    public map :any;

    /**
     * 图表div绑定的id
     *
     * @type {}
     * @memberof MobMapControlBase
     */   
    public mapId:string = this.$util.createUUID();

    /**
     * 地图样式
     *
     * @type {}
     * @memberof MobMapControlBase
     */   
    public mapStyle = '';

    /**
     * 地图数据项模型
     *
     * @type {}
     * @memberof MobMapControlBase
     */   
    public mapItems:any = {}

    /**
     * 初始化配置
     *
     * @type {}
     * @memberof MobMapControlBase
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
            roam: false,
            zoom: 1.23,
            label: {
                normal: {
                    show: true,
                    fontSize: '10',
                    // 字体颜色
                    color: 'rgba(0,0,0,0.7)',
                }
            },
            itemStyle: {
                normal: {
                    // 边框颜色
                    borderColor: 'rgba(0, 0, 0, 0.2)',
                    // 地图默认背景
                    areaColor: "#efefef",
    
                },
                emphasis: {
                    // 点击背景颜色
                    areaColor: '#F3B329',
                    shadowOffsetX: 0,
                    shadowOffsetY: 0,
                    shadowBlur: 20,
                    borderWidth: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)',
                }
            }
        },
        visualMap: [],
        series: []
    }

    /**
     * 处理数据集
     *
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
     * @memberof MapControlBase
     */
    public handleSeriesOptions(tempItem: any, item: any, data: any) {
        //  序列
        tempItem.push(item.seriesDataIndex);
    }

    /**
     * afterMounted
     *
     * @type {}
     * @memberof MobMapControlBase
     */ 
    public afterMounted(){
        let element: any = this.$refs[this.mapId];
        this.map = echarts.init(element);
        this.map.setOption(this.initOptions);
    }

    /**
     * 部件挂载
     *
     * @memberof MobMapControlBase
     */
    public ctrlMounted(){
        super.ctrlMounted();
        this.afterMounted();
    }
    
    /**
     * 绘制
     *
     * @type {}
     * @memberof MobMapControlBase
     */ 
    public setOptions(){
        if (!this.map) {
            return;
        }
        const options = JSON.parse(JSON.stringify(this.initOptions));
        this.map.setOption(options);
    }

    /**
     * 初始化地图参数
     */
    public initMapModel() {
        const mapItems: IPSSysMapItem[] | null = (this.controlInstance as IPSSysMap).getPSSysMapItems();
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


}