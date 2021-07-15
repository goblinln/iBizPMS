import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ICaseStats, CaseStats } from '../../entities';
import keys from '../../entities/case-stats/case-stats-keys';

/**
 * 测试用例统计服务对象基类
 *
 * @export
 * @class CaseStatsBaseService
 * @extends {EntityBaseService}
 */
export class CaseStatsBaseService extends EntityBaseService<ICaseStats> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'CaseStats';
    protected APPDENAMEPLURAL = 'CaseStats';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/CaseStats.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    newEntity(data: ICaseStats): CaseStats {
        return new CaseStats(data);
    }

    async addLocal(context: IContext, entity: ICaseStats): Promise<ICaseStats | null> {
        return this.cache.add(context, new CaseStats(entity) as any);
    }

    async createLocal(context: IContext, entity: ICaseStats): Promise<ICaseStats | null> {
        return super.createLocal(context, new CaseStats(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ICaseStats> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ICaseStats): Promise<ICaseStats> {
        return super.updateLocal(context, new CaseStats(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ICaseStats = {}): Promise<ICaseStats> {
        return new CaseStats(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStatsService
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
     * @memberof CaseStatsService
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
        const res = await this.http.post(`/casestats`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStatsService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/casestats/${_context.casestats}`);
        res.data = await this.afterExecuteAction(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStatsService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/casestats/getdraft`, _data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStatsService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.delete(`/casestats/${_context.casestats}`);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStatsService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data);
        const res = await this.http.put(`/casestats/${_context.casestats}`, _data);
        res.data = await this.afterExecuteAction(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStatsService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/casestats/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * FetchTestCaseStats
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStatsService
     */
    async FetchTestCaseStats(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/casestats/fetchtestcasestats`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStatsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/casestats/${_context.casestats}/select`);
        return res;
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
}
