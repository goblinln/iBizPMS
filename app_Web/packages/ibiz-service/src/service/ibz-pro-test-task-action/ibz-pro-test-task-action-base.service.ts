import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzProTestTaskAction, IbzProTestTaskAction } from '../../entities';
import keys from '../../entities/ibz-pro-test-task-action/ibz-pro-test-task-action-keys';

/**
 * 测试单日志服务对象基类
 *
 * @export
 * @class IbzProTestTaskActionBaseService
 * @extends {EntityBaseService}
 */
export class IbzProTestTaskActionBaseService extends EntityBaseService<IIbzProTestTaskAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzProTestTaskAction';
    protected APPDENAMEPLURAL = 'IbzProTestTaskActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        testtask: 'objectid',
    };

    newEntity(data: IIbzProTestTaskAction): IbzProTestTaskAction {
        return new IbzProTestTaskAction(data);
    }

    async addLocal(context: IContext, entity: IIbzProTestTaskAction): Promise<IIbzProTestTaskAction | null> {
        return this.cache.add(context, new IbzProTestTaskAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzProTestTaskAction): Promise<IIbzProTestTaskAction | null> {
        return super.createLocal(context, new IbzProTestTaskAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzProTestTaskAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getTestTaskService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzProTestTaskAction): Promise<IIbzProTestTaskAction> {
        return super.updateLocal(context, new IbzProTestTaskAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzProTestTaskAction = {}): Promise<IIbzProTestTaskAction> {
        if (_context.testtask && _context.testtask !== '') {
            const s = await ___ibz___.gs.getTestTaskService();
            const data = await s.getLocal2(_context, _context.testtask);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IbzProTestTaskAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
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
     * @memberof IbzProTestTaskActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
            return this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/select`);
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
            return this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/select`);
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
            return this.http.get(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/select`);
        }
        return this.http.get(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions`, _data);
        }
        if (_context.product && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions`, _data);
        }
        if (_context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzprotesttaskactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`, _data);
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`, _data);
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
            return this.http.delete(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`);
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
            return this.http.delete(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`);
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
            return this.http.delete(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`);
        }
        return this.http.delete(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
            const res = await this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`);
            return res;
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
            const res = await this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`);
            return res;
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
            const res = await this.http.get(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`);
            return res;
        }
        const res = await this.http.get(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/getdraft`, _data);
            return res;
        }
        if (_context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testtasks/${_context.testtask}/ibzprotesttaskactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzprotesttaskactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/comment`, _data);
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/comment`, _data);
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/comment`, _data);
        }
        return this.http.post(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/createhis`, _data);
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/createhis`, _data);
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/createhis`, _data);
        }
        return this.http.post(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/editcomment`, _data);
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/editcomment`, _data);
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/editcomment`, _data);
        }
        return this.http.post(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/managepmsee`, _data);
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/managepmsee`, _data);
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendmarkdone`, _data);
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendmarkdone`, _data);
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendtodo`, _data);
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendtodo`, _data);
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendtoread`, _data);
        }
        if (_context.product && _context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendtoread`, _data);
        }
        if (_context.testtask && _context.ibzprotesttaskaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibzprotesttaskactions/${_context.ibzprotesttaskaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/fetchdefault`, _data);
        }
        if (_context.product && _context.testtask && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/fetchdefault`, _data);
        }
        if (_context.testtask && true) {
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibzprotesttaskactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProTestTaskActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/fetchtype`, _data);
        }
        if (_context.product && _context.testtask && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/fetchtype`, _data);
        }
        if (_context.testtask && true) {
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/fetchtype`, _data);
        }
        return this.http.post(`/ibzprotesttaskactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProTestTaskActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/createhisbatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/createhisbatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprotesttaskactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProTestTaskActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/editcommentbatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/editcommentbatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprotesttaskactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProTestTaskActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/managepmseebatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/managepmseebatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprotesttaskactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProTestTaskActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/sendmarkdonebatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprotesttaskactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProTestTaskActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/sendtodobatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/sendtodobatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprotesttaskactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProTestTaskActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/ibzprotesttaskactions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/ibzprotesttaskactions/sendtoreadbatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/ibzprotesttaskactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzprotesttaskactions/sendtoreadbatch`,_data);
    }
}
