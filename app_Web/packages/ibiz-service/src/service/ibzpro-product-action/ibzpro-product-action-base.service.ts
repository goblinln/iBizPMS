import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZProProductAction, IBZProProductAction } from '../../entities';
import keys from '../../entities/ibzpro-product-action/ibzpro-product-action-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品日志服务对象基类
 *
 * @export
 * @class IBZProProductActionBaseService
 * @extends {EntityBaseService}
 */
export class IBZProProductActionBaseService extends EntityBaseService<IIBZProProductAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZProProductAction';
    protected APPDENAMEPLURAL = 'IBZProProductActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        product: 'objectid',
    };

    newEntity(data: IIBZProProductAction): IBZProProductAction {
        return new IBZProProductAction(data);
    }

    async addLocal(context: IContext, entity: IIBZProProductAction): Promise<IIBZProProductAction | null> {
        return this.cache.add(context, new IBZProProductAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZProProductAction): Promise<IIBZProProductAction | null> {
        return super.createLocal(context, new IBZProProductAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZProProductAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZProProductAction): Promise<IIBZProProductAction> {
        return super.updateLocal(context, new IBZProProductAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZProProductAction = {}): Promise<IIBZProProductAction> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IBZProProductAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
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
            const strCond: any[] = ['AND', ['EQ', 'OBJECTTYPE','product']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getMobTypeCond() {
        if (!this.condCache.has('mobType')) {
            const strCond: any[] = ['AND', ['EQ', 'OBJECTID',{ type: 'DATACONTEXT', value: 'srfparentkey'}], ['EQ', 'OBJECTTYPE',{ type: 'DATACONTEXT', value: 'objecttype'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('mobType', cond);
            }
        }
        return this.condCache.get('mobType');
    }

    protected getProductTrendsCond() {
        return this.condCache.get('productTrends');
    }

    protected getSimpleCond() {
        return this.condCache.get('simple');
    }

    protected getTypeCond() {
        if (!this.condCache.has('type')) {
            const strCond: any[] = ['AND', ['EQ', 'OBJECTID',{ type: 'DATACONTEXT', value: 'srfparentkey'}], ['EQ', 'OBJECTTYPE',{ type: 'DATACONTEXT', value: 'objecttype'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('type', cond);
            }
        }
        return this.condCache.get('type');
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
     * @memberof IBZProProductActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
            return this.http.get(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}/select`);
        }
        return this.http.get(`/ibzproproductactions/${_context.ibzproproductaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
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
            return this.http.post(`/products/${_context.product}/ibzproproductactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzproproductactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzproproductactions/${_context.ibzproproductaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
            return this.http.delete(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}`);
        }
        return this.http.delete(`/ibzproproductactions/${_context.ibzproproductaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
            const res = await this.http.get(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}`);
            return res;
        }
        const res = await this.http.get(`/ibzproproductactions/${_context.ibzproproductaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/ibzproproductactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzproproductactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}/comment`, _data);
        }
        return this.http.post(`/ibzproproductactions/${_context.ibzproproductaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}/createhis`, _data);
        }
        return this.http.post(`/ibzproproductactions/${_context.ibzproproductaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}/editcomment`, _data);
        }
        return this.http.post(`/ibzproproductactions/${_context.ibzproproductaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibzproproductactions/${_context.ibzproproductaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibzproproductactions/${_context.ibzproproductaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibzproproductactions/${_context.ibzproproductaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.ibzproproductaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/${_context.ibzproproductaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibzproproductactions/${_context.ibzproproductaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/ibzproproductactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibzproproductactions/fetchdefault`, _data);
    }
    /**
     * FetchMobType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async FetchMobType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/ibzproproductactions/fetchmobtype`, _data);
        }
        return this.http.post(`/ibzproproductactions/fetchmobtype`, _data);
    }
    /**
     * FetchProductTrends
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async FetchProductTrends(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/ibzproproductactions/fetchproducttrends`, _data);
        }
        return this.http.post(`/ibzproproductactions/fetchproducttrends`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZProProductActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/ibzproproductactions/fetchtype`, _data);
        }
        return this.http.post(`/ibzproproductactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProductActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproproductactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProductActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproproductactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProductActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproproductactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProductActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproproductactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProductActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproproductactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZProProductActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/ibzproproductactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproproductactions/sendtoreadbatch`,_data);
    }
}
