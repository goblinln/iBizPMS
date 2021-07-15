import { TestCaseBaseService } from './test-case-base.service';
import { HttpResponse } from 'ibiz-core';

/**
 * 测试用例服务
 *
 * @export
 * @class TestCaseService
 * @extends {TestCaseBaseService}
 */
export class TestCaseService extends TestCaseBaseService {
    /**
     * Creates an instance of TestCaseService.
     * @memberof TestCaseService
     */
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TestCaseService}
     * @memberof TestCaseService
     */
	static getInstance(context?: any): TestCaseService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TestCaseService` : `TestCaseService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TestCaseService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
    
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.test && true) {
            _data = await this.obtainMinor(_context, _data);
            //删除从实体【testcasestepnesteds】数据主键
            let testcasestepnestedsData:any = _data.testcasestepnesteds;
            if (testcasestepnestedsData && testcasestepnestedsData.length > 0) {
                testcasestepnestedsData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                    if(item.ibizcase) {
                        item.ibizcase = null;
                        if(item.hasOwnProperty('ibizcase') && item.ibizcase) delete item.ibizcase;
                    }
                })
                _data.testcasestepnesteds = testcasestepnestedsData;
            }
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tests/${_context.test}/testcases`, _data);
        }
        this.log.warn([`[TestCase]>>>[Create函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestCaseService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.test && _context.testcase) {
            _data = await this.obtainMinor(_context, _data);
            //删除从实体【testcasestepnesteds】数据主键
            let testcasestepnestedsData:any = _data.testcasestepnesteds;
            if (testcasestepnestedsData && testcasestepnestedsData.length > 0) {
                testcasestepnestedsData.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                    if(item.ibizcase) {
                        item.ibizcase = null;
                        if(item.hasOwnProperty('ibizcase') && item.ibizcase) delete item.ibizcase;
                    }
                })
                _data.testcasestepnesteds = testcasestepnestedsData;
            }
            return this.http.put(`/tests/${_context.test}/testcases/${_context.testcase}`, _data);
        }
        this.log.warn([`[TestCase]>>>[Update函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
}
export default TestCaseService;
