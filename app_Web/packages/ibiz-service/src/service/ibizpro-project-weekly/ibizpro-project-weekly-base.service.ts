import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbizproProjectWeekly, IbizproProjectWeekly } from '../../entities';
import keys from '../../entities/ibizpro-project-weekly/ibizpro-project-weekly-keys';

/**
 * 项目周报服务对象基类
 *
 * @export
 * @class IbizproProjectWeeklyBaseService
 * @extends {EntityBaseService}
 */
export class IbizproProjectWeeklyBaseService extends EntityBaseService<IIbizproProjectWeekly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbizproProjectWeekly';
    protected APPDENAMEPLURAL = 'IbizproProjectWeeklies';
    protected APPDEKEY = 'projectweeklyid';
    protected APPDETEXT = 'projectweeklyname';
    protected quickSearchFields = ['projectweeklyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbizproProjectWeekly): IbizproProjectWeekly {
        return new IbizproProjectWeekly(data);
    }

    async addLocal(context: IContext, entity: IIbizproProjectWeekly): Promise<IIbizproProjectWeekly | null> {
        return this.cache.add(context, new IbizproProjectWeekly(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbizproProjectWeekly): Promise<IIbizproProjectWeekly | null> {
        return super.createLocal(context, new IbizproProjectWeekly(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbizproProjectWeekly> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbizproProjectWeekly): Promise<IIbizproProjectWeekly> {
        return super.updateLocal(context, new IbizproProjectWeekly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbizproProjectWeekly = {}): Promise<IIbizproProjectWeekly> {
        return new IbizproProjectWeekly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectWeeklyService
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
     * @memberof IbizproProjectWeeklyService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibizproprojectweeklies/${_context.ibizproprojectweekly}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectWeeklyService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibizproprojectweeklies`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectWeeklyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibizproprojectweeklies/${_context.ibizproprojectweekly}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectWeeklyService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibizproprojectweeklies/${_context.ibizproprojectweekly}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectWeeklyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibizproprojectweeklies/${_context.ibizproprojectweekly}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectWeeklyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibizproprojectweeklies/getdraft`, _data);
        return res;
    }
    /**
     * PushSumProjectWeekly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectWeeklyService
     */
    async PushSumProjectWeekly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproprojectweeklies/${_context.ibizproprojectweekly}/pushsumprojectweekly`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbizproProjectWeeklyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibizproprojectweeklies/fetchdefault`, _data);
    }
}