import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISysDepartment, SysDepartment } from '../../entities';
import keys from '../../entities/sys-department/sys-department-keys';

/**
 * 部门服务对象基类
 *
 * @export
 * @class SysDepartmentBaseService
 * @extends {EntityBaseService}
 */
export class SysDepartmentBaseService extends EntityBaseService<ISysDepartment> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'SysDepartment';
    protected APPDENAMEPLURAL = 'SysDepartments';
    protected APPDEKEY = 'deptid';
    protected APPDETEXT = 'deptname';
    protected quickSearchFields = ['deptname',];
    protected selectContextParam = {
    };

    newEntity(data: ISysDepartment): SysDepartment {
        return new SysDepartment(data);
    }

    async addLocal(context: IContext, entity: ISysDepartment): Promise<ISysDepartment | null> {
        return this.cache.add(context, new SysDepartment(entity) as any);
    }

    async createLocal(context: IContext, entity: ISysDepartment): Promise<ISysDepartment | null> {
        return super.createLocal(context, new SysDepartment(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISysDepartment> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISysDepartment): Promise<ISysDepartment> {
        return super.updateLocal(context, new SysDepartment(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISysDepartment = {}): Promise<ISysDepartment> {
        return new SysDepartment(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysDepartmentService
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
     * @memberof SysDepartmentService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/sysdepartments/${_context.sysdepartment}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysDepartmentService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/sysdepartments`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysDepartmentService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/sysdepartments/${_context.sysdepartment}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysDepartmentService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/sysdepartments/${_context.sysdepartment}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysDepartmentService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/sysdepartments/${_context.sysdepartment}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysDepartmentService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/sysdepartments/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SysDepartmentService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/sysdepartments/fetchdefault`, _data);
    }
}
