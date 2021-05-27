import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IHistory, History } from '../../entities';
import keys from '../../entities/history/history-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 操作历史服务对象基类
 *
 * @export
 * @class HistoryBaseService
 * @extends {EntityBaseService}
 */
export class HistoryBaseService extends EntityBaseService<IHistory> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'History';
    protected APPDENAMEPLURAL = 'Histories';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'diff';
    protected quickSearchFields = ['diff',];
    protected selectContextParam = {
        action: 'action',
    };

    newEntity(data: IHistory): History {
        return new History(data);
    }

    async addLocal(context: IContext, entity: IHistory): Promise<IHistory | null> {
        return this.cache.add(context, new History(entity) as any);
    }

    async createLocal(context: IContext, entity: IHistory): Promise<IHistory | null> {
        return super.createLocal(context, new History(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IHistory> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.action && entity.action !== '') {
            const s = await ___ibz___.gs.getActionService();
            const data = await s.getLocal2(context, entity.action);
            if (data) {
                entity.action = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IHistory): Promise<IHistory> {
        return super.updateLocal(context, new History(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IHistory = {}): Promise<IHistory> {
        if (_context.action && _context.action !== '') {
            const s = await ___ibz___.gs.getActionService();
            const data = await s.getLocal2(_context, _context.action);
            if (data) {
                entity.action = data.id;
            }
        }
        return new History(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof HistoryService
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
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
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
     * @memberof HistoryService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action && _context.history) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/histories/${_context.history}/select`);
        }
        if (_context.project && _context.action && _context.history) {
            return this.http.get(`/projects/${_context.project}/actions/${_context.action}/histories/${_context.history}/select`);
        }
        if (_context.story && _context.action && _context.history) {
            return this.http.get(`/stories/${_context.story}/actions/${_context.action}/histories/${_context.history}/select`);
        }
        if (_context.product && _context.action && _context.history) {
            return this.http.get(`/products/${_context.product}/actions/${_context.action}/histories/${_context.history}/select`);
        }
        return this.http.get(`/histories/${_context.history}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof HistoryService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/histories`, _data);
        }
        if (_context.project && _context.action && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/histories`, _data);
        }
        if (_context.story && _context.action && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/histories`, _data);
        }
        if (_context.product && _context.action && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/actions/${_context.action}/histories`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/histories`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof HistoryService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action && _context.history) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/histories/${_context.history}`, _data);
        }
        if (_context.project && _context.action && _context.history) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/actions/${_context.action}/histories/${_context.history}`, _data);
        }
        if (_context.story && _context.action && _context.history) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/actions/${_context.action}/histories/${_context.history}`, _data);
        }
        if (_context.product && _context.action && _context.history) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/actions/${_context.action}/histories/${_context.history}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/histories/${_context.history}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof HistoryService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action && _context.history) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/histories/${_context.history}`);
        }
        if (_context.project && _context.action && _context.history) {
            return this.http.delete(`/projects/${_context.project}/actions/${_context.action}/histories/${_context.history}`);
        }
        if (_context.story && _context.action && _context.history) {
            return this.http.delete(`/stories/${_context.story}/actions/${_context.action}/histories/${_context.history}`);
        }
        if (_context.product && _context.action && _context.history) {
            return this.http.delete(`/products/${_context.product}/actions/${_context.action}/histories/${_context.history}`);
        }
        return this.http.delete(`/histories/${_context.history}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof HistoryService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action && _context.history) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/histories/${_context.history}`);
            return res;
        }
        if (_context.project && _context.action && _context.history) {
            const res = await this.http.get(`/projects/${_context.project}/actions/${_context.action}/histories/${_context.history}`);
            return res;
        }
        if (_context.story && _context.action && _context.history) {
            const res = await this.http.get(`/stories/${_context.story}/actions/${_context.action}/histories/${_context.history}`);
            return res;
        }
        if (_context.product && _context.action && _context.history) {
            const res = await this.http.get(`/products/${_context.product}/actions/${_context.action}/histories/${_context.history}`);
            return res;
        }
        const res = await this.http.get(`/histories/${_context.history}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof HistoryService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/histories/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.action && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/actions/${_context.action}/histories/getdraft`, _data);
            return res;
        }
        if (_context.story && _context.action && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/actions/${_context.action}/histories/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.action && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/actions/${_context.action}/histories/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/histories/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof HistoryService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.project && _context.action && true) {
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.story && _context.action && true) {
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.action && true) {
            return this.http.post(`/products/${_context.product}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        return this.http.post(`/histories/fetchdefault`, _data);
    }
}
