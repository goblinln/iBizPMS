import { IPSDEMultiEditViewPanel } from '@ibiz/dynamic-model-api';
import { ControlServiceBase } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppMobMEditviewPanelModel, Errorlog } from 'ibiz-vue';


/**
 * Main 部件服务对象
 *
 * @export
 * @class AppmultieditviewpanelService
 */
export class AppMobMEditViewPanelService extends ControlServiceBase {

    /**
    * 多编辑视图实例对象
    *
    * @memberof AppMEditViewPanelService
    */
    public controlInstance !: IPSDEMultiEditViewPanel;

    /**
     * 数据服务对象
     *
     * @type {any}
     * @memberof AppMEditViewPanelService
     */
    public appEntityService!: any;

    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppMEditViewPanelService
     */
    public async initServiceParam(opts: any) {
        this.controlInstance = opts;
        if (this.appDeCodeName) {
            this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
        }
        this.model = new AppMobMEditviewPanelModel(opts);
    }

    /**
     * Creates an instance of AppMEditViewPanelService
     * 
     * @param {*} [opts={}]
     * @memberof AppMEditViewPanelService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 查询数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppMEditViewPanelService
     */
    @Errorlog
    public get(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestData(action, context, data);
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data, isloading);
            } else {
                result = this.appEntityService.Get(Context, Data, isloading);
            }
            result.then((response) => {
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }

    /**
     * 加载草稿
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppMEditViewPanelService
     */
    @Errorlog
    public loadDraft(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestData(action, context, data);
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data, isloading);
            } else {
                result = this.appEntityService.GetDraft(Context, Data, isloading);
            }
            result.then((response) => {
                //处理返回数据，补充判断标识
                if (response.data) {
                    Object.assign(response.data, { srfuf: 0 });
                }
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }
}