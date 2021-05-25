import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductPlanAction, ProductPlanAction } from '../../entities';
import keys from '../../entities/product-plan-action/product-plan-action-keys';

/**
 * 产品计划日志服务对象基类
 *
 * @export
 * @class ProductPlanActionBaseService
 * @extends {EntityBaseService}
 */
export class ProductPlanActionBaseService extends EntityBaseService<IProductPlanAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductPlanAction';
    protected APPDENAMEPLURAL = 'ProductPlanActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        productplan: 'objectid',
    };

    newEntity(data: IProductPlanAction): ProductPlanAction {
        return new ProductPlanAction(data);
    }

    async addLocal(context: IContext, entity: IProductPlanAction): Promise<IProductPlanAction | null> {
        return this.cache.add(context, new ProductPlanAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductPlanAction): Promise<IProductPlanAction | null> {
        return super.createLocal(context, new ProductPlanAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductPlanAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getProductPlanService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductPlanAction): Promise<IProductPlanAction> {
        return super.updateLocal(context, new ProductPlanAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductPlanAction = {}): Promise<IProductPlanAction> {
        if (_context.productplan && _context.productplan !== '') {
            const s = await ___ibz___.gs.getProductPlanService();
            const data = await s.getLocal2(_context, _context.productplan);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new ProductPlanAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
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
     * @memberof ProductPlanActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
            return this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/select`);
        }
        if (_context.productplan && _context.productplanaction) {
            return this.http.get(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/select`);
        }
        return this.http.get(`/productplanactions/${_context.productplanaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions`, _data);
        }
        if (_context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/productplans/${_context.productplan}/productplanactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/productplanactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}`, _data);
        }
        if (_context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/productplanactions/${_context.productplanaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
            return this.http.delete(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}`);
        }
        if (_context.productplan && _context.productplanaction) {
            return this.http.delete(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}`);
        }
        return this.http.delete(`/productplanactions/${_context.productplanaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}`);
            return res;
        }
        if (_context.productplan && _context.productplanaction) {
            const res = await this.http.get(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}`);
            return res;
        }
        const res = await this.http.get(`/productplanactions/${_context.productplanaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/getdraft`, _data);
            return res;
        }
        if (_context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/productplans/${_context.productplan}/productplanactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/productplanactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/comment`, _data);
        }
        if (_context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/comment`, _data);
        }
        return this.http.post(`/productplanactions/${_context.productplanaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/createhis`, _data);
        }
        if (_context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/createhis`, _data);
        }
        return this.http.post(`/productplanactions/${_context.productplanaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/editcomment`, _data);
        }
        if (_context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/editcomment`, _data);
        }
        return this.http.post(`/productplanactions/${_context.productplanaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/managepmsee`, _data);
        }
        if (_context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/managepmsee`, _data);
        }
        return this.http.post(`/productplanactions/${_context.productplanaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/sendmarkdone`, _data);
        }
        if (_context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/productplanactions/${_context.productplanaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/sendtodo`, _data);
        }
        if (_context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/sendtodo`, _data);
        }
        return this.http.post(`/productplanactions/${_context.productplanaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/sendtoread`, _data);
        }
        if (_context.productplan && _context.productplanaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/${_context.productplanaction}/sendtoread`, _data);
        }
        return this.http.post(`/productplanactions/${_context.productplanaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/fetchdefault`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/fetchdefault`, _data);
        }
        return this.http.post(`/productplanactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductPlanActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/fetchtype`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/fetchtype`, _data);
        }
        return this.http.post(`/productplanactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/createhisbatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplanactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/editcommentbatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplanactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/managepmseebatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplanactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/sendmarkdonebatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplanactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/sendtodobatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplanactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductPlanActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/productplanactions/sendtoreadbatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/productplanactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/productplanactions/sendtoreadbatch`,_data);
    }
}
