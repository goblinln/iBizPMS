import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISuiteCase, SuiteCase } from '../../entities';
import keys from '../../entities/suite-case/suite-case-keys';

/**
 * 套件用例服务对象基类
 *
 * @export
 * @class SuiteCaseBaseService
 * @extends {EntityBaseService}
 */
export class SuiteCaseBaseService extends EntityBaseService<ISuiteCase> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SuiteCase';
    protected APPDENAMEPLURAL = 'SuiteCases';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/SuiteCase.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'ibizcase';
    protected quickSearchFields = ['ibizcase',];
    protected selectContextParam = {
    };

    newEntity(data: ISuiteCase): SuiteCase {
        return new SuiteCase(data);
    }

    async addLocal(context: IContext, entity: ISuiteCase): Promise<ISuiteCase | null> {
        return this.cache.add(context, new SuiteCase(entity) as any);
    }

    async createLocal(context: IContext, entity: ISuiteCase): Promise<ISuiteCase | null> {
        return super.createLocal(context, new SuiteCase(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISuiteCase> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISuiteCase): Promise<ISuiteCase> {
        return super.updateLocal(context, new SuiteCase(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISuiteCase = {}): Promise<ISuiteCase> {
        return new SuiteCase(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SuiteCaseService
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
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SuiteCaseService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/suitecases`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SuiteCaseService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/suitecases/${_context.suitecase}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SuiteCaseService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/suitecases/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SuiteCaseService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/suitecases/${_context.suitecase}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SuiteCaseService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/suitecases/${_context.suitecase}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SuiteCaseService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/suitecases/fetchdefault`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SuiteCaseService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/suitecases/${_context.suitecase}/select`);
    }
}
