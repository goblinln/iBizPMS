import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestCaseLibCaseStepNested, TestCaseLibCaseStepNested } from '../../entities';
import keys from '../../entities/test-case-lib-case-step-nested/test-case-lib-case-step-nested-keys';
import { SearchFilter } from 'ibiz-core';

/**
 * 用例库用例步骤服务对象基类
 *
 * @export
 * @class TestCaseLibCaseStepNestedBaseService
 * @extends {EntityBaseService}
 */
export class TestCaseLibCaseStepNestedBaseService extends EntityBaseService<ITestCaseLibCaseStepNested> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestCaseLibCaseStepNested';
    protected APPDENAMEPLURAL = 'TestCaseLibCaseStepNesteds';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'expect';
    protected quickSearchFields = ['expect',];
    protected selectContextParam = {
    };

    newEntity(data: ITestCaseLibCaseStepNested): TestCaseLibCaseStepNested {
        return new TestCaseLibCaseStepNested(data);
    }

    async addLocal(context: IContext, entity: ITestCaseLibCaseStepNested): Promise<ITestCaseLibCaseStepNested | null> {
        return this.cache.add(context, new TestCaseLibCaseStepNested(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestCaseLibCaseStepNested): Promise<ITestCaseLibCaseStepNested | null> {
        return super.createLocal(context, new TestCaseLibCaseStepNested(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestCaseLibCaseStepNested> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestCaseLibCaseStepNested): Promise<ITestCaseLibCaseStepNested> {
        return super.updateLocal(context, new TestCaseLibCaseStepNested(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestCaseLibCaseStepNested = {}): Promise<ITestCaseLibCaseStepNested> {
        return new TestCaseLibCaseStepNested(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseLibCaseStepNestedService
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
     * @memberof TestCaseLibCaseStepNestedService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.searchAppLocal(null, new SearchFilter(_context, _data));
    }
}
