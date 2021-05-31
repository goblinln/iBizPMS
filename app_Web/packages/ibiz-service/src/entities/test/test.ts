import { TestBase } from './test-base';

/**
 * 产品
 *
 * @export
 * @class Test
 * @extends {TestBase}
 * @implements {ITest}
 */
export class Test extends TestBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Test
     */
    clone(): Test {
        return new Test(this);
    }
}
export default Test;
