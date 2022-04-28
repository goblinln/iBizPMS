import { IPSDEChart } from '@ibiz/dynamic-model-api';
import { ControlServiceBase } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppChartModel } from 'ibiz-vue';


/**
 * 图表部件服务对象
 *
 * @export
 * @class AppChartService
 */
export class AppChartService extends ControlServiceBase {

    /**
    * 图表实例对象
    *
    * @memberof AppChartService
    */
    public controlInstance !: IPSDEChart;

    /**
     * 数据服务对象
     *
     * @type {any}
     * @memberof AppChartService
     */
    public appEntityService!: any;

    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppChartService
     */
    public async initServiceParam(opts: any) {
        this.controlInstance = opts;
        if (this.appDeCodeName) {
            this.appEntityService = await new GlobalService().getService(this.appDeCodeName, this.context);
        }
        this.model = new AppChartModel(opts);
    }

    /**
     * Creates an instance of AppChartService.
     * 
     * @param {*} [opts={}]
     * @memberof AppChartService
     */
    constructor(opts: any = {}, context?: any) {
        super(opts, context);

    }

    /**
     * 加载服务参数
     *
     * @type {boolean}
     * @memberof AppChartService
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
     * @memberof AppChartService
     */
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