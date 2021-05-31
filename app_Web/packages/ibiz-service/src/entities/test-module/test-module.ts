import { TestModuleBase } from './test-module-base';

/**
 * 测试模块
 *
 * @export
 * @class TestModule
 * @extends {TestModuleBase}
 * @implements {ITestModule}
 */
export class TestModule extends TestModuleBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestModule
     */
    clone(): TestModule {
        return new TestModule(this);
    }
}
export default TestModule;
