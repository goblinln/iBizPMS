import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISubStory, SubStory } from '../../entities';
import keys from '../../entities/sub-story/sub-story-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

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
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/SubStory.json';
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

    protected getAccountCond() {
        return this.condCache.get('account');
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

    protected getMyCond() {
        return this.condCache.get('my');
    }

    protected getMyAgentStoryCond() {
        return this.condCache.get('myAgentStory');
    }

    protected getMyCreateCond() {
        if (!this.condCache.has('myCreate')) {
            const strCond: any[] = ['AND', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCreate', cond);
            }
        }
        return this.condCache.get('myCreate');
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

    protected getSimpleCond() {
        return this.condCache.get('simple');
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
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/substories/${_context.substory}/activate`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/allpush`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/assignto`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/batchassignto`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/batchchangebranch`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/batchchangemodule`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/batchchangeplan`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/batchchangestage`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/batchclose`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/batchreview`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/batchunlinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/bugtostory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/buildbatchunlinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/buildlinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/buildunlinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/buildunlinkstorys`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/change`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/close`, _data);
        return res;
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
        const res = await this.http.post(`/substories`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/createtasks`, _data);
        return res;
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
     * GetStorySpec
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async GetStorySpec(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/substories/${_context.substory}/getstoryspec`, _data);
        return res;
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
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
            _data =  await this.executeAppDELogic('GetCurUserConcat',_context,_data);
            return new HttpResponse(_data, {
                ok: true,
                status: 200
            });
        }catch (error) {
            return new HttpResponse({message:error.message}, {
                ok: false,
                status: 500,
            });
        }
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
        const res = await this.http.post(`/substories/${_context.substory}/importplanstories`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/linkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/projectbatchunlinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/projectlinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/projectunlinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/projectunlinkstorys`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/push`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/releasebatchunlinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/releaselinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/releaseunlinkstory`, _data);
        return res;
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
        const res = await this.http.delete(`/substories/${_context.substory}`);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/resetreviewedby`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/review`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/sendmessage`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/sendmsgpreprocess`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/setstage`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/storyfavorites`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/storynfavorites`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/syncfromibiz`, _data);
        return res;
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
        const res = await this.http.post(`/substories/${_context.substory}/unlinkstory`, _data);
        return res;
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
        const res = await this.http.put(`/substories/${_context.substory}`, _data);
        return res;
    }
    /**
     * FetchAccount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchAccount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/substories/fetchaccount`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchassignedtomystory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchassignedtomystorycalendar`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchbugstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchbuildlinkcompletedstories`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchbuildlinkablestories`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchbuildstories`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchbymodule`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchcasestory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchchildmore`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchdefault`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchesbulk`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchgetproductstories`, _data);
        return res;
    }
    /**
     * FetchMy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubStoryService
     */
    async FetchMy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/substories/fetchmy`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchmyagentstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchmycuropenedstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchmyfavorites`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchnotcurplanlinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchparentdefault`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchparentdefaultq`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchprojectlinkstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchprojectstories`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchreleaselinkablestories`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchreleasestories`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchreportstories`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchstorychild`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchstoryrelated`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchsubstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchtaskrelatedstory`, _data);
        return res;
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
        const res = await this.http.post(`/substories/fetchview`, _data);
        return res;
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
        const res = await this.http.get(`/substories/${_context.substory}/select`);
        return res;
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
