import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectTeam, ProjectTeam } from '../../entities';
import keys from '../../entities/project-team/project-team-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 项目团队服务对象基类
 *
 * @export
 * @class ProjectTeamBaseService
 * @extends {EntityBaseService}
 */
export class ProjectTeamBaseService extends EntityBaseService<IProjectTeam> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectTeam';
    protected APPDENAMEPLURAL = 'ProjectTeams';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
        project: 'root',
    };

    newEntity(data: IProjectTeam): ProjectTeam {
        return new ProjectTeam(data);
    }

    async addLocal(context: IContext, entity: IProjectTeam): Promise<IProjectTeam | null> {
        return this.cache.add(context, new ProjectTeam(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectTeam): Promise<IProjectTeam | null> {
        return super.createLocal(context, new ProjectTeam(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectTeam> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.projectname = data.name;
                entity.root = data.id;
                entity.project = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectTeam): Promise<IProjectTeam> {
        return super.updateLocal(context, new ProjectTeam(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectTeam = {}): Promise<IProjectTeam> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.root = data.id;
                entity.project = data;
            }
        }
        return new ProjectTeam(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
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
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','project']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getProjectTeamPmCond() {
        return this.condCache.get('projectTeamPm');
    }

    protected getRowEditDefaultCond() {
        return this.condCache.get('rowEditDefault');
    }

    protected getTaskCntEstimateConsumedLeftCond() {
        return this.condCache.get('taskCntEstimateConsumedLeft');
    }

    protected getViewCond() {
        if (!this.condCache.has('view')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','project']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('view', cond);
            }
        }
        return this.condCache.get('view');
    }
    /**
     * getuserrole
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async getuserrole(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectteams/${_context.projectteam}/getuserrole`, _data);
        }
        return this.http.put(`/projectteams/${_context.projectteam}/getuserrole`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectteam) {
            return this.http.get(`/projects/${_context.project}/projectteams/${_context.projectteam}/select`);
        }
        return this.http.get(`/projectteams/${_context.projectteam}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/projectteams`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/projectteams`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectteams/${_context.projectteam}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/projectteams/${_context.projectteam}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectteam) {
            return this.http.delete(`/projects/${_context.project}/projectteams/${_context.projectteam}`);
        }
        return this.http.delete(`/projectteams/${_context.projectteam}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectteam) {
            const res = await this.http.get(`/projects/${_context.project}/projectteams/${_context.projectteam}`);
            return res;
        }
        const res = await this.http.get(`/projectteams/${_context.projectteam}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/projectteams/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/projectteams/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projectteams/fetchdefault`, _data);
        }
        return this.http.post(`/projectteams/fetchdefault`, _data);
    }
    /**
     * FetchProjectTeamPm
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async FetchProjectTeamPm(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projectteams/fetchprojectteampm`, _data);
        }
        return this.http.post(`/projectteams/fetchprojectteampm`, _data);
    }
    /**
     * FetchRowEditDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async FetchRowEditDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projectteams/fetchroweditdefault`, _data);
        }
        return this.http.post(`/projectteams/fetchroweditdefault`, _data);
    }
    /**
     * FetchTaskCntEstimateConsumedLeft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTeamService
     */
    async FetchTaskCntEstimateConsumedLeft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projectteams/fetchtaskcntestimateconsumedleft`, _data);
        }
        return this.http.post(`/projectteams/fetchtaskcntestimateconsumedleft`, _data);
    }

    /**
     * getuserroleBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectTeamServiceBase
     */
    public async getuserroleBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/projects/${_context.project}/projectteams/getuserrolebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/projectteams/getuserrolebatch`,_data);
    }
}
