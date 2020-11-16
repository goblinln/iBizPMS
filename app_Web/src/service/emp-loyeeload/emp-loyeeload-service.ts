import { Http } from '@/utils';
import { Util } from '@/utils';
import EmpLoyeeloadServiceBase from './emp-loyeeload-service-base';


/**
 * 员工负载表服务对象
 *
 * @export
 * @class EmpLoyeeloadService
 * @extends {EmpLoyeeloadServiceBase}
 */
export default class EmpLoyeeloadService extends EmpLoyeeloadServiceBase {

    /**
     * Creates an instance of  EmpLoyeeloadService.
     * 
     * @param {*} [opts={}]
     * @memberof  EmpLoyeeloadService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * UpdateTime接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof EmployEeloadServiceBase
     */
    public async UpdateTime(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        let res: any = { status: 200, data: {} };

        if(!(data && data.begin && data.end)) {
            return res;
        }
        let begin: Date = new Date(data.begin);
        let end: Date = new Date(data.end);
        let period = Math.floor((end.getTime() - begin.getTime())/(1000 * 60 * 60 *24)) + 1;
        let days: number = 0;
        let curWeek: number = begin.getDay();
        begin.setDate(begin.getDate() + period);
        for(; period > 0; period--, curWeek++) {
            curWeek = curWeek > 6 ? (curWeek - 7) : curWeek;
            if(curWeek > 0 && curWeek < 6) {
                days++;
            }
        }

        Object.assign(res.data, {

            workday: days
        });

        return res;
    }


}