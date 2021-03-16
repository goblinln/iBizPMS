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
    protected APPNAME = 'Web';
    protected APPDENAME = 'DynaDashboard';
    protected APPDENAMEPLURAL = 'DynaDashboards';
    protected APPDEKEY = 'dynadashboardid';
    protected APPDETEXT = 'dynadashboardname';
    protected quickSearchFields = ['dynadashboardname',];
    protected selectContextParam = {
    };

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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof DynaDashboardService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/dynadashboards/${_context.dynadashboard}/select`);
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
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/dynadashboards`, _data);
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
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/dynadashboards/${_context.dynadashboard}`, _data);
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
        return this.http.delete(`/dynadashboards/${_context.dynadashboard}`);
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
        const res = await this.http.get(`/dynadashboards/${_context.dynadashboard}`);
        return res;
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
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/dynadashboards/getdraft`, _data);
        return res;
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
        return this.http.get(`/dynadashboards/fetchdefault`, _data);
    }
}
