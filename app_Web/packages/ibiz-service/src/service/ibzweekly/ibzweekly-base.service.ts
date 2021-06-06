import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZWEEKLY, IBZWEEKLY } from '../../entities';
import keys from '../../entities/ibzweekly/ibzweekly-keys';

/**
 * 周报服务对象基类
 *
 * @export
 * @class IBZWEEKLYBaseService
 * @extends {EntityBaseService}
 */
export class IBZWEEKLYBaseService extends EntityBaseService<IIBZWEEKLY> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZWEEKLY';
    protected APPDENAMEPLURAL = 'IBZWEEKLies';
    protected APPDEKEY = 'ibzweeklyid';
    protected APPDETEXT = 'ibzweeklyname';
    protected quickSearchFields = ['ibzweeklyname',];
    protected selectContextParam = {
    };

    newEntity(data: IIBZWEEKLY): IBZWEEKLY {
        return new IBZWEEKLY(data);
    }

    async addLocal(context: IContext, entity: IIBZWEEKLY): Promise<IIBZWEEKLY | null> {
        return this.cache.add(context, new IBZWEEKLY(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZWEEKLY): Promise<IIBZWEEKLY | null> {
        return super.createLocal(context, new IBZWEEKLY(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZWEEKLY> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZWEEKLY): Promise<IIBZWEEKLY> {
        return super.updateLocal(context, new IBZWEEKLY(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZWEEKLY = {}): Promise<IIBZWEEKLY> {
        return new IBZWEEKLY(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
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
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzweeklies/getdraft`, _data);
        return res;
    }
    /**
     * Submit
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Submit(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/submit`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzweeklies/${_context.ibzweekly}`, _data);
    }
    /**
     * Read
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Read(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/read`, _data);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzweeklies`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzweeklies/${_context.ibzweekly}`);
        return res;
    }
    /**
     * Notice
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async Notice(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/notice`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/fetchdefault`, _data);
    }
    /**
     * AutoCreate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZWEEKLYService
     */
    async AutoCreate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzweeklies/${_context.ibzweekly}/autocreate`, _data);
    }

    /**
     * SubmitBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZWEEKLYServiceBase
     */
    public async SubmitBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzweeklies/submitbatch`,_data);
    }

    /**
     * ReadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZWEEKLYServiceBase
     */
    public async ReadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzweeklies/readbatch`,_data);
    }

    /**
     * NoticeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZWEEKLYServiceBase
     */
    public async NoticeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzweeklies/noticebatch`,_data);
    }

    /**
     * AutoCreateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof IBZWEEKLYServiceBase
     */
    public async AutoCreateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/ibzweeklies/autocreatebatch`,_data);
    }
}
