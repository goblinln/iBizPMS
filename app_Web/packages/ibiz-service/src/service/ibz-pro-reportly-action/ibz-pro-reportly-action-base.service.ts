import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzProReportlyAction, IbzProReportlyAction } from '../../entities';
import keys from '../../entities/ibz-pro-reportly-action/ibz-pro-reportly-action-keys';

/**
 * 汇报日志服务对象基类
 *
 * @export
 * @class IbzProReportlyActionBaseService
 * @extends {EntityBaseService}
 */
export class IbzProReportlyActionBaseService extends EntityBaseService<IIbzProReportlyAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzProReportlyAction';
    protected APPDENAMEPLURAL = 'IbzProReportlyActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        ibzreportly: 'objectid',
    };

    newEntity(data: IIbzProReportlyAction): IbzProReportlyAction {
        return new IbzProReportlyAction(data);
    }

    async addLocal(context: IContext, entity: IIbzProReportlyAction): Promise<IIbzProReportlyAction | null> {
        return this.cache.add(context, new IbzProReportlyAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzProReportlyAction): Promise<IIbzProReportlyAction | null> {
        return super.createLocal(context, new IbzProReportlyAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzProReportlyAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getIbzReportlyService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.ibzreportlyid;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzProReportlyAction): Promise<IIbzProReportlyAction> {
        return super.updateLocal(context, new IbzProReportlyAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzProReportlyAction = {}): Promise<IIbzProReportlyAction> {
        if (_context.ibzreportly && _context.ibzreportly !== '') {
            const s = await ___ibz___.gs.getIbzReportlyService();
            const data = await s.getLocal2(_context, _context.ibzreportly);
            if (data) {
                entity.objectid = data.ibzreportlyid;
            }
        }
        return new IbzProReportlyAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
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
     * @memberof IbzProReportlyActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && _context.ibzproreportlyaction) {
            return this.http.get(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/${_context.ibzproreportlyaction}/select`);
        }
        return this.http.get(`/ibzproreportlyactions/${_context.ibzproreportlyaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzproreportlyactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && _context.ibzproreportlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/${_context.ibzproreportlyaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzproreportlyactions/${_context.ibzproreportlyaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && _context.ibzproreportlyaction) {
            return this.http.delete(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/${_context.ibzproreportlyaction}`);
        }
        return this.http.delete(`/ibzproreportlyactions/${_context.ibzproreportlyaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && _context.ibzproreportlyaction) {
            const res = await this.http.get(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/${_context.ibzproreportlyaction}`);
            return res;
        }
        const res = await this.http.get(`/ibzproreportlyactions/${_context.ibzproreportlyaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzproreportlyactions/getdraft`, _data);
        return res;
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && _context.ibzproreportlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/${_context.ibzproreportlyaction}/createhis`, _data);
        }
        return this.http.post(`/ibzproreportlyactions/${_context.ibzproreportlyaction}/createhis`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && _context.ibzproreportlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/${_context.ibzproreportlyaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibzproreportlyactions/${_context.ibzproreportlyaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && _context.ibzproreportlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/${_context.ibzproreportlyaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibzproreportlyactions/${_context.ibzproreportlyaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && _context.ibzproreportlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/${_context.ibzproreportlyaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibzproreportlyactions/${_context.ibzproreportlyaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && _context.ibzproreportlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/${_context.ibzproreportlyaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibzproreportlyactions/${_context.ibzproreportlyaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && true) {
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibzproreportlyactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProReportlyActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzreportly && true) {
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/fetchtype`, _data);
        }
        return this.http.post(`/ibzproreportlyactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProReportlyActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.ibzreportly && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreportlyactions/createhisbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProReportlyActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.ibzreportly && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreportlyactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProReportlyActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.ibzreportly && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreportlyactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProReportlyActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.ibzreportly && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreportlyactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProReportlyActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.ibzreportly && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/ibzproreportlyactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzproreportlyactions/sendtoreadbatch`,_data);
    }
}
