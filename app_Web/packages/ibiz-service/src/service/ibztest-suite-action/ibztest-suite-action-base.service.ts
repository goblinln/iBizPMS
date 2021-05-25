import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZTestSuiteAction, IBZTestSuiteAction } from '../../entities';
import keys from '../../entities/ibztest-suite-action/ibztest-suite-action-keys';

/**
 * 套件日志服务对象基类
 *
 * @export
 * @class IBZTestSuiteActionBaseService
 * @extends {EntityBaseService}
 */
export class IBZTestSuiteActionBaseService extends EntityBaseService<IIBZTestSuiteAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZTestSuiteAction';
    protected APPDENAMEPLURAL = 'IBZTestSuiteActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        testsuite: 'objectid',
    };

    newEntity(data: IIBZTestSuiteAction): IBZTestSuiteAction {
        return new IBZTestSuiteAction(data);
    }

    async addLocal(context: IContext, entity: IIBZTestSuiteAction): Promise<IIBZTestSuiteAction | null> {
        return this.cache.add(context, new IBZTestSuiteAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZTestSuiteAction): Promise<IIBZTestSuiteAction | null> {
        return super.createLocal(context, new IBZTestSuiteAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZTestSuiteAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getTestSuiteService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZTestSuiteAction): Promise<IIBZTestSuiteAction> {
        return super.updateLocal(context, new IBZTestSuiteAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZTestSuiteAction = {}): Promise<IIBZTestSuiteAction> {
        if (_context.testsuite && _context.testsuite !== '') {
            const s = await ___ibz___.gs.getTestSuiteService();
            const data = await s.getLocal2(_context, _context.testsuite);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IBZTestSuiteAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
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
     * @memberof IBZTestSuiteActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
            return this.http.get(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/select`);
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
            return this.http.get(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/select`);
        }
        return this.http.get(`/ibztestsuiteactions/${_context.ibztestsuiteaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions`, _data);
        }
        if (_context.testsuite && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibztestsuiteactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}`, _data);
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibztestsuiteactions/${_context.ibztestsuiteaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
            return this.http.delete(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}`);
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
            return this.http.delete(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}`);
        }
        return this.http.delete(`/ibztestsuiteactions/${_context.ibztestsuiteaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
            const res = await this.http.get(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}`);
            return res;
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
            const res = await this.http.get(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}`);
            return res;
        }
        const res = await this.http.get(`/ibztestsuiteactions/${_context.ibztestsuiteaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/getdraft`, _data);
            return res;
        }
        if (_context.testsuite && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testsuites/${_context.testsuite}/ibztestsuiteactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibztestsuiteactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/comment`, _data);
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/comment`, _data);
        }
        return this.http.post(`/ibztestsuiteactions/${_context.ibztestsuiteaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/createhis`, _data);
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/createhis`, _data);
        }
        return this.http.post(`/ibztestsuiteactions/${_context.ibztestsuiteaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/editcomment`, _data);
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/editcomment`, _data);
        }
        return this.http.post(`/ibztestsuiteactions/${_context.ibztestsuiteaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/managepmsee`, _data);
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibztestsuiteactions/${_context.ibztestsuiteaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/sendmarkdone`, _data);
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibztestsuiteactions/${_context.ibztestsuiteaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/sendtodo`, _data);
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibztestsuiteactions/${_context.ibztestsuiteaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/sendtoread`, _data);
        }
        if (_context.testsuite && _context.ibztestsuiteaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/${_context.ibztestsuiteaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibztestsuiteactions/${_context.ibztestsuiteaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && true) {
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/fetchdefault`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibztestsuiteactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestSuiteActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.testsuite && true) {
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/fetchtype`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/fetchtype`, _data);
        }
        return this.http.post(`/ibztestsuiteactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestSuiteActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/createhisbatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestsuiteactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestSuiteActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/editcommentbatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestsuiteactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestSuiteActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/managepmseebatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestsuiteactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestSuiteActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/sendmarkdonebatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestsuiteactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestSuiteActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/sendtodobatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestsuiteactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestSuiteActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/ibztestsuiteactions/sendtoreadbatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/ibztestsuiteactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestsuiteactions/sendtoreadbatch`,_data);
    }
}
