/**
 * 用例步骤
 *
 * @export
 * @interface IBZCaseStep
 */
export interface IBZCaseStep {

    /**
     * 用例步骤类型
     *
     * @returns {*}
     * @memberof IBZCaseStep
     */
    type?: any;

    /**
     * 编号
     *
     * @returns {*}
     * @memberof IBZCaseStep
     */
    id?: any;

    /**
     * 步骤
     *
     * @returns {*}
     * @memberof IBZCaseStep
     */
    desc?: any;

    /**
     * 预期
     *
     * @returns {*}
     * @memberof IBZCaseStep
     */
    expect?: any;

    /**
     * 用例版本
     *
     * @returns {*}
     * @memberof IBZCaseStep
     */
    version?: any;

    /**
     * 用例
     *
     * @returns {*}
     * @memberof IBZCaseStep
     */
    ibizcase?: any;

    /**
     * 分组用例步骤的组编号
     *
     * @returns {*}
     * @memberof IBZCaseStep
     */
    parent?: any;
}