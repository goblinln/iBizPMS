import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZStoryAction, IBZStoryAction } from '../../entities';
import keys from '../../entities/ibzstory-action/ibzstory-action-keys';

/**
 * 需求日志服务对象基类
 *
 * @export
 * @class IBZStoryActionBaseService
 * @extends {EntityBaseService}
 */
export class IBZStoryActionBaseService extends EntityBaseService<IIBZStoryAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZStoryAction';
    protected APPDENAMEPLURAL = 'IBZStoryActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        story: 'objectid',
    };

    newEntity(data: IIBZStoryAction): IBZStoryAction {
        return new IBZStoryAction(data);
    }

    async addLocal(context: IContext, entity: IIBZStoryAction): Promise<IIBZStoryAction | null> {
        return this.cache.add(context, new IBZStoryAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZStoryAction): Promise<IIBZStoryAction | null> {
        return super.createLocal(context, new IBZStoryAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZStoryAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZStoryAction): Promise<IIBZStoryAction> {
        return super.updateLocal(context, new IBZStoryAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZStoryAction = {}): Promise<IIBZStoryAction> {
        if (_context.story && _context.story !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(_context, _context.story);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IBZStoryAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZStoryActionService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZStoryActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.ibzstoryaction) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/ibzstoryactions/${_context.ibzstoryaction}/select`);
        }
        if (_context.story && _context.ibzstoryaction) {
            return this.http.get(`/stories/${_context.story}/ibzstoryactions/${_context.ibzstoryaction}/select`);
        }
        return this.http.get(`/ibzstoryactions/${_context.ibzstoryaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZStoryActionService
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
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/ibzstoryactions`, _data);
        }
        if (_context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/ibzstoryactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzstoryactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZStoryActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.ibzstoryaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/ibzstoryactions/${_context.ibzstoryaction}`, _data);
        }
        if (_context.story && _context.ibzstoryaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/ibzstoryactions/${_context.ibzstoryaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzstoryactions/${_context.ibzstoryaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZStoryActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.ibzstoryaction) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/ibzstoryactions/${_context.ibzstoryaction}`);
        }
        if (_context.story && _context.ibzstoryaction) {
            return this.http.delete(`/stories/${_context.story}/ibzstoryactions/${_context.ibzstoryaction}`);
        }
        return this.http.delete(`/ibzstoryactions/${_context.ibzstoryaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZStoryActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.ibzstoryaction) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/ibzstoryactions/${_context.ibzstoryaction}`);
            return res;
        }
        if (_context.story && _context.ibzstoryaction) {
            const res = await this.http.get(`/stories/${_context.story}/ibzstoryactions/${_context.ibzstoryaction}`);
            return res;
        }
        const res = await this.http.get(`/ibzstoryactions/${_context.ibzstoryaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZStoryActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/ibzstoryactions/getdraft`, _data);
            return res;
        }
        if (_context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/ibzstoryactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzstoryactions/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZStoryActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/ibzstoryactions/fetchdefault`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/ibzstoryactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibzstoryactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZStoryActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/ibzstoryactions/fetchtype`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/ibzstoryactions/fetchtype`, _data);
        }
        return this.http.post(`/ibzstoryactions/fetchtype`, _data);
    }
}
