import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IUserYearWorkStats, UserYearWorkStats } from '../../entities';
import keys from '../../entities/user-year-work-stats/user-year-work-stats-keys';

/**
 * 用户年度工作内容统计服务对象基类
 *
 * @export
 * @class UserYearWorkStatsBaseService
 * @extends {EntityBaseService}
 */
export class UserYearWorkStatsBaseService extends EntityBaseService<IUserYearWorkStats> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'UserYearWorkStats';
    protected APPDENAMEPLURAL = 'UserYearWorkStats';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    newEntity(data: IUserYearWorkStats): UserYearWorkStats {
        return new UserYearWorkStats(data);
    }

    async addLocal(context: IContext, entity: IUserYearWorkStats): Promise<IUserYearWorkStats | null> {
        return this.cache.add(context, new UserYearWorkStats(entity) as any);
    }

    async createLocal(context: IContext, entity: IUserYearWorkStats): Promise<IUserYearWorkStats | null> {
        return super.createLocal(context, new UserYearWorkStats(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IUserYearWorkStats> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IUserYearWorkStats): Promise<IUserYearWorkStats> {
        return super.updateLocal(context, new UserYearWorkStats(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IUserYearWorkStats = {}): Promise<IUserYearWorkStats> {
        return new UserYearWorkStats(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
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
     * @memberof UserYearWorkStatsService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/useryearworkstats`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/useryearworkstats/${_context.useryearworkstats}`);
        return res;
    }
    /**
     * GetDevInfomation
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async GetDevInfomation(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/useryearworkstats/${_context.useryearworkstats}/getdevinfomation`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/useryearworkstats/getdraft`, _data);
        return res;
    }
    /**
     * GetPoInfomation
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async GetPoInfomation(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/useryearworkstats/${_context.useryearworkstats}/getpoinfomation`);
        return res;
    }
    /**
     * GetQaInfomation
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async GetQaInfomation(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/useryearworkstats/${_context.useryearworkstats}/getqainfomation`);
        return res;
    }
    /**
     * GetUserYearAction
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async GetUserYearAction(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/useryearworkstats/${_context.useryearworkstats}/getuseryearaction`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/useryearworkstats/${_context.useryearworkstats}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/useryearworkstats/${_context.useryearworkstats}`, _data);
    }
    /**
     * UpdateTitleByYear
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async UpdateTitleByYear(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/useryearworkstats/${_context.useryearworkstats}/updatetitlebyyear`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/useryearworkstats/fetchdefault`, _data);
    }
    /**
     * FetchMonthFinishTaskAndBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async FetchMonthFinishTaskAndBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/useryearworkstats/fetchmonthfinishtaskandbug`, _data);
    }
    /**
     * FetchMonthOpenedBugAndCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async FetchMonthOpenedBugAndCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/useryearworkstats/fetchmonthopenedbugandcase`, _data);
    }
    /**
     * FetchMonthOpenedStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async FetchMonthOpenedStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/useryearworkstats/fetchmonthopenedstory`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof UserYearWorkStatsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/useryearworkstats/${_context.useryearworkstats}/select`);
    }

    /**
     * GetUserYearActionBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async GetUserYearActionBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/useryearworkstats/getuseryearactionbatch`,_data);
    }

    /**
     * UpdateTitleByYearBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof UserYearWorkStatsServiceBase
     */
    public async UpdateTitleByYearBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/useryearworkstats/updatetitlebyyearbatch`,_data);
    }
}
