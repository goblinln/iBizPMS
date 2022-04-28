import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectStats, ProjectStats } from '../../entities';
import keys from '../../entities/project-stats/project-stats-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 项目统计服务对象基类
 *
 * @export
 * @class ProjectStatsBaseService
 * @extends {EntityBaseService}
 */
export class ProjectStatsBaseService extends EntityBaseService<IProjectStats> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectStats';
    protected APPDENAMEPLURAL = 'ProjectStats';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/ProjectStats.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    constructor(opts?: any) {
        super(opts, 'ProjectStats');
    }

    newEntity(data: IProjectStats): ProjectStats {
        return new ProjectStats(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectStats> {
        const entity = await super.getLocal(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectStats): Promise<IProjectStats> {
        return super.updateLocal(context, new ProjectStats(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectStats = {}): Promise<IProjectStats> {
        return new ProjectStats(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getNoOpenProductCond() {
        if (!this.condCache.has('noOpenProduct')) {
            const strCond: any[] = ['AND', ['NOTEQ', 'STATUS','closed']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('noOpenProduct', cond);
            }
        }
        return this.condCache.get('noOpenProduct');
    }

    protected getProjectBugTypeCond() {
        return this.condCache.get('projectBugType');
    }

    protected getProjectInputStatsCond() {
        return this.condCache.get('projectInputStats');
    }

    protected getProjectProgressCond() {
        return this.condCache.get('projectProgress');
    }

    protected getProjectQualityCond() {
        return this.condCache.get('projectQuality');
    }

    protected getProjectStoryStageStatsCond() {
        return this.condCache.get('projectStoryStageStats');
    }

    protected getProjectStoryStatusStatsCond() {
        return this.condCache.get('projectStoryStatusStats');
    }

    protected getProjectTaskCountByTaskStatusCond() {
        return this.condCache.get('projectTaskCountByTaskStatus');
    }

    protected getProjectTaskCountByTypeCond() {
        return this.condCache.get('projectTaskCountByType');
    }

    protected getTaskTimeCond() {
        return this.condCache.get('taskTime');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
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
        const res = await this.http.post(`/projectstats`, _data);
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
     * @memberof ProjectStatsService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/projectstats/${encodeURIComponent(_context.projectstats)}`);
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
     * @memberof ProjectStatsService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/projectstats/getdraft`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * ProjectQualitySum
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async ProjectQualitySum(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/${encodeURIComponent(_context.projectstats)}/projectqualitysum`, _data);
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
     * @memberof ProjectStatsService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.delete(`/projectstats/${encodeURIComponent(_context.projectstats)}`);
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
     * @memberof ProjectStatsService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/projectstats/${encodeURIComponent(_context.projectstats)}`, _data);
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
     * @memberof ProjectStatsService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchNoOpenProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async FetchNoOpenProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchnoopenproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchNoOpenProduct');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectBugType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async FetchProjectBugType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchprojectbugtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectBugType');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectInputStats
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async FetchProjectInputStats(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchprojectinputstats`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectInputStats');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectProgress
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async FetchProjectProgress(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchprojectprogress`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectProgress');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectQuality
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async FetchProjectQuality(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchprojectquality`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectQuality');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectStoryStageStats
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async FetchProjectStoryStageStats(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchprojectstorystagestats`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectStoryStageStats');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectStoryStatusStats
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async FetchProjectStoryStatusStats(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchprojectstorystatusstats`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectStoryStatusStats');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectTaskCountByTaskStatus
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async FetchProjectTaskCountByTaskStatus(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchprojecttaskcountbytaskstatus`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectTaskCountByTaskStatus');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProjectTaskCountByType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async FetchProjectTaskCountByType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchprojecttaskcountbytype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProjectTaskCountByType');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchTaskTime
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async FetchTaskTime(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/projectstats/fetchtasktime`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchTaskTime');
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
     * @memberof ProjectStatsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/projectstats/${encodeURIComponent(_context.projectstats)}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
