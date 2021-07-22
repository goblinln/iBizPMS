import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IDynaDashboard, DynaDashboard } from '../../entities';
import keys from '../../entities/dyna-dashboard/dyna-dashboard-keys';

/**
 * 动态数据看板服务对象基类
 *
 * @export
 * @class DynaDashboardBaseService
 * @extends {EntityBaseService}
 */
export class DynaDashboardBaseService extends EntityBaseService<IDynaDashboard> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'DynaDashboard';
    protected APPDENAMEPLURAL = 'DynaDashboards';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/DynaDashboard.json';
    protected APPDEKEY = 'dynadashboardid';
    protected APPDETEXT = 'dynadashboardname';
    protected quickSearchFields = ['dynadashboardname',];
    protected selectContextParam = {
    };

    newEntity(data: IDynaDashboard): DynaDashboard {
        return new DynaDashboard(data);
    }

    async addLocal(context: IContext, entity: IDynaDashboard): Promise<IDynaDashboard | null> {
        return this.cache.add(context, new DynaDashboard(entity) as any);
    }

    async createLocal(context: IContext, entity: IDynaDashboard): Promise<IDynaDashboard | null> {
        return super.createLocal(context, new DynaDashboard(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IDynaDashboard> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IDynaDashboard): Promise<IDynaDashboard> {
        return super.updateLocal(context, new DynaDashboard(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IDynaDashboard = {}): Promise<IDynaDashboard> {
        return new DynaDashboard(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaDashboardService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaDashboardService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/dynadashboards`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaDashboardService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/dynadashboards/${_context.dynadashboard}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaDashboardService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/dynadashboards/getdraft`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaDashboardService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.delete(`/dynadashboards/${_context.dynadashboard}`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaDashboardService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/dynadashboards/${_context.dynadashboard}`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaDashboardService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/dynadashboards/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaDashboardService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/dynadashboards/${_context.dynadashboard}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
