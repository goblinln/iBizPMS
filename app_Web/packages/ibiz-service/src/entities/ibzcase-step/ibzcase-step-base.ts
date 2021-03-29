import { EntityBase } from 'ibiz-core';
import { IIBZCaseStep } from '../interface';

/**
 * 用例步骤基类
 *
 * @export
 * @abstract
 * @class IBZCaseStepBase
 * @extends {EntityBase}
 * @implements {IIBZCaseStep}
 */
export abstract class IBZCaseStepBase extends EntityBase implements IIBZCaseStep {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IBZCaseStepBase
     */
    get srfdename(): string {
        return 'ZT_CASESTEP';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.expect;
    }
    set srfmajortext(val: any) {
        this.expect = val;
    }
    /**
     * 用例步骤编号
     */
    casestepid?: any;
    /**
     * 实际情况
     */
    reals?: any;
    /**
     * 测试结果
     *
     * @type {('n/a' | 'pass' | 'fail' | 'blocked')} n/a: 忽略, pass: 通过, fail: 失败, blocked: 阻塞
     */
    steps?: 'n/a' | 'pass' | 'fail' | 'blocked';
    /**
     * 用例步骤类型
     *
     * @type {('step' | 'group' | 'item')} step: 步骤, group: 分组, item: 分组步骤
     */
    type?: 'step' | 'group' | 'item';
    /**
     * 编号
     */
    id?: any;
    /**
     * 步骤
     */
    desc?: any;
    /**
     * 预期
     */
    expect?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 执行编号
     */
    runid?: any;
    /**
     * 用例版本
     */
    version?: any;
    /**
     * 用例
     */
    ibizcase?: any;
    /**
     * 分组用例步骤的组编号
     */
    parent?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IBZCaseStepBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.expect = data.expect || data.srfmajortext;
    }
}
