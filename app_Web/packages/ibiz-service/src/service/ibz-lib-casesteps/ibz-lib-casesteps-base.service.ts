import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzLibCasesteps, IbzLibCasesteps } from '../../entities';
import keys from '../../entities/ibz-lib-casesteps/ibz-lib-casesteps-keys';

/**
 * 用例库用例步骤服务对象基类
 *
 * @export
 * @class IbzLibCasestepsBaseService
 * @extends {EntityBaseService}
 */
export class IbzLibCasestepsBaseService extends EntityBaseService<IIbzLibCasesteps> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzLibCasesteps';
    protected APPDENAMEPLURAL = 'IbzLibCasesteps';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzLibCasesteps.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'expect';
    protected quickSearchFields = ['expect',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzLibCasesteps): IbzLibCasesteps {
        return new IbzLibCasesteps(data);
    }

    async addLocal(context: IContext, entity: IIbzLibCasesteps): Promise<IIbzLibCasesteps | null> {
        return this.cache.add(context, new IbzLibCasesteps(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzLibCasesteps): Promise<IIbzLibCasesteps | null> {
        return super.createLocal(context, new IbzLibCasesteps(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzLibCasesteps> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzLibCasesteps): Promise<IIbzLibCasesteps> {
        return super.updateLocal(context, new IbzLibCasesteps(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzLibCasesteps = {}): Promise<IIbzLibCasesteps> {
        return new IbzLibCasesteps(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibCasestepsService
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
