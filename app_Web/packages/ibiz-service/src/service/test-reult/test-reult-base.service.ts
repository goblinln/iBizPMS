import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestReult, TestReult } from '../../entities';
import keys from '../../entities/test-reult/test-reult-keys';

/**
 * 测试结果服务对象基类
 *
 * @export
 * @class TestReultBaseService
 * @extends {EntityBaseService}
 */
export class TestReultBaseService extends EntityBaseService<ITestReult> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestReult';
    protected APPDENAMEPLURAL = 'TestReults';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/TestReult.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        testcase: 'ibizcase',
    };

    constructor(opts?: any) {
        super(opts, 'TestReult');
    }

    newEntity(data: ITestReult): TestReult {
        return new TestReult(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestReult> {
        const entity = await super.getLocal(context, srfKey);
        if (entity && entity.ibizcase && entity.ibizcase !== '') {
            const s = await ___ibz___.gs.getTestCaseService();
            const data = await s.getLocal2(context, entity.ibizcase);
            if (data) {
                entity.product = data.product;
                entity.ibizcase = data.id;
                entity.testcase = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestReult): Promise<ITestReult> {
        return super.updateLocal(context, new TestReult(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestReult = {}): Promise<ITestReult> {
        if (_context.testcase && _context.testcase !== '') {
            const s = await ___ibz___.gs.getTestCaseService();
            const data = await s.getLocal2(_context, _context.testcase);
            if (data) {
                entity.product = data.product;
                entity.ibizcase = data.id;
                entity.testcase = data;
            }
        }
        return new TestReult(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReultService
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
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestReultService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && _context.testcase && _context.testreult) {
            const res = await this.http.get(`/tests/${_context.test}/testcases/${_context.testcase}/testreults/${_context.testreult}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
    this.log.warn([`[TestReult]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
