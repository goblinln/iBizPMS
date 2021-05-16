import { HttpResponse } from 'ibiz-core';
import { IbzMonthlyBaseService } from './ibz-monthly-base.service';

/**
 * 月报服务
 *
 * @export
 * @class IbzMonthlyService
 * @extends {IbzMonthlyBaseService}
 */
export class IbzMonthlyService extends IbzMonthlyBaseService {
    /**
     * Creates an instance of IbzMonthlyService.
     * @memberof IbzMonthlyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzMonthlyService')) {
            return ___ibz___.sc.get('IbzMonthlyService');
        }
        ___ibz___.sc.set('IbzMonthlyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzMonthlyService}
     * @memberof IbzMonthlyService
     */
    static getInstance(): IbzMonthlyService {
        if (!___ibz___.sc.has('IbzMonthlyService')) {
            new IbzMonthlyService();
        }
        return ___ibz___.sc.get('IbzMonthlyService');
    }

    /**
     * CreateGetInfo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMonthlyService
     */
    async CreateGetInfo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data.ibzmonthly = 0;
        _data.ibzmonthlyid = 0;
        _context.ibzmonthly = 0;
        return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/creategetinfo`, _data);
    }
}
export default IbzMonthlyService;
