import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IBugStats, BugStats } from '../../entities';
import keys from '../../entities/bug-stats/bug-stats-keys';

/**
 * Bug统计服务对象基类
 *
 * @export
 * @class BugStatsBaseService
 * @extends {EntityBaseService}
 */
export class BugStatsBaseService extends EntityBaseService<IBugStats> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'BugStats';
    protected APPDENAMEPLURAL = 'BugStats';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/BugStats.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    newEntity(data: IBugStats): BugStats {
        return new BugStats(data);
    }

    async addLocal(context: IContext, entity: IBugStats): Promise<IBugStats | null> {
        return this.cache.add(context, new BugStats(entity) as any);
    }

    async createLocal(context: IContext, entity: IBugStats): Promise<IBugStats | null> {
        return super.createLocal(context, new BugStats(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IBugStats> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IBugStats): Promise<IBugStats> {
        return super.updateLocal(context, new BugStats(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IBugStats = {}): Promise<IBugStats> {
        return new BugStats(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
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
     * @memberof BugStatsService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/bugstats`, _data);
        return res;
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/bugstats/${_context.bugstats}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/bugstats/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.delete(`/bugstats/${_context.bugstats}`);
        return res;
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.put(`/bugstats/${_context.bugstats}`, _data);
        return res;
    }
    /**
     * FetchBugCountInResolution
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async FetchBugCountInResolution(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/bugstats/fetchbugcountinresolution`, _data);
        return res;
    }
    /**
     * FetchBugResolvedBy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async FetchBugResolvedBy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/bugstats/fetchbugresolvedby`, _data);
        return res;
    }
    /**
     * FetchBugResolvedGird
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async FetchBugResolvedGird(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/bugstats/fetchbugresolvedgird`, _data);
        return res;
    }
    /**
     * FetchBugassignedTo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async FetchBugassignedTo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/bugstats/fetchbugassignedto`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/bugstats/fetchdefault`, _data);
        return res;
    }
    /**
     * FetchProductBugResolutionStats
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async FetchProductBugResolutionStats(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/bugstats/fetchproductbugresolutionstats`, _data);
        return res;
    }
    /**
     * FetchProductBugStatusSum
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async FetchProductBugStatusSum(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/bugstats/fetchproductbugstatussum`, _data);
        return res;
    }
    /**
     * FetchProductCreateBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async FetchProductCreateBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/bugstats/fetchproductcreatebug`, _data);
        return res;
    }
    /**
     * FetchProjectBugStatusCount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async FetchProjectBugStatusCount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/bugstats/fetchprojectbugstatuscount`, _data);
        return res;
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BugStatsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/bugstats/${_context.bugstats}/select`);
        return res;
    }
}
