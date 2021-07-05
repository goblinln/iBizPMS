import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IPSSystemDBCfg, PSSystemDBCfg } from '../../entities';
import keys from '../../entities/pssystem-dbcfg/pssystem-dbcfg-keys';

/**
 * 系统数据库服务对象基类
 *
 * @export
 * @class PSSystemDBCfgBaseService
 * @extends {EntityBaseService}
 */
export class PSSystemDBCfgBaseService extends EntityBaseService<IPSSystemDBCfg> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'PSSystemDBCfg';
    protected APPDENAMEPLURAL = 'PSSystemDBCfgs';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/PSSystemDBCfg.json';
    protected APPDEKEY = 'pssystemdbcfgid';
    protected APPDETEXT = 'pssystemdbcfgname';
    protected quickSearchFields = ['pssystemdbcfgname',];
    protected selectContextParam = {
    };

    newEntity(data: IPSSystemDBCfg): PSSystemDBCfg {
        return new PSSystemDBCfg(data);
    }

    async addLocal(context: IContext, entity: IPSSystemDBCfg): Promise<IPSSystemDBCfg | null> {
        return this.cache.add(context, new PSSystemDBCfg(entity) as any);
    }

    async createLocal(context: IContext, entity: IPSSystemDBCfg): Promise<IPSSystemDBCfg | null> {
        return super.createLocal(context, new PSSystemDBCfg(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IPSSystemDBCfg> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IPSSystemDBCfg): Promise<IPSSystemDBCfg> {
        return super.updateLocal(context, new PSSystemDBCfg(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IPSSystemDBCfg = {}): Promise<IPSSystemDBCfg> {
        return new PSSystemDBCfg(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSystemDBCfgService
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
     * @memberof PSSystemDBCfgService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/pssystemdbcfgs`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSystemDBCfgService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/pssystemdbcfgs/${_context.pssystemdbcfg}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSystemDBCfgService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/pssystemdbcfgs/getdraft`, _data);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSystemDBCfgService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/pssystemdbcfgs/${_context.pssystemdbcfg}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSystemDBCfgService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/pssystemdbcfgs/${_context.pssystemdbcfg}`, _data);
    }
    /**
     * FetchBuild
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSystemDBCfgService
     */
    async FetchBuild(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/pssystemdbcfgs/fetchbuild`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSystemDBCfgService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/pssystemdbcfgs/fetchdefault`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PSSystemDBCfgService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/pssystemdbcfgs/${_context.pssystemdbcfg}/select`);
    }
}
