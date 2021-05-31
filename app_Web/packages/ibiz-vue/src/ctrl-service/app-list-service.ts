import { IPSDEList } from '@ibiz/dynamic-model-api';
import { ControlServiceBase } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppListModel } from 'ibiz-vue';

/**
 * Main 部件服务对象
 *
 * @export
 * @class AppListService
 */
export class AppListService extends ControlServiceBase {

    /**
    * 表单实例对象
    *
    * @memberof AppListService
    */
    public controlInstance !: IPSDEList;

    /**
     * 数据服务对象
     *
     * @type {any}
     * @memberof AppListService
     */
    public appEntityService!: any;




    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppListService
     */
    public async initServiceParam(opts: any) {
        this.controlInstance = opts;
        this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
        this.model = new AppListModel(opts);
    }

    /**
     * Creates an instance of AppListService.
     * 
     * @param {*} [opts={}]
     * @memberof AppListService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * async loaded
     */
    public async loaded(opts: any) {
        await this.initServiceParam(opts);
    }

    /**
     * 查询数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppListService
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
            result.then(async (response) => {
                await this.handleResponse(action, response);
                resolve(response);
            }).catch(response => {
                reject(response);
            });
        });
    }

    /**
     * 删除数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppListService
     */
    public delete(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestData(action, context, data, true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data, isloading);
            } else {
                result = _appEntityService.remove(Context, Data, isloading);
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
     * 添加数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppListService
     */
    public add(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestData(action, context, data, true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data, isloading);
            } else {
                result = _appEntityService.Create(Context, Data, isloading);
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
     * 修改数据
     *
     * @param {string} action
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof AppListService
     */
    public update(action: string, context: any = {}, data: any = {}, isloading?: boolean): Promise<any> {
        const { data: Data, context: Context } = this.handleRequestData(action, context, data, true);
        return new Promise((resolve: any, reject: any) => {
            const _appEntityService: any = this.appEntityService;
            let result: Promise<any>;
            if (_appEntityService[action] && _appEntityService[action] instanceof Function) {
                result = _appEntityService[action](Context, Data, isloading);
            } else {
                result = _appEntityService.Update(Context, Data, isloading);
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