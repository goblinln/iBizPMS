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
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/UserYearWorkStats.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    constructor(opts?: any) {
        super(opts, 'UserYearWorkStats');
    }

    newEntity(data: IUserYearWorkStats): UserYearWorkStats {
        return new UserYearWorkStats(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IUserYearWorkStats> {
        const entity = await super.getLocal(context, srfKey);
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
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/useryearworkstats`, _data);
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
     * @memberof UserYearWorkStatsService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/useryearworkstats/${encodeURIComponent(_context.useryearworkstats)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.get(`/useryearworkstats/${encodeURIComponent(_context.useryearworkstats)}/getdevinfomation`);
        res.data = await this.afterExecuteAction(_context,res?.data,'GetDevInfomation');
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
     * @memberof UserYearWorkStatsService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/useryearworkstats/getdraft`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.get(`/useryearworkstats/${encodeURIComponent(_context.useryearworkstats)}/getpoinfomation`);
        res.data = await this.afterExecuteAction(_context,res?.data,'GetPoInfomation');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.get(`/useryearworkstats/${encodeURIComponent(_context.useryearworkstats)}/getqainfomation`);
        res.data = await this.afterExecuteAction(_context,res?.data,'GetQaInfomation');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.put(`/useryearworkstats/${encodeURIComponent(_context.useryearworkstats)}/getuseryearaction`, _data);
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
     * @memberof UserYearWorkStatsService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.delete(`/useryearworkstats/${encodeURIComponent(_context.useryearworkstats)}`);
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
     * @memberof UserYearWorkStatsService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/useryearworkstats/${encodeURIComponent(_context.useryearworkstats)}`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.put(`/useryearworkstats/${encodeURIComponent(_context.useryearworkstats)}/updatetitlebyyear`, _data);
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
     * @memberof UserYearWorkStatsService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/useryearworkstats/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/useryearworkstats/fetchmonthfinishtaskandbug`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMonthFinishTaskAndBug');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/useryearworkstats/fetchmonthopenedbugandcase`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMonthOpenedBugAndCase');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/useryearworkstats/fetchmonthopenedstory`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMonthOpenedStory');
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
     * @memberof UserYearWorkStatsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/useryearworkstats/${encodeURIComponent(_context.useryearworkstats)}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        const res = await this.http.post(`/useryearworkstats/getuseryearactionbatch`,_data);
        return res;
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
        const res = await this.http.post(`/useryearworkstats/updatetitlebyyearbatch`,_data);
        return res;
    }
}
