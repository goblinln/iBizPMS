import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IEmpLoyeeload, EmpLoyeeload } from '../../entities';
import keys from '../../entities/emp-loyeeload/emp-loyeeload-keys';

/**
 * 员工负载表服务对象基类
 *
 * @export
 * @class EmpLoyeeloadBaseService
 * @extends {EntityBaseService}
 */
export class EmpLoyeeloadBaseService extends EntityBaseService<IEmpLoyeeload> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'EmpLoyeeload';
    protected APPDENAMEPLURAL = 'EmpLoyeeloads';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/EmpLoyeeload.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: IEmpLoyeeload): EmpLoyeeload {
        return new EmpLoyeeload(data);
    }

    async addLocal(context: IContext, entity: IEmpLoyeeload): Promise<IEmpLoyeeload | null> {
        return this.cache.add(context, new EmpLoyeeload(entity) as any);
    }

    async createLocal(context: IContext, entity: IEmpLoyeeload): Promise<IEmpLoyeeload | null> {
        return super.createLocal(context, new EmpLoyeeload(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IEmpLoyeeload> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IEmpLoyeeload): Promise<IEmpLoyeeload> {
        return super.updateLocal(context, new EmpLoyeeload(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IEmpLoyeeload = {}): Promise<IEmpLoyeeload> {
        return new EmpLoyeeload(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmpLoyeeloadService
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
     * @memberof EmpLoyeeloadService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/employeeloads`, _data);
        return res;
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmpLoyeeloadService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/employeeloads/${_context.employeeload}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmpLoyeeloadService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/employeeloads/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmpLoyeeloadService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.delete(`/employeeloads/${_context.employeeload}`);
        return res;
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmpLoyeeloadService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.put(`/employeeloads/${_context.employeeload}`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmpLoyeeloadService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/employeeloads/fetchdefault`, _data);
        return res;
    }
    /**
     * FetchGETWOERKLOAD
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmpLoyeeloadService
     */
    async FetchGETWOERKLOAD(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/employeeloads/fetchgetwoerkload`, _data);
        return res;
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof EmpLoyeeloadService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/employeeloads/${_context.employeeload}/select`);
        return res;
    }
}
