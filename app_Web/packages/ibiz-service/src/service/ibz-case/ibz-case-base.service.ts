import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzCase, IbzCase } from '../../entities';
import keys from '../../entities/ibz-case/ibz-case-keys';

/**
 * 测试用例服务对象基类
 *
 * @export
 * @class IbzCaseBaseService
 * @extends {EntityBaseService}
 */
export class IbzCaseBaseService extends EntityBaseService<IIbzCase> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzCase';
    protected APPDENAMEPLURAL = 'IbzCases';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzCase.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzCase): IbzCase {
        return new IbzCase(data);
    }

    async addLocal(context: IContext, entity: IIbzCase): Promise<IIbzCase | null> {
        return this.cache.add(context, new IbzCase(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzCase): Promise<IIbzCase | null> {
        return super.createLocal(context, new IbzCase(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzCase> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzCase): Promise<IIbzCase> {
        return super.updateLocal(context, new IbzCase(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzCase = {}): Promise<IIbzCase> {
        return new IbzCase(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzCaseService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
}
