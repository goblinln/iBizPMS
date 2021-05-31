import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestCaseStep, TestCaseStep } from '../../entities';
import keys from '../../entities/test-case-step/test-case-step-keys';

/**
 * 用例步骤服务对象基类
 *
 * @export
 * @class TestCaseStepBaseService
 * @extends {EntityBaseService}
 */
export class TestCaseStepBaseService extends EntityBaseService<ITestCaseStep> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'TestCaseStep';
    protected APPDENAMEPLURAL = 'TestCaseSteps';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'expect';
    protected quickSearchFields = ['expect',];
    protected selectContextParam = {
        testcase: 'ibizcase',
    };

    newEntity(data: ITestCaseStep): TestCaseStep {
        return new TestCaseStep(data);
    }

    async addLocal(context: IContext, entity: ITestCaseStep): Promise<ITestCaseStep | null> {
        return this.cache.add(context, new TestCaseStep(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestCaseStep): Promise<ITestCaseStep | null> {
        return super.createLocal(context, new TestCaseStep(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestCaseStep> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.ibizcase && entity.ibizcase !== '') {
            const s = await ___ibz___.gs.getTestCaseService();
            const data = await s.getLocal2(context, entity.ibizcase);
            if (data) {
                entity.ibizcase = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestCaseStep): Promise<ITestCaseStep> {
        return super.updateLocal(context, new TestCaseStep(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestCaseStep = {}): Promise<ITestCaseStep> {
        if (_context.testcase && _context.testcase !== '') {
            const s = await ___ibz___.gs.getTestCaseService();
            const data = await s.getLocal2(_context, _context.testcase);
            if (data) {
                entity.ibizcase = data.id;
            }
        }
        return new TestCaseStep(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseStepService
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
     * @memberof TestCaseStepService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.test && _context.testcase && true) {
            return this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/testcasesteps/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
