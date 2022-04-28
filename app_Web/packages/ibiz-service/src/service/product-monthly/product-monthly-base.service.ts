import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductMonthly, ProductMonthly } from '../../entities';
import keys from '../../entities/product-monthly/product-monthly-keys';

/**
 * 产品月报服务对象基类
 *
 * @export
 * @class ProductMonthlyBaseService
 * @extends {EntityBaseService}
 */
export class ProductMonthlyBaseService extends EntityBaseService<IProductMonthly> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductMonthly';
    protected APPDENAMEPLURAL = 'ProductMonthlies';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/ProductMonthly.json';
    protected APPDEKEY = 'ibizproproductmonthlyid';
    protected APPDETEXT = 'ibizproproductmonthlyname';
    protected quickSearchFields = ['ibizproproductmonthlyname',];
    protected selectContextParam = {
    };

    constructor(opts?: any) {
        super(opts, 'ProductMonthly');
    }

    newEntity(data: IProductMonthly): ProductMonthly {
        return new ProductMonthly(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductMonthly> {
        const entity = await super.getLocal(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductMonthly): Promise<IProductMonthly> {
        return super.updateLocal(context, new ProductMonthly(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductMonthly = {}): Promise<IProductMonthly> {
        return new ProductMonthly(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductMonthlyService
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
     * AutoCreate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductMonthlyService
     */
    async AutoCreate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/productmonthlies/${encodeURIComponent(_context.productmonthly)}/autocreate`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductMonthlyService
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
        const res = await this.http.post(`/productmonthlies`, _data);
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
     * @memberof ProductMonthlyService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/productmonthlies/${encodeURIComponent(_context.productmonthly)}`);
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
     * @memberof ProductMonthlyService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/productmonthlies/getdraft`, _data);
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
     * @memberof ProductMonthlyService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/productmonthlies/${encodeURIComponent(_context.productmonthly)}`, _data);
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
     * @memberof ProductMonthlyService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/productmonthlies/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }

    /**
     * AutoCreateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ProductMonthlyServiceBase
     */
    public async AutoCreateBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/productmonthlies/autocreatebatch`,_data);
        return res;
    }
}
