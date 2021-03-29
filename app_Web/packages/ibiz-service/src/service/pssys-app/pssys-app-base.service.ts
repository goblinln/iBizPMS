import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IPSSysApp, PSSysApp } from '../../entities';
import keys from '../../entities/pssys-app/pssys-app-keys';

/**
 * 系统应用服务对象基类
 *
 * @export
 * @class PSSysAppBaseService
 * @extends {EntityBaseService}
 */
export class PSSysAppBaseService extends EntityBaseService<IPSSysApp> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'PSSysApp';
    protected APPDENAMEPLURAL = 'PSSysApps';
    protected APPDEKEY = 'pssysappid';
    protected APPDETEXT = 'pssysappname';
    protected quickSearchFields = ['pssysappname',];
    protected selectContextParam = {
    };

    newEntity(data: IPSSysApp): PSSysApp {
        return new PSSysApp(data);
    }

    async addLocal(context: IContext, entity: IPSSysApp): Promise<IPSSysApp | null> {
        return this.cache.add(context, new PSSysApp(entity) as any);
    }

    async createLocal(context: IContext, entity: IPSSysApp): Promise<IPSSysApp | null> {
        return super.createLocal(context, new PSSysApp(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IPSSysApp> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IPSSysApp): Promise<IPSSysApp> {
        return super.updateLocal(context, new PSSysApp(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IPSSysApp = {}): Promise<IPSSysApp> {
        return new PSSysApp(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysAppService
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
     * @memberof PSSysAppService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/pssysapps/${_context.pssysapp}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysAppService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/pssysapps`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysAppService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/pssysapps/${_context.pssysapp}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysAppService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/pssysapps/${_context.pssysapp}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysAppService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/pssysapps/${_context.pssysapp}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysAppService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/pssysapps/getdraft`, _data);
        return res;
    }
    /**
     * FetchBuild
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysAppService
     */
    async FetchBuild(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/pssysapps/fetchbuild`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSysAppService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/pssysapps/fetchdefault`, _data);
    }
}
