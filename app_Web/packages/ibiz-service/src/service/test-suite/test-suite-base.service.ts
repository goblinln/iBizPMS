import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestSuite, TestSuite } from '../../entities';
import keys from '../../entities/test-suite/test-suite-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 测试套件服务对象基类
 *
 * @export
 * @class TestSuiteBaseService
 * @extends {EntityBaseService}
 */
export class TestSuiteBaseService extends EntityBaseService<ITestSuite> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestSuite';
    protected APPDENAMEPLURAL = 'TestSuites';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: ITestSuite): TestSuite {
        return new TestSuite(data);
    }

    async addLocal(context: IContext, entity: ITestSuite): Promise<ITestSuite | null> {
        return this.cache.add(context, new TestSuite(entity) as any);
    }

    async createLocal(context: IContext, entity: ITestSuite): Promise<ITestSuite | null> {
        return super.createLocal(context, new TestSuite(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestSuite> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestSuite): Promise<ITestSuite> {
        return super.updateLocal(context, new TestSuite(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestSuite = {}): Promise<ITestSuite> {
        return new TestSuite(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestSuiteService
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

    protected getPublicTestSuiteCond() {
        if (!this.condCache.has('publicTestSuite')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'TYPE','public'], ['AND', ['EQ', 'TYPE','private'], ['EQ', 'ADDEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]], ['EQ', 'PRODUCT',{ type: 'WEBCONTEXT', value: 'srfparentkey'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('publicTestSuite', cond);
            }
        }
        return this.condCache.get('publicTestSuite');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
}
