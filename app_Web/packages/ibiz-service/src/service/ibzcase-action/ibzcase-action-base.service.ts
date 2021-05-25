import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZCaseAction, IBZCaseAction } from '../../entities';
import keys from '../../entities/ibzcase-action/ibzcase-action-keys';

/**
 * 测试用例日志服务对象基类
 *
 * @export
 * @class IBZCaseActionBaseService
 * @extends {EntityBaseService}
 */
export class IBZCaseActionBaseService extends EntityBaseService<IIBZCaseAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZCaseAction';
    protected APPDENAMEPLURAL = 'IBZCaseActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        case: 'objectid',
    };

    newEntity(data: IIBZCaseAction): IBZCaseAction {
        return new IBZCaseAction(data);
    }

    async addLocal(context: IContext, entity: IIBZCaseAction): Promise<IIBZCaseAction | null> {
        return this.cache.add(context, new IBZCaseAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZCaseAction): Promise<IIBZCaseAction | null> {
        return super.createLocal(context, new IBZCaseAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZCaseAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getCaseService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZCaseAction): Promise<IIBZCaseAction> {
        return super.updateLocal(context, new IBZCaseAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZCaseAction = {}): Promise<IIBZCaseAction> {
        if (_context.case && _context.case !== '') {
            const s = await ___ibz___.gs.getCaseService();
            const data = await s.getLocal2(_context, _context.case);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IBZCaseAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
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
     * @memberof IBZCaseActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/select`);
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
            return this.http.get(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/select`);
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
            return this.http.get(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/select`);
        }
        if (_context.case && _context.ibzcaseaction) {
            return this.http.get(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/select`);
        }
        return this.http.get(`/ibzcaseactions/${_context.ibzcaseaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions`, _data);
        }
        if (_context.story && _context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions`, _data);
        }
        if (_context.product && _context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions`, _data);
        }
        if (_context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/cases/${_context.case}/ibzcaseactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzcaseactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`, _data);
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`, _data);
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`, _data);
        }
        if (_context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzcaseactions/${_context.ibzcaseaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`);
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
            return this.http.delete(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`);
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
            return this.http.delete(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`);
        }
        if (_context.case && _context.ibzcaseaction) {
            return this.http.delete(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`);
        }
        return this.http.delete(`/ibzcaseactions/${_context.ibzcaseaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`);
            return res;
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
            const res = await this.http.get(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`);
            return res;
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`);
            return res;
        }
        if (_context.case && _context.ibzcaseaction) {
            const res = await this.http.get(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}`);
            return res;
        }
        const res = await this.http.get(`/ibzcaseactions/${_context.ibzcaseaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/getdraft`, _data);
            return res;
        }
        if (_context.story && _context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/getdraft`, _data);
            return res;
        }
        if (_context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/cases/${_context.case}/ibzcaseactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzcaseactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/comment`, _data);
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/comment`, _data);
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/comment`, _data);
        }
        if (_context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/comment`, _data);
        }
        return this.http.post(`/ibzcaseactions/${_context.ibzcaseaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/createhis`, _data);
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/createhis`, _data);
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/createhis`, _data);
        }
        if (_context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/createhis`, _data);
        }
        return this.http.post(`/ibzcaseactions/${_context.ibzcaseaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/editcomment`, _data);
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/editcomment`, _data);
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/editcomment`, _data);
        }
        if (_context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/editcomment`, _data);
        }
        return this.http.post(`/ibzcaseactions/${_context.ibzcaseaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/managepmsee`, _data);
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/managepmsee`, _data);
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/managepmsee`, _data);
        }
        if (_context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibzcaseactions/${_context.ibzcaseaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendmarkdone`, _data);
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendmarkdone`, _data);
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendmarkdone`, _data);
        }
        if (_context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibzcaseactions/${_context.ibzcaseaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendtodo`, _data);
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendtodo`, _data);
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendtodo`, _data);
        }
        if (_context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibzcaseactions/${_context.ibzcaseaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendtoread`, _data);
        }
        if (_context.story && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendtoread`, _data);
        }
        if (_context.product && _context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendtoread`, _data);
        }
        if (_context.case && _context.ibzcaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/${_context.ibzcaseaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibzcaseactions/${_context.ibzcaseaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/fetchdefault`, _data);
        }
        if (_context.story && _context.case && true) {
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/fetchdefault`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/fetchdefault`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibzcaseactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/fetchtype`, _data);
        }
        if (_context.story && _context.case && true) {
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/fetchtype`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/fetchtype`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/fetchtype`, _data);
        }
        return this.http.post(`/ibzcaseactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZCaseActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/createhisbatch`,_data);
        }
        if(_context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/createhisbatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/createhisbatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzcaseactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZCaseActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/editcommentbatch`,_data);
        }
        if(_context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/editcommentbatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/editcommentbatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzcaseactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZCaseActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/managepmseebatch`,_data);
        }
        if(_context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/managepmseebatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/managepmseebatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzcaseactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZCaseActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/sendmarkdonebatch`,_data);
        }
        if(_context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/sendmarkdonebatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzcaseactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZCaseActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/sendtodobatch`,_data);
        }
        if(_context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/sendtodobatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/sendtodobatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzcaseactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZCaseActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/sendtoreadbatch`,_data);
        }
        if(_context.story && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/ibzcaseactions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/ibzcaseactions/sendtoreadbatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/ibzcaseactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzcaseactions/sendtoreadbatch`,_data);
    }
}
