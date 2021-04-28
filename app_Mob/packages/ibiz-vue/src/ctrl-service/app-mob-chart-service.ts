import { ControlServiceBase } from 'ibiz-core';
import { IPSDEChart } from '@ibiz/dynamic-model-api';
import { Errorlog } from '../decorators';
import { AppMobChartModel } from '../ctrl-model/app-mob-chart-model';
import { GlobalService } from 'ibiz-service';


/**
 * 图表部件服务对象
 *
 * @export
 * @class AppMobChartService
 */
export class AppMobChartService extends ControlServiceBase {


    /**
    * 图表实例对象
    *
    * @memberof AppMobChartService
    */
    public controlInstance !: IPSDEChart;

    /**
     * 数据服务对象
     *
     * @type {any}
     * @memberof AppMobChartService
     */
    public appEntityService!: any;

    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppMobChartService
     */
    public async initServiceParam(opts: any) {
        this.controlInstance = opts;
        this.model = new AppMobChartModel();
        if (this.appDeCodeName) {
            this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
        }
    }

    /**
     * Creates an instance of AppMobChartService.
     * 
     * @param {*} [opts={}]
     * @memberof AppMobChartService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 加载服务参数
     *
     * @type {boolean}
     * @memberof AppMobChartService
     */
    public async loaded(opt: any) {
        await this.initServiceParam(opt);
    }

    /**
     * 查询数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppMobChartService
     */
    @Errorlog
    public search(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestData(action, context, data, true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data, isloading);
            } else {
                result = _appEntityService.FetchDefault(Context, Data, isloading);
            }
            result.then((response) => {
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }
}