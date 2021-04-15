import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IAccountTaskestimate, AccountTaskestimate } from '../../entities';
import keys from '../../entities/account-taskestimate/account-taskestimate-keys';

/**
 * 用户工时统计服务对象基类
 *
 * @export
 * @class AccountTaskestimateBaseService
 * @extends {EntityBaseService}
 */
export class AccountTaskestimateBaseService extends EntityBaseService<IAccountTaskestimate> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'AccountTaskestimate';
    protected APPDENAMEPLURAL = 'AccountTaskestimates';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'date';
    protected quickSearchFields = ['date',];
    protected selectContextParam = {
    };

    newEntity(data: IAccountTaskestimate): AccountTaskestimate {
        return new AccountTaskestimate(data);
    }

    async addLocal(context: IContext, entity: IAccountTaskestimate): Promise<IAccountTaskestimate | null> {
        return this.cache.add(context, new AccountTaskestimate(entity) as any);
    }

    async createLocal(context: IContext, entity: IAccountTaskestimate): Promise<IAccountTaskestimate | null> {
        return super.createLocal(context, new AccountTaskestimate(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IAccountTaskestimate> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IAccountTaskestimate): Promise<IAccountTaskestimate> {
        return super.updateLocal(context, new AccountTaskestimate(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IAccountTaskestimate = {}): Promise<IAccountTaskestimate> {
        return new AccountTaskestimate(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTaskestimateService
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
     * @memberof AccountTaskestimateService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/accounttaskestimates/${_context.accounttaskestimate}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTaskestimateService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/accounttaskestimates`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTaskestimateService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/accounttaskestimates/${_context.accounttaskestimate}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTaskestimateService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/accounttaskestimates/${_context.accounttaskestimate}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTaskestimateService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/accounttaskestimates/${_context.accounttaskestimate}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTaskestimateService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/accounttaskestimates/getdraft`, _data);
        return res;
    }
    /**
     * FetchAllAccountEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTaskestimateService
     */
    async FetchAllAccountEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/accounttaskestimates/fetchallaccountestimate`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof AccountTaskestimateService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/accounttaskestimates/fetchdefault`, _data);
    }
}