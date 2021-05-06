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
        story: 'parent',
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
        if (entity && entity.parent && entity.parent !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(context, entity.parent);
            if (data) {
                entity.parentname = data.title;
                entity.parent = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISubStory): Promise<ISubStory> {
        return super.updateLocal(context, new SubStory(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISubStory = {}): Promise<ISubStory> {
        if (_context.story && _context.story !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(_context, _context.story);
            if (data) {
                entity.parentname = data.title;
                entity.parent = data.id;
            }
        }
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
        if (_context.product && _context.story && _context.substory) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/select`);
        }
        if (_context.story && _context.substory) {
            return this.http.get(`/stories/${_context.story}/substories/${_context.substory}/select`);
        }
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
        if (_context.product && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories`, _data);
        }
        if (_context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/substories`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/substories/${_context.substory}`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}`);
        }
        if (_context.story && _context.substory) {
            return this.http.delete(`/stories/${_context.story}/substories/${_context.substory}`);
        }
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
        if (_context.product && _context.story && _context.substory) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}`);
            return res;
        }
        if (_context.story && _context.substory) {
            const res = await this.http.get(`/stories/${_context.story}/substories/${_context.substory}`);
            return res;
        }
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
        if (_context.product && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/substories/getdraft`, _data);
            return res;
        }
        if (_context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/substories/getdraft`, _data);
            return res;
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/activate`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/activate`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/allpush`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/allpush`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/assignto`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/assignto`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/batchassignto`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/batchassignto`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/batchchangebranch`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/batchchangebranch`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/batchchangemodule`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/batchchangemodule`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/batchchangeplan`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/batchchangeplan`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/batchchangestage`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/batchchangestage`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/batchclose`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/batchclose`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/batchreview`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/batchreview`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/batchunlinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/batchunlinkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/bugtostory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/bugtostory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/buildbatchunlinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/buildbatchunlinkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/buildlinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/buildlinkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/buildunlinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/buildunlinkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/buildunlinkstorys`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/buildunlinkstorys`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/change`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/change`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/close`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/close`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/createtasks`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/createtasks`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/getstoryspec`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/getstoryspec`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/getstoryspecs`);
            return res;
        }
        if (_context.story && _context.substory) {
            const res = await this.http.get(`/stories/${_context.story}/substories/${_context.substory}/getstoryspecs`);
            return res;
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/importplanstories`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/importplanstories`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/linkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/linkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/projectbatchunlinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/projectbatchunlinkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/projectlinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/projectlinkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/projectunlinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/projectunlinkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/projectunlinkstorys`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/projectunlinkstorys`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/push`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/push`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/releasebatchunlinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/releasebatchunlinkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/releaselinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/releaselinkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/releaseunlinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/releaseunlinkstory`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/resetreviewedby`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/resetreviewedby`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/review`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/review`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/sendmessage`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/sendmessage`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/sendmsgpreprocess`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/sendmsgpreprocess`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/setstage`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/setstage`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/storyfavorites`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/storyfavorites`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/storynfavorites`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/storynfavorites`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/syncfromibiz`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/syncfromibiz`, _data);
        }
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
        if (_context.product && _context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/${_context.substory}/unlinkstory`, _data);
        }
        if (_context.story && _context.substory) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/substories/${_context.substory}/unlinkstory`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchassignedtomystory`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchassignedtomystory`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchassignedtomystorycalendar`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchassignedtomystorycalendar`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchbugstory`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchbugstory`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchbuildlinkcompletedstories`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchbuildlinkcompletedstories`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchbuildlinkablestories`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchbuildlinkablestories`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchbuildstories`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchbuildstories`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchbymodule`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchbymodule`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchcasestory`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchcasestory`, _data);
        }
        return this.http.post(`/substories/fetchcasestory`, _data);
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchdefault`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchdefault`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchesbulk`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchesbulk`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchgetproductstories`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchgetproductstories`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchmyagentstory`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchmyagentstory`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchmycuropenedstory`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchmycuropenedstory`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchmyfavorites`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchmyfavorites`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchnotcurplanlinkstory`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchnotcurplanlinkstory`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchparentdefault`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchparentdefault`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchparentdefaultq`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchparentdefaultq`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchprojectlinkstory`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchprojectlinkstory`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchprojectstories`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchprojectstories`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchreleaselinkablestories`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchreleaselinkablestories`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchreleasestories`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchreleasestories`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchreportstories`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchreportstories`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchstorychild`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchstorychild`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchstoryrelated`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchstoryrelated`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchsubstory`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchsubstory`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchtaskrelatedstory`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchtaskrelatedstory`, _data);
        }
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
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/substories/fetchview`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/substories/fetchview`, _data);
        }
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
    public async ActivateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/activatebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/activatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/activatebatch`,tempData,isloading);
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
    public async AllPushBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/allpushbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/allpushbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/allpushbatch`,tempData,isloading);
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
    public async AssignToBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/assigntobatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/assigntobatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/assigntobatch`,tempData,isloading);
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
    public async BatchAssignToBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/batchassigntobatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/batchassigntobatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/batchassigntobatch`,tempData,isloading);
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
    public async BatchChangeBranchBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/batchchangebranchbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/batchchangebranchbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/batchchangebranchbatch`,tempData,isloading);
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
    public async BatchChangeModuleBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/batchchangemodulebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/batchchangemodulebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/batchchangemodulebatch`,tempData,isloading);
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
    public async BatchChangePlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/batchchangeplanbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/batchchangeplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/batchchangeplanbatch`,tempData,isloading);
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
    public async BatchChangeStageBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/batchchangestagebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/batchchangestagebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/batchchangestagebatch`,tempData,isloading);
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
    public async BatchCloseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/batchclosebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/batchclosebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/batchclosebatch`,tempData,isloading);
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
    public async BatchReviewBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/batchreviewbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/batchreviewbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/batchreviewbatch`,tempData,isloading);
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
    public async BatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/batchunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/batchunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/batchunlinkstorybatch`,tempData,isloading);
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
    public async BugToStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/bugtostorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/bugtostorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/bugtostorybatch`,tempData,isloading);
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
    public async BuildBatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/buildbatchunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/buildbatchunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/buildbatchunlinkstorybatch`,tempData,isloading);
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
    public async BuildLinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/buildlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/buildlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/buildlinkstorybatch`,tempData,isloading);
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
    public async BuildUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/buildunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/buildunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/buildunlinkstorybatch`,tempData,isloading);
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
    public async ChangeBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/changebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/changebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/changebatch`,tempData,isloading);
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
    public async CloseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/closebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/closebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/closebatch`,tempData,isloading);
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
    public async CreateTasksBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/createtasksbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/createtasksbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/createtasksbatch`,tempData,isloading);
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
    public async GetStorySpecBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/getstoryspecbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/getstoryspecbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/getstoryspecbatch`,tempData,isloading);
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
    public async ImportPlanStoriesBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/importplanstoriesbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/importplanstoriesbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/importplanstoriesbatch`,tempData,isloading);
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
    public async LinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/linkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/linkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/linkstorybatch`,tempData,isloading);
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
    public async ProjectBatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/projectbatchunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/projectbatchunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/projectbatchunlinkstorybatch`,tempData,isloading);
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
    public async ProjectLinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/projectlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/projectlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/projectlinkstorybatch`,tempData,isloading);
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
    public async ProjectUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/projectunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/projectunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/projectunlinkstorybatch`,tempData,isloading);
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
    public async PushBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/pushbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/pushbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/pushbatch`,tempData,isloading);
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
    public async ReleaseBatchUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/releasebatchunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/releasebatchunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/releasebatchunlinkstorybatch`,tempData,isloading);
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
    public async ReleaseLinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/releaselinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/releaselinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/releaselinkstorybatch`,tempData,isloading);
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
    public async ReleaseUnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/releaseunlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/releaseunlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/releaseunlinkstorybatch`,tempData,isloading);
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
    public async ResetReviewedByBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/resetreviewedbybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/resetreviewedbybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/resetreviewedbybatch`,tempData,isloading);
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
    public async ReviewBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/reviewbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/reviewbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/reviewbatch`,tempData,isloading);
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
    public async SendMessageBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/sendmessagebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/sendmessagebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/sendmessagebatch`,tempData,isloading);
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
    public async SendMsgPreProcessBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/sendmsgpreprocessbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/sendmsgpreprocessbatch`,tempData,isloading);
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
    public async SetStageBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/setstagebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/setstagebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/setstagebatch`,tempData,isloading);
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
    public async SyncFromIbizBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/syncfromibizbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/syncfromibizbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/syncfromibizbatch`,tempData,isloading);
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
    public async UnlinkStoryBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/products/${context.product}/stories/${context.story}/substories/unlinkstorybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await this.http.post(`/stories/${context.story}/substories/unlinkstorybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await this.http.post(`/substories/unlinkstorybatch`,tempData,isloading);
    }
}
