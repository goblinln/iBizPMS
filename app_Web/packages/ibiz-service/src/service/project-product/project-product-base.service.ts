import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectProduct, ProjectProduct } from '../../entities';
import keys from '../../entities/project-product/project-product-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 项目产品服务对象基类
 *
 * @export
 * @class ProjectProductBaseService
 * @extends {EntityBaseService}
 */
export class ProjectProductBaseService extends EntityBaseService<IProjectProduct> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectProduct';
    protected APPDENAMEPLURAL = 'ProjectProducts';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'productname';
    protected quickSearchFields = ['productname','projectname','project','projectcode',];
    protected selectContextParam = {
    };

    newEntity(data: IProjectProduct): ProjectProduct {
        return new ProjectProduct(data);
    }

    async addLocal(context: IContext, entity: IProjectProduct): Promise<IProjectProduct | null> {
        return this.cache.add(context, new ProjectProduct(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectProduct): Promise<IProjectProduct | null> {
        return super.createLocal(context, new ProjectProduct(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectProduct> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectProduct): Promise<IProjectProduct> {
        return super.updateLocal(context, new ProjectProduct(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectProduct = {}): Promise<IProjectProduct> {
        return new ProjectProduct(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectProductService
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
}
