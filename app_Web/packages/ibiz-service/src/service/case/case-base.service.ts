import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ICase, Case } from '../../entities';
import keys from '../../entities/case/case-keys';
import { clone, mergeDeepLeft } from 'ramda';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { GetCaseStepByIdVersionLogic } from '../../logic/entity/case/get-case-step-by-id-version/get-case-step-by-id-version-logic';

/**
 * 测试用例服务对象基类
 *
 * @export
 * @class CaseBaseService
 * @extends {EntityBaseService}
 */
export class CaseBaseService extends EntityBaseService<ICase> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Case';
    protected APPDENAMEPLURAL = 'Cases';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        product: 'product',
        story: 'story',
    };

    newEntity(data: ICase): Case {
        return new Case(data);
    }

    async addLocal(context: IContext, entity: ICase): Promise<ICase | null> {
        return this.cache.add(context, new Case(entity) as any);
    }

    async createLocal(context: IContext, entity: ICase): Promise<ICase | null> {
        return super.createLocal(context, new Case(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ICase> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        if (entity && entity.story && entity.story !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(context, entity.story);
            if (data) {
                entity.storyname = data.title;
                entity.story = data.id;
                entity.story = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ICase): Promise<ICase> {
        return super.updateLocal(context, new Case(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ICase = {}): Promise<ICase> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        if (_context.story && _context.story !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(_context, _context.story);
            if (data) {
                entity.storyname = data.title;
                entity.story = data.id;
                entity.story = data;
            }
        }
        return new Case(entity);
    }

    protected async fillMinor(_context: IContext, _data: ICase): Promise<any> {
        if (_data.ibzcasesteps) {
            await this.setMinorLocal('IBZCaseStep', _context, _data.ibzcasesteps);
            delete _data.ibzcasesteps;
        }
        this.addLocal(_context, _data);
        return _data;
    }

    protected async obtainMinor(_context: IContext, _data: ICase = new Case()): Promise<ICase> {
        const res = await this.GetTemp(_context, _data);
        if (res.ok) {
            _data = mergeDeepLeft(_data, this.filterEntityData(res.data)) as any;
        }
        const ibzcasestepsList = await this.getMinorLocal('IBZCaseStep', _context, { ibizcase: _data.id });
        if (ibzcasestepsList?.length > 0) {
            _data.ibzcasesteps = ibzcasestepsList;
        }
        return _data;
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const oldData = clone(data);
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
            {
                let items: any[] = [];
                const s = await ___ibz___.gs.getIBZCaseStepService();
                items = await s.selectLocal(context, { ibizcase: oldData.id });
                if (items) {
                    for (let i = 0; i < items.length; i++) {
                        const item = items[i];
                        const res = await s.DeepCopyTemp({ ...context, case: entity.srfkey }, item);
                        if (!res.ok) {
                            throw new Error(
                                `「Case(${oldData.srfkey})」关联实体「IBZCaseStep(${item.srfkey})」拷贝失败。`,
                            );
                        }
                    }
                }
            }
        }
        return new HttpResponse(entity);
    }

    protected getBatchNewCond() {
        if (!this.condCache.has('batchNew')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('batchNew', cond);
            }
        }
        return this.condCache.get('batchNew');
    }

    protected getCurOpenedCaseCond() {
        if (!this.condCache.has('curOpenedCase')) {
            const strCond: any[] = ['AND', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curOpenedCase', cond);
            }
        }
        return this.condCache.get('curOpenedCase');
    }

    protected getCurSuiteCond() {
        return this.condCache.get('curSuite');
    }

    protected getCurTestTaskCond() {
        return this.condCache.get('curTestTask');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getESBulkCond() {
        return this.condCache.get('eSBulk');
    }

    protected getModuleRePortCaseCond() {
        if (!this.condCache.has('moduleRePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('moduleRePortCase', cond);
            }
        }
        return this.condCache.get('moduleRePortCase');
    }

    protected getModuleRePortCaseEntryCond() {
        if (!this.condCache.has('moduleRePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('moduleRePortCaseEntry', cond);
            }
        }
        return this.condCache.get('moduleRePortCaseEntry');
    }

    protected getModuleRePortCase_ProjectCond() {
        if (!this.condCache.has('moduleRePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('moduleRePortCase_Project', cond);
            }
        }
        return this.condCache.get('moduleRePortCase_Project');
    }

    protected getMyFavoriteCond() {
        if (!this.condCache.has('myFavorite')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myFavorite', cond);
            }
        }
        return this.condCache.get('myFavorite');
    }

    protected getNotCurTestSuiteCond() {
        return this.condCache.get('notCurTestSuite');
    }

    protected getNotCurTestTaskCond() {
        return this.condCache.get('notCurTestTask');
    }

    protected getNotCurTestTaskProjectCond() {
        return this.condCache.get('notCurTestTaskProject');
    }

    protected getRePortCaseCond() {
        if (!this.condCache.has('rePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('rePortCase', cond);
            }
        }
        return this.condCache.get('rePortCase');
    }

    protected getRePortCaseEntryCond() {
        if (!this.condCache.has('rePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('rePortCaseEntry', cond);
            }
        }
        return this.condCache.get('rePortCaseEntry');
    }

    protected getRePortCase_ProjectCond() {
        if (!this.condCache.has('rePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('rePortCase_Project', cond);
            }
        }
        return this.condCache.get('rePortCase_Project');
    }

    protected getRunERRePortCaseCond() {
        if (!this.condCache.has('runERRePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runERRePortCase', cond);
            }
        }
        return this.condCache.get('runERRePortCase');
    }

    protected getRunERRePortCaseEntryCond() {
        if (!this.condCache.has('runERRePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runERRePortCaseEntry', cond);
            }
        }
        return this.condCache.get('runERRePortCaseEntry');
    }

    protected getRunERRePortCase_ProjectCond() {
        if (!this.condCache.has('runERRePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runERRePortCase_Project', cond);
            }
        }
        return this.condCache.get('runERRePortCase_Project');
    }

    protected getRunRePortCaseCond() {
        if (!this.condCache.has('runRePortCase')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runRePortCase', cond);
            }
        }
        return this.condCache.get('runRePortCase');
    }

    protected getRunRePortCaseEntryCond() {
        if (!this.condCache.has('runRePortCaseEntry')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runRePortCaseEntry', cond);
            }
        }
        return this.condCache.get('runRePortCaseEntry');
    }

    protected getRunRePortCase_ProjectCond() {
        if (!this.condCache.has('runRePortCase_Project')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('runRePortCase_Project', cond);
            }
        }
        return this.condCache.get('runRePortCase_Project');
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
     * @memberof CaseService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/select`);
        }
        if (_context.story && _context.case) {
            return this.http.get(`/stories/${_context.story}/cases/${_context.case}/select`);
        }
        if (_context.product && _context.case) {
            return this.http.get(`/products/${_context.product}/cases/${_context.case}/select`);
        }
        return this.http.get(`/cases/${_context.case}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
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
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases`, _data);
        }
        if (_context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/cases`, _data);
        }
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/cases`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/cases`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/cases/${_context.case}`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/cases/${_context.case}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/cases/${_context.case}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}`);
        }
        if (_context.story && _context.case) {
            return this.http.delete(`/stories/${_context.story}/cases/${_context.case}`);
        }
        if (_context.product && _context.case) {
            return this.http.delete(`/products/${_context.product}/cases/${_context.case}`);
        }
        return this.http.delete(`/cases/${_context.case}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.story && _context.case) {
            const res = await this.http.get(`/stories/${_context.story}/cases/${_context.case}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.product && _context.case) {
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        const res = await this.http.get(`/cases/${_context.case}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/cases/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/cases/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/cases/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
        return res;
    }
    /**
     * CaseFavorite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async CaseFavorite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casefavorite`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/casefavorite`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/casefavorite`, _data);
        }
        return this.http.post(`/cases/${_context.case}/casefavorite`, _data);
    }
    /**
     * CaseNFavorite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async CaseNFavorite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casenfavorite`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/casenfavorite`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/casenfavorite`, _data);
        }
        return this.http.post(`/cases/${_context.case}/casenfavorite`, _data);
    }
    /**
     * ConfirmChange
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async ConfirmChange(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/confirmchange`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/cases/${_context.case}/confirmchange`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/cases/${_context.case}/confirmchange`, _data);
        }
        return this.http.put(`/cases/${_context.case}/confirmchange`, _data);
    }
    /**
     * Confirmstorychange
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async Confirmstorychange(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/confirmstorychange`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/confirmstorychange`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/confirmstorychange`, _data);
        }
        return this.http.post(`/cases/${_context.case}/confirmstorychange`, _data);
    }
    /**
     * GetByTestTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async GetByTestTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/getbytesttask`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.story && _context.case) {
            const res = await this.http.get(`/stories/${_context.story}/cases/${_context.case}/getbytesttask`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.product && _context.case) {
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}/getbytesttask`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        const res = await this.http.get(`/cases/${_context.case}/getbytesttask`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
        return res;
    }
    /**
     * GetTestTaskCntRun
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async GetTestTaskCntRun(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/gettesttaskcntrun`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/cases/${_context.case}/gettesttaskcntrun`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/cases/${_context.case}/gettesttaskcntrun`, _data);
        }
        return this.http.put(`/cases/${_context.case}/gettesttaskcntrun`, _data);
    }
    /**
     * LinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async LinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/linkcase`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/linkcase`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/linkcase`, _data);
        }
        return this.http.post(`/cases/${_context.case}/linkcase`, _data);
    }
    /**
     * MobLinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async MobLinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/moblinkcase`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/moblinkcase`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/moblinkcase`, _data);
        }
        return this.http.post(`/cases/${_context.case}/moblinkcase`, _data);
    }
    /**
     * RunCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async RunCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/runcase`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/runcase`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/runcase`, _data);
        }
        return this.http.post(`/cases/${_context.case}/runcase`, _data);
    }
    /**
     * RunCases
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async RunCases(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/runcases`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/runcases`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/runcases`, _data);
        }
        return this.http.post(`/cases/${_context.case}/runcases`, _data);
    }
    /**
     * TestRunCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async TestRunCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testruncase`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/testruncase`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/testruncase`, _data);
        }
        return this.http.post(`/cases/${_context.case}/testruncase`, _data);
    }
    /**
     * TestRunCases
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async TestRunCases(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testruncases`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/testruncases`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/testruncases`, _data);
        }
        return this.http.post(`/cases/${_context.case}/testruncases`, _data);
    }
    /**
     * TestsuitelinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async TestsuitelinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testsuitelinkcase`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/testsuitelinkcase`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/testsuitelinkcase`, _data);
        }
        return this.http.post(`/cases/${_context.case}/testsuitelinkcase`, _data);
    }
    /**
     * UnlinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async UnlinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/unlinkcase`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/unlinkcase`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/unlinkcase`, _data);
        }
        return this.http.post(`/cases/${_context.case}/unlinkcase`, _data);
    }
    /**
     * UnlinkCases
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async UnlinkCases(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/unlinkcases`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/unlinkcases`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/unlinkcases`, _data);
        }
        return this.http.post(`/cases/${_context.case}/unlinkcases`, _data);
    }
    /**
     * UnlinkSuiteCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async UnlinkSuiteCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/unlinksuitecase`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/unlinksuitecase`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/unlinksuitecase`, _data);
        }
        return this.http.post(`/cases/${_context.case}/unlinksuitecase`, _data);
    }
    /**
     * UnlinkSuiteCases
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async UnlinkSuiteCases(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/unlinksuitecases`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/unlinksuitecases`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/unlinksuitecases`, _data);
        }
        return this.http.post(`/cases/${_context.case}/unlinksuitecases`, _data);
    }
    /**
     * FetchBatchNew
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchBatchNew(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchbatchnew`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchbatchnew`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchbatchnew`, _data);
        }
        return this.http.post(`/cases/fetchbatchnew`, _data);
    }
    /**
     * FetchCurOpenedCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchCurOpenedCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchcuropenedcase`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchcuropenedcase`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchcuropenedcase`, _data);
        }
        return this.http.post(`/cases/fetchcuropenedcase`, _data);
    }
    /**
     * FetchCurSuite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchCurSuite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchcursuite`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchcursuite`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchcursuite`, _data);
        }
        return this.http.post(`/cases/fetchcursuite`, _data);
    }
    /**
     * FetchCurTestTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchCurTestTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchcurtesttask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchcurtesttask`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchcurtesttask`, _data);
        }
        return this.http.post(`/cases/fetchcurtesttask`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchdefault`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchdefault`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchdefault`, _data);
        }
        return this.http.post(`/cases/fetchdefault`, _data);
    }
    /**
     * FetchESBulk
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchESBulk(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchesbulk`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchesbulk`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchesbulk`, _data);
        }
        return this.http.post(`/cases/fetchesbulk`, _data);
    }
    /**
     * FetchModuleRePortCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchModuleRePortCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchmodulereportcase`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchmodulereportcase`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchmodulereportcase`, _data);
        }
        return this.http.post(`/cases/fetchmodulereportcase`, _data);
    }
    /**
     * FetchModuleRePortCaseEntry
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchModuleRePortCaseEntry(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchmodulereportcaseentry`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchmodulereportcaseentry`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchmodulereportcaseentry`, _data);
        }
        return this.http.post(`/cases/fetchmodulereportcaseentry`, _data);
    }
    /**
     * FetchModuleRePortCase_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchModuleRePortCase_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchmodulereportcase_project`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchmodulereportcase_project`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchmodulereportcase_project`, _data);
        }
        return this.http.post(`/cases/fetchmodulereportcase_project`, _data);
    }
    /**
     * FetchMyFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchMyFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchmyfavorites`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchmyfavorites`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchmyfavorites`, _data);
        }
        return this.http.post(`/cases/fetchmyfavorites`, _data);
    }
    /**
     * FetchNotCurTestSuite
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchNotCurTestSuite(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchnotcurtestsuite`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchnotcurtestsuite`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchnotcurtestsuite`, _data);
        }
        return this.http.post(`/cases/fetchnotcurtestsuite`, _data);
    }
    /**
     * FetchNotCurTestTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchNotCurTestTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchnotcurtesttask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchnotcurtesttask`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchnotcurtesttask`, _data);
        }
        return this.http.post(`/cases/fetchnotcurtesttask`, _data);
    }
    /**
     * FetchNotCurTestTaskProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchNotCurTestTaskProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchnotcurtesttaskproject`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchnotcurtesttaskproject`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchnotcurtesttaskproject`, _data);
        }
        return this.http.post(`/cases/fetchnotcurtesttaskproject`, _data);
    }
    /**
     * FetchRePortCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchRePortCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchreportcase`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchreportcase`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchreportcase`, _data);
        }
        return this.http.post(`/cases/fetchreportcase`, _data);
    }
    /**
     * FetchRePortCaseEntry
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchRePortCaseEntry(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchreportcaseentry`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchreportcaseentry`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchreportcaseentry`, _data);
        }
        return this.http.post(`/cases/fetchreportcaseentry`, _data);
    }
    /**
     * FetchRePortCase_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchRePortCase_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchreportcase_project`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchreportcase_project`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchreportcase_project`, _data);
        }
        return this.http.post(`/cases/fetchreportcase_project`, _data);
    }
    /**
     * FetchRunERRePortCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchRunERRePortCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchrunerreportcase`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchrunerreportcase`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchrunerreportcase`, _data);
        }
        return this.http.post(`/cases/fetchrunerreportcase`, _data);
    }
    /**
     * FetchRunERRePortCaseEntry
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchRunERRePortCaseEntry(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchrunerreportcaseentry`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchrunerreportcaseentry`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchrunerreportcaseentry`, _data);
        }
        return this.http.post(`/cases/fetchrunerreportcaseentry`, _data);
    }
    /**
     * FetchRunERRePortCase_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchRunERRePortCase_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchrunerreportcase_project`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchrunerreportcase_project`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchrunerreportcase_project`, _data);
        }
        return this.http.post(`/cases/fetchrunerreportcase_project`, _data);
    }
    /**
     * FetchRunRePortCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchRunRePortCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchrunreportcase`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchrunreportcase`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchrunreportcase`, _data);
        }
        return this.http.post(`/cases/fetchrunreportcase`, _data);
    }
    /**
     * FetchRunRePortCaseEntry
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchRunRePortCaseEntry(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchrunreportcaseentry`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchrunreportcaseentry`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchrunreportcaseentry`, _data);
        }
        return this.http.post(`/cases/fetchrunreportcaseentry`, _data);
    }
    /**
     * FetchRunRePortCase_Project
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async FetchRunRePortCase_Project(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/fetchrunreportcase_project`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/cases/fetchrunreportcase_project`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/cases/fetchrunreportcase_project`, _data);
        }
        return this.http.post(`/cases/fetchrunreportcase_project`, _data);
    }

    /**
     * ConfirmChangeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async ConfirmChangeBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/confirmchangebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/confirmchangebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/confirmchangebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/confirmchangebatch`,_data);
    }

    /**
     * ConfirmstorychangeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async ConfirmstorychangeBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/confirmstorychangebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/confirmstorychangebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/confirmstorychangebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/confirmstorychangebatch`,_data);
    }

    /**
     * GetByTestTaskBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async GetByTestTaskBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/getbytesttaskbatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/getbytesttaskbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/getbytesttaskbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/getbytesttaskbatch`,_data);
    }

    /**
     * GetTestTaskCntRunBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async GetTestTaskCntRunBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/gettesttaskcntrunbatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/gettesttaskcntrunbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/gettesttaskcntrunbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/gettesttaskcntrunbatch`,_data);
    }

    /**
     * LinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async LinkCaseBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/linkcasebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/linkcasebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/linkcasebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/linkcasebatch`,_data);
    }

    /**
     * MobLinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async MobLinkCaseBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/moblinkcasebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/moblinkcasebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/moblinkcasebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/moblinkcasebatch`,_data);
    }

    /**
     * RunCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async RunCaseBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/runcasebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/runcasebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/runcasebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/runcasebatch`,_data);
    }

    /**
     * TestRunCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async TestRunCaseBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/testruncasebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/testruncasebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/testruncasebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/testruncasebatch`,_data);
    }

    /**
     * TestsuitelinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async TestsuitelinkCaseBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/testsuitelinkcasebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/testsuitelinkcasebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/testsuitelinkcasebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/testsuitelinkcasebatch`,_data);
    }

    /**
     * UnlinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async UnlinkCaseBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/unlinkcasebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/unlinkcasebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/unlinkcasebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/unlinkcasebatch`,_data);
    }

    /**
     * UnlinkSuiteCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async UnlinkSuiteCaseBatch(_context: any = {},_data: any = {}): Promise<IHttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/unlinksuitecasebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/stories/${_context.story}/cases/unlinksuitecasebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return await this.http.post(`/products/${_context.product}/cases/unlinksuitecasebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return await this.http.post(`/cases/unlinksuitecasebatch`,_data);
    }
}
