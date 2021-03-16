import { EntityBase } from 'ibiz-core';
import { IProjectTaskestimate } from '../interface';

/**
 * 项目工时统计基类
 *
 * @export
 * @abstract
 * @class ProjectTaskestimateBase
 * @extends {EntityBase}
 * @implements {IProjectTaskestimate}
 */
export abstract class ProjectTaskestimateBase extends EntityBase implements IProjectTaskestimate {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProjectTaskestimateBase
     */
    get srfdename(): string {
        return 'PROJECTTASKESTIMATE';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    // ProjectTaskestimate 实体未设置主文本属性
    /**
     * 二十五号评估工时
     */
    twentyfiveevaluationtime?: any;
    /**
     * 十七号工时
     */
    seventeenconsumed?: any;
    /**
     * 项目标识
     */
    project?: any;
    /**
     * 十五号评估工时
     */
    fifteenevaluationtime?: any;
    /**
     * 其他项目工时
     */
    otherconsumed?: any;
    /**
     * 十三号评估成本
     */
    thirteenevaluationcost?: any;
    /**
     * 三十号评估成本
     */
    thirtyevaluationcost?: any;
    /**
     * 评估成本
     */
    evaluationcost?: any;
    /**
     * 二十六号评估成本
     */
    twentysixevaluationcost?: any;
    /**
     * 二号评估工时
     */
    twoevaluationtime?: any;
    /**
     * 三十号评估工时
     */
    thirtyevaluationtime?: any;
    /**
     * 二十六号工时
     */
    twentysixconsumed?: any;
    /**
     * 十九号评估工时
     */
    nineteenevaluationtime?: any;
    /**
     * 二十一号工时
     */
    twentyoneconsumed?: any;
    /**
     * 评估工时
     */
    evaluationtime?: any;
    /**
     * 二十四号评估成本
     */
    twentyfourevaluationcost?: any;
    /**
     * 三号评估成本
     */
    threeevaluationcost?: any;
    /**
     * 三十一号评估成本
     */
    thirtyoneevaluationcost?: any;
    /**
     * 六号评估成本
     */
    sixevaluationcost?: any;
    /**
     * 十八号评估工时
     */
    eighteenevaluationtime?: any;
    /**
     * 十三号评估工时
     */
    thirteenevaluationtime?: any;
    /**
     * 二十九号评估成本
     */
    twentynineevaluationcost?: any;
    /**
     * 二十号评估工时
     */
    twentyevaluationtime?: any;
    /**
     * 二十二号评估工时
     */
    twentytwoevaluationtime?: any;
    /**
     * 一号评估工时
     */
    oneevaluationtime?: any;
    /**
     * 十一号工时
     */
    elevenconsumed?: any;
    /**
     * 十四号评估工时
     */
    fourteenevaluationtime?: any;
    /**
     * 二十二号工时
     */
    twentytwoconsumed?: any;
    /**
     * 二十一号评估成本
     */
    twentyoneevaluationcost?: any;
    /**
     * 八号工时
     */
    eightconsumed?: any;
    /**
     * 二十八号评估工时
     */
    twentyeightevaluationtime?: any;
    /**
     * 三号工时
     */
    threeconsumed?: any;
    /**
     * 二十三号评估工时
     */
    twentythreeevaluationtime?: any;
    /**
     * 十号评估工时
     */
    tenevaluationtime?: any;
    /**
     * 六号工时
     */
    sixconsumed?: any;
    /**
     * 二十五号工时
     */
    twentyfiveconsumed?: any;
    /**
     * 二十八号工时
     */
    twentyeightconsumed?: any;
    /**
     * 十三号工时
     */
    thirteenconsumed?: any;
    /**
     * 四号评估成本
     */
    fourevaluationcost?: any;
    /**
     * 二十二号评估成本
     */
    twentytwoevaluationcost?: any;
    /**
     * 月
     */
    month?: any;
    /**
     * 十二号评估工时
     */
    twelveevaluationtime?: any;
    /**
     * 十号评估成本
     */
    tenevaluationcost?: any;
    /**
     * 七号评估成本
     */
    sevenevaluationcost?: any;
    /**
     * 十五号评估成本
     */
    fifteenevaluationcost?: any;
    /**
     * 九号评估成本
     */
    nineevaluationcost?: any;
    /**
     * 三十号工时
     */
    thirtyconsumed?: any;
    /**
     * 十一号评估成本
     */
    elevenevaluationcost?: any;
    /**
     * 三号评估工时
     */
    threeevaluationtime?: any;
    /**
     * 二十九号评估工时
     */
    twentynineevaluationtime?: any;
    /**
     * 九号工时
     */
    nineconsumed?: any;
    /**
     * 一号评估成本
     */
    oneevaluationcost?: any;
    /**
     * 二十号工时
     */
    twentyconsumed?: any;
    /**
     * 五号评估成本
     */
    fiveevaluationcost?: any;
    /**
     * 二十一号评估工时
     */
    twentyoneevaluationtime?: any;
    /**
     * 四号工时
     */
    fourconsumed?: any;
    /**
     * 其他项目评估工时
     */
    otherevaluationtime?: any;
    /**
     * 二十七号工时
     */
    twentysevenconsumed?: any;
    /**
     * 二号工时
     */
    twoconsumed?: any;
    /**
     * 二十四号评估工时
     */
    twentyfourevaluationtime?: any;
    /**
     * 二十六号评估工时
     */
    twentysixevaluationtime?: any;
    /**
     * 九号评估工时
     */
    nineevaluationtime?: any;
    /**
     * 十八号评估成本
     */
    eighteenevaluationcost?: any;
    /**
     * 二十三号评估成本
     */
    twentythreeevaluationcost?: any;
    /**
     * 四号评估工时
     */
    fourevaluationtime?: any;
    /**
     * 项目名称
     */
    projectname?: any;
    /**
     * 八号评估成本
     */
    eightevaluationcost?: any;
    /**
     * 八号评估工时
     */
    eightevaluationtime?: any;
    /**
     * 二十九号工时
     */
    twentynineconsumed?: any;
    /**
     * 工时
     */
    consumed?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 十六号工时
     */
    sixteenconsumed?: any;
    /**
     * 三十一号评估工时
     */
    thirtyoneevaluationtime?: any;
    /**
     * 十六号评估成本
     */
    sixteenevaluationcost?: any;
    /**
     * 十六号评估工时
     */
    sixteenevaluationtime?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 七号工时
     */
    sevenconsumed?: any;
    /**
     * 十九号工时
     */
    nineteenconsumed?: any;
    /**
     * 年
     */
    year?: any;
    /**
     * 六号评估工时
     */
    sixevaluationtime?: any;
    /**
     * 二十五号评估成本
     */
    twentyfiveevaluationcost?: any;
    /**
     * 十七号评估工时
     */
    seventeenevaluationtime?: any;
    /**
     * 二十七号评估工时
     */
    twentysevenevaluationtime?: any;
    /**
     * 十四号工时
     */
    fourteenconsumed?: any;
    /**
     * 十八号工时
     */
    eighteenconsumed?: any;
    /**
     * 主键
     */
    id?: any;
    /**
     * 二十四号工时
     */
    twentyfourconsumed?: any;
    /**
     * 三十一号工时
     */
    thirtyoneconsumed?: any;
    /**
     * 十二号评估成本
     */
    twelveevaluationcost?: any;
    /**
     * 十七号评估成本
     */
    seventeenevaluationcost?: any;
    /**
     * 二十号评估成本
     */
    twentyevaluationcost?: any;
    /**
     * 十号工时
     */
    tenconsumed?: any;
    /**
     * 十一号评估工时
     */
    elevenevaluationtime?: any;
    /**
     * 五号评估工时
     */
    fiveevaluationtime?: any;
    /**
     * 十五号工时
     */
    fifteenconsumed?: any;
    /**
     * 二十八号评估成本
     */
    twentyeightevaluationcost?: any;
    /**
     * 十二号工时
     */
    twelveconsumed?: any;
    /**
     * 十四号评估成本
     */
    fourteenevaluationcost?: any;
    /**
     * 五号工时
     */
    fiveconsumed?: any;
    /**
     * 二十三号工时
     */
    twentythreeconsumed?: any;
    /**
     * 七号评估工时
     */
    sevenevaluationtime?: any;
    /**
     * 二十七号评估成本
     */
    twentysevenevaluationcost?: any;
    /**
     * 一号工时
     */
    oneconsumed?: any;
    /**
     * 二号评估成本
     */
    twoevaluationcost?: any;
    /**
     * 十九号评估成本
     */
    nineteenevaluationcost?: any;
    /**
     * 投入成本
     */
    inputcost?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProjectTaskestimateBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        // ProjectTaskestimate 实体未设置主文本属性
    }
}
