import { IPSDEWizardPanel } from '@ibiz/dynamic-model-api';
import { ControlServiceBase } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppWizardPanelModel } from '../ctrl-model';

export class AppWizardPanelService extends ControlServiceBase {

    /**
    * 表格实例对象
    *
    * @memberof AppGridService
    */
   public controlInstance !: IPSDEWizardPanel;

   /**
    * 数据服务对象
    *
    * @type {any}
    * @memberof AppGridService
    */
   public appEntityService!: any;

    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppGridService
     */
    public async initServiceParam() {
        this.appEntityService = await new GlobalService().getService(this.appDeCodeName, this.context);
        this.model = new AppWizardPanelModel(this.controlInstance);
    }

    /**
     * Creates an instance of AppGridService.
     * 
     * @param {*} [opts={}]
     * @memberof AppGridService
     */
    constructor(opts: any = {}, context?: any) {
        super(opts, context);
        this.controlInstance = opts;
    }

    public async loaded() {
        await this.initServiceParam()
    }
    
    /**
     * 初始化向导
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ${srfclassname('${ctrl.codeName}')}Service
     */
    public init(action: string, context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        const {data:Data,context:Context} = this.handleRequestData(action,context,data);
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context,Data, isloading);
            } else {
                result = this.appEntityService.Create(Context,Data, isloading);
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
     * 向导结束
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ${srfclassname('${ctrl.codeName}')}Service
     */
    public finish(action: string, context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        const {data:Data,context:Context} = this.handleRequestData(action,context,data);
        return new Promise((resolve: any, reject: any) => {
            let result: Promise<any>;
            const _appEntityService: any = this.appEntityService;
            // 忽略版本检查
            Object.assign(Data,{ignoreversioncheck:1});
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context,Data, isloading);
            } else {
                result = this.appEntityService.Update(Context,Data, isloading);
            }
            result.then((response) => {
                this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }
}