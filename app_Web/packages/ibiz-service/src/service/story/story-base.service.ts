import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IStory, Story } from '../../entities';
import keys from '../../entities/story/story-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { GetCurUserConcatLogic } from '../../logic/entity/story/get-cur-user-concat/get-cur-user-concat-logic';

/**
 * 需求服务对象基类
 *
 * @export
 * @class StoryBaseService
 * @extends {EntityBaseService}
 */
export class StoryBaseService extends EntityBaseService<IStory> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Story';
    protected APPDENAMEPLURAL = 'Stories';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title','id',];
    protected selectContextParam = {
        product: 'product',
    };

    newEntity(data: IStory): Story {
        return new Story(data);
    }

    async addLocal(context: IContext, entity: IStory): Promise<IStory | null> {
        return this.cache.add(context, new Story(entity) as any);
    }

    async createLocal(context: IContext, entity: IStory): Promise<IStory | null> {
        return super.createLocal(context, new Story(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IStory> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IStory): Promise<IStory> {
        return super.updateLocal(context, new Story(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IStory = {}): Promise<IStory> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        return new Story(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
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
     * FetchMyFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchMyFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchmyfavorites`, _data);
        }
        return this.http.post(`/stories/fetchmyfavorites`, _data);
    }
    /**
     * Review
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async Review(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/review`, _data);
        }
        return this.http.post(`/stories/${_context.story}/review`, _data);
    }
    /**
     * FetchBugStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchBugStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchbugstory`, _data);
        }
        return this.http.post(`/stories/fetchbugstory`, _data);
    }
    /**
     * FetchAssignedToMyStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchAssignedToMyStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchassignedtomystory`, _data);
        }
        return this.http.post(`/stories/fetchassignedtomystory`, _data);
    }
    /**
     * StoryFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async StoryFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/storyfavorites`, _data);
        }
        return this.http.post(`/stories/${_context.story}/storyfavorites`, _data);
    }
    /**
     * FetchCaseStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchCaseStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchcasestory`, _data);
        }
        return this.http.post(`/stories/fetchcasestory`, _data);
    }
    /**
     * FetchBuildLinkableStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchBuildLinkableStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchbuildlinkablestories`, _data);
        }
        return this.http.post(`/stories/fetchbuildlinkablestories`, _data);
    }
    /**
     * FetchChildMore
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchChildMore(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchchildmore`, _data);
        }
        return this.http.post(`/stories/fetchchildmore`, _data);
    }
    /**
     * FetchReleaseStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchReleaseStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchreleasestories`, _data);
        }
        return this.http.post(`/stories/fetchreleasestories`, _data);
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/activate`, _data);
        }
        return this.http.post(`/stories/${_context.story}/activate`, _data);
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/close`, _data);
        }
        return this.http.post(`/stories/${_context.story}/close`, _data);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/stories`, _data);
    }
    /**
     * FetchStoryRelated
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchStoryRelated(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchstoryrelated`, _data);
        }
        return this.http.post(`/stories/fetchstoryrelated`, _data);
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/stories/getdraft`, _data);
        return res;
    }
    /**
     * FetchbuildLinkCompletedStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchbuildLinkCompletedStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchbuildlinkcompletedstories`, _data);
        }
        return this.http.post(`/stories/fetchbuildlinkcompletedstories`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/stories/${_context.story}`, _data);
    }
    /**
     * FetchByModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchByModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchbymodule`, _data);
        }
        return this.http.post(`/stories/fetchbymodule`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}`);
        }
        return this.http.delete(`/stories/${_context.story}`);
    }
    /**
     * StoryNFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async StoryNFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/storynfavorites`, _data);
        }
        return this.http.post(`/stories/${_context.story}/storynfavorites`, _data);
    }
    /**
     * FetchAssignedToMyStoryCalendar
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchAssignedToMyStoryCalendar(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchassignedtomystorycalendar`, _data);
        }
        return this.http.post(`/stories/fetchassignedtomystorycalendar`, _data);
    }
    /**
     * Change
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async Change(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/change`, _data);
        }
        return this.http.post(`/stories/${_context.story}/change`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}`);
            return res;
        }
        const res = await this.http.get(`/stories/${_context.story}`);
        return res;
    }
    /**
     * AssignTo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async AssignTo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/assignto`, _data);
        }
        return this.http.post(`/stories/${_context.story}/assignto`, _data);
    }
    /**
     * FetchParentDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchParentDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchparentdefault`, _data);
        }
        return this.http.post(`/stories/fetchparentdefault`, _data);
    }
    /**
     * FetchprojectLinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchprojectLinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchprojectlinkstory`, _data);
        }
        return this.http.post(`/stories/fetchprojectlinkstory`, _data);
    }
    /**
     * FetchBuildStories
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchBuildStories(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchbuildstories`, _data);
        }
        return this.http.post(`/stories/fetchbuildstories`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/stories/fetchdefault`, _data);
        }
        return this.http.post(`/stories/fetchdefault`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/select`);
        }
        return this.http.get(`/stories/${_context.story}/select`);
    }
    /**
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof StoryService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const appLogic = new GetCurUserConcatLogic(_context, _data);
        _data = await appLogic.onExecute();
        return new HttpResponse(_data);
    }

    /**
     * ReviewBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof StoryServiceBase
     */
    public async ReviewBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/reviewbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/stories/reviewbatch`,_data);
    }

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof StoryServiceBase
     */
    public async ActivateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/activatebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/stories/activatebatch`,_data);
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof StoryServiceBase
     */
    public async CloseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/closebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/stories/closebatch`,_data);
    }

    /**
     * ChangeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof StoryServiceBase
     */
    public async ChangeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/changebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/stories/changebatch`,_data);
    }

    /**
     * AssignToBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof StoryServiceBase
     */
    public async AssignToBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/assigntobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/stories/assigntobatch`,_data);
    }
}
