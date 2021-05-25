import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZTestReportAction, IBZTestReportAction } from '../../entities';
import keys from '../../entities/ibztest-report-action/ibztest-report-action-keys';

/**
 * 报告日志服务对象基类
 *
 * @export
 * @class IBZTestReportActionBaseService
 * @extends {EntityBaseService}
 */
export class IBZTestReportActionBaseService extends EntityBaseService<IIBZTestReportAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZTestReportAction';
    protected APPDENAMEPLURAL = 'IBZTestReportActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        testreport: 'objectid',
    };

    newEntity(data: IIBZTestReportAction): IBZTestReportAction {
        return new IBZTestReportAction(data);
    }

    async addLocal(context: IContext, entity: IIBZTestReportAction): Promise<IIBZTestReportAction | null> {
        return this.cache.add(context, new IBZTestReportAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZTestReportAction): Promise<IIBZTestReportAction | null> {
        return super.createLocal(context, new IBZTestReportAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZTestReportAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getTestReportService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZTestReportAction): Promise<IIBZTestReportAction> {
        return super.updateLocal(context, new IBZTestReportAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZTestReportAction = {}): Promise<IIBZTestReportAction> {
        if (_context.testreport && _context.testreport !== '') {
            const s = await ___ibz___.gs.getTestReportService();
            const data = await s.getLocal2(_context, _context.testreport);
            if (data) {
                entity.objectid = data.id;
            }
        }
        return new IBZTestReportAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
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
     * @memberof IBZTestReportActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
            return this.http.get(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/select`);
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
            return this.http.get(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/select`);
        }
        if (_context.testreport && _context.ibztestreportaction) {
            return this.http.get(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/select`);
        }
        return this.http.get(`/ibztestreportactions/${_context.ibztestreportaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions`, _data);
        }
        if (_context.product && _context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions`, _data);
        }
        if (_context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibztestreportactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}`, _data);
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}`, _data);
        }
        if (_context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibztestreportactions/${_context.ibztestreportaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
            return this.http.delete(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}`);
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
            return this.http.delete(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}`);
        }
        if (_context.testreport && _context.ibztestreportaction) {
            return this.http.delete(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}`);
        }
        return this.http.delete(`/ibztestreportactions/${_context.ibztestreportaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
            const res = await this.http.get(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}`);
            return res;
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
            const res = await this.http.get(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}`);
            return res;
        }
        if (_context.testreport && _context.ibztestreportaction) {
            const res = await this.http.get(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}`);
            return res;
        }
        const res = await this.http.get(`/ibztestreportactions/${_context.ibztestreportaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/getdraft`, _data);
            return res;
        }
        if (_context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testreports/${_context.testreport}/ibztestreportactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibztestreportactions/getdraft`, _data);
        return res;
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/comment`, _data);
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/comment`, _data);
        }
        if (_context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/comment`, _data);
        }
        return this.http.post(`/ibztestreportactions/${_context.ibztestreportaction}/comment`, _data);
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/createhis`, _data);
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/createhis`, _data);
        }
        if (_context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/createhis`, _data);
        }
        return this.http.post(`/ibztestreportactions/${_context.ibztestreportaction}/createhis`, _data);
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/editcomment`, _data);
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/editcomment`, _data);
        }
        if (_context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/editcomment`, _data);
        }
        return this.http.post(`/ibztestreportactions/${_context.ibztestreportaction}/editcomment`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/managepmsee`, _data);
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/managepmsee`, _data);
        }
        if (_context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibztestreportactions/${_context.ibztestreportaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/sendmarkdone`, _data);
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/sendmarkdone`, _data);
        }
        if (_context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibztestreportactions/${_context.ibztestreportaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/sendtodo`, _data);
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/sendtodo`, _data);
        }
        if (_context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibztestreportactions/${_context.ibztestreportaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/sendtoread`, _data);
        }
        if (_context.product && _context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/sendtoread`, _data);
        }
        if (_context.testreport && _context.ibztestreportaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/${_context.ibztestreportaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibztestreportactions/${_context.ibztestreportaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/fetchdefault`, _data);
        }
        if (_context.product && _context.testreport && true) {
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/fetchdefault`, _data);
        }
        if (_context.testreport && true) {
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibztestreportactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZTestReportActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/fetchtype`, _data);
        }
        if (_context.product && _context.testreport && true) {
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/fetchtype`, _data);
        }
        if (_context.testreport && true) {
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/fetchtype`, _data);
        }
        return this.http.post(`/ibztestreportactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestReportActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/createhisbatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/createhisbatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestreportactions/createhisbatch`,_data);
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestReportActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/editcommentbatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/editcommentbatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/editcommentbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestreportactions/editcommentbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestReportActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/managepmseebatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/managepmseebatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestreportactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestReportActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/sendmarkdonebatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestreportactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestReportActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/sendtodobatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/sendtodobatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestreportactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZTestReportActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/ibztestreportactions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/ibztestreportactions/sendtoreadbatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/ibztestreportactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibztestreportactions/sendtoreadbatch`,_data);
    }
}
