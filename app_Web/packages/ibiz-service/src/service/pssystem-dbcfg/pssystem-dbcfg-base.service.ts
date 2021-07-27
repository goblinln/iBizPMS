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

    constructor(opts?: any) {
        super(opts, 'PSSystemDBCfg');
    }

    newEntity(data: IPSSystemDBCfg): PSSystemDBCfg {
        return new PSSystemDBCfg(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IPSSystemDBCfg> {
        const entity = await super.getLocal(context, srfKey);
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
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/pssystemdbcfgs`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.get(`/pssystemdbcfgs/${_context.pssystemdbcfg}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/pssystemdbcfgs/getdraft`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.delete(`/pssystemdbcfgs/${_context.pssystemdbcfg}`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/pssystemdbcfgs/${_context.pssystemdbcfg}`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/pssystemdbcfgs/fetchbuild`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchBuild');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.post(`/pssystemdbcfgs/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        const res = await this.http.get(`/pssystemdbcfgs/${_context.pssystemdbcfg}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
