import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProductProject, ProductProject } from '../../entities';
import keys from '../../entities/product-project/product-project-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 项目产品服务对象基类
 *
 * @export
 * @class ProductProjectBaseService
 * @extends {EntityBaseService}
 */
export class ProductProjectBaseService extends EntityBaseService<IProductProject> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProductProject';
    protected APPDENAMEPLURAL = 'ProductProjects';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'productname';
    protected quickSearchFields = ['productname','projectname','project','projectcode',];
    protected selectContextParam = {
        product: 'product',
        project: 'project',
    };

    newEntity(data: IProductProject): ProductProject {
        return new ProductProject(data);
    }

    async addLocal(context: IContext, entity: IProductProject): Promise<IProductProject | null> {
        return this.cache.add(context, new ProductProject(entity) as any);
    }

    async createLocal(context: IContext, entity: IProductProject): Promise<IProductProject | null> {
        return super.createLocal(context, new ProductProject(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProductProject> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
                entity.product = data;
            }
        }
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
                entity.project = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProductProject): Promise<IProductProject> {
        return super.updateLocal(context, new ProductProject(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProductProject = {}): Promise<IProductProject> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
                entity.product = data;
            }
        }
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
                entity.project = data;
            }
        }
        return new ProductProject(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductProjectService
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
        return this.condCache.get('default');
    }

    protected getRelationPlanCond() {
        if (!this.condCache.has('relationPlan')) {
            const strCond: any[] = ['AND', ['NOTEQ', 'PLAN','0']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('relationPlan', cond);
            }
        }
        return this.condCache.get('relationPlan');
    }

    protected getSimpleCond() {
        return this.condCache.get('simple');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductProjectService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/productprojects/fetchdefault`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productprojects/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchProductPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProductProjectService
     */
    async FetchProductPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/productprojects/fetchproductplan`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productprojects/fetchproductplan`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
