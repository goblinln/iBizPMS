import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProject, Project } from '../../entities';
import keys from '../../entities/project/project-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { ProjectTaskQCntLogic } from '../../logic/entity/project/project-task-qcnt/project-task-qcnt-logic';

/**
 * 项目服务对象基类
 *
 * @export
 * @class ProjectBaseService
 * @extends {EntityBaseService}
 */
export class ProjectBaseService extends EntityBaseService<IProject> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Project';
    protected APPDENAMEPLURAL = 'Projects';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name','code','projectsn',];
    protected selectContextParam = {
    };

    newEntity(data: IProject): Project {
        return new Project(data);
    }

    async addLocal(context: IContext, entity: IProject): Promise<IProject | null> {
        return this.cache.add(context, new Project(entity) as any);
    }

    async createLocal(context: IContext, entity: IProject): Promise<IProject | null> {
        return super.createLocal(context, new Project(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProject> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProject): Promise<IProject> {
        return super.updateLocal(context, new Project(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProject = {}): Promise<IProject> {
        return new Project(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getBugSelectableProjectListCond() {
        return this.condCache.get('bugSelectableProjectList');
    }

    protected getCurDefaultQueryCond() {
        return this.condCache.get('curDefaultQuery');
    }

    protected getCurDefaultQueryExpCond() {
        return this.condCache.get('curDefaultQueryExp');
    }

    protected getCurPlanProjectCond() {
        return this.condCache.get('curPlanProject');
    }

    protected getCurProductCond() {
        return this.condCache.get('curProduct');
    }

    protected getCurUserCond() {
        return this.condCache.get('curUser');
    }

    protected getCurUserSaCond() {
        return this.condCache.get('curUserSa');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getDeveloperQueryCond() {
        if (!this.condCache.has('developerQuery')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('developerQuery', cond);
            }
        }
        return this.condCache.get('developerQuery');
    }

    protected getESBulkCond() {
        return this.condCache.get('eSBulk');
    }

    protected getInvolvedProjectCond() {
        return this.condCache.get('involvedProject');
    }

    protected getInvolvedProjectStoryTaskBugCond() {
        return this.condCache.get('involvedProjectStoryTaskBug');
    }

    protected getMyProjectCond() {
        if (!this.condCache.has('myProject')) {
            const strCond: any[] = ['AND', ['EQ', 'DELETED','0']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myProject', cond);
            }
        }
        return this.condCache.get('myProject');
    }

    protected getOpenByQueryCond() {
        if (!this.condCache.has('openByQuery')) {
            const strCond: any[] = ['AND', ['EQ', 'ACL','private'], ['OR', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'UPDATEBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'PM',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'RD',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'PO',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'QD',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('openByQuery', cond);
            }
        }
        return this.condCache.get('openByQuery');
    }

    protected getOpenQueryCond() {
        if (!this.condCache.has('openQuery')) {
            const strCond: any[] = ['AND', ['EQ', 'ACL','open']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('openQuery', cond);
            }
        }
        return this.condCache.get('openQuery');
    }

    protected getProjectTeamCond() {
        return this.condCache.get('projectTeam');
    }

    protected getStoryProjectCond() {
        return this.condCache.get('storyProject');
    }

    protected getUnDoneProjectCond() {
        return this.condCache.get('unDoneProject');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Putoff
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async Putoff(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/putoff`, _data);
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/projects/getdraft`, _data);
        return res;
    }
    /**
     * FetchProjectTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchProjectTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchprojectteam`, _data);
    }
    /**
     * FetchCurDefaultQuery
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchCurDefaultQuery(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchcurdefaultquery`, _data);
    }
    /**
     * FetchCurProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchCurProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchcurproduct`, _data);
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/close`, _data);
    }
    /**
     * FetchCurPlanProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchCurPlanProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchcurplanproject`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/projects/${_context.project}`);
        return res;
    }
    /**
     * LinkProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async LinkProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/linkproduct`, _data);
    }
    /**
     * FetchBugProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchBugProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchbugproject`, _data);
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/activate`, _data);
    }
    /**
     * FetchUnDoneProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchUnDoneProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchundoneproject`, _data);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/projects`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/projects/${_context.project}`, _data);
    }
    /**
     * Start
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async Start(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/start`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/projects/${_context.project}`);
    }
    /**
     * FetchCurDefaultQueryExp
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchCurDefaultQueryExp(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchcurdefaultqueryexp`, _data);
    }
    /**
     * Suspend
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async Suspend(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/suspend`, _data);
    }
    /**
     * LinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async LinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/linkstory`, _data);
    }
    /**
     * FetchCurUser
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchCurUser(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchcuruser`, _data);
    }
    /**
     * UnlinkProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async UnlinkProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/unlinkproduct`, _data);
    }
    /**
     * FetchMyProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchMyProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchmyproject`, _data);
    }

    /**
     * PutoffBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async PutoffBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projects/putoffbatch`,_data);
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async CloseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projects/closebatch`,_data);
    }

    /**
     * LinkProductBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async LinkProductBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projects/linkproductbatch`,_data);
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projects/activatebatch`,_data);
    }

    /**
     * StartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async StartBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projects/startbatch`,_data);
    }

    /**
     * SuspendBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async SuspendBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projects/suspendbatch`,_data);
    }

    /**
     * LinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async LinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projects/linkstorybatch`,_data);
    }

    /**
     * UnlinkProductBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProjectServiceBase
     */
    public async UnlinkProductBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/projects/unlinkproductbatch`,_data);
    }
}
