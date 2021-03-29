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
    protected quickSearchFields = ['id','name','code',];
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
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'ORGID',{ type: 'SESSIONCONTEXT', value: 'srforgid'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/projects/${_context.project}/select`);
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
     * BatchUnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async BatchUnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/batchunlinkstory`, _data);
    }
    /**
     * CancelProjectTop
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async CancelProjectTop(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/cancelprojecttop`, _data);
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
     * ManageMembers
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async ManageMembers(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/managemembers`, _data);
    }
    /**
     * MobProjectCount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async MobProjectCount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/mobprojectcount`, _data);
    }
    /**
     * PmsEeProjectAllTaskCount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async PmsEeProjectAllTaskCount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/pmseeprojectalltaskcount`, _data);
    }
    /**
     * PmsEeProjectTodoTaskCount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async PmsEeProjectTodoTaskCount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/pmseeprojecttodotaskcount`, _data);
    }
    /**
     * ProjectTaskQCnt
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async ProjectTaskQCnt(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/projecttaskqcnt`, _data);
    }
    /**
     * ProjectTop
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async ProjectTop(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/projecttop`, _data);
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
     * UnlinkMember
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async UnlinkMember(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/unlinkmember`, _data);
    }
    /**
     * UnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async UnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/${_context.project}/unlinkstory`, _data);
    }
    /**
     * UpdateOrder
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async UpdateOrder(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.put(`/projects/${_context.project}/updateorder`, _data);
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
        return this.http.get(`/projects/fetchbugproject`, _data);
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
        return this.http.get(`/projects/fetchcurplanproject`, _data);
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
     * FetchCurUserSa
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchCurUserSa(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchcurusersa`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/projects/fetchdefault`, _data);
    }
    /**
     * FetchESBulk
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchESBulk(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/projects/fetchesbulk`, _data);
    }
    /**
     * FetchInvolvedProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchInvolvedProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/projects/fetchinvolvedproject`, _data);
    }
    /**
     * FetchInvolvedProject_StoryTaskBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchInvolvedProject_StoryTaskBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/projects/fetchinvolvedproject_storytaskbug`, _data);
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
     * FetchProjectTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchProjectTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/projects/fetchprojectteam`, _data);
    }
    /**
     * FetchStoryProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectService
     */
    async FetchStoryProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/projects/fetchstoryproject`, _data);
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
        return this.http.get(`/projects/fetchundoneproject`, _data);
    }
}