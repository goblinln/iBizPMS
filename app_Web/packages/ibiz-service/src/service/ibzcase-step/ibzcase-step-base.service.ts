import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIBZCaseStep, IBZCaseStep } from '../../entities';
import keys from '../../entities/ibzcase-step/ibzcase-step-keys';
import { SearchFilter } from 'ibiz-core';

/**
 * 用例步骤服务对象基类
 *
 * @export
 * @class IBZCaseStepBaseService
 * @extends {EntityBaseService}
 */
export class IBZCaseStepBaseService extends EntityBaseService<IIBZCaseStep> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IBZCaseStep';
    protected APPDENAMEPLURAL = 'IBZCaseSteps';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'expect';
    protected quickSearchFields = ['expect',];
    protected selectContextParam = {
        case: 'ibizcase',
    };

    async addLocal(context: IContext, entity: IIBZCaseStep): Promise<IIBZCaseStep | null> {
        return this.cache.add(context, new IBZCaseStep(entity) as any);
    }

    async createLocal(context: IContext, entity: IIBZCaseStep): Promise<IIBZCaseStep | null> {
        return super.createLocal(context, new IBZCaseStep(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIBZCaseStep> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.ibizcase && entity.ibizcase !== '') {
            const s = await ___ibz___.gs.getCaseService();
            const data = await s.getLocal2(context, entity.ibizcase);
            if (data) {
                entity.ibizcase = data.id;
                entity.case = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIBZCaseStep): Promise<IIBZCaseStep> {
        return super.updateLocal(context, new IBZCaseStep(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIBZCaseStep = {}): Promise<IIBZCaseStep> {
        if (_context.case && _context.case !== '') {
            const s = await ___ibz___.gs.getCaseService();
            const data = await s.getLocal2(_context, _context.case);
            if (data) {
                entity.ibizcase = data.id;
                entity.case = data;
            }
        }
        return new IBZCaseStep(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseStepService
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
     * FetchCurTest
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseStepService
     */
    async FetchCurTest(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.searchAppLocal(null, new SearchFilter(_context, _data));
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseStepService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.searchAppLocal(null, new SearchFilter(_context, _data));
    }
    /**
     * FetchDefault1
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseStepService
     */
    async FetchDefault1(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.searchAppLocal(null, new SearchFilter(_context, _data));
    }
    /**
     * FetchMob
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseStepService
     */
    async FetchMob(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.searchAppLocal(null, new SearchFilter(_context, _data));
    }
    /**
     * FetchVersion
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseStepService
     */
    async FetchVersion(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.searchAppLocal(null, new SearchFilter(_context, _data));
    }
    /**
     * FetchVersions
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IBZCaseStepService
     */
    async FetchVersions(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.searchAppLocal(null, new SearchFilter(_context, _data));
    }
}
