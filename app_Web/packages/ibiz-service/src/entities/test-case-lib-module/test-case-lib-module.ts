import { TestCaseLibModuleBase } from './test-case-lib-module-base';

/**
 * 用例库模块
 *
 * @export
 * @class TestCaseLibModule
 * @extends {TestCaseLibModuleBase}
 * @implements {ITestCaseLibModule}
 */
export class TestCaseLibModule extends TestCaseLibModuleBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestCaseLibModule
     */
    clone(): TestCaseLibModule {
        return new TestCaseLibModule(this);
    }
}
export default TestCaseLibModule;
