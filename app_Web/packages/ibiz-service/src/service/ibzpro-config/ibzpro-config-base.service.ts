import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzproConfig, IbzproConfig } from '../../entities';
import keys from '../../entities/ibzpro-config/ibzpro-config-keys';

/**
 * 系统配置表服务对象基类
 *
 * @export
 * @class IbzproConfigBaseService
 * @extends {EntityBaseService}
 */
export class IbzproConfigBaseService extends EntityBaseService<IIbzproConfig> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzproConfig';
    protected APPDENAMEPLURAL = 'IbzproConfigs';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzproConfig.json';
    protected APPDEKEY = 'ibzproconfigid';
    protected APPDETEXT = 'ibzproconfigname';
    protected quickSearchFields = ['ibzproconfigname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzproConfig): IbzproConfig {
        return new IbzproConfig(data);
    }

    async addLocal(context: IContext, entity: IIbzproConfig): Promise<IIbzproConfig | null> {
        return this.cache.add(context, new IbzproConfig(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzproConfig): Promise<IIbzproConfig | null> {
        return super.createLocal(context, new IbzproConfig(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzproConfig> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzproConfig): Promise<IIbzproConfig> {
        return super.updateLocal(context, new IbzproConfig(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzproConfig = {}): Promise<IIbzproConfig> {
        return new IbzproConfig(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproConfigService
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
     * @memberof IbzproConfigService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/ibzproconfigs`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
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
     * @memberof IbzproConfigService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/ibzproconfigs/${_context.ibzproconfig}`);
        res.data = await this.afterExecuteAction(_context,res?.data);
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
     * @memberof IbzproConfigService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzproconfigs/getdraft`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * GetSystemConfig
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzproConfigService
     */
    async GetSystemConfig(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.put(`/ibzproconfigs/${_context.ibzproconfig}/getsystemconfig`, _data);
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
     * @memberof IbzproConfigService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.delete(`/ibzproconfigs/${_context.ibzproconfig}`);
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
     * @memberof IbzproConfigService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
        const res = await this.http.put(`/ibzproconfigs/${_context.ibzproconfig}`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
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
     * @memberof IbzproConfigService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/ibzproconfigs/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
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
     * @memberof IbzproConfigService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/ibzproconfigs/${_context.ibzproconfig}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }

    /**
     * GetSystemConfigBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzproConfigServiceBase
     */
    public async GetSystemConfigBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/ibzproconfigs/getsystemconfigbatch`,_data);
        return res;
    }
}
