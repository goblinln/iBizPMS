import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZProReleaseAction, IBZProReleaseAction } from '../../entities';
import keys from '../../entities/ibzpro-release-action/ibzpro-release-action-keys';

/**
 * 发布日志服务对象基类
 *
 * @export
 * @class IBZProReleaseActionBaseService
 * @extends {EntityBaseService}
 */
export class IBZProReleaseActionBaseService extends EntityBaseService<IIBZProReleaseAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZProReleaseAction';
    protected APPDENAMEPLURAL = 'IBZProReleaseActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        release: 'objectid',
    };

    newEntity(data: IIBZProReleaseAction): IBZProReleaseAction {
        return new IBZProReleaseAction(data);
    }

    async addLocal(context: IContext, entity: IIBZProReleaseAction): Promise<IIBZProReleaseAction | null> {
        return this.cache.add(context, new IBZProReleaseAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZProReleaseAction): Promise<IIBZProReleaseAction | null> {
        return super.createLocal(context, new IBZProReleaseAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZProReleaseAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getReleaseService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZProReleaseAction): Promise<IIBZProReleaseAction> {
        return super.updateLocal(context, new IBZProReleaseAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZProReleaseAction = {}): Promise<IIBZProReleaseAction> {
        if (_context.release && _context.release !== '') {
            const s = await ___ibz___.gs.getReleaseService();
            const data = await s.getLocal2(_context, _context.release);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IBZProReleaseAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
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
     * @memberof IBZProReleaseActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
            return this.http.get(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/select`);
        }
        if (_context.release && _context.ibzproreleaseaction) {
            return this.http.get(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/select`);
        }
        return this.http.get(`/ibzproreleaseactions/${_context.ibzproreleaseaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions`, _data);
        }
        if (_context.release && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzproreleaseactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}`, _data);
        }
        if (_context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzproreleaseactions/${_context.ibzproreleaseaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
            return this.http.delete(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}`);
        }
        if (_context.release && _context.ibzproreleaseaction) {
            return this.http.delete(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}`);
        }
        return this.http.delete(`/ibzproreleaseactions/${_context.ibzproreleaseaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
            const res = await this.http.get(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}`);
            return res;
        }
        if (_context.release && _context.ibzproreleaseaction) {
            const res = await this.http.get(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}`);
            return res;
        }
        const res = await this.http.get(`/ibzproreleaseactions/${_context.ibzproreleaseaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/getdraft`, _data);
            return res;
        }
        if (_context.release && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/releases/${_context.release}/ibzproreleaseactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzproreleaseactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/comment`, _data);
        }
        if (_context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/comment`, _data);
        }
        return this.http.post(`/ibzproreleaseactions/${_context.ibzproreleaseaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/createhis`, _data);
        }
        if (_context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/createhis`, _data);
        }
        return this.http.post(`/ibzproreleaseactions/${_context.ibzproreleaseaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/editcomment`, _data);
        }
        if (_context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/editcomment`, _data);
        }
        return this.http.post(`/ibzproreleaseactions/${_context.ibzproreleaseaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/managepmsee`, _data);
        }
        if (_context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibzproreleaseactions/${_context.ibzproreleaseaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/sendmarkdone`, _data);
        }
        if (_context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibzproreleaseactions/${_context.ibzproreleaseaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/sendtodo`, _data);
        }
        if (_context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibzproreleaseactions/${_context.ibzproreleaseaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/sendtoread`, _data);
        }
        if (_context.release && _context.ibzproreleaseaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/${_context.ibzproreleaseaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibzproreleaseactions/${_context.ibzproreleaseaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && true) {
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/fetchdefault`, _data);
        }
        if (_context.release && true) {
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibzproreleaseactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProReleaseActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.release && true) {
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/fetchtype`, _data);
        }
        if (_context.release && true) {
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/fetchtype`, _data);
        }
        return this.http.post(`/ibzproreleaseactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProReleaseActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/createhisbatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreleaseactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProReleaseActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/editcommentbatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreleaseactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProReleaseActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/managepmseebatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreleaseactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProReleaseActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/sendmarkdonebatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreleaseactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProReleaseActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/sendtodobatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreleaseactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProReleaseActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/ibzproreleaseactions/sendtoreadbatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/ibzproreleaseactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreleaseactions/sendtoreadbatch`,_data);
    }
}
