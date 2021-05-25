import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzProMonthlyAction, IbzProMonthlyAction } from '../../entities';
import keys from '../../entities/ibz-pro-monthly-action/ibz-pro-monthly-action-keys';

/**
 * 月报日志服务对象基类
 *
 * @export
 * @class IbzProMonthlyActionBaseService
 * @extends {EntityBaseService}
 */
export class IbzProMonthlyActionBaseService extends EntityBaseService<IIbzProMonthlyAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzProMonthlyAction';
    protected APPDENAMEPLURAL = 'IbzProMonthlyActions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
        ibzmonthly: 'objectid',
    };

    newEntity(data: IIbzProMonthlyAction): IbzProMonthlyAction {
        return new IbzProMonthlyAction(data);
    }

    async addLocal(context: IContext, entity: IIbzProMonthlyAction): Promise<IIbzProMonthlyAction | null> {
        return this.cache.add(context, new IbzProMonthlyAction(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzProMonthlyAction): Promise<IIbzProMonthlyAction | null> {
        return super.createLocal(context, new IbzProMonthlyAction(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzProMonthlyAction> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.objectid && entity.objectid !== '') {
            const s = await ___ibz___.gs.getIbzMonthlyService();
            const data = await s.getLocal2(context, entity.objectid);
            if (data) {
                entity.objectid = data.ibzmonthlyid;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzProMonthlyAction): Promise<IIbzProMonthlyAction> {
        return super.updateLocal(context, new IbzProMonthlyAction(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzProMonthlyAction = {}): Promise<IIbzProMonthlyAction> {
        if (_context.ibzmonthly && _context.ibzmonthly !== '') {
            const s = await ___ibz___.gs.getIbzMonthlyService();
            const data = await s.getLocal2(_context, _context.ibzmonthly);
            if (data) {
                entity.objectid = data.ibzmonthlyid;
            }
        }
        return new IbzProMonthlyAction(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
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
     * @memberof IbzProMonthlyActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && _context.ibzpromonthlyaction) {
            return this.http.get(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/select`);
        }
        return this.http.get(`/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzpromonthlyactions`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && _context.ibzpromonthlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/${_context.ibzpromonthlyaction}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzpromonthlyactions/${_context.ibzpromonthlyaction}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && _context.ibzpromonthlyaction) {
            return this.http.delete(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/${_context.ibzpromonthlyaction}`);
        }
        return this.http.delete(`/ibzpromonthlyactions/${_context.ibzpromonthlyaction}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && _context.ibzpromonthlyaction) {
            const res = await this.http.get(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/${_context.ibzpromonthlyaction}`);
            return res;
        }
        const res = await this.http.get(`/ibzpromonthlyactions/${_context.ibzpromonthlyaction}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzpromonthlyactions/getdraft`, _data);
        return res;
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && _context.ibzpromonthlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/createhis`, _data);
        }
        return this.http.post(`/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/createhis`, _data);
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && _context.ibzpromonthlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/managepmsee`, _data);
        }
        return this.http.post(`/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/managepmsee`, _data);
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && _context.ibzpromonthlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/sendmarkdone`, _data);
        }
        return this.http.post(`/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/sendmarkdone`, _data);
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && _context.ibzpromonthlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/sendtodo`, _data);
        }
        return this.http.post(`/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/sendtodo`, _data);
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && _context.ibzpromonthlyaction) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/sendtoread`, _data);
        }
        return this.http.post(`/ibzpromonthlyactions/${_context.ibzpromonthlyaction}/sendtoread`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && true) {
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/fetchdefault`, _data);
        }
        return this.http.post(`/ibzpromonthlyactions/fetchdefault`, _data);
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzProMonthlyActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzmonthly && true) {
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/fetchtype`, _data);
        }
        return this.http.post(`/ibzpromonthlyactions/fetchtype`, _data);
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProMonthlyActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.ibzmonthly && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/createhisbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzpromonthlyactions/createhisbatch`,_data);
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProMonthlyActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.ibzmonthly && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/managepmseebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzpromonthlyactions/managepmseebatch`,_data);
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProMonthlyActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.ibzmonthly && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/sendmarkdonebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzpromonthlyactions/sendmarkdonebatch`,_data);
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProMonthlyActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.ibzmonthly && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/sendtodobatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzpromonthlyactions/sendtodobatch`,_data);
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IbzProMonthlyActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.ibzmonthly && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/ibzpromonthlyactions/sendtoreadbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzpromonthlyactions/sendtoreadbatch`,_data);
    }
}
