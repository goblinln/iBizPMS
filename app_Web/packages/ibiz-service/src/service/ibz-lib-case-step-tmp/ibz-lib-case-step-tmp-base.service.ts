import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzLibCaseStepTmp, IbzLibCaseStepTmp } from '../../entities';
import keys from '../../entities/ibz-lib-case-step-tmp/ibz-lib-case-step-tmp-keys';
import { SearchFilter } from 'ibiz-core';

/**
 * 用例库用例步骤服务对象基类
 *
 * @export
 * @class IbzLibCaseStepTmpBaseService
 * @extends {EntityBaseService}
 */
export class IbzLibCaseStepTmpBaseService extends EntityBaseService<IIbzLibCaseStepTmp> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzLibCaseStepTmp';
    protected APPDENAMEPLURAL = 'IbzLibCaseStepTmps';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'expect';
    protected quickSearchFields = ['expect',];
    protected selectContextParam = {
        ibzcase: 'ibizcase',
    };

    async addLocal(context: IContext, entity: IIbzLibCaseStepTmp): Promise<IIbzLibCaseStepTmp | null> {
        return this.cache.add(context, new IbzLibCaseStepTmp(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzLibCaseStepTmp): Promise<IIbzLibCaseStepTmp | null> {
        return super.createLocal(context, new IbzLibCaseStepTmp(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzLibCaseStepTmp> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.ibizcase && entity.ibizcase !== '') {
            const s = await ___ibz___.gs.getIbzCaseService();
            const data = await s.getLocal2(context, entity.ibizcase);
            if (data) {
                entity.ibizcase = data.id;
                entity.ibzcase = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzLibCaseStepTmp): Promise<IIbzLibCaseStepTmp> {
        return super.updateLocal(context, new IbzLibCaseStepTmp(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzLibCaseStepTmp = {}): Promise<IIbzLibCaseStepTmp> {
        if (_context.ibzcase && _context.ibzcase !== '') {
            const s = await ___ibz___.gs.getIbzCaseService();
            const data = await s.getLocal2(_context, _context.ibzcase);
            if (data) {
                entity.ibizcase = data.id;
                entity.ibzcase = data;
            }
        }
        return new IbzLibCaseStepTmp(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibCaseStepTmpService
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
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibCaseStepTmpService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.searchAppLocal(null, new SearchFilter(_context, _data));
    }
}
