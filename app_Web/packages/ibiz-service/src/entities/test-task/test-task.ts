import { TestTaskBase } from './test-task-base';

/**
 * 测试版本
 *
 * @export
 * @class TestTask
 * @extends {TestTaskBase}
 * @implements {ITestTask}
 */
export class TestTask extends TestTaskBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TestTask
     */
    clone(): TestTask {
        return new TestTask(this);
    }
}
export default TestTask;
