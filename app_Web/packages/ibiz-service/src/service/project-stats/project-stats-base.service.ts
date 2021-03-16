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
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    async addLocal(context: IContext, entity: IProjectStats): Promise<IProjectStats | null> {
        return this.cache.add(context, new ProjectStats(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectStats): Promise<IProjectStats | null> {
        return super.createLocal(context, new ProjectStats(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectStats> {
        const entity = this.cache.get(context, srfKey);
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectStatsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/projectstats/${_context.projectstats}/select`);
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
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/projectstats`, _data);
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
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/projectstats/${_context.projectstats}`, _data);
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
        return this.http.delete(`/projectstats/${_context.projectstats}`);
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
        const res = await this.http.get(`/projectstats/${_context.projectstats}`);
        return res;
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
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/projectstats/getdraft`, _data);
        return res;
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
        return this.http.post(`/projectstats/${_context.projectstats}/projectqualitysum`, _data);
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
        return this.http.get(`/projectstats/fetchdefault`, _data);
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
        return this.http.get(`/projectstats/fetchnoopenproduct`, _data);
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
        return this.http.get(`/projectstats/fetchprojectbugtype`, _data);
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
        return this.http.get(`/projectstats/fetchprojectinputstats`, _data);
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
        return this.http.get(`/projectstats/fetchprojectprogress`, _data);
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
        return this.http.get(`/projectstats/fetchprojectquality`, _data);
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
        return this.http.get(`/projectstats/fetchprojectstorystagestats`, _data);
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
        return this.http.get(`/projectstats/fetchprojectstorystatusstats`, _data);
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
        return this.http.get(`/projectstats/fetchprojecttaskcountbytaskstatus`, _data);
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
        return this.http.get(`/projectstats/fetchprojecttaskcountbytype`, _data);
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
        return this.http.get(`/projectstats/fetchtasktime`, _data);
    }
}
