import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISubStory, SubStory } from '../../entities';
import keys from '../../entities/sub-story/sub-story-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { GetCurUserConcatLogic } from '../../logic/entity/sub-story/get-cur-user-concat/get-cur-user-concat-logic';

/**
 * 需求服务对象基类
 *
 * @export
 * @class SubStoryBaseService
 * @extends {EntityBaseService}
 */
export class SubStoryBaseService extends EntityBaseService<ISubStory> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SubStory';
    protected APPDENAMEPLURAL = 'SubStories';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title','id',];
    protected selectContextParam = {
    };

    newEntity(data: ISubStory): SubStory {
        return new SubStory(data);
    }

    async addLocal(context: IContext, entity: ISubStory): Promise<ISubStory | null> {
        return this.cache.add(context, new SubStory(entity) as any);
    }

    async createLocal(context: IContext, entity: ISubStory): Promise<ISubStory | null> {
        return super.createLocal(context, new SubStory(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISubStory> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISubStory): Promise<ISubStory> {
        return super.updateLocal(context, new SubStory(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISubStory = {}): Promise<ISubStory> {
        return new SubStory(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getAssignedToMyStoryCond() {
        if (!this.condCache.has('assignedToMyStory')) {
            const strCond: any[] = ['AND', ['EQ', 'ASSIGNEDTO',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('assignedToMyStory', cond);
            }
        }
        return this.condCache.get('assignedToMyStory');
    }

    protected getAssignedToMyStoryCalendarCond() {
        if (!this.condCache.has('assignedToMyStoryCalendar')) {
            const strCond: any[] = ['AND', ['EQ', 'ASSIGNEDTO',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('assignedToMyStoryCalendar', cond);
            }
        }
        return this.condCache.get('assignedToMyStoryCalendar');
    }

    protected getBugStoryCond() {
        if (!this.condCache.has('bugStory')) {
            const strCond: any[] = ['AND', ['OR']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('bugStory', cond);
            }
        }
        return this.condCache.get('bugStory');
    }

    protected getBuildLinkCompletedStoriesCond() {
        if (!this.condCache.has('buildLinkCompletedStories')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildLinkCompletedStories', cond);
            }
        }
        return this.condCache.get('buildLinkCompletedStories');
    }

    protected getBuildLinkableStoriesCond() {
        return this.condCache.get('buildLinkableStories');
    }

    protected getBuildStoriesCond() {
        if (!this.condCache.has('buildStories')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'BRANCH',{ type: 'DATACONTEXT', value: 'branch'}], ['EQ', 'MODULE',{ type: 'DATACONTEXT', value: 'nodeid'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('buildStories', cond);
            }
        }
        return this.condCache.get('buildStories');
    }

    protected getByModuleCond() {
        return this.condCache.get('byModule');
    }

    protected getCaseStoryCond() {
        return this.condCache.get('caseStory');
    }

    protected getChildMoreCond() {
        return this.condCache.get('childMore');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getESBulkCond() {
        return this.condCache.get('eSBulk');
    }

    protected getGetProductStoriesCond() {
        if (!this.condCache.has('getProductStories')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'BRANCH',{ type: 'DATACONTEXT', value: 'branch'}], ['EQ', 'MODULE',{ type: 'DATACONTEXT', value: 'nodeid'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('getProductStories', cond);
            }
        }
        return this.condCache.get('getProductStories');
    }

    protected getMyAgentStoryCond() {
        return this.condCache.get('myAgentStory');
    }

    protected getMyCreateOrPartakeCond() {
        if (!this.condCache.has('myCreateOrPartake')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'LASTEDITEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'CLOSEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'REVIEWEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'ASSIGNEDTO',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCreateOrPartake', cond);
            }
        }
        return this.condCache.get('myCreateOrPartake');
    }

    protected getMyCurOpenedStoryCond() {
        if (!this.condCache.has('myCurOpenedStory')) {
            const strCond: any[] = ['AND', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCurOpenedStory', cond);
            }
        }
        return this.condCache.get('myCurOpenedStory');
    }

    protected getMyFavoritesCond() {
        return this.condCache.get('myFavorites');
    }

    protected getMyReProductCond() {
        if (!this.condCache.has('myReProduct')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myReProduct', cond);
            }
        }
        return this.condCache.get('myReProduct');
    }

    protected getNotCurPlanLinkStoryCond() {
        return this.condCache.get('notCurPlanLinkStory');
    }

    protected getParentDefaultCond() {
        if (!this.condCache.has('parentDefault')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'PARENT','-1'], ['EQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('parentDefault', cond);
            }
        }
        return this.condCache.get('parentDefault');
    }

    protected getParentDefaultQCond() {
        if (!this.condCache.has('parentDefaultQ')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'PARENT','-1'], ['EQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('parentDefaultQ', cond);
            }
        }
        return this.condCache.get('parentDefaultQ');
    }

    protected getProjectLinkStoryCond() {
        if (!this.condCache.has('projectLinkStory')) {
            const strCond: any[] = ['AND', ['NOTIN', 'STATUS','draft,closed']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectLinkStory', cond);
            }
        }
        return this.condCache.get('projectLinkStory');
    }

    protected getProjectStoriesCond() {
        return this.condCache.get('projectStories');
    }

    protected getReleaseLinkableStoriesCond() {
        return this.condCache.get('releaseLinkableStories');
    }

    protected getReleaseStoriesCond() {
        if (!this.condCache.has('releaseStories')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'BRANCH',{ type: 'DATACONTEXT', value: 'branch'}], ['EQ', 'MODULE',{ type: 'DATACONTEXT', value: 'nodeid'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('releaseStories', cond);
            }
        }
        return this.condCache.get('releaseStories');
    }

    protected getReportStoriesCond() {
        return this.condCache.get('reportStories');
    }

    protected getStoryChildCond() {
        if (!this.condCache.has('storyChild')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'BRANCH',{ type: 'DATACONTEXT', value: 'branch'}], ['EQ', 'MODULE',{ type: 'DATACONTEXT', value: 'nodeid'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('storyChild', cond);
            }
        }
        return this.condCache.get('storyChild');
    }

    protected getStoryRelatedCond() {
        if (!this.condCache.has('storyRelated')) {
            const strCond: any[] = ['AND', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'BRANCH',{ type: 'DATACONTEXT', value: 'branch'}], ['EQ', 'MODULE',{ type: 'DATACONTEXT', value: 'nodeid'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('storyRelated', cond);
            }
        }
        return this.condCache.get('storyRelated');
    }

    protected getSubStoryCond() {
        if (!this.condCache.has('subStory')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('subStory', cond);
            }
        }
        return this.condCache.get('subStory');
    }

    protected getTaskRelatedStoryCond() {
        return this.condCache.get('taskRelatedStory');
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
     * @memberof SubStoryService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/substories/${_context.substory}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/substories`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/substories/${_context.substory}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/substories/${_context.substory}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/substories/${_context.substory}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/substories/getdraft`, _data);
        return res;
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/activate`, _data);
    }
    /**
     * AllPush
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async AllPush(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/allpush`, _data);
    }
    /**
     * AssignTo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async AssignTo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/assignto`, _data);
    }
    /**
     * BatchAssignTo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BatchAssignTo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/batchassignto`, _data);
    }
    /**
     * BatchChangeBranch
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BatchChangeBranch(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/batchchangebranch`, _data);
    }
    /**
     * BatchChangeModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BatchChangeModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/batchchangemodule`, _data);
    }
    /**
     * BatchChangePlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BatchChangePlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/batchchangeplan`, _data);
    }
    /**
     * BatchChangeStage
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BatchChangeStage(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/batchchangestage`, _data);
    }
    /**
     * BatchClose
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BatchClose(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/batchclose`, _data);
    }
    /**
     * BatchReview
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BatchReview(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/batchreview`, _data);
    }
    /**
     * BatchUnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BatchUnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/batchunlinkstory`, _data);
    }
    /**
     * BugToStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BugToStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/bugtostory`, _data);
    }
    /**
     * BuildBatchUnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BuildBatchUnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/buildbatchunlinkstory`, _data);
    }
    /**
     * BuildLinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BuildLinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/buildlinkstory`, _data);
    }
    /**
     * BuildUnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BuildUnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/buildunlinkstory`, _data);
    }
    /**
     * BuildUnlinkStorys
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async BuildUnlinkStorys(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/buildunlinkstorys`, _data);
    }
    /**
     * Change
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async Change(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/change`, _data);
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/close`, _data);
    }
    /**
     * CreateTasks
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async CreateTasks(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/createtasks`, _data);
    }
    /**
     * GetStorySpec
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async GetStorySpec(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/getstoryspec`, _data);
    }
    /**
     * GetStorySpecs
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async GetStorySpecs(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/substories/${_context.substory}/getstoryspecs`);
        return res;
    }
    /**
     * ImportPlanStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async ImportPlanStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/importplanstories`, _data);
    }
    /**
     * LinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async LinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/linkstory`, _data);
    }
    /**
     * ProjectBatchUnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async ProjectBatchUnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/projectbatchunlinkstory`, _data);
    }
    /**
     * ProjectLinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async ProjectLinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/projectlinkstory`, _data);
    }
    /**
     * ProjectUnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async ProjectUnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/projectunlinkstory`, _data);
    }
    /**
     * ProjectUnlinkStorys
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async ProjectUnlinkStorys(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/projectunlinkstorys`, _data);
    }
    /**
     * Push
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async Push(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/push`, _data);
    }
    /**
     * ReleaseBatchUnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async ReleaseBatchUnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/releasebatchunlinkstory`, _data);
    }
    /**
     * ReleaseLinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async ReleaseLinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/releaselinkstory`, _data);
    }
    /**
     * ReleaseUnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async ReleaseUnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/releaseunlinkstory`, _data);
    }
    /**
     * ResetReviewedBy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async ResetReviewedBy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/resetreviewedby`, _data);
    }
    /**
     * Review
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async Review(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/review`, _data);
    }
    /**
     * SendMessage
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async SendMessage(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/sendmessage`, _data);
    }
    /**
     * SendMsgPreProcess
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async SendMsgPreProcess(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/sendmsgpreprocess`, _data);
    }
    /**
     * SetStage
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async SetStage(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/setstage`, _data);
    }
    /**
     * StoryFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async StoryFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/storyfavorites`, _data);
    }
    /**
     * StoryNFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async StoryNFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/storynfavorites`, _data);
    }
    /**
     * SyncFromIbiz
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async SyncFromIbiz(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/syncfromibiz`, _data);
    }
    /**
     * UnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async UnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/${_context.substory}/unlinkstory`, _data);
    }
    /**
     * FetchAssignedToMyStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchAssignedToMyStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchassignedtomystory`, _data);
    }
    /**
     * FetchAssignedToMyStoryCalendar
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchAssignedToMyStoryCalendar(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchassignedtomystorycalendar`, _data);
    }
    /**
     * FetchBugStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchBugStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchbugstory`, _data);
    }
    /**
     * FetchBuildLinkCompletedStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchBuildLinkCompletedStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchbuildlinkcompletedstories`, _data);
    }
    /**
     * FetchBuildLinkableStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchBuildLinkableStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchbuildlinkablestories`, _data);
    }
    /**
     * FetchBuildStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchBuildStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchbuildstories`, _data);
    }
    /**
     * FetchByModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchByModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchbymodule`, _data);
    }
    /**
     * FetchCaseStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchCaseStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchcasestory`, _data);
    }
    /**
     * FetchChildMore
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchChildMore(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchchildmore`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchdefault`, _data);
    }
    /**
     * FetchESBulk
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchESBulk(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchesbulk`, _data);
    }
    /**
     * FetchGetProductStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchGetProductStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchgetproductstories`, _data);
    }
    /**
     * FetchMyAgentStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchMyAgentStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchmyagentstory`, _data);
    }
    /**
     * FetchMyCurOpenedStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchMyCurOpenedStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchmycuropenedstory`, _data);
    }
    /**
     * FetchMyFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchMyFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchmyfavorites`, _data);
    }
    /**
     * FetchNotCurPlanLinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchNotCurPlanLinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchnotcurplanlinkstory`, _data);
    }
    /**
     * FetchParentDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchParentDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchparentdefault`, _data);
    }
    /**
     * FetchParentDefaultQ
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchParentDefaultQ(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchparentdefaultq`, _data);
    }
    /**
     * FetchProjectLinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchProjectLinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchprojectlinkstory`, _data);
    }
    /**
     * FetchProjectStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchProjectStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchprojectstories`, _data);
    }
    /**
     * FetchReleaseLinkableStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchReleaseLinkableStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchreleaselinkablestories`, _data);
    }
    /**
     * FetchReleaseStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchReleaseStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchreleasestories`, _data);
    }
    /**
     * FetchReportStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchReportStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchreportstories`, _data);
    }
    /**
     * FetchStoryChild
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchStoryChild(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchstorychild`, _data);
    }
    /**
     * FetchStoryRelated
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchStoryRelated(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchstoryrelated`, _data);
    }
    /**
     * FetchSubStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchSubStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchsubstory`, _data);
    }
    /**
     * FetchTaskRelatedStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchTaskRelatedStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchtaskrelatedstory`, _data);
    }
    /**
     * FetchView
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchView(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/substories/fetchview`, _data);
    }
    /**
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const appLogic = new GetCurUserConcatLogic(_context, _data);
        _data = await appLogic.onExecute();
        return new HttpResponse(_data);
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/activatebatch`,_data);
    }

    /**
     * AllPushBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async AllPushBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/allpushbatch`,_data);
    }

    /**
     * AssignToBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async AssignToBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/assigntobatch`,_data);
    }

    /**
     * BatchAssignToBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchAssignToBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/batchassigntobatch`,_data);
    }

    /**
     * BatchChangeBranchBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangeBranchBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/batchchangebranchbatch`,_data);
    }

    /**
     * BatchChangeModuleBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangeModuleBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/batchchangemodulebatch`,_data);
    }

    /**
     * BatchChangePlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangePlanBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/batchchangeplanbatch`,_data);
    }

    /**
     * BatchChangeStageBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchChangeStageBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/batchchangestagebatch`,_data);
    }

    /**
     * BatchCloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchCloseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/batchclosebatch`,_data);
    }

    /**
     * BatchReviewBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchReviewBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/batchreviewbatch`,_data);
    }

    /**
     * BatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BatchUnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/batchunlinkstorybatch`,_data);
    }

    /**
     * BugToStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BugToStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/bugtostorybatch`,_data);
    }

    /**
     * BuildBatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BuildBatchUnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/buildbatchunlinkstorybatch`,_data);
    }

    /**
     * BuildLinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BuildLinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/buildlinkstorybatch`,_data);
    }

    /**
     * BuildUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async BuildUnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/buildunlinkstorybatch`,_data);
    }

    /**
     * ChangeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ChangeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/changebatch`,_data);
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async CloseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/closebatch`,_data);
    }

    /**
     * CreateTasksBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async CreateTasksBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/createtasksbatch`,_data);
    }

    /**
     * GetStorySpecBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async GetStorySpecBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/getstoryspecbatch`,_data);
    }

    /**
     * ImportPlanStoriesBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ImportPlanStoriesBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/importplanstoriesbatch`,_data);
    }

    /**
     * LinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async LinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/linkstorybatch`,_data);
    }

    /**
     * ProjectBatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ProjectBatchUnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/projectbatchunlinkstorybatch`,_data);
    }

    /**
     * ProjectLinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ProjectLinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/projectlinkstorybatch`,_data);
    }

    /**
     * ProjectUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ProjectUnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/projectunlinkstorybatch`,_data);
    }

    /**
     * PushBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async PushBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/pushbatch`,_data);
    }

    /**
     * ReleaseBatchUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReleaseBatchUnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/releasebatchunlinkstorybatch`,_data);
    }

    /**
     * ReleaseLinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReleaseLinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/releaselinkstorybatch`,_data);
    }

    /**
     * ReleaseUnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReleaseUnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/releaseunlinkstorybatch`,_data);
    }

    /**
     * ResetReviewedByBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ResetReviewedByBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/resetreviewedbybatch`,_data);
    }

    /**
     * ReviewBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async ReviewBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/reviewbatch`,_data);
    }

    /**
     * SendMessageBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async SendMessageBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/sendmessagebatch`,_data);
    }

    /**
     * SendMsgPreProcessBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async SendMsgPreProcessBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/sendmsgpreprocessbatch`,_data);
    }

    /**
     * SetStageBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async SetStageBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/setstagebatch`,_data);
    }

    /**
     * SyncFromIbizBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async SyncFromIbizBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/syncfromibizbatch`,_data);
    }

    /**
     * UnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof SubStoryServiceBase
     */
    public async UnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/substories/unlinkstorybatch`,_data);
    }
}
