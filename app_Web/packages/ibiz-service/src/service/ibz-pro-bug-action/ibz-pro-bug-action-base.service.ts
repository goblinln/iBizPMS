import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzProBugAction, IbzProBugAction } from '../../entities';
import keys from '../../entities/ibz-pro-bug-action/ibz-pro-bug-action-keys';

/**
 * Bug日志服务对象基类
 *
 * @export
 * @class IbzProBugActionBaseService
 * @extends {EntityBaseService}
 */
export class IbzProBugActionBaseService extends EntityBaseService<IIbzProBugAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzProBugAction';
    protected APPDENAMEPLURAL = 'IbzProBugActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        bug: 'objectid',
    };

    newEntity(data: IIbzProBugAction): IbzProBugAction {
        return new IbzProBugAction(data);
    }

    async addLocal(context: IContext, entity: IIbzProBugAction): Promise<IIbzProBugAction | null> {
        return this.cache.add(context, new IbzProBugAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzProBugAction): Promise<IIbzProBugAction | null> {
        return super.createLocal(context, new IbzProBugAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzProBugAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getBugService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzProBugAction): Promise<IIbzProBugAction> {
        return super.updateLocal(context, new IbzProBugAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzProBugAction = {}): Promise<IIbzProBugAction> {
        if (_context.bug && _context.bug !== '') {
            const s = await ___ibz___.gs.getBugService();
            const data = await s.getLocal2(_context, _context.bug);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IbzProBugAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
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
     * @memberof IbzProBugActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/select`);
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
            return this.http.get(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/select`);
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
            return this.http.get(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/select`);
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
            return this.http.get(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/select`);
        }
        if (_context.bug && _context.ibzprobugaction) {
            return this.http.get(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/select`);
        }
        return this.http.get(`/ibzprobugactions/${_context.ibzprobugaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions`, _data);
        }
        if (_context.project && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions`, _data);
        }
        if (_context.story && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions`, _data);
        }
        if (_context.product && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions`, _data);
        }
        if (_context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzprobugactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`, _data);
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`, _data);
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`, _data);
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`, _data);
        }
        if (_context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzprobugactions/${_context.ibzprobugaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`);
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
            return this.http.delete(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`);
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
            return this.http.delete(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`);
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
            return this.http.delete(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`);
        }
        if (_context.bug && _context.ibzprobugaction) {
            return this.http.delete(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`);
        }
        return this.http.delete(`/ibzprobugactions/${_context.ibzprobugaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`);
            return res;
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
            const res = await this.http.get(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`);
            return res;
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
            const res = await this.http.get(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`);
            return res;
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
            const res = await this.http.get(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`);
            return res;
        }
        if (_context.bug && _context.ibzprobugaction) {
            const res = await this.http.get(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}`);
            return res;
        }
        const res = await this.http.get(`/ibzprobugactions/${_context.ibzprobugaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/getdraft`, _data);
            return res;
        }
        if (_context.story && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/getdraft`, _data);
            return res;
        }
        if (_context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/bugs/${_context.bug}/ibzprobugactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzprobugactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/comment`, _data);
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/comment`, _data);
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/comment`, _data);
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/comment`, _data);
        }
        if (_context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/comment`, _data);
        }
        return this.http.post(`/ibzprobugactions/${_context.ibzprobugaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/createhis`, _data);
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/createhis`, _data);
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/createhis`, _data);
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/createhis`, _data);
        }
        if (_context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/createhis`, _data);
        }
        return this.http.post(`/ibzprobugactions/${_context.ibzprobugaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/editcomment`, _data);
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/editcomment`, _data);
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/editcomment`, _data);
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/editcomment`, _data);
        }
        if (_context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/editcomment`, _data);
        }
        return this.http.post(`/ibzprobugactions/${_context.ibzprobugaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/managepmsee`, _data);
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/managepmsee`, _data);
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/managepmsee`, _data);
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/managepmsee`, _data);
        }
        if (_context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibzprobugactions/${_context.ibzprobugaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendmarkdone`, _data);
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendmarkdone`, _data);
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendmarkdone`, _data);
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendmarkdone`, _data);
        }
        if (_context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibzprobugactions/${_context.ibzprobugaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendtodo`, _data);
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendtodo`, _data);
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendtodo`, _data);
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendtodo`, _data);
        }
        if (_context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibzprobugactions/${_context.ibzprobugaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendtoread`, _data);
        }
        if (_context.project && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendtoread`, _data);
        }
        if (_context.story && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendtoread`, _data);
        }
        if (_context.product && _context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendtoread`, _data);
        }
        if (_context.bug && _context.ibzprobugaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/${_context.ibzprobugaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibzprobugactions/${_context.ibzprobugaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/fetchdefault`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/fetchdefault`, _data);
        }
        if (_context.story && _context.bug && true) {
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/fetchdefault`, _data);
        }
        if (_context.product && _context.bug && true) {
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/fetchdefault`, _data);
        }
        if (_context.bug && true) {
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibzprobugactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProBugActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.bug && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/fetchtype`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/fetchtype`, _data);
        }
        if (_context.story && _context.bug && true) {
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/fetchtype`, _data);
        }
        if (_context.product && _context.bug && true) {
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/fetchtype`, _data);
        }
        if (_context.bug && true) {
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/fetchtype`, _data);
        }
        return this.http.post(`/ibzprobugactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBugActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/createhisbatch`,_data);
        }
        if(_context.project && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/createhisbatch`,_data);
        }
        if(_context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/createhisbatch`,_data);
        }
        if(_context.product && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/createhisbatch`,_data);
        }
        if(_context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobugactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBugActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/editcommentbatch`,_data);
        }
        if(_context.project && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/editcommentbatch`,_data);
        }
        if(_context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/editcommentbatch`,_data);
        }
        if(_context.product && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/editcommentbatch`,_data);
        }
        if(_context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobugactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBugActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/managepmseebatch`,_data);
        }
        if(_context.project && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/managepmseebatch`,_data);
        }
        if(_context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/managepmseebatch`,_data);
        }
        if(_context.product && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/managepmseebatch`,_data);
        }
        if(_context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobugactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBugActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/sendmarkdonebatch`,_data);
        }
        if(_context.project && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/sendmarkdonebatch`,_data);
        }
        if(_context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/sendmarkdonebatch`,_data);
        }
        if(_context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobugactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBugActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/sendtodobatch`,_data);
        }
        if(_context.project && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/sendtodobatch`,_data);
        }
        if(_context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/sendtodobatch`,_data);
        }
        if(_context.product && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/sendtodobatch`,_data);
        }
        if(_context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobugactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProBugActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/sendtoreadbatch`,_data);
        }
        if(_context.project && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/ibzprobugactions/sendtoreadbatch`,_data);
        }
        if(_context.story && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/bugs/${_context.bug}/ibzprobugactions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/ibzprobugactions/sendtoreadbatch`,_data);
        }
        if(_context.bug && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/bugs/${_context.bug}/ibzprobugactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprobugactions/sendtoreadbatch`,_data);
    }
}
