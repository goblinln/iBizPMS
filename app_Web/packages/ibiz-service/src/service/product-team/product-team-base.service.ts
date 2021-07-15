import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductTeam, ProductTeam } from '../../entities';
import keys from '../../entities/product-team/product-team-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品团队服务对象基类
 *
 * @export
 * @class ProductTeamBaseService
 * @extends {EntityBaseService}
 */
export class ProductTeamBaseService extends EntityBaseService<IProductTeam> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductTeam';
    protected APPDENAMEPLURAL = 'ProductTeams';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/ProductTeam.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
        product: 'root',
    };

    newEntity(data: IProductTeam): ProductTeam {
        return new ProductTeam(data);
    }

    async addLocal(context: IContext, entity: IProductTeam): Promise<IProductTeam | null> {
        return this.cache.add(context, new ProductTeam(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductTeam): Promise<IProductTeam | null> {
        return super.createLocal(context, new ProductTeam(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductTeam> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductTeam): Promise<IProductTeam> {
        return super.updateLocal(context, new ProductTeam(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductTeam = {}): Promise<IProductTeam> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.root = data.id;
            }
        }
        return new ProductTeam(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductTeamService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getDefaultCond() {
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','product']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getProductTeamInfoCond() {
        return this.condCache.get('productTeamInfo');
    }

    protected getProjectAppCond() {
        if (!this.condCache.has('projectApp')) {
            const strCond: any[] = ['AND', ['EQ', 'ROOT',{ type: 'WEBCONTEXT', value: 'product'}], ['EQ', 'TYPE','product']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectApp', cond);
            }
        }
        return this.condCache.get('projectApp');
    }

    protected getRowEditDefaultProductTeamCond() {
        return this.condCache.get('rowEditDefaultProductTeam');
    }

    protected getSpecifyTeamCond() {
        return this.condCache.get('specifyTeam');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductTeamService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${_context.product}/productteams`, _data);
            return res;
        }
    this.log.warn([`[ProductTeam]>>>[Create函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductTeamService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productteam) {
            const res = await this.http.get(`/products/${_context.product}/productteams/${_context.productteam}`);
            return res;
        }
    this.log.warn([`[ProductTeam]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductTeamService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productteams/getdraft`, _data);
            return res;
        }
    this.log.warn([`[ProductTeam]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductTeamService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productteam) {
            const res = await this.http.delete(`/products/${_context.product}/productteams/${_context.productteam}`);
            return res;
        }
    this.log.warn([`[ProductTeam]>>>[Remove函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductTeamService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productteam) {
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.put(`/products/${_context.product}/productteams/${_context.productteam}`, _data);
            return res;
        }
    this.log.warn([`[ProductTeam]>>>[Update函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchProductTeamInfo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductTeamService
     */
    async FetchProductTeamInfo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            const res = await this.http.post(`/products/${_context.product}/productteams/fetchproductteaminfo`, _data);
            return res;
        }
    this.log.warn([`[ProductTeam]>>>[FetchProductTeamInfo函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchSpecifyTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductTeamService
     */
    async FetchSpecifyTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            const res = await this.http.post(`/products/${_context.product}/productteams/fetchspecifyteam`, _data);
            return res;
        }
    this.log.warn([`[ProductTeam]>>>[FetchSpecifyTeam函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
}
