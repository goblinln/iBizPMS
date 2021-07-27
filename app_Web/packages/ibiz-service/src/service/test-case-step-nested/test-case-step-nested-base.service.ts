import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestCaseStepNested, TestCaseStepNested } from '../../entities';
import keys from '../../entities/test-case-step-nested/test-case-step-nested-keys';
import { SearchFilter } from 'ibiz-core';

/**
 * 用例步骤服务对象基类
 *
 * @export
 * @class TestCaseStepNestedBaseService
 * @extends {EntityBaseService}
 */
export class TestCaseStepNestedBaseService extends EntityBaseService<ITestCaseStepNested> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestCaseStepNested';
    protected APPDENAMEPLURAL = 'TestCaseStepNesteds';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/TestCaseStepNested.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'expect';
    protected quickSearchFields = ['expect',];
    protected selectContextParam = {
        testcase: 'ibizcase',
    };

    constructor(opts?: any) {
        super(opts, 'TestCaseStepNested');
    }

    newEntity(data: ITestCaseStepNested): TestCaseStepNested {
        return new TestCaseStepNested(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestCaseStepNested> {
        const entity = await super.getLocal(context, srfKey);
        if (entity && entity.ibizcase && entity.ibizcase !== '') {
            const s = await ___ibz___.gs.getTestCaseService();
            const data = await s.getLocal2(context, entity.ibizcase);
            if (data) {
                entity.ibizcase = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestCaseStepNested): Promise<ITestCaseStepNested> {
        return super.updateLocal(context, new TestCaseStepNested(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestCaseStepNested = {}): Promise<ITestCaseStepNested> {
        if (_context.testcase && _context.testcase !== '') {
            const s = await ___ibz___.gs.getTestCaseService();
            const data = await s.getLocal2(_context, _context.testcase);
            if (data) {
                entity.ibizcase = data.id;
            }
        }
        return new TestCaseStepNested(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseStepNestedService
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
     * @memberof TestCaseStepNestedService
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
     * @memberof TestCaseStepNestedService
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
     * @memberof TestCaseStepNestedService
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
     * @memberof TestCaseStepNestedService
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
     * @memberof TestCaseStepNestedService
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
     * @memberof TestCaseStepNestedService
     */
    async FetchVersions(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.searchAppLocal(null, new SearchFilter(_context, _data));
    }
}
