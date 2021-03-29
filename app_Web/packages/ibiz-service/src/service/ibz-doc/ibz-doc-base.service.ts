import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBzDoc, IBzDoc } from '../../entities';
import keys from '../../entities/ibz-doc/ibz-doc-keys';

/**
 * 文档服务对象基类
 *
 * @export
 * @class IBzDocBaseService
 * @extends {EntityBaseService}
 */
export class IBzDocBaseService extends EntityBaseService<IIBzDoc> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBzDoc';
    protected APPDENAMEPLURAL = 'IBzDocs';
    protected APPDEKEY = 'ibzdocid';
    protected APPDETEXT = 'ibzdocname';
    protected quickSearchFields = ['ibzdocname',];
    protected selectContextParam = {
    };

    newEntity(data: IIBzDoc): IBzDoc {
        return new IBzDoc(data);
    }

    async addLocal(context: IContext, entity: IIBzDoc): Promise<IIBzDoc | null> {
        return this.cache.add(context, new IBzDoc(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBzDoc): Promise<IIBzDoc | null> {
        return super.createLocal(context, new IBzDoc(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBzDoc> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBzDoc): Promise<IIBzDoc> {
        return super.updateLocal(context, new IBzDoc(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBzDoc = {}): Promise<IIBzDoc> {
        return new IBzDoc(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBzDocService
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
     * @memberof IBzDocService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzdocs/${_context.ibzdoc}/select`);
    }
}
